// **********************************************************
// Assignment0:
// UTORID: yangch66
// UT Student #: 1002064874
// Author: Zachary (Chin Chen) Yang
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences. In this semester
// we will select any three of your assignments from total of 5 and run it
// for plagiarism check.
// *********************************************************
package driver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import a0.Cfiltering;

public class CfilteringDriver {

  /**
   * Reads user movie ratings from a text file, calculates similarity scores and
   * prints a score matrix.
   */
  public static void main(String[] args) {
    try {

      // open file to read
      String fileName;
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the name of input file? ");
      fileName = in.nextLine();
      FileInputStream fStream = new FileInputStream(fileName);
      BufferedReader br = new BufferedReader(new InputStreamReader(fStream));

      // Read dimensions: number of users and number of movies
      int numberOfUsers = Integer.parseInt(br.readLine());
      int numberOfMovies = Integer.parseInt(br.readLine());
      System.out.println();
      System.out.println();

      /*
       * create a new Cfiltering object that contains: a) 2d matrix
       * i.e.userMovieMatrix (#users*#movies) b) 2d matrix i.e. userUserMatrix
       * (#users*#users)
       */
      Cfiltering cfObject = new Cfiltering(numberOfUsers, numberOfMovies);

      // this is a blankline being read
      br.readLine();

      // read each line of movie ratings and populate the
      // userMovieMatrix
      String row;
      // initialize the rownum and colnum to count where in matrix
      int rownum, colnum;
      rownum = colnum = -1;
      while ((row = br.readLine()) != null) {
        // allRatings is a list of all String numbers on one row
        // add one to rownum, make colnum start at -1
        rownum++;
        String allRatings[] = row.split(" ");
        colnum = -1;
        for (String singleRating : allRatings) {
          // add one to colnum
          colnum++;
          // make the String number into an integer
          int rating = Integer.parseInt(singleRating);
          // populate userMovieMatrix
          cfObject.populateUserMovieMatrix(rownum, colnum, rating);
        }
      }
      // close the file
      fStream.close();

      /*
       * COMPLETE THIS ( I.E. CALL THE APPROPRIATE FUNCTIONS THAT DOES THE
       * FOLLOWING)
       */
      // CALCULATE THE SIMILARITY SCORE BETWEEN USERS.
      cfObject.calculateSimilarityScore();
      // PRINT OUT THE userUserMatrix
      cfObject.printUserUserMatrix();
      // print two empty lines
      System.out.println();
      System.out.println();
      // PRINT OUT THE MOST SIMILAR PAIRS OF USER AND THE MOST
      cfObject.findAndprintMostSimilarPairOfUsers();
      // print two empty lines
      System.out.println();
      System.out.println();
      // Dissimilar pair of users
      cfObject.findAndprintMostDissimilarPairOfUsers();
    } catch (FileNotFoundException e) {
      System.err.println("Do you have the input file in the root folder "
          + "of your project?");
      System.err.print(e.getMessage());
    } catch (IOException e) {
      System.err.print(e.getMessage());
    }
  }

}
