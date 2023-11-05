package efub.session.community.comment.controller;

import efub.session.community.comment.domain.Comment;
import efub.session.community.comment.service.CommentService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Arrays;

import static javax.swing.UIManager.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest2 {
    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;

    // 댓글 리스트 찾기 성공
    @Test
    void commentListFindSuccess() throws Exception {
        Comment comment1 = new Comment();
        Comment comment2 = new Comment();
        given(commentService.findCommentListByPost(1L)).willReturn(Arrays.asList(comment1, comment2));

        mockMvc.perform((RequestBuilder) get("/comments/posts/1"))
                .andExpect(status().isOk());
    }

    // 댓글 리스트 찾기 실패
    @Test
    void commentListFindFailure() throws Exception {
        given(commentService.findCommentListByPost(1L)).willReturn(null);

        mockMvc.perform((RequestBuilder) get("/comments/posts/1"))
                .andExpect(status().isNotFound());
    }

}