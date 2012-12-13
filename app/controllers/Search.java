package controllers;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import play.Logger;
import play.mvc.Controller;
import utils.SolrServerFactory;

import java.net.MalformedURLException;
import java.util.Collection;

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

        String url = "http://173.230.242.45:8080/solr/" ;

        SolrServer solrserver =   SolrServerFactory.getServer(url);

        SolrQuery query = new SolrQuery();

        query.setQuery("*:*");

         try
         {
             QueryResponse qresponse = solrserver.query(query);

             SolrDocumentList solrdocs = qresponse.getResults();


             for(SolrDocument doc : solrdocs)
             {

                 Collection<String> names = doc.getFieldNames();

                 for(String name : names)
                     System.out.println(name + " : "+doc.getFieldValue(name)+"<br>");


                 System.out.println("-----------------------------------------------------------------------------");


             }



         }catch(Exception e)
         {
             Logger.error("Solr Error :"+e.toString() ,e );
         }


    }


}
