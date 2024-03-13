package com.revature.project.parser.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("parsedRecords")
public class ParsedRecord {

  @Id
  @Field("_id")
  private ObjectId id;

  @Field("user_id")
  private String userId;

  @Field("metadata_id")
  private String metadataId;

  @Field("parsed_data")
  private org.bson.Document parsedData;

  public ParsedRecord() {
  }

  public ParsedRecord(ObjectId id, String userId, String metadataId, org.bson.Document parsedData) {
    this.id = id;
    this.userId = userId;
    this.metadataId = metadataId;
    this.parsedData = parsedData;
  }

  public ParsedRecord(String userId, String metadataId, org.bson.Document parsedData) {
    this.userId = userId;
    this.metadataId = metadataId;
    this.parsedData = parsedData;
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

  public org.bson.Document getParsedData() {
    return parsedData;
  }

  public void setParsedData(org.bson.Document parsedData) {
    this.parsedData = parsedData;
  }

}
