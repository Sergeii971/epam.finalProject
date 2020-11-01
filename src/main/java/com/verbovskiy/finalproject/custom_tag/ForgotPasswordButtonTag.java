package com.verbovskiy.finalproject.custom_tag;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class ForgotPasswordButtonTag extends BodyTagSupport {
    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<form action=\"${pageContext.request.contextPath}/controller\" method=\"post\"" +
                    " class=\"registration-form\">");
            out.write("<input type=\"hidden\" name=\"command\" value=\"SEND_RECOVERY_KEY\">");
           // out.write("<input type=\"email\"name=\"email\" value = ${email}>");
            out.write("<input type=\"submit\" value=\"<fmt:message key=\"button.ok\"/>\" ");
            out.write("</form>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }
    @Override
    public int doAfterBody() throws JspTagException {
//        if (rows-- > 1) {
            try {
                pageContext.getOut().write("<input type=\"submit\" value=\"<fmt:message key=\"button.okkk\"/>\"");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
//        } else {
//            return SKIP_BODY;
//        }
    }
    @Override
    public int doEndTag() throws JspTagException {
        try {
            pageContext.getOut().write("<input type=\"submit\" value=\"<fmt:message key=\"button.okkk\"/>\"");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}

