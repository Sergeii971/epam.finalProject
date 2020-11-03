package com.verbovskiy.finalproject.custom_tag;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.model.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class UserManagementTable extends BodyTagSupport {
    private static final Logger logger = LogManager.getLogger(UserManagementTable.class);
    private static final int PAGE_ENTRIES = 5;
    private static final String CONTENT_PAGE = "property/contentPage";
    private static final String BLOCK_BUTTON_TITLE = "button.block_user";
    private static final String UNBLOCK_BUTTON_TITLE = "button.unblock_user";
    private static final String CHANGE_STATUS_LABEL = "column.change_user_status";
    private static final String DELETE_USER_BUTTON = "button.delete";

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        Locale locale = new Locale((String) session.getAttribute(AttributeKey.LOCALE));
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, locale);
        List<User> userList = (List<User>) session.getAttribute(AttributeKey.USER_LIST);
        try {
            int rowNumber = userList.size();
//            int pageNumber = 5;
//            int fromIndex = pageNumber * PAGE_ENTRIES - PAGE_ENTRIES;
//            int toIndex = Math.min(pageNumber * PAGE_ENTRIES, userList.size());
            JspWriter out = pageContext.getOut();
            for (int i = 0; i < rowNumber; i++) {
                if (!userList.get(i).getAccount().isAdmin()) {
                    User user = userList.get(i);
                    out.write("<tr>");
                    out.write("<td>" + user.getEmail() + "</td>");
                    out.write("<td>" + user.getName() + "</td>");
                    out.write("<td>" + user.getSurname() + "</td>");
                    out.write("<td>" + user.getAccount().isConfirmed() + "</td>");
                    out.write("<td>" + user.getAccount().isBlocked() + "</td>");
                    out.write("<form action=\"controller\"" +
                            "method=\"post\">");
                    out.write("<td>");
                    out.write("<label class=\"custom-form\">");
                    out.write("<input type=\"hidden\" name=\"userIndex\" value=" + i + ">");
                    out.write("<input type=\"hidden\" name=\"command\" value=\"CHANGE_USER_BLOCK_STATUS\">");
                    if (!user.getAccount().isBlocked()) {
                        out.write("<input type=\"hidden\" name=\"userStatus\" value=\"true\">");
                        out.write("<button class=\"submit-button\" type=\"submit\">");
                        out.write(bundle.getString(BLOCK_BUTTON_TITLE));
                    } else {
                        out.write("<input type=\"hidden\" name=\"userStatus\" value=\"false\">");
                        out.write("<button class=\"submit-button\" type=\"submit\">");
                        out.write(bundle.getString(UNBLOCK_BUTTON_TITLE));
                    }
                    out.write("</button>");
                    out.write("</label>");
                    out.write("</td>");
                    out.write("</form>");
                    if (!user.getAccount().isConfirmed()) {
                        out.write("<td>");
                        out.write("<form action=\"controller\" method=\"post\">");
                        out.write("<label class=\"custom-form\">");
                        out.write("<input type=\"hidden\" name=\"userIndex\" value=" + i + ">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"DELETE_NOT_CONFIRMED_USER\">");
                        out.write("<button class=\"submit-button\" type=\"submit\">");
                        out.write(bundle.getString(DELETE_USER_BUTTON));
                        out.write("</button>");
                        out.write("</label>");
                        out.write("</td>");
                        out.write("</form>");
                    }
                    out.write("</tr>");
                }
            }
            out.write("<tr>");
          //  out.write("<td colspan=\"7\" align=\"center\" id=\"pagination\">");
//            if (fromIndex >= PAGE_ENTRIES) {
//                out.write("<a href=\"${pageContext.request.contextPath}/controller?command=pagination&" +
//                        "pagination_subject=clientsPageNumber&pagination_direction=previous_page\">&lt; </a>");
//            }
//            out.write("<label>" + pageNumber + "</label>");
//            if (toIndex < userList.size()) {
//                out.write("<a href=\"process_controller?command=pagination&" +
//                        "pagination_subject=clientsPageNumber&pagination_direction=next_page\"> &gt;</a>");
//            }
          //  out.write("</td>");
            out.write("</tr>");
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing data on jsp page", e);
        }

        return SKIP_BODY;
    }
}
