/**
 * A RequestProcessor is a class of object that contains the logic 
 * necessary for a processing particular type of HTTP request (GET, POST, etc).</br>
 * 
 * A concrete sub type of RequestProcessor is required for processing an actual</br>
 * request e.g. a GetRequestProcessor for an HTTP GET request.
 * 
 * @author Stephan Jamieson
 * @version 16/02/2016
 */
public abstract class RequestProcessor {
	
		private final HTTPMethodType methodType;
		
		/**
		 * Called by a concrete subtype to set the type of HTTP method that can be processed.
		 */
		protected RequestProcessor(final HTTPMethodType methodType) {
			this.methodType = methodType;
		}
		
		/**
		 * Returns true if this RequestProcessor object can handle requests using the given method type.
		 */
		public boolean canProcess(final HTTPMethodType methodType) {
			return this.methodType.equals(methodType);
		}
		
		/**
		 * Obtain the type of HTTP method that this processor can handle.
		 */
		public HTTPMethodType getMethodType() { return this.methodType; }
		
		/**
		 * Process the given request, creating and returning an HTTP Response.</br>
		 * Requires that <code>request.getMethodType().equals(this.getMethodType())</code>.
		 */
		public abstract Response process(Request request) throws Exception;
}