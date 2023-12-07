import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class session extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Date createTime = new Date(session.getCreationTime());
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        String title = "Welcome Back To Session Tracking";
	Integer visitCount = Integer.valueOf(0);
        String visitCountKey = new String("visitCount");
        String userIDKey = new String("userID");
        String userID = new String("abc");

        if (session.isNew()) {
            title = "Welcome to Session Tracking";
            session.setAttribute(userIDKey, userID);
        } else {
            visitCount = (Integer) session.getAttribute(visitCountKey);
            visitCount = visitCount + 1;
            userID = (String) session.getAttribute(userIDKey);
        }

        session.setAttribute(visitCountKey, visitCount);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.print("<html>\n" + "<head><title>" + title + "</title></head>\n" + "<body>"
                + "<h1 align='center'>" + title + "</h1>\n" + "<h2 align='center'>Session Information</h2>\n"
                + "<table border= '1' align='center'>\n" + "<tr>\n" + "<th>Session info</th>\n"
                + "<th>value</th>\n" + "</tr>\n" + "<tr>\n" + "<td>id</td>\n" + "<td>" + session.getId() + "</td>\n"
                + "</tr>\n");

        out.print("<tr>\n" + "<td>Creation Time</td>\n" + "<td>\n" + createTime + "</td>\n" + "</tr>\n"
                + "<tr>\n" + "<td>Time of Last Access</td>\n" + "<td>\n" + lastAccessTime + "</td>\n" + "</tr>\n"
                + "<tr>\n" + "<td>UserID</td>\n" + "<td>\n" + userID + "</td>\n" + "</tr>"
                + "<tr>\n" + "<td>Number of visits</td>\n" + "<td>\n" + visitCount + "</td>\n" + "</tr>\n" +
                "</table></body></html>");
    }
}
