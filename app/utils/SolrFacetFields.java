package utils;


import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import play.Logger;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: TE162141
 * Date: 9/13/12
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolrFacetFields {


    public static List<String> getFacetFieldValues(String query, String[] fieldNames)     throws MalformedURLException, SolrServerException
    {

        List<String> facetResult = new ArrayList<String>();

        SolrServer server = SolrServerFactory.getServer();


        SolrQuery solrquery = new SolrQuery();

        solrquery.setQuery("*:*");
        solrquery.setFacet(true);
        solrquery.setFacetMinCount(1);
        solrquery.setFacetLimit(10);

        for(String fieldName : fieldNames)
            solrquery.addFacetField(fieldName);


        QueryResponse rsp = server.query(solrquery);
        Logger.info("fired the query");
        List<FacetField> facetlist = rsp.getFacetFields();
        Logger.info("Got the facetlist");

        int c=0;

        SolrQuery allItemsQuery = new SolrQuery();
        allItemsQuery.setQuery("*:*");
        QueryResponse allItemsResponse = server.query(allItemsQuery);

        c = allItemsResponse.getResults().size();


        facetResult.add("All ("+c+")");


        for(FacetField facet : facetlist)
        {

            List<FacetField.Count> facetvalues = facet.getValues();

            for(FacetField.Count  fcount: facetvalues)
            {
                // c+=fcount.getCount();
                facetResult.add(fcount.getName() + "  ("+fcount.getCount()+")" );
            }

        }

        // facetResult.set(0,"All ("+c+")");

        return facetResult;
    }




    public static Map<Integer,Integer> getFacetPriceRangeValues(String strquery, String[] facetFieldNames)   throws MalformedURLException,SolrServerException
    {

        HashMap<Integer, Integer> facetResults = new HashMap<Integer, Integer>();

        SolrServer server = SolrServerFactory.getServer();

        SolrQuery query = new SolrQuery();

        query.setQuery(strquery);

        query.setFacet(true);

        for(String facetField : facetFieldNames)
            query.addFacetField(facetField);


        Logger.info("Adding Facet Ranges to the Query ");

        query.addFacetQuery("facet.field=price");
        query.addFacetQuery("facet.range.start=0");
        query.addFacetQuery("facet.range.end=1000");
        query.addFacetQuery("facet.range.gap=200");


        try
        {
            QueryResponse response = server.query(query);

            Logger.info("Got the facet range queryresponse");

            SolrDocumentList dlist = response.getResults();

            List<FacetField> facetFieldList = response.getFacetFields();

            for(FacetField f :facetFieldList)
            {

                List<FacetField.Count> list = f.getValues();
                FacetField.Count fc = list.get(0);
                List<FacetField.Count> priceRangeList = fc.getFacetField().getValues();

                for(FacetField.Count price : priceRangeList)
                {
                    String priceName = price.getName();
                    String priceField = price.toString();



                    float hprice = Float.parseFloat(priceName)+1;
                    int a = (int) hprice;
                    int b =(int) price.getCount();
                    facetResults.put(a,b);

                }

            }

        }
        catch(SolrServerException se)
        {

            Logger.error("Solr FacetRange Query Exception : "+se);
        }




        return facetResults;
    }


}
