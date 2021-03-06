package ro.andreearosu.Assignment2;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction") 
public class App
{
    public static void main( String[] args) throws Exception
    {
    	//create web server service
    	HttpServer server = HttpServer.create(new InetSocketAddress(8001),0);
        //set handler to specified path
    	server.createContext("/", new MyHtmlHandlerMain());
    	server.createContext("/SelectGenre", new MyHtmlHandler1());
    	server.createContext("/SelectGenre/DisplayBooks", new MyHtmlHandler2());
    	server.createContext("/TableParams", new MyHtmlHandler3());
    	server.createContext("/ExportParams", new MyHtmlHandler4());
    	server.createContext("/DatabaseOps", new MyHtmlHandler5());
    	server.createContext("/DatabaseOps/Insert", new MyHtmlHandler6());
    	server.createContext("/DatabaseOps/Insert/InsertMsg", new MyHtmlHandler7());
    	server.createContext("/DatabaseOps/Display", new MyHtmlHandler8());
    	server.createContext("/DatabaseOps/Delete", new MyHtmlHandler9());
    	server.createContext("/DatabaseOps/Delete/DeleteMsg", new MyHtmlHandler10());
    	server.setExecutor(null); //create default executor
    	server.start(); //start the server
    	System.out.println("Server started at port 8001 ");
    }
}
