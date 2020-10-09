package org.jefesimpson.blog.configuration;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.jefesimpson.blog.model.Blogs;
import org.jefesimpson.blog.model.Users;

import java.sql.SQLException;

public class DatabaseUtils {

    public static ConnectionSource connectionSource() throws SQLException{
        return new JdbcConnectionSource(Constants.DB_PATH);
    }

    static{
        try {
            TableUtils.createTableIfNotExists(connectionSource(), Users.class);
            TableUtils.createTableIfNotExists(connectionSource(), Blogs.class);
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private DatabaseUtils(){}
}
