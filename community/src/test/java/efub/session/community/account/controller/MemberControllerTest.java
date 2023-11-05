package efub.session.community.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import efub.session.community.account.dto.SignUpRequestDto;
import efub.session.community.account.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest {
    @Mock
    private MemberService memberService;
    @InjectMocks
    private MemberController memberController;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(memberController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    // 회원가입 성공
    @Test
    void signUpSuccess() throws Exception{
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
                .email("sj@ewhain.net")
                .password("sj0000")
                .nickname("soso")
                .university("ewha")
                .studentId("21212121")
                .build();

        when(memberService.signup(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn("회원가입 완료");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("회원 가입 완료"));
    }


    // 회원가입 실패
    @Test
    void signUpFailure() throws Exception{
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
                .email("sj@ewhain.net")
                .password("sj0000")
                .nickname("soso")
                .university("ewha")
                .studentId("21212121")
                .build();

        when(memberService.signup(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("이미 존재하는 이메일입니다."));

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RuntimeException))
                .andExpect(result -> assertEquals("이미 존재하는 이메일입니다.", result.getResolvedException().getMessage()));
    }

}