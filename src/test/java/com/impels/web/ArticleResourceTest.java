package com.impels.web;
/**
 * @author Carol Mak
 *
 */
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Unit test for ArticleResourceTest 
 */
public class ArticleResourceTest  
    extends TestCase
{
	static final String REST_URI = "http://localhost:9999/articles";
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ArticleResourceTest( String testName )
    {
    	super( testName );
    	ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ArticleResourceTest.class );
    }

    /**
     * 
     */
    public void testGetArticles()
    {
    	
        assertTrue( true );
    }
}

