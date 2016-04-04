import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
//
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * A Message object represents an HTML message in abstract.</br>
 * A Message has a header comprising a start line, followed by zero or more header fields of the
 * form '&lt;name&gt;:&lt;value&gt;', followed by a carriage return and line feed.</br>
 * A Message may have a body. This is left to subclasses to implement.
 * 
 * @author Stephan Jamieson
 * @version 16/2/2016
 */
public abstract class Message {
        
    public final static String CRLF = "\r\n";
    
    private  final LinkedHashMap<String, String> headerFields;

    /**
     * Called by a sub class to instantiate variables that hold header field data.
     */
    protected Message() {
            this.headerFields = new LinkedHashMap<String, String>();
    }
        
    /**
     * Returns the first line of the message header.
     */
    public abstract String getStartLine();
    
    /**
     * Obtain a list of the names of fields in the header.</br>
     */
    public List<String> getHeaderFieldNames() { 
        return new ArrayList<String>(headerFields.keySet());
    }
    
    /**
     * Given a header field name, obtain the value.
     */
    public String getHeaderFieldValue(final String name) {
        return headerFields.get(name);
    }
    
    /**
     *  Add (or update) a header field.
     */
    public void setHeaderField(final String name, final String value) {
        assert(name!=null&&value!=null);
        headerFields.put(name, value);
    }
    
    /**
     * Obtain a String representation of the message header (for debugging purposes).
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getStartLine()+"\n");
        for(String fieldName : this.getHeaderFieldNames()) {
            builder.append(fieldName+":"+this.getHeaderFieldValue(fieldName)+"\n");
        }
        return builder.toString();
    }
}