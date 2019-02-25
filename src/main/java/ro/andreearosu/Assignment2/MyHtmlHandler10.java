package ro.andreearosu.Assignment2;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler10 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
        String response = "";
        String referinta = "";
        MyHtmlHandlerMain h = new MyHtmlHandlerMain();
        DBConnection dbc = new DBConnection();
        if(t.getRequestURI().getQuery() == null) {
			response = "<html><body><h3>Please insert a value to delete. </h3><br><p><a href=\"http://localhost:8001/DatabaseOps\">Back</a></p></body></html>";
		}
		else {
			referinta = t.getRequestURI().getQuery().toString().split("=")[1];
	    	try {
				dbc.DeleteFromDB("users",referinta);
				ArrayList<String[]> table = dbc.selectToHtml("users");
				dbc.closeConn();
			response = "<html><body><h2>User deleted succesfully! </h2><br><br><h2>Here is the USERS database: </h2>"+ h.getHtmlTable(table)+"<br><br>" + 
	    				"<p><a href=\"http://localhost:8001/DatabaseOps\">Back</a></p></body></html>";
	    	} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
			OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
			os.write(response.getBytes());
			os.close();
		}
	}
	
}