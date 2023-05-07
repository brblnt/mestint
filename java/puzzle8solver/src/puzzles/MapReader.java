package puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapReader {

  public static List<String> readTextFile(String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    StringBuilder stringBuilder = new StringBuilder();
    List<String> lines = new ArrayList<>();
    String line = "";
    try {
      while (( line = reader.readLine()) != null) {
        lines.add(line);
      }
      return lines;
    } finally {
      reader.close();
    }
  }


}
