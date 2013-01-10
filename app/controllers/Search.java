package controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.SolrPing;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrInputDocument;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import utils.SolrServerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 12/12/12
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class Search extends Controller {


    public static void search() throws MalformedURLException
    {
         System.out.println("***********Inside Search Controller **********************");


        String q = params.get("q");


        System.out.println("Query Value :"+q);

        String url = "http://localhost:8080/solr/" ;

        SolrServer solrserver =   SolrServerFactory.getServer(url);

        SolrQuery query = new SolrQuery();

        query.setQuery("text:"+q);

         try
         {
             QueryResponse qresponse = solrserver.query(query);

             SolrDocumentList solrdocs = qresponse.getResults();

           //  System.out.println("got reponse....");


             /*for(SolrDocument doc : solrdocs)
             {

                 Collection<String> names = doc.getFieldNames();

                 for(String name : names)
                     System.out.println(name + " : "+doc.getFieldValue(name)+"<br>");


                 System.out.println("-----------------------------------------------------------------------------");


             }*/

             //render(solrdocs,q);

             render(q, solrdocs);


         }catch(Exception e)
         {
             Logger.error("Solr Error :"+e.toString() ,e );
         }



    }



    public static void test()   throws IOException, SolrException, SolrServerException
    {


        SolrServer server = new HttpSolrServer("http://localhost:8080/solr/");


        SolrPingResponse resp = server.ping();

        String pingStatusText = HttpStatus.getStatusText(resp.getStatus());
	System.out.println("Pingresponse : "+pingStatusText);
        render();


    }



    public static void solrTestInsert() throws Exception
    {

        SolrServer server = SolrServerFactory.getServer();

        SolrInputDocument doc = new SolrInputDocument();

        doc.setField("id","03");
        doc.setField("name","Electrician Service");
        doc.setField("description", "All Electrical applianaces fittign, repair - rajesh sandeep pratik ");
        doc.setField("price",300);
        doc.setField("photos", new ArrayList<String>());

        server.add(doc);

        System.out.println("Document added to server");

        server.commit();

    }


    public static JsonObject jqueryTest()
    {
        List<Double> list = new ArrayList<Double>();

        JsonObject obj = new JsonObject();

        for(int i=0;i<10;i++)
        {
            obj.add("id",new JsonPrimitive(Math.random()));
        }




        return obj;
    }




    public static void filterPriceRange()
    {
        String solServerUrl = Play.configuration.getProperty("solr.server.url");


        System.out.println("Solr Server URL : "+solServerUrl);


    }


}
