package dfbz.com.controller.meeting;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.Conference;
import dfbz.com.pojo.Department;
import dfbz.com.service.DepartmentService;
import dfbz.com.service.MeetingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {

    private MeetingService service = new MeetingService();

    public void showMeeting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Map<String, Object>> conferences = service.getConferences();
        req.getSession().setAttribute("conferences", conferences);
        List<Map<String, Object>> departments = new DepartmentService().getDepartments();
        req.getSession().setAttribute("departments", departments);
        resp.sendRedirect(req.getContextPath() + "/html/meeting.jsp");
    }

    public void postMeeting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer conferenceId = service.getConferenceId();
        String title = req.getParameter("title");
        String deptId = req.getParameter("dept");
        String content = req.getParameter("content");
        Date publishDate = new Date(System.currentTimeMillis());
        String temp = req.getParameter("time");
        temp += ":00";
        Time publishTime = Time.valueOf(temp);
        Conference conference;
        if (deptId != null) {
            String deptName = service.getDeptName(Integer.parseInt(deptId));
            conference = new Conference(conferenceId, deptName, Integer.parseInt(deptId),
                    title, content, publishDate, publishTime, 1);
        } else {
            conference = new Conference(conferenceId, null, null, title,
                    content, publishDate, publishTime, 1);
        }
        service.postConference(conference);
        resp.getWriter().write("success");
    }

    public void getMeetingDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String meetingId = req.getParameter("id");
        System.out.println(meetingId);
        Conference conference = service.getConferenceDetail(Integer.parseInt(meetingId));
        req.getSession().setAttribute("conference", conference);
        resp.sendRedirect("/html/meeting_detail.jsp");
    }
}
