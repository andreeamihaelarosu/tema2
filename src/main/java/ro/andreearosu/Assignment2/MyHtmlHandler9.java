package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler9 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		String response = "";
		DBConnection dbc = new DBConnection();
		MyHtmlHandlerMain h = new MyHtmlHandlerMain();
		try {
			ArrayList<String[]> table = dbc.selectToHtml("users");
			dbc.closeConn();
			response = "<html><body><h2>Here is the USERS database: </h2>"+ h.getHtmlTable(table)+"<br><br>" + 
    				"<h3>Insert the user you want to delete:</h3><form method=\"GET\" action=\"/DatabaseOps/Delete/DeleteMsg\"><input type=\"text\" name=\"id\" value=\"1\"><br><br><input type=\"submit\" value=\"Delete\"></form><br><p><a href=\"http://localhost:8001/DatabaseOps\">Back</a></p>";

			t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
			OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
			os.write(response.getBytes());
			os.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
}