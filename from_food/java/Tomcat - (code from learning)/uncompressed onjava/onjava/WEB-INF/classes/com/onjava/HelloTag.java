/*
 * HelloTag.java
 *
 * Created on April 23, 2003, 11:07 AM
 */

package com.onjava;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author  jsandlin
 */
public class HelloTag extends TagSupport{
    
    /** Creates a new instance of HelloTag */
    public void HelloTag() {
    }
    //Method called when the closing hello tag is encountered
    public int doEndTag() throws JspException{
        try{
            //We use the pageContext to get a Writer.
            //We then print the text string Hello
            pageContext.getOut().print("I luv u ");
        }
        catch(Exception e){
            throw new JspTagException(e.getMessage());
        }
        //Return SKIP_BODY because this Tag doesn't support a Tag Body
        return SKIP_BODY;
    }
    public void release(){
        //Call the parent's release to release any resources used by 
        //   the parent tag.
        super.release();
    }
}
