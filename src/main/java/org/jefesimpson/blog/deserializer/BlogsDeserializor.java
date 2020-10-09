package org.jefesimpson.blog.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import org.jefesimpson.blog.configuration.DatabaseUtils;
import org.jefesimpson.blog.model.Blogs;
import org.jefesimpson.blog.model.Users;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class BlogsDeserializor extends StdDeserializer<Blogs>{
    public BlogsDeserializor() {
        super(Blogs.class);
    }

    @Override
    public Blogs deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {    
        JsonNode js = jsonParser.getCodec().readTree(jsonParser);
        int user = js.get("users_id").asInt();
        String name = js.get("blog_name").asText();
        String text = js.get("blog_text").asText();
        LocalDate date = LocalDate.parse(js.get("blog_data_of_create").asText());
        Boolean access = js.get("blog_vip_access").asBoolean();


        Users users = null;
        try {
            Dao<Users, Integer> dao = DaoManager.createDao(DatabaseUtils.connectionSource(), Users.class);
            users = dao.queryForId(user);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Blogs(users,name,text,date,access);

    }
}
