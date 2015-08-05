package ch15;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.*;
import java.io.Writer;
import java.util.*;
import java.io.IOException;
import com.javaexchange.dbConnectionBroker.*;
import java.sql.*;

public class MenuTag extends TagSupport {
    protected String menuName;
    public int doStartTag() throws JspException{
        ServletRequest request = pageContext.getRequest();
        HttpLinksCacheBean linksCache = 
            (HttpLinksCacheBean)pageContext.getAttribute(
                   "linksCache", pageContext.APPLICATION_SCOPE);
        if((linksCache != null) && menuName != null)){
            HttpLinksBean menu = linksCache.getLinks(menuName);
            if(menu != null){
                pageContext.setAttrubute(menuName, menu);
                return(EVAL_BODY_INCLUDE);
            }
        }
        return(SKIP_BODY);
    }
    public java.lang.String getMenuName(){
        return menuName;
    }
    public void setMenuName(java.lang.String newMenuName){
        menuName = newMenuName;
    }
}
import javax.servlet.jsp.tagext.*;
import java.util.*;

public class MenuTEI extends TagExtraInfo{
    public VariableInfo[] getVariableInfo(TagData data){
        return new VariableInfo[]{
            new VariableInfo(
                data.getAttributeString("menuName"),
                "ch15.HttpLinksBean",
                true,
                VariableInfo.NESTED)
        };
    }
}
