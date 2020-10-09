package org.jefesimpson.blog.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jefesimpson.blog.model.Users;

import java.io.IOException;
import java.time.LocalDate;

public class UsersDeserializor extends StdDeserializer<Users> {
    public UsersDeserializor() {
        super(Users.class);
    }

    @Override
    public Users deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode js = jsonParser.getCodec().readTree(jsonParser);
        String nickname = js.get("nickname").asText();
        String email = js.get("email").asText();
        String password = js.get("password").asText();
        Users.RoleEnum roleEnum = Users.RoleEnum.valueOf(js.get("role").asText().toUpperCase());
        LocalDate localDate = LocalDate.parse(js.get("data_of_create").asText());

        return new Users(nickname, email , roleEnum, password, localDate);
    }
}
