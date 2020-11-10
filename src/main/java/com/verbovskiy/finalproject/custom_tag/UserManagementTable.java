package com.verbovskiy.finalproject.custom_tag;

import com.verbovskiy.finalproject.controller.AttributeKey;
import com.verbovskiy.finalproject.controller.command.Constant;
import com.verbovskiy.finalproject.controller.command.RequestParameter;
import com.verbovskiy.finalproject.model.entity.User;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
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
    private static final String CONTENT_PAGE = "property/contentPage";
    private static final String BLOCK_BUTTON_TITLE = "button.block_user";
    private static final String UNBLOCK_BUTTON_TITLE = "button.unblock_user";
    private static final String DELETE_USER_BUTTON = "button.delete";
    private static final String IS_CONFIRMED = "label.yes";
    private static final String IS_NOT_CONFIRMED = "label.no";
    private static final String IS_BLOCKED = "label.yes";
    private static final String IS_NOT_BLOCKED = "label.no";

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        Locale locale = new Locale((String) session.getAttribute(AttributeKey.LOCALE));
        ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, locale);
        List<User> userList = (List<User>) session.getAttribute(AttributeKey.USER_LIST);
        try {
            int fromIndex = (int) session.getAttribute(AttributeKey.FROM_INDEX);
            int toIndex = (int) session.getAttribute(AttributeKey.TO_INDEX);
            JspWriter out = pageContext.getOut();
            for (int i = fromIndex; i < toIndex; i++) {
                    User user = userList.get(i);
                    out.write("<tr>");
                    out.write("<td>" + user.getEmail() + "</td>");
                    out.write("<td>" + user.getName() + "</td>");
                    out.write("<td>" + user.getSurname() + "</td>");
                    String isConfirmed = user.getAccount().isConfirmed() ? bundle.getString(IS_CONFIRMED) :
                            bundle.getString(IS_NOT_CONFIRMED);
                    out.write("<td>" + isConfirmed + "</td>");
                String isBlocked = user.getAccount().isBlocked() ? bundle.getString(IS_BLOCKED) :
                        bundle.getString(IS_NOT_BLOCKED);
                    out.write("<td>" + isBlocked + "</td>");
                    out.write("<form action=\"controller\"" +
                            "method=\"post\">");
                    out.write("<td>");
                    out.write("<label class=\"custom-form\">");
                    out.write("<input type=\"hidden\" name=\"userIndex\" value=" + i + ">");
                    out.write("<input type=\"hidden\" name=\"command\" value=\"CHANGE_USER_BLOCK_STATUS\">");
                    out.write("<div class=\"text-center\">");
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
                    out.write("</div>");
                    out.write("</label>");
                    out.write("</td>");
                    out.write("</form>");

                    if (!user.getAccount().isConfirmed()) {
                        out.write("<td>");
                        out.write("<div class=\"text-center\">");
                        out.write("<form action=\"controller\" method=\"post\">");
                        out.write("<label class=\"custom-form\">");
                        out.write("<input type=\"hidden\" name=\"userIndex\" value=" + i + ">");
                        out.write("<input type=\"hidden\" name=\"command\" value=\"DELETE_NOT_CONFIRMED_USER\">");
                        out.write("<button class=\"submit-button\" type=\"submit\">");
                        out.write(bundle.getString(DELETE_USER_BUTTON));
                        out.write("</button>");
                        out.write("</label>");
                        out.write("</form>");
                        out.write("</div>");
                        out.write("</td>");

                    }
                    out.write("</tr>");
                }
            if (fromIndex >= Constant.NUMBER_OF_USER_PER_PAGE) {
                session.setAttribute(RequestParameter.HAS_PREVIOUS_PAGE, true);
            } else {
                session.setAttribute(RequestParameter.HAS_PREVIOUS_PAGE, false);
            }
            if (toIndex % Constant.NUMBER_OF_USER_PER_PAGE == 0 && toIndex != userList.size()) {
                session.setAttribute(RequestParameter.HAS_NEXT_PAGE, true);
            } else {
            session.setAttribute(RequestParameter.HAS_NEXT_PAGE, false);
        }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while writing data on jsp page", e);
        }
        return SKIP_BODY;
    }
}
