package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler5 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		String response = "<html><body><h1>Select db operation:</h1>"
				+"<form action=\"../DatabaseOps/Insert\" method=\"GET\"><input type=\"submit\" value=\"1. Insert user    \"></form>"
				+"<form action=\"../DatabaseOps/Display\" method=\"GET\"><input type=\"submit\" value=\"2. Display users\"></form>"
				+"<form action=\"../DatabaseOps/Delete\" method=\"GET\"><input type=\"submit\" value=\"3. Delete user  \"></form>"
				+"<p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
		t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
		OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
}