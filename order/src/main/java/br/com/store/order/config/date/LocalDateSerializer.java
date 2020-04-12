package br.com.store.order.config.date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.time.LocalDate;

public class LocalDateSerializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 1L;

    protected LocalDateSerializer() {
        super( LocalDate.class );
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt ) {
        try {
            return LocalDate.parse( jp.readValueAs( String.class ) );
        }
        catch (Exception e) {
            // TODO: handle exception
            return null;
        }
   }
}
