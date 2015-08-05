/*
 * Copied from http://www.rgagnon.com/javadetails/java-0063.html
 * MsgLog.write("This need to be logged");
 * will be written in the default log file. Or you can specify a file name. 
 */
package logging;
import java.io.*;
import java.text.*;
import java.util.*;
public class Secretary implements java.io.Serializable {
    private static String blanks = "";
    private static String logDir = "c:\\Inetpub\\Tomcat\\5.0\\logs\\";
    private static TimeZone tz = TimeZone.getTimeZone("PST"); // or PST, MID, etc ...
    private static DateFormat df = new SimpleDateFormat ("kk:mm:ss "); 
    private static DateFormat fileDate = new SimpleDateFormat ("yyyy_MM_dd"); 
    /*
     * METHOD NAME : write
     * ARGUMENTS : The String you wish to write to the log
     * REQUIRE: The file is in a dir where write permissions are enabled
     * FUNCTION : Write the provided String to the log file with the current time.
     * RETURN: n/a
     */
    /*
    public static void write(String s){
        Date now = new Date();
        df.setTimeZone(tz);
        fileDate.setTimeZone(tz);
        String currentTime = df.format(now); 
        String currentDate = fileDate.format(now);
        String fileName = logDir + currentDate + "_shipping.log";
        try{
            FileWriter aWriter = new FileWriter(fileName, true);
            String toWrite = currentTime + " -> " + blanks;            
            toWrite += s + System.getProperty("line.separator"); 
            System.out.print(toWrite);
            aWriter.write(toWrite);
            aWriter.flush();
            aWriter.close();
        }catch(IOException e){
            System.out.println("IOException " + e.toString() + " in Secretary.write");
        }
    }
     */
    public static void write(String s)
    {
        Date now = new Date();
        df.setTimeZone(tz);
        String currentTime = df.format(now); 
        String toWrite = currentTime + " -> " + blanks;            
        toWrite += s + System.getProperty("line.separator"); 
        System.out.print(toWrite);
    }
    /*
     * METHOD NAME : logAllRequestVars
     * ARGUMENTS : a HttpServletRequest
     * REQUIRE: An HttpServletRequest has been made previous to calling.
     * FUNCTION : Log all request variables from the provided Servlet Request.
     * RETURN: n/a
     */
    public static void logAllRequestVars(javax.servlet.http.HttpServletRequest request){
        startFxn("Secretary.logAllRequestVars(request)");
        java.util.Map paramMap = request.getParameterMap();
        java.util.Set paramSet = paramMap.keySet();
        java.util.Iterator params = paramSet.iterator();
        Date now = new Date();
        df.setTimeZone(tz);
        fileDate.setTimeZone(tz);
        String currentTime = df.format(now); 
        String currentDate = fileDate.format(now);
        String fileName = logDir + currentDate + "_shipping.log";
        try{
            FileWriter aWriter = new FileWriter(fileName, true);
            while(params.hasNext()){
                String toWrite = "";    
                String name = (String)(params.next());
                //if(!(name.startsWith("quantity") || name.startsWith("sum") || name.startsWith("cost")))
                //{
                    String value[] = request.getParameterValues(name);
                    for(int i = 0; i < value.length; i++)
                    {
                        toWrite += currentTime + " -> " + blanks + name 
                                + "[" + i + "]" + " = " + value[i]
                                + System.getProperty("line.separator"); 
                    }
                    System.out.print(toWrite);
                    aWriter.write(toWrite);
                //}
                aWriter.flush();
            }
            aWriter.close();
        }catch(IOException e){
            System.out.println("IOException " + e.toString() + " in Secretary.write");
        }
        endFxn("Secretary.logAllRequestVars(request)");
    }    
    
    public static void startFxn(String fxn)
    {
        write(fxn);
        blanks = blanks.concat("  ");
    }   
    
    public static void endFxn(String fxn)
    {
        blanks = blanks.substring(2);
        write(fxn + " END");
    }
    public static void clearBlanks()
    {
        blanks  = "";
    }
}