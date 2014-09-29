package com.impels.web;
/**
 * @author Carol Mak
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class ImpelsHTTPServer {
	final static String QUIT="quit";
	final static String QUIT_MSG="Enter \"quit\" to stop the server:";
	static final String BASE_URI = "http://localhost:9999/ArticleResource";
	 
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServerFactory.create(BASE_URI);
            server.start();
            System.out.println(QUIT_MSG);
            String command="";
            while(true){
	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            try {
	               command = br.readLine();
	             } catch (IOException ioe) {
	            	 ioe.printStackTrace();
	            	 server.stop(1);
	            	 System.exit(1);
	             }
	            if(command.equalsIgnoreCase(QUIT)){
	            	server.stop(0);
	            	System.exit(0);
	            }else{
	            	System.out.println(QUIT_MSG);
	            }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}
