package com.revature.project.parser.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "users")
public class User {

  @Field("_id")
  private ObjectId id;

  @Field("username")
  private String username;

  @Field("password")
  private String password;

  @Field("is_admin")
  private Boolean isAdmin;

  public User() {
  }

  public User(ObjectId id, String username, String password, Boolean isAdmin) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.isAdmin = isAdmin;
  }

  public User(ObjectId id, String username, String password) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.isAdmin = false;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.isAdmin = false;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
