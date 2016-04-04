import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Simple Web Server.
 * 
 * The server opens a TCP port and listens on that port for HTTP requests.
 * The server accepts a port number as an optional parameter.</br>
 * If no parameter is given then it requests one be randomly assigned when
 * opening the TCP server socket.</br>
 * In all cases, the server prints out the port that it is using.
 * 
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */
public class WebServer {

    private WebServer() {}
    /**
     * Run the web server. The server accepts a port number as an optional parameter.</br>
     * If no parameter is given then it requests one be randomly assigned when opening the TCP server socket.</br>
     * In all cases, the server prints out the port that it is using.
     */
    public static void main(String argv[]) throws Exception {
		// Get the port number from the command line.
		int port = argv.length>0 ?(new Integer(argv[0])).intValue():0;
        // Listen to the port for an incoming request
        ServerSocket sSocket = new ServerSocket(port);
        Socket socket = sSocket.accept();
        GetRequestProcessor rp = new GetRequestProcessor();
        Response response = rp.process(Request.parse(socket.getInputStream()));
        response.send(new FileOutputStream(argv[1]), response);
     }
}
