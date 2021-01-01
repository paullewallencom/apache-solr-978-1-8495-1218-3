package pl.solr;

import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrQuerying {
    public static void main(String[] args) throws Exception {
        CommonsHttpSolrServer server = new CommonsHttpSolrServer( "http://localhost:8983/solr" );
        server.setParser(new XMLResponseParser());
        
        SolrQuery query = new SolrQuery();
        query.setQuery("document");
        query.setStart(0);
        query.setRows(10);
        
        QueryResponse response = server.query( query );
        SolrDocumentList documents = response.getResults();
        Iterator<SolrDocument> itr = documents.iterator();
        System.out.println("DOCUMENTS");
        while (itr.hasNext()) {
            SolrDocument doc = itr.next();
            System.out.println(doc.getFieldValue("id") + ":" + doc.getFieldValue("name"));
        }
    }
}
