import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
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
            while(in.readLine()!=null){
                try{
                    String line = in.readLine();
                    if(line.startsWith("GET")){
                        System.out.println("Get method in execution");
                        String[] params = line.split(" ");
                        String uri = params[1];
                        String v = params[2];
                        return(new Request(HTTPMethodType.GET, uri, v));
                    }
                    else{
                        System.out.println(line);
                    }
                } catch (IOException e){
                    System.out.println("Readline failed");
                    System.exit(-1);
                }
            }
        } catch (Exception e){
            System.out.println("Exception thrown");
            System.exit(-1);
        }
        return null;
    }

}