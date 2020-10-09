package org.jefesimpson.blog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.javalin.Javalin;
import org.jefesimpson.blog.configuration.Constants;
import org.jefesimpson.blog.controller.BlogsController;
import org.jefesimpson.blog.controller.UsersController;
import org.jefesimpson.blog.model.Users;

import static io.javalin.apibuilder.ApiBuilder.crud;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(new LocalDateSerializer());
//        mapper.registerModule(module);
//
//        Users u = new Users("john15", "john_1995@gmail.com", Users.RoleEnum.COMMON, "john199515", LocalDate.of(2007,1,12));
//        System.out.println(mapper.writeValueAsString(u));

        Javalin app = Javalin.create();


        app.routes(() -> {
            crud("users/:id", new UsersController());
            //get users
            //post users
            //get users/:id
            //patch users/:id
            //delete users/:id
            crud("blogs/:id", new BlogsController());
        });

        app.start(Constants.PORT);
    }
}
