package org.jefesimpson.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jefesimpson.blog.model.Users;

import java.io.IOException;

public class UsersSerializor extends StdSerializer<Users> {
    public UsersSerializor() {
        super(Users.class);
    }

    @Override
    public void serialize(Users users, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeStringField("nickname", users.getNickname());
        jsonGenerator.writeStringField("email", users.getEmail());
        jsonGenerator.writeStringField("password", users.getPassword());
        jsonGenerator.writeStringField("role", users.getRoleEnum().toString());
        jsonGenerator.writeStringField("data_of_create", String.valueOf(users.getData()));

        jsonGenerator.writeEndObject();
    }
}
