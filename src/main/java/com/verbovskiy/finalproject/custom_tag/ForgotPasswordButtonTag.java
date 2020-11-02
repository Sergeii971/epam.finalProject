package com.verbovskiy.finalproject.custom_tag;

import com.verbovskiy.finalproject.controller.AttributeKey;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@SuppressWarnings("serial")
public class ForgotPasswordButtonTag extends BodyTagSupport {
    private static final String CONTENT_PAGE = "property/contentPage";

    @Override
    public int doStartTag() throws JspTagException {
        try {
            HttpSession session = pageContext.getSession();
            Locale locale = new Locale((String) session.getAttribute(AttributeKey.LOCALE));
            ResourceBundle bundle = ResourceBundle.getBundle(CONTENT_PAGE, locale);
            JspWriter out = pageContext.getOut();
            out.write("<div class=\"text-center\">");
            out.write("<input type=\"submit\" value=");
            out.write(bundle.getString("button.ok"));
            out.write(">");
            out.write("</div>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}

