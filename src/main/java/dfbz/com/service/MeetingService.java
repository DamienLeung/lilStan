package dfbz.com.service;

import dfbz.com.dao.MeetingDao;
import dfbz.com.pojo.Conference;

import java.util.List;
import java.util.Map;

public class MeetingService {
    private MeetingDao dao = new MeetingDao();

    public List<Map<String, Object>> getConferences() {
        return dao.getConferences();
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
}
