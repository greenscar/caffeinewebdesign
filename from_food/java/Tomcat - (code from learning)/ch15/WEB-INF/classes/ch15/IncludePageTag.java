package ch15;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import javax.servlet.*;
import java.io.Writer;
import java.util.*;
import java.io.IOException;

public class IncludePageTag extends TagSupport {
    protected String includeAttribute = "includePage";
    protected String newExtension = "zzz";
    
    public int doStartTag() throws JspException{
        ServletRequest request = pageContext.getRequest();
        String includePage = (String)request.getAttribute("includePage");
        int i = includePage.lastIndexOf(".");
        if(i>0){
            includePage = includePage.substring(0,i) + newExtension;
            try{
                pageContext.include(includePage);
            }catch(IOException e1){
                throw new JspException(e1.getMessage());
            }catch(ServletException e2){
                throw new JspException(e2.getMessage());
            }
        }
        return EVAL_BODY_INCLUDE;
    }
    public java.lang.String getIncludeAttribute(){
        return includeAttribute;
    }
    public java.lang.String getNewExtension(){
        return newExtension;
    }
    public void setIncludeAttribute(java.lang.String newIncludeAttribute){
        includeAttribute = newIncludeAttribute;
    }
    public void setNewExtension(java.lang.String newNewExtension){
        newExtension = newNewExtension;
    }
}
