package algsex.chapter1.section3;

import java.util.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import edu.princeton.cs.algs4.*;

// 1.3.43 Listing files. A folder is a list of files and folders. Write a program that takes the
// name of a folder as a command-line argument and prints out all of the files contained
// in that folder, with the contents of each folder recursively listed (indented) under that
// folder’s name. Hint: Use a queue, and see java.io.File.
public class Exercise43 {
  private static final String PROJECT_ROOT = System.getenv("PROJECT_ROOT");
  private static final Path PROJECT_ROOT_PATH = Paths.get(PROJECT_ROOT);

  public static void main(String[] args) {
    if (args.length > 0) readContents(args[0]);
    else runTests();
  }

  private static void runTests() {
    // String testFolderPath = PROJECT_ROOT_PATH.resolve("data/some_folder").toString();

    // assert readContents(testFolderPath).equals(
    //   "some_folder\n" +
    //   "  some_sub_folder\n" +
    //   "    some_sub_sub_folder\n" +
    //   "      file4.bogus\n" +
    //   "    file2.bogus\n" +
    //   "    file4.bogus\n" +
    //   "  file1.bogus\n"
    // );
  }

  private static void readContents(String path) {
    File root = new File(path);
    readContents(root);
  }

  private static void readContents(File file) {
    readContents(file, 0);
  }

  private static void readContents(File file, int depth) {
    if (!file.isDirectory()) {
      for (int i = 0; i < depth; i++) StdOut.print("  ");
      StdOut.println(file);
      return;
    }

    for (File subFile : file.listFiles()) readContents(subFile, depth + 1);
  }
}
