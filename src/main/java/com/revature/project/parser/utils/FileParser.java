package com.revature.project.parser.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.project.parser.models.Field;

public class FileParser {

  private FileParser() {

  }

  public static String readCompleteChars(File file) throws IOException {
    FileInputStream stream = new FileInputStream(file);
    StringBuilder builder = new StringBuilder();
    try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
      while (reader.ready()) {
        builder.append((char) reader.read());
      }
    }

    return builder.toString();
  }

  public static List<String> readStringFields(String data, Map<String, Field> spec) {
    List<String> fieldList = new ArrayList<>();

    Set<String> fields = spec.keySet();
    for (String fieldName : fields) {
      Field field = spec.get(fieldName);
      String fieldValue = data.substring(field.getStartPos(), field.getEndPos() + 1).trim();
      fieldList.add(fieldValue);
      System.out.println("[" + fieldName + "][" + fieldValue + "]");
    }
    return fieldList;
  }
}
