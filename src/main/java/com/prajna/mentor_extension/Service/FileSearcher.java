package com.prajna.mentor_extension.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

  private static final String ROOT_DIR="./workspace";
  // String targetFile="PortfolioManagerImpl.java";
  
  public static List<File> search(String fileName) {
    File rootDir = new File(ROOT_DIR);

    List<File> matchingFiles = new ArrayList<>();
    searchHelper(rootDir, fileName, matchingFiles);

    if (matchingFiles.isEmpty()) {
      System.out.println("No files found with name: " + fileName);
    } else {
      System.out.println("Found files:");
      for (File file : matchingFiles) {
        System.out.println(file.getAbsolutePath());
      }
    }
    return matchingFiles;
  }

  // Helper method for recursion
  private static void searchHelper(File directory, String fileName, List<File> matchingFiles) {
    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isDirectory()) {
            searchHelper(file, fileName, matchingFiles);
          } else if (file.getName().equals(fileName)) {
            matchingFiles.add(file);
          }
        }
      }
    }
  }

  // public static void main(String[] args) {
  //   String name ="Meme.java";
  //   search(name);

  // }
}