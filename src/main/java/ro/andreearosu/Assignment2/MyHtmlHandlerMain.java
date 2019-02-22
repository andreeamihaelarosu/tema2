package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandlerMain implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
		String URI = "";
		String response = "";
		if(t.getRequestURI().getQuery() == null) {
			response = "<html><body><h1>Welcome to my simple server! </h1><p>1. <a href=\"http://localhost:8001/SelectGenres\">Get available books </a> by genre.</p><p>2. <a href=\"http://localhost:8001/TableParams\">Display parameters</a> in table.</p><p>3. <a href=\"http://localhost:8001/ExportParams\">Export parameters</a> to a txt file.</p>4. <a href=\"http://localhost:8001/Login\">Login</a></p></body></html>";
		}
		else {
			URI = t.getRequestURI().getQuery().toString();
			//display params in Console
			Map<String, String> m = getQueryParameters(URI);
			response = "<html><body><h1>Welcome to my simple server! </h1><p>1. <a href=\"http://localhost:8001/SelectGenres\">Get available books </a> by genre.</p><p>2. <a href=\"http://localhost:8001/TableParams/?"+URI+"\">Display parameters</a> in table.</p><p>3. <a href=\"http://localhost:8001/ExportParams/?"+URI+"\">Export parameters</a> to a txt file.</p><p>4. <a href=\"http://localhost:8001/Login/?"+URI+"\">Login</a></p></body></html>";
		}
		t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
		OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
	
	//display param in Console
	public static Map<String, String> getQueryParameters(String queryString) {
	    
	    String[] parameters = queryString.split("&");
	    Map<String, String> queryParameters = new HashMap();

	    for (String parameter : parameters) {
	        String[] keyValuePair = parameter.split("=");
	        queryParameters.put(keyValuePair[0], keyValuePair[1]);
	        System.out.println("Key: " + keyValuePair[0] + "-> Value: "+ keyValuePair[1]);
	    }
	    return queryParameters;    
	}
}