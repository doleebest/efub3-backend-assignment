package efub.session.community.account.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDto {

    @NotBlank(message = "작성자는 필수값입니다.")
    private Long memberId;

    @Builder
    public MemberUpdateRequestDto(Long memberId){
        this.memberId = getMemberId();
    }

}
