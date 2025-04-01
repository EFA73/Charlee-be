package com.efa73.charleeweb.company.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.service.AccountService;
import com.efa73.charleeweb.common.exception.CharleeException;
import com.efa73.charleeweb.common.exception.CommonErrorCode;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;   // JPA repository 모의 객체
    @Mock
    private PasswordEncoder passwordEncoder;         // PasswordEncoder 모의 객체
    @Mock
    private AccountService accountService;           // AccountService 모의 객체

    @InjectMocks
    private CompanyService companyService;           // 테스트 대상, 모의 객체들이 주입됨

    private Long companyId = 999L;
    private String email = "test@email.com";
    private String name = "testName";
    private String rawPassword = "password";
    private String encodedPassword = "encodedPassword";

    private String updatedEmail = "test@newEmail.com";
    private String updatedName = "newName";
    private String newRawPassword = "newPassword";
    private String newEncodedPassword = "newEncodedPassword";

    private Account account;
    private Company company;
    private Account updatedAccount;
    private Company updatedCompany;

    @BeforeEach
    void setUp() {
        account = Account.createEntity(email, encodedPassword, Role.COMPANY);
        company = Company.createEntity(name, account);
        updatedAccount = Account.createEntity(updatedEmail, newEncodedPassword, Role.COMPANY);
        updatedCompany = Company.createEntity(updatedName, updatedAccount);
    }

    @Test
    @DisplayName("createCompany: 새로운 회사 생성 시 비밀번호를 인코딩하고 저장해야 한다")
    void createCompany_ShouldEncodePasswordAndSave() {
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(accountService.create(email, encodedPassword, Role.COMPANY)).thenReturn(account);
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company result = companyService.createCompany(email, rawPassword, name);

        assertNotNull(result);
        assertEquals(email, result.getAccount().getEmail());
        assertEquals(name, result.getName());
        assertEquals(encodedPassword, result.getAccount().getPassword(), "비밀번호는 인코딩되어야 합니다.");

        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    @DisplayName("createCompany: 이미 존재하는 이메일이면 예외를 던져야 한다")
    void createCompany_WhenEmailAlreadyExists_ShouldThrowException() {
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        when(accountService.create(email, encodedPassword, Role.COMPANY))
                .thenThrow(new CharleeException(CommonErrorCode.EMAIL_ALREADY_EXISTS));

        assertThrows(CharleeException.class, () ->
                        companyService.createCompany(email, rawPassword, name),
                "이미 존재하는 이메일일 경우 createCompany는 예외를 던져야 합니다."
        );

        verify(companyRepository, never()).save(any());
    }

    @Test
    @DisplayName("updateCompany: 회사 정보 업데이트 시 필드가 올바르게 변경되어 저장되어야 한다")
    void updateCompany_ShouldUpdateFieldsAndSave() {
        when(companyRepository.findById(any())).thenReturn(Optional.of(company));
        when(passwordEncoder.encode(newRawPassword)).thenReturn(newEncodedPassword);
        when(companyRepository.save(any(Company.class))).thenReturn(updatedCompany);

        Company updated = companyService.updateCompany(companyId, updatedEmail, newRawPassword, updatedName);

        assertEquals(updatedEmail, updated.getAccount().getEmail());
        assertEquals(updatedName, updated.getName());
        assertEquals(newEncodedPassword, updated.getAccount().getPassword(), "비밀번호는 업데이트되고 인코딩되어야 합니다.");

        verify(passwordEncoder).encode(newRawPassword);
        verify(companyRepository).findById(companyId);
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    @DisplayName("updateCompany: 존재하지 않는 회사의 경우 예외를 던져야 한다")
    void updateCompany_WhenNotFound_ShouldThrow() {
        when(companyRepository.findById(companyId)).thenThrow(new CharleeException((CommonErrorCode.NOT_FOUND)));

        assertThrows(CharleeException.class, () ->
                companyService.updateCompany(companyId, updatedEmail, newEncodedPassword, updatedName)
        );

        verify(passwordEncoder).encode(newEncodedPassword);
        verify(companyRepository).findById(companyId);
        verify(companyRepository, never()).save(any());
    }
}
