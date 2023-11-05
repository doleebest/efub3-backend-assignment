package efub.session.community.account.service;

import efub.session.community.account.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

class MemberServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void signUpSuccess() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(false);

        String response = memberService.signup("sj@ewhain.net", "sj0000", "ewha", "soso", "212121");
        assertEquals("회원 가입이 완료되었습니다.", response);
    }

    @Test
    void signUpFailure() {
        when(memberRepository.existsByEmail(anyString())).thenReturn(true);

        memberService.signup("sj@ewhain.net", "sj0000", "ewha", "soso", "212121");
    }

}