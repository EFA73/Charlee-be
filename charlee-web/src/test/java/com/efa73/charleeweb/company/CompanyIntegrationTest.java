package com.efa73.charleeweb.company;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.efa73.charleeweb.account.domain.entity.Account;
import com.efa73.charleeweb.account.domain.entity.Role;
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
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "docs.api.com")
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CompanyRepository companyRepository;

    Account account;
    Company company;

    @BeforeEach
    void setUp() {
        account = Account.createEntity("test@email.com", "testPassword", Role.COMPANY);
        company = Company.createEntity("testCompany", account);
    }

    @Test
    @Transactional
    void createCompany() throws Exception {
        CompanyCreateRequest request = new CompanyCreateRequest(
                "test@email.com",
                "testPassword",
                "testCompany"
        );

        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/company")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        MvcResult result = resultActions.andExpect(status().isCreated())
                .andDo(
                        MockMvcRestDocumentation.document("create-company", preprocessRequest(prettyPrint()),
                                requestFields(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("업체명")
                                )
                        ))
                .andReturn();
    }

    @Test
    @Transactional
    void getCompany() throws Exception {
        var saved = companyRepository.save(company);

        Long companyId = saved.getId();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/company/{company_id}", companyId)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        MvcResult result = resultActions.andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentation.document("get-company",
                                pathParameters(
                                        parameterWithName("company_id").description("회사의 고유 식별자")
                                )
                        ))
                .andReturn();
    }

    @Test
    @Transactional
    void updateCompany() throws Exception {
        var saved = companyRepository.save(company);

        CompanyUpdateRequest request = new CompanyUpdateRequest(
                "update@email.com",
                "updatePassword",
                "updateCompany"
        );

        ObjectNode rootNode = objectMapper.valueToTree(request);

        String requestBody = objectMapper.writeValueAsString(rootNode);

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/company/{company_id}", saved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        );

        MvcResult result = resultActions.andExpect(status().isOk())
                .andDo(
                        MockMvcRestDocumentation.document("update-company",
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
    void deleteCompany() {
    }
}
