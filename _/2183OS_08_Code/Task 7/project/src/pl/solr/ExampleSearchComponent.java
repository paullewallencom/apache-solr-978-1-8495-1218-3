package pl.solr;

import java.io.IOException;

import org.apache.solr.handler.component.ResponseBuilder;
import org.apache.solr.handler.component.SearchComponent;
import org.apache.solr.request.UnInvertedField;
import org.apache.solr.search.SolrIndexSearcher;

public class ExampleSearchComponent extends SearchComponent {
    String[] fieldNames = null;
    long totalMemorySize = 0;
    
    @Override
    public void prepare(ResponseBuilder builder) throws IOException {
        fieldNames = builder.req.getParams().get("exampleFields", "").split(",");
        totalMemorySize = 0;
    }

    @Override
    public void process(ResponseBuilder builder) throws IOException {
        if (fieldNames != null) {
            SolrIndexSearcher searcher = builder.req.getSearcher();
            for (String fieldName : fieldNames) {
                UnInvertedField field = UnInvertedField.getUnInvertedField(fieldName, searcher);
                totalMemorySize += field.memSize();
            }
        }
        builder.rsp.add( "example", totalMemorySize );
    }
    
    
    @Override
    public String getDescription() {
        return "ExampleSearchComponent";
    }

    @Override
    public String getSource() {
        return "";
    }

    @Override
    public String getSourceId() {
        return "";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}
