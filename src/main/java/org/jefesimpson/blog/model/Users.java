package org.jefesimpson.blog.model;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;

@DatabaseTable
public class Users {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "nickname")
    private String nickname;
    @DatabaseField(columnName = "email")
    private String email;
    @DatabaseField(dataType = DataType.ENUM_STRING, columnName = "role")
    private RoleEnum roleEnum;
    @DatabaseField(columnName = "password")
    private String password;
    @DatabaseField(columnName = "data_of_create", dataType = DataType.SERIALIZABLE)
    private LocalDate data;

    public Users(String nickname, String email, RoleEnum roleEnum, String password, LocalDate data) {
        this.id = 0;
        this.nickname = nickname;
        this.email = email;
        this.roleEnum = roleEnum;
        this.password = password;
        this.data = data;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public enum RoleEnum {
        COMMON, VIP, ADMIN;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", roleEnum=" + roleEnum +
                ", password='" + password + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
