package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import play.Logger;
import play.Play;
import play.mvc.Controller;
import play.vfs.VirtualFile;
import utils.SolrServerFactory;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 1/7/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class FacetController extends Controller {


    public static List<FacetField> getFacets()
    {

        Logger.info("Facets Controller *******************");
        List<FacetField.Count> facetlist =null;

        List<FacetField> facetFieldList =null;

        try
        {
            SolrServer server = SolrServerFactory.getServer();

            SolrQuery query = new SolrQuery();
            query.setQuery("*:*");
            query.setFacet(true);

            query.addFacetField("price");


            Logger.info("Adding Facet Ranges to the Query ");

            query.addFacetQuery("facet.field=price");
            query.addFacetQuery("facet.range.start=0");
            query.addFacetQuery("facet.range.end=1000");
            query.addFacetQuery("facet.range.gap=200");
            QueryResponse response = server.query(query);

            Logger.info("Got the facet range queryresponse");


            SolrDocumentList dlist = response.getResults();

            facetFieldList = response.getFacetFields();




            //render(facetlist);

        }
        catch(MalformedURLException me )
        {
              Logger.error("Malformed URL Exception, Solr Server not found in the url, please check application.conf for solr.server.url ");
        }
        catch(SolrServerException se)
        {
            Logger.error("Solr Server Exception "+se);
        }






        return facetFieldList;

    }


}
