package utils;



import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.core.CoreContainer;
import org.xml.sax.SAXException;
import play.Play;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;


/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 12/12/12
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolrServerFactory {

    // return solr server from default localhost 8080 port http://localhost:8080/solr/admin
    public static SolrServer getServer() throws MalformedURLException
    {
        Properties properties = Play.configuration;

        String url = (String) properties.get("solr.server.url");

        return getServer(url) ;

    }

    public static  SolrServer getServer(String url) throws MalformedURLException
    {

        SolrServer server = new HttpSolrServer( url );
        

        return server;

    }


    //returns default Embedded SOLR server at $APPLICATION_HOME/solr

    public static SolrServer getEmbeddedServer()   throws IOException, SAXException , ParserConfigurationException
    {
        String path = "./solr"  ;
        return getEmbeddedServer(path);

    }


    public static  SolrServer getEmbeddedServer(String path)   throws IOException, SAXException , ParserConfigurationException
    {
        // Note that the following property could be set through JVM level arguments too

        System.setProperty("solr.solr.home", path);
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = initializer.initialize();
        EmbeddedSolrServer server = new EmbeddedSolrServer(coreContainer, "");


        return server;
    }





}
