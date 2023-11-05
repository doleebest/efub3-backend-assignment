package efub.session.community.comment.service;

import efub.session.community.account.domain.Member;
import efub.session.community.account.repository.MemberRepository;
import efub.session.community.comment.domain.Comment;
import efub.session.community.comment.dto.CommentRequestDto;
import efub.session.community.comment.repository.CommentRepository;
import efub.session.community.post.domain.Post;
import efub.session.community.post.repository.PostRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class CommentServiceTest {
    @Mock
    private CommentRepository commentRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;
    private Member writer;
    private Post post;
    private CommentRequestDto requestDto;
    private Long postId = 1L;
    private Long commentId = 1L;
    private Long writerId = 1L;

    @BeforeEach
    void setUp() {
        writer = Member.builder()
                .memberId(writerId)
                .build();

        post = Post.builder()
                .postId(postId)
                .build();

        comment = Comment.builder()
                .content("Content")
                .writer(writer)
                .post(post)
                .build();

        requestDto = CommentRequestDto.builder()
                .content("Content")
                .writerId(writerId)
                .build();
    }

    @Test
    void findCommentSuccess() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.of(comment));

        // When
        Comment foundComment = commentService.findCommentById(commentId);

        // Then
        assertNotNull(foundComment);
        assertEquals(comment.getContent(), foundComment.getContent());
        verify(commentRepository).findById(commentId);
    }

    @Test
    void findCommentFailure() {
        // Given
        given(commentRepository.findById(commentId)).willReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> commentService.findCommentById(commentId));
    }

}