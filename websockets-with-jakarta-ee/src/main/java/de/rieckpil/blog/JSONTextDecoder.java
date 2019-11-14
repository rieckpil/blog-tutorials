package de.rieckpil.blog;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class JSONTextDecoder implements Decoder.Text<JsonObject> {

    @Override
    public JsonObject decode(String s) throws DecodeException {
        try (JsonReader jsonReader = Json.createReader(new StringReader(s))) {
            return jsonReader.readObject();
        }
    }

    @Override
    public boolean willDecode(String s) {
        try (JsonReader jsonReader = Json.createReader(new StringReader(s))) {
            jsonReader.readObject();
            return true;
        } catch (JsonException e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
