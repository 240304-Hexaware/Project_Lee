package com.revature.project.parser.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.project.parser.exceptions.ParsingFailedException;
import com.revature.project.parser.models.Field;

public class FileParser {

  public enum SUPPORTED_DATA_TYPE {
    STRING, FLOAT, INTEGER, DATE
  }

  private FileParser() {

  }

  public static Map<String, Object> readStringFields(String data, Map<String, Field> spec)
      throws ParsingFailedException {
    Map<String, Object> resultMap = new HashMap<>();

    try {
      for (Map.Entry<String, Field> entry : spec.entrySet()) {
        Field field = spec.get(entry.getKey());
        String fieldValue = data.substring(field.getStartPos(), field.getEndPos() + 1).trim();
        String dataType = field.getDataType();
        resultMap.put(entry.getKey(), castData(fieldValue, dataType));
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new ParsingFailedException("Invalid flatfile, failed at " + e.getMessage());
    }
    return resultMap;
  }

  private static Object castData(String value, String dataType) throws ParsingFailedException {
    if (dataType.equalsIgnoreCase(SUPPORTED_DATA_TYPE.DATE.name())) {
      // decided not to parse data at this time because of the lack of time to
      // implement the logic to produce a formatted data string
      // return parseDate(value);
      return value;
    } else if (dataType.equalsIgnoreCase(SUPPORTED_DATA_TYPE.STRING.name())) {
      return value;
    } else if (dataType.equalsIgnoreCase(SUPPORTED_DATA_TYPE.INTEGER.name())) {
      return parseInt(value);
    } else if (dataType.equalsIgnoreCase(SUPPORTED_DATA_TYPE.FLOAT.name())) {
      return parseDouble(value);
    }
    throw new ParsingFailedException("Unsupported data type found: " + dataType);

  }

  private static LocalDate parseDate(String date) {
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    return LocalDate.parse(date, formatter);
  }

  private static Integer parseInt(String value) {
    return Integer.parseInt(value.replaceAll(",", ""));
  }

  private static Double parseDouble(String value) {
    return Double.parseDouble(value.replaceAll(",", ""));
  }

  public static List<Map<String, Object>> readStringFieldsAsList(List<String> data, Map<String, Field> spec)
      throws ParsingFailedException {
    List<Map<String, Object>> read = new ArrayList<>();
    for (String str : data) {
      read.add(readStringFields(str, spec));
    }
    return read;
  }
}
