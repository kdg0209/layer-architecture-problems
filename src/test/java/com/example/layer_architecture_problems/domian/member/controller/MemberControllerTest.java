package com.example.layer_architecture_problems.domian.member.controller;


import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.example.layer_architecture_problems.domian.member.dto.request.MemberCreateRequest;
import com.example.layer_architecture_problems.domian.member.dto.response.MemberCreateResponse;
import com.example.layer_architecture_problems.domian.member.service.MemberCreateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static com.epages.restdocs.apispec.ResourceDocumentation.*;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@AutoConfigureRestDocs
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberCreateService memberCreateService;

    @Test
    void create() throws Exception {

        MemberCreateResponse response = new MemberCreateResponse(1L);
        MemberCreateRequest request = new MemberCreateRequest("test01", "123456", "홍길동", "test@naver.com", "010-1234-5678");
        when(memberCreateService.create(any())).thenReturn(response);

        ResultActions actions = mockMvc.perform(post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        );

        actions
                .andExpect(status().isOk())
                .andDo(document("members/create",
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("members")
                                .summary("사용자 생성 API")
                                .requestFields(
                                        fieldWithPath("memberId").type(STRING).description("사용자 아이디"),
                                        fieldWithPath("password").type(STRING).description("사용자 비밀번호"),
                                        fieldWithPath("name").type(STRING).description("사용자 이름"),
                                        fieldWithPath("email").type(STRING).description("사용자 이메일"),
                                        fieldWithPath("phone").type(STRING).description("사용자 연락처")
                                )
                                .build()
                        )));
    }
}