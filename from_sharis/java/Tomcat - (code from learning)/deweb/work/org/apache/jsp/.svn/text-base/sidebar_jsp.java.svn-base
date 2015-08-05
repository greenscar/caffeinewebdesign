package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class sidebar_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_deweb_menu_menuName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection;

  public sidebar_jsp() {
    _jspx_tagPool_deweb_menu_menuName = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_deweb_menu_menuName.release();
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

      out.write("\r\n");
      out.write("\r\n");
      /* ----  deweb:menu ---- */
      com.covecomm.deweb.taglib.MenuTag _jspx_th_deweb_menu_0 = (com.covecomm.deweb.taglib.MenuTag) _jspx_tagPool_deweb_menu_menuName.get(com.covecomm.deweb.taglib.MenuTag.class);
      _jspx_th_deweb_menu_0.setPageContext(pageContext);
      _jspx_th_deweb_menu_0.setParent(null);
      _jspx_th_deweb_menu_0.setMenuName("leftsidebar");
      int _jspx_eval_deweb_menu_0 = _jspx_th_deweb_menu_0.doStartTag();
      if (_jspx_eval_deweb_menu_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        com.covecomm.deweb.doc.HttpLinksBean leftsidebar = null;
        leftsidebar = (com.covecomm.deweb.doc.HttpLinksBean) pageContext.findAttribute("leftsidebar");
        do {
          out.write("\r\n   ");
          /* ----  deweb:iterate ---- */
          com.covecomm.deweb.taglib.IterateTag _jspx_th_deweb_iterate_0 = (com.covecomm.deweb.taglib.IterateTag) _jspx_tagPool_deweb_iterate_iteratedItemName_iteratedItemClass_collection.get(com.covecomm.deweb.taglib.IterateTag.class);
          _jspx_th_deweb_iterate_0.setPageContext(pageContext);
          _jspx_th_deweb_iterate_0.setParent(_jspx_th_deweb_menu_0);
          _jspx_th_deweb_iterate_0.setCollection("leftsidebar");
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
              out.write("\r\n ");
              JspRuntimeLibrary.introspecthelper(pageContext.findAttribute("menuItem"), "style",
"color: #21007b",null, null, false);
              out.write("\r\n ");
              out.print(JspRuntimeLibrary.toString(JspRuntimeLibrary.handleGetProperty(pageContext.findAttribute("menuItem"), "formattedLink")));
              out.write("<br>\r\n   ");
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
          out.write("\r\n   ");
          out.write("<p>&nbsp;");
          out.write("</p>\r\n");
          int evalDoAfterBody = _jspx_th_deweb_menu_0.doAfterBody();
          leftsidebar = (com.covecomm.deweb.doc.HttpLinksBean) pageContext.findAttribute("leftsidebar");
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_deweb_menu_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
        return;
      _jspx_tagPool_deweb_menu_menuName.reuse(_jspx_th_deweb_menu_0);
      out.write("  \r\n");
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
