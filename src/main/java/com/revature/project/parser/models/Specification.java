package com.revature.project.parser.models;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@Document(collection = "specifications")
public class Specification {

  @Id
  @Field("_id")
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("user_id")
  private String userId;

  @Field("specification_details")
  private Map<String, com.revature.project.parser.models.Field> specs;

  @Field("name")
  private String name;

  public Specification() {
  }

  public Specification(ObjectId id, String userId, Map<String, com.revature.project.parser.models.Field> specs,
      String name) {
    this.id = id;
    this.userId = userId;
    this.specs = specs;
    this.name = name;
  }

  public Specification(String userId, Map<String, com.revature.project.parser.models.Field> specs, String name) {
    this.userId = userId;
    this.specs = specs;
    this.name = name;
  }

  public Specification(ObjectId id, String userId, Map<String, com.revature.project.parser.models.Field> specs) {
    this.id = id;
    this.userId = userId;
    this.specs = specs;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Map<String, com.revature.project.parser.models.Field> getSpecs() {
    return specs;
  }

  public void setSpecs(Map<String, com.revature.project.parser.models.Field> specs) {
    this.specs = specs;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
