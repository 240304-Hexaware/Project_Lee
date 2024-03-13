package com.revature.project.parser.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.revature.project.parser.models.Field;

public class FileParser {

  private FileParser() {

  }

  // TODO: make it to handle date type
  public static Map<String, String> readStringFields(String data, Map<String, Field> spec) {
    Map<String, String> resultMap = new HashMap<>();

    Set<String> fields = spec.keySet();
    for (String fieldName : fields) {
      Field field = spec.get(fieldName);
      String fieldValue = data.substring(field.getStartPos(), field.getEndPos() + 1).trim();
      resultMap.put(fieldName, fieldValue);
    }
    return resultMap;
  }
}
