package com.revature.project.parser.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import jakarta.validation.constraints.NotNull;

@Document(collection = "fixedLengthFiles")
public class FixedLengthFile {

  @Field("_id")
  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  @Field("name")
  private String fileName;

  @Field("user_id")
  private String userId;

  @Field("path")
  private String filePath;

  @Field("metadata_id")
  private String metaDataId;

  public FixedLengthFile() {
  }

  public FixedLengthFile(ObjectId id, String fileName, String userId, String filePath, String metaDataId) {
    this.id = id;
    this.fileName = fileName;
    this.userId = userId;
    this.filePath = filePath;
    this.metaDataId = metaDataId;
  }

  public FixedLengthFile(String fileName, String userId, String filePath, String metaDataId) {
    this.fileName = fileName;
    this.userId = userId;
    this.filePath = filePath;
    this.metaDataId = metaDataId;
  }

  public FixedLengthFile(@NotNull FixedLengthFile oldFile, @NotNull String metadataId) {
    this(oldFile.getId(), oldFile.getFileName(), oldFile.getUserId(), oldFile.getFilePath(), metadataId);
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getMetaDataId() {
    return metaDataId;
  }

  public void setMetaDataId(String metaDataId) {
    this.metaDataId = metaDataId;
  }

}
