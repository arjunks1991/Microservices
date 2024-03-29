package microservices.book.gamification.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * author arks 26-May-2021
 *
 * Class used to deserialize the attempt json object coming from the Multiplication microservice into gamification
 * representation of the attempt.
 */
public class MultiplicationResultAttemptDeserializer extends JsonDeserializer<MultiplicationResultAttempt> {

    @Override
    public MultiplicationResultAttempt deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
        throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);
        return new MultiplicationResultAttempt(jsonNode.get("user").get("alias").asText(),
                jsonNode.get("multiplication").get("factorA").asInt(),
                jsonNode.get("multiplication").get("factorB").asInt(),
                jsonNode.get("resultAttempt").asInt(),
                jsonNode.get("correct").asBoolean());
    }
}
