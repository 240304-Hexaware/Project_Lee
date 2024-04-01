package com.revature.project.parser.models;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "fileMetadata")
public class FileMetadata {
  @Id
  @Field("_id")
  private ObjectId id;

  @Field("raw_file_id")
  private String rawFileId;

  @Field("parsed_data_id")
  private String parsedDataId;

  @Field("specification_id")
  private String specificationId;

  @Field("user_id")
  private String userId;

  @Field("parsed_at")
  private Date parsedAt;

  public FileMetadata() {
  }

  public FileMetadata(ObjectId id, String rawFileId, String parsedDataId, String specificationId, String userId,
      Date parsedAt) {
    this.id = id;
    this.rawFileId = rawFileId;
    this.parsedDataId = parsedDataId;
    this.specificationId = specificationId;
    this.userId = userId;
    this.parsedAt = parsedAt;
  }

  public FileMetadata(String rawFileId, String parsedDataId, String specificationId, String userId, Date parsedAt) {
    this.rawFileId = rawFileId;
    this.parsedDataId = parsedDataId;
    this.specificationId = specificationId;
    this.userId = userId;
    this.parsedAt = parsedAt;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getRawFileId() {
    return rawFileId;
  }

  public void setRawFileId(String rawFileId) {
    this.rawFileId = rawFileId;
  }

  public String getParsedDataId() {
    return parsedDataId;
  }

  public void setParsedDataId(String parsedDataId) {
    this.parsedDataId = parsedDataId;
  }

  public String getSpecificationId() {
    return specificationId;
  }

  public void setSpecificationId(String specificationId) {
    this.specificationId = specificationId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getParsedAt() {
    return parsedAt;
  }

  public void setParsedAt(Date parsedAt) {
    this.parsedAt = parsedAt;
  }

}
