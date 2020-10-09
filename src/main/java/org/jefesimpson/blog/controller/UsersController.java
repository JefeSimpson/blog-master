package org.jefesimpson.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.jefesimpson.blog.configuration.Constants;
import org.jefesimpson.blog.configuration.DatabaseUtils;
import org.jefesimpson.blog.configuration.JacksonUtils;
import org.jefesimpson.blog.deserializer.UsersDeserializor;
import org.jefesimpson.blog.model.Users;
import org.jefesimpson.blog.serializer.UsersSerializor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.sql.SQLException;

public class UsersController implements CrudHandler {
    private Dao<Users, Integer> dao;
    private Logger logger;

    public UsersController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            dao = DaoManager.createDao(DatabaseUtils.connectionSource(), Users.class);
        } catch (SQLException throwables) {
            logger.error("error creating dao");
        }

    }


    @Override
    public void create(@NotNull Context context){
        JacksonUtils.getModule().addDeserializer(Users.class, new UsersDeserializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        try {
            Users users = JacksonUtils.getMapper().readValue(context.body(), Users.class);
            dao.create(users);
            context.status(Constants.CREATED_201);
        } catch (JsonProcessingException | SQLException e) {
            logger.error("error creating users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }

    @Override
    public void delete(@NotNull Context context, @NotNull String s) {
        int id = Integer.valueOf(s);
        try {
            dao.deleteById(id);
        } catch (SQLException throwables) {
            logger.error("error deleting users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }

    @Override
    public void getAll(@NotNull Context context) {
        JacksonUtils.getModule().addSerializer(Users.class, new UsersSerializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        try {
            JacksonUtils.getMapper().writeValueAsString(dao.queryForAll());
        } catch (JsonProcessingException | SQLException throwables) {
            logger.error("error getAll users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }

    @Override
    public void getOne(@NotNull Context context, @NotNull String s) {
        JacksonUtils.getModule().addSerializer(Users.class, new UsersSerializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        int id = Integer.valueOf(s);
        try {
            Users users = dao.queryForId(id);
            if(users != null){
                JacksonUtils.getMapper().writeValueAsString(dao.queryForId(id));
            }
            else{
                context.status(Constants.NOT_FOUND_404);
            }
        } catch (SQLException | JsonProcessingException throwables) {
            logger.error("error getOne users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }

    @Override
    public void update(@NotNull Context context, @NotNull String s) {
        JacksonUtils.getModule().addSerializer(Users.class, new UsersSerializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        int id = Integer.valueOf(s);
        try {
            Users users = JacksonUtils.getMapper().readValue(context.body(), Users.class);
            users.setId(id);
            dao.update(users);
        } catch (JsonProcessingException | SQLException e) {
            logger.error("error updating users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }
}
