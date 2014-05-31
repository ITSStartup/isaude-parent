package br.com.its.isaude.core.json.deserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class TimeDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            return new SimpleDateFormat("HHmm").parse(jsonParser.getText());
        } catch (ParseException ex) {
            Logger.getLogger(TimeDeserializer.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException(ex.getCause());
        }
    }
    
}
