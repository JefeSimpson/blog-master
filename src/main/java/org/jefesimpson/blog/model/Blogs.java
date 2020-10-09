package org.jefesimpson.blog.model;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;

@DatabaseTable
public class Blogs {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreignAutoCreate = true, foreignAutoRefresh = true, foreign = true, columnName = "users_id")
    private Users users;
    @DatabaseField(columnName = "blog_name")
    private String name;
    @DatabaseField(columnName = "blog_text")
    private String text;
    @DatabaseField(columnName = "blog_data_of_create", dataType = DataType.SERIALIZABLE)
    private LocalDate data;
    @DatabaseField(columnName = "blog_vip_access")
    private boolean access;

    public Blogs(Users users, String name, String text, LocalDate data, boolean access) {
        this.id = 0;
        this.users = users;
        this.name = name;
        this.text = text;
        this.data = data;
        this.access = access;
    }

    public Blogs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean getVipAccess() {
        return access;
    }

    public void setVipAccess(boolean access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "Blogs{" +
                "id=" + id +
                ", users=" + users +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", data='" + data + '\'' +
                ", access=" + access +
                '}';
    }
}
