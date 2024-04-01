package com.revature.project.parser.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.project.parser.exceptions.ParsingFailedException;
import com.revature.project.parser.models.Field;

public class FileParser {

  private FileParser() {

  }

  // TODO: make it to handle date type
  public static Map<String, String> readStringFields(String data, Map<String, Field> spec)
      throws ParsingFailedException {
    Map<String, String> resultMap = new HashMap<>();

    try {
      for (Map.Entry<String, Field> entry : spec.entrySet()) {
        Field field = spec.get(entry.getKey());
        String fieldValue = data.substring(field.getStartPos(), field.getEndPos() + 1).trim();
        resultMap.put(entry.getKey(), fieldValue);
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ParsingFailedException("Invalid flatfile, failed at " + e.getMessage());
    }
    return resultMap;
  }

  public static List<Map<String, String>> readStringFieldsAsList(List<String> data, Map<String, Field> spec)
      throws ParsingFailedException {
    List<Map<String, String>> read = new ArrayList<>();
    for (String str : data) {
      read.add(readStringFields(str, spec));
    }
    return read;
  }
}
