package pl.solr;

import org.apache.lucene.analysis.TokenStream;
import org.apache.solr.analysis.BaseTokenFilterFactory;

public class ExampleFilterFactory extends BaseTokenFilterFactory {
    @Override
    public TokenStream create(TokenStream stream) {
        return new ExampleFilter(stream);
    }
}
