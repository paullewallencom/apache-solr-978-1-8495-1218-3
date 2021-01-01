package pl.solr;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;

public class ExampleFilter extends TokenFilter {
    private final TermAttribute termAttr = (TermAttribute) addAttribute(TermAttribute.class);
    
    public ExampleFilter(TokenStream stream) {
        super(stream);
    }
    
    @Override
    public boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            String term = termAttr.term();
            if (term.length() <= 1) {
                return true;
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(term.charAt(1)).append(term.charAt(0)).append(term.substring(2));
            
            termAttr.setTermBuffer(buffer.toString());
            termAttr.setTermLength(buffer.length());
            return true;
        }
        return false;
    }
}
