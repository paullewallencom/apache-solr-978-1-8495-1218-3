package pl.solr;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.SolrInputDocument;

public class SolrIndex {
    public static void main(String[] args) throws Exception {
        CommonsHttpSolrServer server = new CommonsHttpSolrServer( "http://localhost:8983/solr" );
        server.setParser(new XMLResponseParser());
        
        SolrInputDocument doc1 = new SolrInputDocument();
        doc1.addField("id", 1);
        doc1.addField("name", "Document one");
        
        SolrInputDocument doc2 = new SolrInputDocument();
        doc2.addField("id", 2);
        doc2.addField("name", "Document two");
        
        Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
        docs.add(doc1);
        docs.add(doc2);
        
        server.add(docs);
        server.commit();
        server.optimize();
    }
}
