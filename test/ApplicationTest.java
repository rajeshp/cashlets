import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.junit.*;
import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;
import utils.SolrServerFactory;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }


    @Test
    public void solrTest()  throws Exception
    {

        SolrServer server = SolrServerFactory.getServer();

        SolrInputDocument doc = new SolrInputDocument();

        doc.setField("name","rajesh");

        server.add(doc);

        System.out.println("Document added to server");

        server.commit();



    }


    
}