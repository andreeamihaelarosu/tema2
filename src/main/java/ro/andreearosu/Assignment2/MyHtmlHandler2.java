package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler2 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
        String response;
        
        String bookGenre = t.getRequestURI().getQuery().split("=")[1];
        BookService b = new BookService();

        switch (bookGenre) {
        case "fantasy":
        	response = "<html><body><h1>These are the available books: </h1><br>";
        	for(String book: b.getAvailableGenres(BookGenre.FANTASY)){
        		response+=book+"<br>";
        	}
        	response += "<br><p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
        	break;
        case "mystery":
        	response = "<html><body><h1>These are the available books: </h1><br>";
        	for(String book: b.getAvailableGenres(BookGenre.MYSTERY)){
        		response+=book+"<br>";
        	}
        	response += "<br><p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
        	break;
        case "fiction":
        	response = "<html><body><h1>These are the available books: </h1><br>";
        	for(String book: b.getAvailableGenres(BookGenre.FICTION)){
        		response+=book+"<br>";
        	}
        	response += "<br><p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
        	break;
        default:
        	response = "";
        	break;
        }
        
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
}