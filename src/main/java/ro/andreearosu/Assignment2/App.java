package ro.andreearosu.Assignment2;

import java.net.InetSocketAddress;
import java.io.IOException;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction") 
public class App
{
    public static void main( String[] args) throws IOException
    {
    	//create web server service
    	HttpServer server = HttpServer.create(new InetSocketAddress(8001),0);
        //set handler to specified path
    	server.createContext("/", new MyHtmlHandlerMain());
    	server.createContext("/SelectGenre", new MyHtmlHandler1());
    	server.createContext("/DisplayBooks", new MyHtmlHandler2());
    	server.createContext("/TableParams", new MyHtmlHandler3());
    	server.setExecutor(null); //create default executor
    	server.start(); //start the server
    	System.out.println("Server started at port 8001 ");
    }
}
