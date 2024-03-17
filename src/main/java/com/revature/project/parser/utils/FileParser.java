package com.revature.project.parser.utils;

import java.util.HashMap;
import java.util.Map;

import com.revature.project.parser.models.Field;

public class FileParser {

  private FileParser() {

  }

  // TODO: make it to handle date type
  public static Map<String, String> readStringFields(String data, Map<String, Field> spec) {
    Map<String, String> resultMap = new HashMap<>();

    for (Map.Entry<String, Field> entry : spec.entrySet()) {
      Field field = spec.get(entry.getKey());
      String fieldValue = data.substring(field.getStartPos(), field.getEndPos() + 1).trim();
      resultMap.put(entry.getKey(), fieldValue);
    }
    return resultMap;
  }
}
