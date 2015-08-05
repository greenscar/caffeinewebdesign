package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class index_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_deweb_articles_articlesBean;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection;

  public index_jsp() {
    _jspx_tagPool_deweb_articles_articlesBean = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_deweb_articles_articlesBean.release();
    _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection.release();
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
      out.write("<td width=\"480\" valign=\"top\" align=\"left\">\r\n      ");
      /* ----  deweb:articles ---- */
      com.covecomm.deweb.taglib.ArticlesTag _jspx_th_deweb_articles_0 = (com.covecomm.deweb.taglib.ArticlesTag) _jspx_tagPool_deweb_articles_articlesBean.get(com.covecomm.deweb.taglib.ArticlesTag.class);
      _jspx_th_deweb_articles_0.setPageContext(pageContext);
      _jspx_th_deweb_articles_0.setParent(null);
      _jspx_th_deweb_articles_0.setArticlesBean("current");
      int _jspx_eval_deweb_articles_0 = _jspx_th_deweb_articles_0.doStartTag();
      if (_jspx_eval_deweb_articles_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n       ");
          /* ----  deweb:iterate ---- */
          com.covecomm.deweb.taglib.IterateTag _jspx_th_deweb_iterate_0 = (com.covecomm.deweb.taglib.IterateTag) _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection.get(com.covecomm.deweb.taglib.IterateTag.class);
          _jspx_th_deweb_iterate_0.setPageContext(pageContext);
          _jspx_th_deweb_iterate_0.setParent(_jspx_th_deweb_articles_0);
          _jspx_th_deweb_iterate_0.setCollection("current");
          _jspx_th_deweb_iterate_0.setIteratedItemName("menuItem");
          _jspx_th_deweb_iterate_0.setIteratedItemClass("com.covecomm.deweb.doc.HttpLinkBean");
          int _jspx_eval_deweb_iterate_0 = _jspx_th_deweb_iterate_0.doStartTag();
          if (_jspx_eval_deweb_iterate_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
            com.covecomm.deweb.doc.HttpLinkBean menuItem = null;
            if (_jspx_eval_deweb_iterate_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
              javax.servlet.jsp.tagext.BodyContent _bc = pageContext.pushBody();
              _bc.clear();
              out = _bc;
              _jspx_th_deweb_iterate_0.setBodyContent(_bc);
              _jspx_th_deweb_iterate_0.doInitBody();
              menuItem = (com.covecomm.deweb.doc.HttpLinkBean) pageContext.findAttribute("menuItem");
            }
            do {
              out.write("\r\n     ");
              out.write("<p align=\"left\">");
              out.write("<b>");
              out.print(JspRuntimeLibrary.toString(JspRuntimeLibrary.handleGetProperty(pageContext.findAttribute("menuItem"), "formattedLink")));
              out.write("</b>");
              out.write("<br>");
              out.print(JspRuntimeLibrary.toString(JspRuntimeLibrary.handleGetProperty(pageContext.findAttribute("menuItem"), "summary")));
              out.write("\r\n     ");
              out.write("<br>");
              out.write("<font size=\"1\">");
              out.write("<i>Posted ");
              out.print(JspRuntimeLibrary.toString(JspRuntimeLibrary.handleGetProperty(pageContext.findAttribute("menuItem"), "formattedDate")));
              out.write("</i>");
              out.write("</font>\r\n       ");
              int evalDoAfterBody = _jspx_th_deweb_iterate_0.doAfterBody();
              menuItem = (com.covecomm.deweb.doc.HttpLinkBean) pageContext.findAttribute("menuItem");
              if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                break;
            } while (true);
            if (_jspx_eval_deweb_iterate_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE)
              out = pageContext.popBody();
          }
          if (_jspx_th_deweb_iterate_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
            return;
          _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection.reuse(_jspx_th_deweb_iterate_0);
          out.write("\r\n      ");
          int evalDoAfterBody = _jspx_th_deweb_articles_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_deweb_articles_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_deweb_articles_articlesBean.reuse(_jspx_th_deweb_articles_0);
      out.write("          \r\n    ");
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
}
