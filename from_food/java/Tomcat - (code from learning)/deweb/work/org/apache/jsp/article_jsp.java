package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class article_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_deweb_includePage;

  public article_jsp() {
    _jspx_tagPool_deweb_includePage = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_deweb_includePage.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html;charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">\r\n");
      out.write("<title>DEWEB Demo Application");
      out.write("</title>\r\n");
      out.write("</head>\r\n\r\n");
      out.write("<body bgcolor=\"#FFFFFF\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table border=\"0\" cellpadding=\"6\" cellspacing=\"0\" width=\"650\">\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td width=\"100%\">\r\n      ");
      out.write("<p align=\"center\">");
      out.write("<img border=\"0\" src=\"images/title.gif\">");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table border=\"0\" cellpadding=\"6\" cellspacing=\"0\" width=\"650\">\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td width=\"142\" valign=\"top\" align=\"left\">\r\n    ");
      JspRuntimeLibrary.include(request, response, "sidebar.jsp", out, true);
      out.write("\r\n    ");
      out.write("</td>\r\n    ");
      out.write("<td width=\"480\" valign=\"top\" align=\"left\">\r\n     ");
      if (_jspx_meth_deweb_includePage_0(pageContext))
        return;
      out.write("\r\n    ");
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table border=\"0\" cellpadding=\"6\" cellspacing=\"0\" width=\"650\">\r\n  ");
      out.write("<tr>\r\n    ");
      out.write("<td width=\"100%\">");
      JspRuntimeLibrary.include(request, response, "footer.htm", out, true);
      out.write("</td>\r\n  ");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</body>\r\n");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_deweb_includePage_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  deweb:includePage ---- */
    com.covecomm.deweb.taglib.IncludePageTag _jspx_th_deweb_includePage_0 = (com.covecomm.deweb.taglib.IncludePageTag) _jspx_tagPool_deweb_includePage.get(com.covecomm.deweb.taglib.IncludePageTag.class);
    _jspx_th_deweb_includePage_0.setPageContext(pageContext);
    _jspx_th_deweb_includePage_0.setParent(null);
    int _jspx_eval_deweb_includePage_0 = _jspx_th_deweb_includePage_0.doStartTag();
    if (_jspx_th_deweb_includePage_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_deweb_includePage.reuse(_jspx_th_deweb_includePage_0);
    return false;
  }
}
