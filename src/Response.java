
import sun.misc.IOUtils;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//
import java.util.List;
import java.util.logging.Level;

/**
 * 
 * Represents a Response type of HTTP message.</br>
 * A Response message has a status line comprising HTTP version, status code
 * and status description. It may contain header fields and may contain a message body.
 * 
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */
public class Response extends Message {
    
    private HTTPStatus status;
    private String httpVersion;
    private InputStream bodyInput;
    private static final Logger log = Logger.getLogger("RequestLogging");

    /**
     * Create a Response message that adheres to the given version of the HTTP.
     */
    public Response(final String httpVersion) {
        this.status = status;
        this.httpVersion = httpVersion;
    }
    
    /**
     * Set the response status.
     */
    public void setStatus(HTTPStatus status) { this.status = status; }
    
    /**
     * Obtain the response status.
     */
    public HTTPStatus getStatus() { return status; }
    
    /**
     * Obtain the HTTP version.
     */
    public String getHTTPVersion() { return httpVersion; }
    
    /**
     * Obtain the response message status line i.e. <code>this.getHTTPVersion()+" "+this.getStatus</code>.
     */
    public String getStartLine() { 
        return this.httpVersion+" "+this.status;
    }
    
    /**
     * Set the message body.</br>
     * (The effect of the method is <code>this.setBody(new ByteArrayInputStream(data))</code>.)
     * 
     */
    public void setBody(byte[] data) {
        this.setBody(new ByteArrayInputStream(data));
    }
    
    /**
     * Set the input stream from which the message body can be read.</br>
     * For use when the body of the message is a file. When the message is sent 
     * (see the <code>send()</code> method) the body may be read a chunk at a time 
     * from the given input stream and written to the output stream.
     */
    public void setBody(InputStream bodyInput) {
        this.bodyInput = bodyInput;
    }

    /**
     * Send the given Response via the given output stream.</br>
     * The method writes the start line, followed by the header
     * fields (one per line), followed by a blank line and then
     * the message body.</br>
     * NOTE That lines are terminated with a carriage return and line feed.
     * (The <code>Message</code> class defines the constant <code>CRLF</code> for this purpose.)
     */
    public static void send(final OutputStream output, final Response response) throws IOException   {
        log.log(Level.INFO, "Attempting to send an HTTP Response to the HTTP GET Request");
        try{
            output.write(response.getStartLine().getBytes());
            output.write("\r\n\r\n".getBytes());
            int line;
            while((line = response.bodyInput.read())!=-1){
                output.write(line);
            }
            output.close();
        }
        catch (IOException e){
            log.log(Level.SEVERE, "Unable to write to the OutputStream for this socket.");
        }
    }

}