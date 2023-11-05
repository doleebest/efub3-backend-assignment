package efub.session.community.account.service;

import efub.session.community.account.repository.MemberRepository;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void signUpSuccess() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);

        String response = memberService.signup("sj@ewhain.com", "password1234", "ewha", "nick", "212121");
        assertEquals("회원 가입 완료", response);
    }

    @Test
    void signUpFailure() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        memberService.signup("sj@ewhain.com", "password1234", "ewha", "nick", "212121");
    }

}