package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImpl extends JavaGrepImpl {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImpl javaGrepLambdaImpl = new JavaGrepLambdaImpl();
    javaGrepLambdaImpl.setRegex(args[0]);
    javaGrepLambdaImpl.setRootPath(args[1]);
    javaGrepLambdaImpl.setOutFile(args[2]);

    try {
      javaGrepLambdaImpl.process();
    } catch (Exception ex) {
      javaGrepLambdaImpl.logger.error("Error: Unable to process", ex);
    }
  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = listFiles(getRootPath()).stream()
        .map(this::readLines)
        .flatMap(Collection::stream)
        .filter(this::containsPattern)
        .collect(Collectors.toList());

    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File dir = new File(rootDir);
    File[] filesInDir = dir.listFiles();

    if (filesInDir == null) {
      return new ArrayList<>();
    }

    List<File> recListedFiles = Arrays.stream(filesInDir).filter(File::isDirectory)
        .map(file -> listFiles(file.getAbsolutePath()))
        .flatMap(List::stream)
        .collect(Collectors.toList());

    return Stream.concat(Arrays.stream(filesInDir).filter(file -> !file.isDirectory()),
            recListedFiles.stream())
        .collect(Collectors.toList());
  }

  @Override
  public List<String> readLines(File inputFile) {

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
      return br.lines().collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }
}
