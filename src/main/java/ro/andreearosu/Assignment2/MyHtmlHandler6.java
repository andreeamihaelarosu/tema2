package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler6 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
		{
	        String response = "";
	        response = "<html><body><h2>Insert user form</h2>\r\n" + 
	        		"<form method=\"GET\" action=\"/DatabaseOps/Insert/InsertMsg\">\r\n" + 
	        		"  <br>Name:<br><input type=\"text\" name=\"name\" value=\"Andreea Rosu\">\r\n" + 
	        		"  <br><br>Email:<br><input type=\"text\" name=\"email\" value=\"andreea.rosu@hartehanks.com\">\r\n" + 
	        		"  <br><br>Phone:<br><input type=\"text\" name=\"phone\" value=\"0745324512\">\r\n" + 
	        		"  <br><br>\r\n" + 
	        		"  <input type=\"submit\" value=\"Insert\">\r\n" + 
	        		"</form><br><p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
	        t.sendResponseHeaders(200, response.length());
	        OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
			os.write(response.getBytes());
			os.close();
		}
	}
}