package org.jefesimpson.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.jefesimpson.blog.model.Blogs;

import java.io.IOException;

public class BlogsSerializor extends StdSerializer<Blogs> {

    public BlogsSerializor() {
        super(Blogs.class);
    }

    @Override
    public void serialize(Blogs blogs, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("author_id", blogs.getUsers().getId());
        jsonGenerator.writeStringField("blog_name", blogs.getName());
        jsonGenerator.writeStringField("blog_text", blogs.getText());
        jsonGenerator.writeStringField("blog_data_of_create", blogs.getData().toString());
        jsonGenerator.writeBooleanField("blog_vip_access", blogs.getVipAccess());

        jsonGenerator.writeEndObject();
    }
}
