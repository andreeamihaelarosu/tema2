package ro.andreearosu.Assignment2;

import java.util.ArrayList;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler8 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
		//parse key=value params
		String response;
		DBConnection dbc = new DBConnection();
		MyHtmlHandlerMain h = new MyHtmlHandlerMain();
		try {
			ArrayList<String[]> table = dbc.selectToHtml("users");
			dbc.closeConn();
			response = "<html><body><h2>Here is the USERS database: </h2>"+ h.getHtmlTable(table)+"<br><br>" + 
    				"<form action=\"../DatabaseOps/Insert\" method=\"POST\"><input type=\"submit\" value=\"Insert user\"></form>"+
					"<p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";

			t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
			OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
			os.write(response.getBytes());
			os.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}