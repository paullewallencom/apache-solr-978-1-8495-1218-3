package pl.solr;

import java.util.logging.Logger;

import org.apache.solr.handler.StandardRequestHandler;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.request.SolrQueryResponse;

public class ExampleRequestHandler extends StandardRequestHandler {
    private static final Logger LOG = Logger.getLogger( ExampleRequestHandler.class.toString() );
    
    public void handleRequestBody(SolrQueryRequest request, SolrQueryResponse response)
            throws Exception {
        super.handleRequestBody(request, response);
        LOG.info( "[" + (response.getEndTime() - request.getStartTime()) + "]:" + request.getParamString() );
    }
}
