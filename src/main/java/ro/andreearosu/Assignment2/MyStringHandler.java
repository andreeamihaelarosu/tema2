package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
public class MyStringHandler implements HttpHandler {
	public void handle(HttpExchange t) throws IOException {
		//set the response as a normal string
		String response = "This is the output from MyStringHandler class";
		
		//set headers and statur code
		t.sendResponseHeaders(200, response.length());
		OutputStream os = t.getResponseBody();
		
		//send response
		os.write(response.getBytes());
		
		//close request
		os.close();
	}

}