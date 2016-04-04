import java.io.*;
//
import java.nio.charset.StandardCharsets;
/**
 * A GetRequestProcessor contains the logic necessary for handling HTTP GET requests.
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */

public class GetRequestProcessor extends RequestProcessor {

    /**
     * Create a new GetRequestProcessor</br>
     * Calling <code>getRequestMethod()</code> on this object returns <code>HTTPMethodType.GET</code>.
     */
    public GetRequestProcessor() {
        super(HTTPMethodType.GET);
    }
    
    /**
     * Process a given HTTP GET Request message, returning the result in an HTTP Response message.</br>
     */
    public Response process(final Request request) throws Exception {
        assert(this.canProcess(request.getMethodType()));
        return null;
    }
}