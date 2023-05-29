package efub.session.community.alarm.repository;

import efub.session.community.alarm.domain.Alarm;
import efub.session.community.account.domain.Member;

import java.util.List;

public interface AlarmRepository {
    List<Alarm> findAllByMember(Member member);
}