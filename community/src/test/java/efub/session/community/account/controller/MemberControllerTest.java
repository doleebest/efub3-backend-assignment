package efub.session.community.account.controller;

import efub.session.community.account.service.MemberService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) // JUniit5와 Mockito를 연동하기
class MemberControllerTest{
    @InjectMocks
    private MemberController userController;

    @Mock
    private MemberService userService;

}