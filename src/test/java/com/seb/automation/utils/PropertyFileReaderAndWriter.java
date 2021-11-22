package com.seb.automation.utils;

import java.io.File;
import java.util.Properties;

/** This is the utility class for data Read/Write from property file */
public class PropertyFileReaderAndWriter {

  private static String defaultDataFile =
      "src"
          + File.separator
          + "test"
          + File.separator
          + "resources"
          + File.separator
          + "testdata"
          + File.separator
          + "TestData.properties";
  private static String ConfigFilePath = "./Config.properties";
  private static Properties prop = new Properties();

  public PropertyFileReaderAndWriter() {}

  /** This method will get the properties pulled from a file located relative to the base dir */
  public static String getPropertyFromConfig(String property) {
    try {
      Properties prop = ResourceLoader.loadProperties(ConfigFilePath);
      return prop.getProperty(property);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  /** This method will get the properties pulled from a file located inside the 'testdata' folder */
  public static String getPropertyFromTestData(String property) {
    try {
      prop = ResourceLoader.loadProperties(defaultDataFile);
      return prop.getProperty(property);
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }
}
