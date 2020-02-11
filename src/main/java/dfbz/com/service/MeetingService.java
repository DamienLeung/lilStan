package dfbz.com.service;

import dfbz.com.dao.MeetingDao;
import dfbz.com.dao.base.BaseDao;
import dfbz.com.pojo.ConJoin;
import dfbz.com.pojo.Conference;

import java.util.List;
import java.util.Map;

public class MeetingService {
    private MeetingDao dao = new MeetingDao();

    public List<Map<String, Object>> getConferences(String pattern, Integer deptId, int page) {
        return dao.getConferences(pattern, deptId, page);
    }

    public int getListSize(String pattern, Integer deptId) {
        return dao.getMeetingNum(pattern, deptId);
    }

    public int getConferenceId() {
        return dao.getConferenceId();
    }

    public String getDeptName(Integer deptId) {
        return dao.getDeptName(deptId);
    }

    public void postConference(Conference conference) {
        dao.add(conference);
    }

    public Conference getConferenceDetail(int meetingId) {
        return dao.rowQuery("id", meetingId, Conference.class);
    }

    public Integer getConJoinId(Integer id, Integer meetingId) {
        return dao.getConJoinId(id, meetingId);
    }

    public int getConJoinId() {
        return dao.getConJoinId();
    }

    public void attend(ConJoin conJoin) {
        new BaseDao<ConJoin>().add(conJoin);
    }

    public void abort(Integer id) {
        new BaseDao<ConJoin>().delById(id, ConJoin.class);
    }
}
