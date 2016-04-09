import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
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
     * Verify that the URI is only a file name and contains no sub-directories in its path.
     * A return value equal to false indicates that there are no sub-directories,
     * while a return value of true indicates that there are sub-directories.
     */
    public boolean checkForSubDir(String uri){
        log.log(Level.INFO, "Checking for sub-directories inside URI "+uri);
        if(uri.lastIndexOf("\\")>0){
            return true;
        }
        return false;
    }

    /**
     * Attempt to fulfill a GET request by searching for the specified file on the server.
     * Accessible server files are placed in a specific location, to prevent the rest of the system being readily
     * accesible to unwanted queries. This preserves a degree of security with minimal effort.
     */
    public HTTPStatus findFile(String uri){
        log.log(Level.INFO, "Attempting to find "+ uri +" amongst available server files.");
        //Check that the URI doesn't reference sub-directories
        File dir = new File(".\\src\\ServerFiles");
        File[] dir_contents = dir.listFiles();
        boolean subDir = checkForSubDir(uri);
        //If there are no sub-directories indicated in the URI, we can search through the expected location
        if(!subDir){
            log.log(Level.INFO, "No sub-directories found within the path name. Checking if file is on the server.");
            for(int i = 0; i<dir_contents.length; i++){
                log.log(Level.INFO, "Directory contents: "+dir_contents[i].getName());
                String filename = uri.substring(1,uri.length());
                if (dir_contents[i].getName().equalsIgnoreCase(filename)){
                    return HTTPStatus.FOUND;
                }
            }
        }
        return HTTPStatus.NOT_FOUND;
    }

    /**
     * Process a given HTTP GET Request message, returning the result in an HTTP Response message.</br>
     */
    public Response process(final Request request) throws Exception {
        //Assert that this is in fact a GET request
        log.log(Level.INFO, "Attempting to process the HTTP GET Request");
        try{
            assert(this.canProcess(request.getMethodType()));
            Response response = new Response(request.getHTTPVersion());
            HTTPStatus status = findFile(request.getURI());
            response.setStatus(status);
            if (status.getCode()=="302"){
                log.log(Level.INFO, "Setting the body of the Response");
                byte[] data = Files.readAllBytes(Paths.get(".\\src\\ServerFiles\\"+request.getURI()));
                response.setBody(data);
            }
            return response;
        }
        catch(AssertionError assertionError){
            log.log(Level.SEVERE, "Attempted to process a request. Failed to assert methodType as GET.");
        }
        return null;
    }
}