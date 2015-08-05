import HTML.*;

public class SearchForm extends HTMLForm {
    
    /** Creates a new instance of SearchForm */
    public SearchForm() {
        setAction("TitleListServlet");
        setAlignment(CENTER);
        
        HTMLTextInput text_input = new HTMLTextInput();
        text_input.setSize(30);
        text_input.setName("search_string");
        addObject(text_input);
        
        HTMLSubmitButton sb = new HTMLSubmitButton();
        sb.setName("Submit");
        sb.setValue("GO");
        addObject(sb);
    }
    
}
