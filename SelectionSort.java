import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Selection Sort.
 *
 * @author Atri Sarker
 * @version 1.0
 * @since 2025-11-10
 */
public final class SelectionSort {
  /**
   * Constant for the file path of the input file.
   */
  private static final String INPUT_FILE_PATH = "./input.txt";

  /**
   * Constant for the file path of the output file.
   */
  private static final String OUTPUT_FILE_PATH = "./output.txt";

  /**
   * Private constructor to satisfy style checker.
   *
   * @exception IllegalStateException for the utility class.
   * @see IllegalStateException
   */
  private SelectionSort() {
    // Prevents illegal states.
    throw new IllegalStateException("Utility class.");
  }

  /**
   * Sorts an array of integers using the selection sort algorithm.
   *
   * @param arr the integer array to be sorted.
   */
  public static void selectionSort(final int[] arr) {
    // Get the array's size
    int arrSize = arr.length;
    // Get the bound for iteration
    int iterationStop = arrSize - 1;
    // Iterate over the bound
    for (int threshold = 0; threshold < iterationStop; threshold++) {
      // Get the smallest number
      // that resides in the unsorted partition
      // Start at the beginning of the unsorted partition.
      int minNumIndex = threshold;
      // Set the first unsorted element as the minimum
      int minNum = arr[minNumIndex];
      // Go through the unsorted partition and get the smallest number.
      for (int index = threshold + 1; index < arrSize; index++) {
        // Get the number found at the index
        int num = arr[index];
        // Check if it is smaller
        if (num < minNum) {
          // If so, make it the new smallest number
          minNum = num;
          minNumIndex = index;
        }
      }
      // Perform the swap
      int temp = arr[threshold];
      arr[threshold] = minNum;
      arr[minNumIndex] = temp;
    }
  }

  /**
   * Entrypoint of the program.
   *
   * @param args UNUSED.
   */
  public static void main(final String[] args) {
    // Copied from 2-01
    try {
      // Access the input file and create a File object.
      File inputFile = new File(INPUT_FILE_PATH);
      // Access the output file and create a FileWriter object.
      FileWriter outputFile = new FileWriter(OUTPUT_FILE_PATH);
      // Scanner that will read the File Object.
      Scanner fileReader = new Scanner(inputFile);
      // Create list to store all the lines
      ArrayList<String> listOfLines = new ArrayList<>();
      // Loop through all available lines
      while (fileReader.hasNextLine()) {
        // Add the line to the list
        listOfLines.add(fileReader.nextLine());
      }
      // Close the file reader
      fileReader.close();
      // Loop through all the lines in the list
      for (String line : listOfLines) {
        // Check if the line is empty
        if (line.equals("")) {
          // If so, write an error message and continue
          outputFile.write("Error: No integers were found on this line.\n");
          continue;
        }
        // Split the line
        String[] stringArr = line.split(" ");
        // Convert String array into an int array
        int[] intArr = new int[stringArr.length];
        for (int index = 0; index < stringArr.length; index++) {
          intArr[index] = Integer.parseInt(stringArr[index]);
        }
        // Print Array Before Sorting
        System.out.print("Before: ");
        for (int num : intArr) {
          System.out.print(num + " ");
        }
        System.out.println();
        // Sort the array
        selectionSort(intArr);
        // Print Array After Sorting
        System.out.print("After: ");
        for (int num : intArr) {
          System.out.print(num + " ");
        }
        System.out.println();
        // Write the sorted array to the output file
        for (int num : intArr) {
          outputFile.write(num + " ");
        }
        // Write a newline
        outputFile.write("\n");
      }
      // Close the FileWriter object
      outputFile.close();
    } catch (IOException error) {
      System.out.println(error);
    }
  }
}
