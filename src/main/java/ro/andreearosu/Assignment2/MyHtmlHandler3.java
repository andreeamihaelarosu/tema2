package ro.andreearosu.Assignment2;

import java.util.Map;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import java.io.IOException;
import java.io.OutputStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction") 
public class MyHtmlHandler3 implements HttpHandler{
	
	public void handle(HttpExchange t) throws IOException{
		
		//parse key=value params
		String response;
		if (t.getRequestURI().getQuery() == null) { 
			response = "<html><body><h3>There are no parameters to export</h3>" + 
    				"<p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
	    }
	        else {
	        	String URI = t.getRequestURI().getQuery().toString(); 
	        	System.out.println(URI);
	        	Map<String, String> m = getQueryParameters(URI);
	        	//set response
	        	response = "<html><body><h1>Table of params </h1>"+ getHtmlTable(m)+"<br><br><form action=\"../smth\" method=\"GET\">\r\n" + 
	        				"<p><a href=\"http://localhost:8001/\">Home</a></p></body></html>";
	        }
		t.sendResponseHeaders(200, response.length()); //send response headers. must be called before next step
		OutputStream os = t.getResponseBody(); //get output stream to send response body. When the response body has been written, the stream must be closed to terminate the exchange.
		os.write(response.getBytes());
		os.close();
	}
	
	//generate a key/value Map
	public static Map<String, String> getQueryParameters(String queryString) {
	    
	    String[] parameters = queryString.split("&");
	    Map<String, String> queryParameters = new HashMap();

	    for (String parameter : parameters) {
	        String[] keyValuePair = parameter.split("=");
	        queryParameters.put(keyValuePair[0], keyValuePair[1]);
	        System.out.println(queryParameters.get(keyValuePair[0]));
	    }
	    return queryParameters;    
	}
	
	//generate html of table with params
	public static String getHtmlTable(Map<String,String> m) {
		
		String link = "<html><body><table style=\"width:25%\" border=\"1px solid black\"><tr><th>Key</th><th>Value</th></tr>";
		for (Map.Entry<String, String> entry : m.entrySet())
		{
			link = link + "<tr><td>" + entry.getKey() + "</td><td>"+ entry.getValue()+ "</td></tr>";
		}
		link = link + "</table></body></html>";
		
		return link;
	}
}