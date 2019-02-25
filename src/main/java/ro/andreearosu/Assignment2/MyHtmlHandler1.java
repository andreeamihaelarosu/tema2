package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler1 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
        String response = "";
        response = "<html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Library</title></head><body><center><h1>Select the genre you are interested in:</h1><form method=\"GET\" action=\"SelectGenre\\DisplayBooks\"><br><input type=\"radio\" name=\"gender\" value=\"fantasy\" checked>FANTASY<br><input type=\"radio\" name=\"gender\" value=\"mystery\">MYSTERY<br><input type=\"radio\" name=\"gender\" value=\"fiction\">FICTION<br><br><input type=\"submit\"></form><br><p><a href=\"http://localhost:8001/\">Home</a></p></center></body></html>";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
}