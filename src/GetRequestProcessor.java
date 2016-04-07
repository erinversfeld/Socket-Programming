import java.io.*;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
//
import java.nio.charset.StandardCharsets;
/**
 * A GetRequestProcessor contains the logic necessary for handling HTTP GET requests.
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */

public class GetRequestProcessor extends RequestProcessor {

    private static final Logger log = Logger.getLogger("GetRequestProcessorLogging");
    /**
     * Create a new GetRequestProcessor</br>
     * Calling <code>getRequestMethod()</code> on this object returns <code>HTTPMethodType.GET</code>.
     */
    public GetRequestProcessor() {
        super(HTTPMethodType.GET);
    }

    /**
     * Attempt to fulfill a GET request by searching for the specified file on the server
     */
    public HTTPStatus findFile(String uri){
        log.log(Level.INFO, "Attempting to find "+ uri +" amongst available server files.");
        File dir = new File(".\\src\\ServerFiles");
        File[] dir_contents = dir.listFiles();
        for(int i = 0; i<dir_contents.length; i++){
            if (dir_contents[i].getName().equalsIgnoreCase(uri)){
                return HTTPStatus.FOUND;
            }
        }
        return HTTPStatus.NOT_FOUND;
    }

    /**
     * Process a given HTTP GET Request message, returning the result in an HTTP Response message.</br>
     */
    public Response process(final Request request) throws Exception {
        //Assert that this is in fact a GET request
        try{
            assert(this.canProcess(request.getMethodType()));
            Response response = new Response(request.getHTTPVersion());
            HTTPStatus status = findFile(request.getURI());
            return response;
        }
        catch(AssertionError assertionError){
            log.log(Level.SEVERE, "Attempted to process a request. Failed to assert methodType as GET.");
        }
        return null;
    }
}