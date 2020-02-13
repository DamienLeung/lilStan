package dfbz.com.controller.meeting;

import dfbz.com.controller.BaseServlet;
import dfbz.com.pojo.ConJoin;
import dfbz.com.pojo.Conference;
import dfbz.com.service.DepartmentService;
import dfbz.com.service.MeetingService;

import javax.servlet.ServletException;
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
        String pattern = req.getParameter("pattern");
        String deptId = req.getParameter("dept");
        String pageStr = req.getParameter("page");
        List<Map<String, Object>> conferences;
        int page;

        if (pageStr != null)
            page = Integer.parseInt(pageStr);
        else
            page = 1;

        if (pattern != null)
            if (!pattern.equals("")) {
                byte[] bytes = pattern.getBytes("ISO-8859-1");
                pattern = new String(bytes, "UTF-8");
            } else {
                pattern = null;
            }

        int listSize;
        if (deptId != null) {
            if (!deptId.equals("")) {
                conferences = service.getConferences(pattern, Integer.parseInt(deptId), page);
                listSize = service.getListSize(pattern, Integer.parseInt(deptId));
                req.setAttribute("deptId", deptId);
                String deptName = service.getDeptName(Integer.parseInt(deptId));
                req.setAttribute("deptName", deptName);
            } else {
                conferences = service.getConferences(pattern, null, page);
                listSize = service.getListSize(pattern, null);
                req.setAttribute("deptId", null);
                req.setAttribute("deptName", null);
            }
        } else {
            conferences = service.getConferences(pattern, null, page);
            listSize = service.getListSize(pattern, null);
            req.setAttribute("deptId", null);
            req.setAttribute("deptName", null);
        }
        int pageSize = listSize % 5 == 0 ?
                listSize / 5 :
                listSize / 5 + 1;
        int startPage = (page - 1) / 5 * 5 + 1;
        req.setAttribute("startPage", startPage);
        if ((startPage + 4) < pageSize)
            req.setAttribute("endPage", startPage + 4);
        else
            req.setAttribute("endPage", pageSize);
        req.setAttribute("pattern", pattern);
        req.setAttribute("maxPage", pageSize);
        req.getSession().setAttribute("conferences", conferences);

        List<Map<String, Object>> departments = new DepartmentService().getDepartments();
        req.getSession().setAttribute("departments", departments);
        try {
            req.getRequestDispatcher(req.getContextPath() + "/html/meeting.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void postMeeting(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer conferenceId = service.getConferenceId();
        String userId = req.getSession().getAttribute("userId").toString();
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
                    title, content, publishDate, publishTime, 0, Integer.parseInt(userId));
        } else {
            conference = new Conference(conferenceId, null, null, title,
                    content, publishDate, publishTime, 0, Integer.parseInt(userId));
        }
        service.postConference(conference);
        service.attend(new ConJoin(
                service.getConJoinId(),
                Integer.parseInt(userId),
                conference.getId(),
                conference.getStatus()));
        resp.getWriter().write("success");
    }

    public void getMeetingDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        String meetingId = req.getParameter("id");
        if (meetingId != null && userId != null) {
            Conference conference = service.getConferenceDetail(Integer.parseInt(meetingId));
            req.getSession().setAttribute("conference", conference);
            Integer conJoinId = service.getConJoinId(Integer.parseInt(userId), Integer.parseInt(meetingId));
            req.setAttribute("conJoinNum", service.getJoinNumber(Integer.parseInt(meetingId)));
            req.setAttribute("MembersNum", service.getMembers(conference.getDeptId()));
            if (conJoinId != null) {
                if (!userId.equals(conference.getPublisherId().toString()))
                    req.setAttribute("buttonVal", "取消参加");
                else if (conference.getStatus() == 0)
                    req.setAttribute("buttonVal", "开始会议");
                else if (conference.getStatus() == 1)
                    req.setAttribute("buttonVal", "结束会议");
                    else
                    req.setAttribute("buttonVal", "会议已结束");
                req.setAttribute("conJoinId", conJoinId);
            } else {
                req.setAttribute("buttonVal", "参加会议");
                req.setAttribute("conJoinId", service.getConJoinId());
            }
        }
        try {
            req.getRequestDispatcher(req.getContextPath() + "/html/meeting_detail.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void attend(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getSession().getAttribute("userId").toString();
        String conId = req.getParameter("mId");
        String status = req.getParameter("status");
        String conJoinId = req.getParameter("conJoinId");
        ConJoin conJoin = new ConJoin(Integer.parseInt(conJoinId),
                Integer.parseInt(userId),
                Integer.parseInt(conId),
                Integer.parseInt(status));
        service.attend(conJoin);
        resp.getWriter().write("success");
    }

    public void abort(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("conJoinId");
        service.abort(Integer.parseInt(id));
        resp.getWriter().write("success");
    }

    public void changeStatus(HttpServletRequest req, HttpServletResponse resp) {
        int status = Integer.parseInt(req.getParameter("status"));
        int conJoinId = Integer.parseInt(req.getParameter("conJoinId"));
        int mId = Integer.parseInt(req.getParameter("mId"));
        service.updateStatus(mId, conJoinId, status);
    }
}
