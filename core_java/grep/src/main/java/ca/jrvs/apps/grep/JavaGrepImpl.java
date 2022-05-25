package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JavaGrepImpl implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImpl javaGrepImpl = new JavaGrepImpl();
    javaGrepImpl.setRegex(args[0]);
    javaGrepImpl.setRootPath(args[1]);
    javaGrepImpl.setOutFile(args[2]);

    try {
      javaGrepImpl.process();
    } catch (Exception ex) {
      javaGrepImpl.logger.error("Error: Unable to process", ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    for (File file : listFiles(getRootPath())) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File dir = new File(rootDir);
    File[] filesInDir = dir.listFiles();
    List<File> listedFiles = new ArrayList<>();

    if (filesInDir == null) {
      return listedFiles;
    }

    for (File file : filesInDir) {
      if (file.isDirectory()) {
        List<File> recListedFiles = listFiles(file.getAbsolutePath());
        listedFiles.addAll(recListedFiles);
      } else {
        listedFiles.add(file);
      }
    }

    return listedFiles;
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> retrievedLines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {

      String sCurrentLine;
      while ((sCurrentLine = br.readLine()) != null) {
        retrievedLines.add(sCurrentLine);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return retrievedLines;
  }

  @Override
  public boolean containsPattern(String line) {
    return line.matches(regex);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {

    File file = new File(getOutFile());
    FileOutputStream out = new FileOutputStream(file, true);
    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
    try {
      for (String line:lines) {
        writer.write(line);
        writer.newLine();
      }
      writer.close();
    } catch (IOException e) {
      throw new IOException();
    }
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
