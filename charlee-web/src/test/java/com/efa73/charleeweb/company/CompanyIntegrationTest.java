package com.efa73.charleeweb.company;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
import com.efa73.charleeweb.account.domain.repository.AccountRepository;
import com.efa73.charleeweb.company.domain.entity.Company;
import com.efa73.charleeweb.company.domain.repository.CompanyRepository;
import com.efa73.charleeweb.company.interfaces.dto.request.CompanyCreateRequest;
import com.efa73.charleeweb.company.interfaces.dto.request.CompanyUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
@Transactional
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CompanyRepository companyRepository;

    Company savedCompany;

    @BeforeEach
    void setUp() {
        Account account = Account.createEntity("test@email.com", "testPassword", Role.COMPANY);
        this.savedCompany = Company.createEntity("testCompany", account);

        accountRepository.save(account);
        this.savedCompany = companyRepository.save(savedCompany);
    }

    @Test
    void createCompany() throws Exception {
        CompanyCreateRequest request = new CompanyCreateRequest(
                "createTest@email.com",
                "createTestPassword",
                "createTestCompany"
        );

        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        MvcResult result = resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.email").value(request.email()))
                .andExpect(jsonPath("$.data.name").value(request.name()))
                .andDo(
                        document("create-company",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("업체명")
                                )
                        ))
                .andReturn();
    }

    @Test
    void getCompany() throws Exception {
        Long companyId = savedCompany.getId();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/company/{company_id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult result = resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(savedCompany.getAccount().getEmail()))
                .andExpect(jsonPath("$.data.name").value(savedCompany.getName()))
                .andDo(
                        document("get-company",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("company_id").description("회사의 고유 식별자")
                                )
                        ))
                .andReturn();
    }

    @Test
    void updateCompany() throws Exception {
        CompanyUpdateRequest request = new CompanyUpdateRequest(
                "update@email.com",
                "updatePassword",
                "updateCompany"
        );

        ObjectNode rootNode = objectMapper.valueToTree(request);

        String requestBody = objectMapper.writeValueAsString(rootNode);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/company/{company_id}", savedCompany.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        MvcResult result = resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value("update@email.com"))
                .andExpect(jsonPath("$.data.name").value("updateCompany"))
                .andDo(
                        document("update-company",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("company_id").description("회사의 고유 식별자")
                                ),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("업체명")
                                )
                        ))
                .andReturn();

    }

    @Test
    void deleteCompany() throws Exception {
        Long companyId = savedCompany.getId();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/company/{company_id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult result = resultActions
                .andExpect(status().isNoContent())
                .andDo(
                        document("delete-company",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters(
                                        parameterWithName("company_id").description("회사의 고유 식별자")
                                )
                        ))
                .andReturn();
    }
}
