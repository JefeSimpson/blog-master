package org.jefesimpson.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import org.jefesimpson.blog.configuration.Constants;
import org.jefesimpson.blog.configuration.DatabaseUtils;
import org.jefesimpson.blog.configuration.JacksonUtils;
import org.jefesimpson.blog.deserializer.BlogsDeserializor;
import org.jefesimpson.blog.deserializer.UsersDeserializor;
import org.jefesimpson.blog.model.Blogs;

import org.jefesimpson.blog.model.Users;
import org.jefesimpson.blog.serializer.BlogsSerializor;
import org.jefesimpson.blog.serializer.UsersSerializor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class BlogsController implements CrudHandler {
    private Dao<Blogs, Integer> dao;
    private Logger logger;

    public BlogsController(){
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            dao = DaoManager.createDao(DatabaseUtils.connectionSource(), Blogs.class);
        } catch (SQLException throwables) {
            logger.error("error creating dao");
        }
    }


    @Override
    public void create(@NotNull Context context) {
        JacksonUtils.getModule().addDeserializer(Blogs.class, new BlogsDeserializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        try {
            Blogs blogs = JacksonUtils.getMapper().readValue(context.body(), Blogs.class);
            dao.create(blogs);
            context.status(Constants.CREATED_201);
        } catch (JsonProcessingException | SQLException e) {
            e.printStackTrace();
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
            logger.error("error deleting blogs(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }

    @Override
    public void getAll(@NotNull Context context) {
        JacksonUtils.getModule().addSerializer(Blogs.class, new BlogsSerializor());
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
        JacksonUtils.getModule().addSerializer(Blogs.class, new BlogsSerializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        int id = Integer.valueOf(s);
        try {
            Blogs blogs = dao.queryForId(id);
            if(blogs != null){
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
        JacksonUtils.getModule().addSerializer(Blogs.class, new BlogsSerializor());
        JacksonUtils.getMapper().registerModule(JacksonUtils.getModule());
        int id = Integer.valueOf(s);
        try {
            Blogs blogs = JacksonUtils.getMapper().readValue(context.body(), Blogs.class);
            blogs.setId(id);
            dao.update(blogs);
        } catch (JsonProcessingException | SQLException e) {
            logger.error("error updating users(table)");
            context.status(Constants.INTERNAL_SERVER_ERROR_500);
        }
    }
}
