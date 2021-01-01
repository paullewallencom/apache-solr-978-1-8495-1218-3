package pl.solr;

import java.io.IOException;

import org.apache.lucene.document.Fieldable;
import org.apache.solr.request.TextResponseWriter;
import org.apache.solr.request.XMLWriter;
import org.apache.solr.schema.SortableIntField;
import org.apache.solr.util.NumberUtils;

public class ExampleFieldType extends SortableIntField {
    public final String toInternal(final String value) {
        return NumberUtils.int2sortableStr(getInternalValue(value));
    }
    
    public final void write(final XMLWriter xmlWriter, final String name, final Fieldable field) throws IOException {
        xmlWriter.writeStr(name, parseFromValue(String.valueOf(getVal(field)))); 
    }
    
    public final void write(final TextResponseWriter writer, final String name, final Fieldable field) throws IOException {
        writer.writeStr(name, parseFromValue(String.valueOf(getVal(field))), false);
    }
    
    protected String getInternalValue( String value ) {
        String internalValue = value.replace(",", ".");
        String[] parts = internalValue.split("\\.");
        internalValue = parts[0];
        if (parts.length > 1 && parts[1] != null) {
            if (parts[1].length() == 2) {
                internalValue += parts[1];
            } else if (parts[1].length() == 1) {
                internalValue += parts[1];
                internalValue += "0";
            } else {
                internalValue += "00";
            }
        } else if (parts.length == 1) {
            internalValue += "00";
        }
        return internalValue;
    }
    
    protected int getVal( Fieldable field ) {
        String stringValue = field.stringValue();
        return NumberUtils.SortableStr2int(stringValue, 0 , stringValue.length());
    }
    
    protected String parseFromValue(final String value) {
        int length = value.length();
        StringBuffer buffer = new StringBuffer();
        if (length > 2) {
            buffer.append(value.substring(0, length - 2));
            buffer.append(".");
            buffer.append(value.substring(length - 2 , length));
        }
        return buffer.toString();
    }
}
