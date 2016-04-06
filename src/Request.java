import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//
import java.util.StringTokenizer;
/**
* Represents an HTTP Request type of message.
* A Request message has a start line comprising HTTP method type, universal 
* resource identifier (URI), and HTTP version.</br>
* It may contain contain header fields, and may have a body.
* 
* 
* 
*/
public class Request extends Message {

    private HTTPMethodType method;
    private String uri;
    private String HTTP_version;
    private byte[] body;
    private static final Logger log = Logger.getLogger("RequestLogging");
    
    public Request() { super(); }
    
    /**
     * Create a Request message with a request-line composed of the given method type, URI and HTTP version.
     */
    public Request(final HTTPMethodType method, final String uri, final String HTTP_version) {
        super();
        this.method=method;
        this.uri=uri;
        this.HTTP_version=HTTP_version;
    }
        
    /**
     * Determine whether this request has a message body.
     */
    public boolean hasMessageBody() { return body!=null; }
    
    /**
     * Obtain the message body.</br>
     * Requires that <code>this.hasMessageBody()</code>.
     */
    public byte[] getBody() { return body; }
    
    /**
     * Obtain the request method type.
     */
    public HTTPMethodType getMethodType() { return this.method; }
    
    /**
     * Obtain the requested URI.
     */
    public String getURI() { return this.uri; }
    
    /**
     * Obtain the message http version.
     */
    public String getHTTPVersion() { return this.HTTP_version; }
    
    /**
     * Obtain the message request line i.e. <code>this.getMethodType()+" "+this.getURI()+" "+this.getHTTPVersion()</code>
     */
    public String getStartLine() {   
        return this.getMethodType()+" "+this.getURI()+" "+this.getHTTPVersion(); 
    }
        
    /**
     * Read an HTTP request from the given input stream and return it as a Request object.
     */
    public static Request parse(final InputStream input) throws IOException {
       try{
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = in.readLine();
            while(in.readLine()!=null){
                if(line.startsWith("GET")){
                    log.log(Level.INFO, "GET request received");
                    String[] params = line.split(" ");
                    String uri = params[1];
                    log.log(Level.INFO, ("GET URI is " +uri));
                    String v = params[2];
                    log.log(Level.INFO, ("GET version is " +v));
                    return(new Request(HTTPMethodType.GET, uri, v));
                }
                else{
                    log.log(Level.WARNING, line);
                }
                line = in.readLine();
            }
        } catch (Exception e){
           log.log(Level.SEVERE, "Exception thrown whist parsing Request");
            System.exit(-1);
        }
        return null;
    }

}