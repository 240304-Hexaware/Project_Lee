package com.revature.project.parser.models;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import jakarta.validation.constraints.NotNull;

@Document("parsedRecords")
public class ParsedRecord {

  @Id
  @Field("_id")
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("user_id")
  private String userId;

  @Field("metadata_id")
  private String metadataId;

  @Field("parsed_data")
  private List<org.bson.Document> parsedData;

  public ParsedRecord() {
  }

  public ParsedRecord(ObjectId id, String userId, String metadataId, List<org.bson.Document> parsedData) {
    this.id = id;
    this.userId = userId;
    this.metadataId = metadataId;
    this.parsedData = parsedData;
  }

  public ParsedRecord(String userId, String metadataId, List<org.bson.Document> parsedData) {
    this.userId = userId;
    this.metadataId = metadataId;
    this.parsedData = parsedData;
  }

  public ParsedRecord(@NotNull ParsedRecord oldRecord, @NotNull String metadataId) {
    this(oldRecord.getId(), oldRecord.getUserId(), metadataId, oldRecord.getParsedData());
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

  public String getMetadataId() {
    return metadataId;
  }

  public void setMetadataId(String metadataId) {
    this.metadataId = metadataId;
  }

  public List<org.bson.Document> getParsedData() {
    return parsedData;
  }

  public void setParsedData(List<org.bson.Document> parsedData) {
    this.parsedData = parsedData;
  }

}
