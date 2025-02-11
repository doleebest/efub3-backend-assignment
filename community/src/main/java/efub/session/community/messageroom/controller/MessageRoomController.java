package efub.session.community.messageroom.controller;

import efub.session.community.messageroom.domain.MessageRoom;
import efub.session.community.messageroom.dto.MessageRoomFindRequestDto;
import efub.session.community.messageroom.dto.MessageRoomRequestDto;
import efub.session.community.messageroom.dto.MessageRoomResponseDto;
import efub.session.community.messageroom.service.MessageRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/message-rooms")
@RequiredArgsConstructor
public class MessageRoomController {
    private final MessageRoomService messageRoomService;
    //private final MessageRoomNotificationService messageRoomNotificationService;


    // 쪽지방 생성
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public MessageRoomResponseDto addMessageRoom(@RequestBody MessageRoomRequestDto requestDto){
        MessageRoom messageRoom=messageRoomService.createMessageRoom(requestDto);

        // messageRoomNotificationService.create(messageRoom.getMessageRoomId());

        return new MessageRoomResponseDto(messageRoom);
    }

    // 쪽지방 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String findMessageRoom(@RequestBody MessageRoomFindRequestDto requestDto){
        Long messageRoomId=messageRoomService.findMessageRoom(requestDto);
        if(messageRoomId==0L){
            return "[ ]";
        }
        else{
            return String.valueOf(messageRoomId);
        }
    }

    // 쪽지방 목록 조회 by memberID : MemberMessageRoomController

    // 쪽지방 삭제
    @DeleteMapping("/{messageRoomId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public String removeMessageRoom(@PathVariable Long messageRoomId){
        messageRoomService.removeMessageRoom(messageRoomId);
        return messageRoomId+"번 쪽지방이 성공적으로 삭제되었습니다.";
    }
}

