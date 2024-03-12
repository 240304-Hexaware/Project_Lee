package com.revature.project.parser.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "fileMetadata")
public class FileMetadata {
  @Id
  @Field("_id")
  private String id;

  @Field("raw_file_id")
  private String rawFileId;

  @Field("parsed_data_id")
  private String parsedDataId;

  @Field("specification_id")
  private String specificationId;

  public FileMetadata() {
  }

  public FileMetadata(String id, String rawFileId, String parsedDataId, String specificationId) {
    this.id = id;
    this.rawFileId = rawFileId;
    this.parsedDataId = parsedDataId;
    this.specificationId = specificationId;
  }

  public FileMetadata(String rawFileId, String parsedDataId, String specificationId) {
    this.rawFileId = rawFileId;
    this.parsedDataId = parsedDataId;
    this.specificationId = specificationId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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

}
