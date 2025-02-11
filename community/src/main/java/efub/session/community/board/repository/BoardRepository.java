package efub.session.community.board.repository;


import efub.session.community.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Boolean existsByTitle(String title);


    Optional<Board> findByBoardIdAndAndOwner_MemberId(Long boardId, Long memberId); // boardId && Owner의 memberId를 기준으로 게시판 탐색

    Board findByBoardId(Long BoardId);
}
