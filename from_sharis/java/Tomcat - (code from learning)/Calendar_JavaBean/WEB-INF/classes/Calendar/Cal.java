package Calendar;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.jsp.*;

public class Cal {
    /*
     * Create Static arrays to hold names and abbrevs of months and names of days
     */
    private static final String[] months = new DateFormatSymbols().getMonths();
    private static String days[] = new DateFormatSymbols().getWeekdays();
    private static String daysAbbrev[] = new DateFormatSymbols().getShortWeekdays();
    
    //Internal Date
    private int month;
    private int day;
    private int year;
    private String printHighLightColor = "grey";
    //Internal Calendar
    private Calendar mCalendar = null;
    //Response Output Stream
    private JspWriter mOut = null;
    private boolean dateStringOnly = false;
    
    //Bean Methods
    public void setYear(int year){
        this.year = year;
    }
    public void setMonth(int month){
        this.month = month;
    }
    public void setDay(int day){
        this.day = day;
    }
    public void setHighLightColor(String color){
        this.printHighLightColor = color;
    }
    public void setDateString(boolean flag){
        this.dateStringOnly = flag;
    }
    public void setCalDate(int month, int day, int year){
        mCalendar = new GregorianCalendar(year, month, day);
    }
    public void setOut(JspWriter out){
        mOut = out;
    }
    /* 
     * Get current date for the system and set the 
     *  instance of the calendar class to that date.
     * Calendar.month is 0 based, therefore we must increment by 1.
     */
    public void setCalCurrentDate(){
        mCalendar = new GregorianCalendar();
        setDay(mCalendar.get(Calendar.DAY_OF_MONTH));
        setMonth(mCalendar.get(Calendar.MONTH) + 1);
        setYear(mCalendar.get(Calendar.YEAR));
    }
    /*
     * Provide a convenient access to a date string within a JSP
     * Return the current date (day month year) as a string
     * This can be used in as JSP as follows:
     *   Today's Date: <jsp:getProperty name="myCal" property="CurrentDate" />
     */
    public String getCurrentDate(){
        if(mCalendar == null)
            setCalCurrentDate();
        return this.day + " " + months[this.month] + " " + this.year;
    }
    /*
     * Provides output to produce calendar output in HTML format.
     * Takes the output stream as an argument.
     */
    public void printCal(JspWriter out) throws ServletException{
        Calendar cal = mCalendar;
        String printAttr;
        //Set to first day of month.
        cal.set(Calendar.DAY_OF_MONTH, 1);
        try{
            //Print the header.
            out.println(
                "<h2> "
                + this.day //Day of month
                + "&nbsp;&nbsp;"
                + months[this.month]
                + "&nbsp;&nbsp;"
                + this.year 
                + "</h2>\n"
            );
            //Begin HTML table for the calendar.
            out.println("<table border=3>");
            //Print the days of the week.
            out.print("<tr>");
            for(int n=1; n<=7;n++)
                out.print("<td>"+daysAbbrev[n]+"</td>");
            out.println("</tr>\n");
            //Print blanks on dates that are not in this month.
            out.print("<tr>");
            //if this is the day of the month, highlight the table cell.
            if(this.day == 1)
                printAttr="<td bgcolor="
                        + '"' + printHighLightColor
                        + '"' + ">";
            else
                printAttr="<td>";
            if(cal.get(Calendar.DAY_OF_WEEK) > 1){
                for(int x = 0; x <= (cal.get(Calendar.DAY_OF_WEEK) - 1); x++){
                    if(x < (cal.get(Calendar.DAY_OF_WEEK)-1))
                        out.print("<td>"+"-"+"</td>\n");
                    else
                        out.print(printAttr + "1" + "</td>\n");
                    if(x == 6){
                        out.println("</tr>\n");
                        out.print("<tr>");
                    }
                }
            }
            else //DAY_OF_WEEK == 'Sunday'
                out.print(printAttr + "1" + "</td>\n");
            printAttr = "<td bgcolor="+'"'+ printHighLightColor +'"' +">";
            for(int n=2; n <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); n++){
                if(n == this.day) //this is the day selected. Highlight it.
                    out.print(printAttr + n + "</td>\n");
                else
                    out.print("<td>"+ n +"</td>\n");
                cal.set(Calendar.DAY_OF_MONTH, n);
                if(cal.get(Calendar.DAY_OF_WEEK) == 7){
                    out.println("</tr>");
                    out.println("<tr>");
                }
            }
            out.println("</tr>");
            out.println("</table>");
        }catch(IOException e){
            throw new ServletException("I/O Exception in Cal.printCal() ");
        }
    }
}
