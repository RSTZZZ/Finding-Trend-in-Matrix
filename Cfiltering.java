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
package a0;

import java.text.DecimalFormat;

public class Cfiltering {
  // this is a 2d matrix i.e. user*movie
  private int userMovieMatrix[][];
  // this is a 2d matrix i.e. user*user
  private float userUserMatrix[][];
  // this keeps track of total number of users
  private int userNum;
  // this keeps track of total movies
  private int movieNum;

  /**
   * Default Constructor.
   */
  public Cfiltering() {
    // this is 2d matrix of size 1*1
    userMovieMatrix = new int[1][1];
    // this is 2d matrix of size 1*1
    userUserMatrix = new float[1][1];
    // by default, these will be one each
    userNum = 1;
    movieNum = 1;
  }

  /*
   * TODO:COMPLETE THIS I.E. APPROPRIATELY CREATE THE userMovieMatrix AND
   * userUserMatrix WITH CORRECT DIMENSIONS.
   */
  /**
   * Constructs an object which contains two 2d matrices, one of size
   * users*movies which will store integer movie ratings and one of size
   * users*users which will store float similarity scores between pairs of
   * users.
   * 
   * @param numberOfUsers Determines size of matrix variables.
   * @param numberOfMovies Determines size of matrix variables.
   */
  public Cfiltering(int numberOfUsers, int numberOfMovies) {
    // create the proper 2D matrix for userMovieMatrix
    userMovieMatrix = new int[numberOfUsers][numberOfMovies];
    // create the proper 2D matrix for userUserMatrix
    userUserMatrix = new float[numberOfUsers][numberOfUsers];
    userNum = numberOfUsers;
    movieNum = numberOfMovies;
  }

  /**
   * The purpose of this method is to populate the UserMovieMatrix. As input
   * parameters it takes in a rowNumber, columnNumber and a rating value. The
   * rating value is then inserted in the UserMovieMatrix at the specified
   * rowNumber and the columnNumber.
   * 
   * @param rowNumber The row number of the userMovieMatrix.
   * @param columnNumber The column number of the userMovieMatrix.
   * @param ratingValue The ratingValue to be inserted in the userMovieMatrix
   */
  public void populateUserMovieMatrix(int rowNumber, int columnNumber,
      int ratingValue) {

    userMovieMatrix[rowNumber][columnNumber] = ratingValue;
  }

  /**
   * The purpose of this helper method is to find the Euclidean distance score.
   * As input parameters, it takes in a user1 and user2. From the parameters
   * taken, it will then calculate the Euclidean distance using the appropriate
   * formula.
   * 
   * @param user1 The rating of values in userMovieMatrix taken by its row #
   * @param user2 The rating of values in userMovieMatrix taken by its row #
   * @return Returns a value with type double, represents the distance
   */
  private double distance(int user1, int user2) {
    // initialize tempval and sum as double
    double tempval, sum;
    // initialize x as integer
    int x;
    // give tempval and sum the value 0
    tempval = sum = 0;
    // continuous loop to go down the row of userMovieMatrix
    // first find the difference, then put it in tempval
    for (x = 0; x < movieNum; x++) {
      sum = (userMovieMatrix[user1][x] - userMovieMatrix[user2][x]);
      tempval += (sum * sum);
    }
    // we square root the end result
    return Math.sqrt(tempval);
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC. Add/remove
   * 
   * @param AND
   * 
   * @return as required below.
   */
  /**
   * Determines how similar a pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   * 
   * @param user1 The first user, whichever row it is inside the matrix
   * @param user2 The second user, whichever row it is inside the matrix
   * 
   */
  private void similarityScore(int user1, int user2) {
    // initialize tempval
    double tempval;
    // the value of the distance is 1 if user1 and user2 is the same
    // not the same, use the formula given
    if (user1 == user2) {
      tempval = 1.0;
    } else {
      tempval = 1 / (1 + distance(user1, user2));
    }
    // add this value to both sides of the matrix
    userUserMatrix[user1][user2] =
        userUserMatrix[user2][user1] = (float) tempval;
  }

  /**
   * Determines how similar each pair of users is based on their ratings. This
   * similarity value is represented with with a float value between 0 and 1,
   * where 1 is perfect/identical similarity. Stores these values in the
   * userUserMatrix.
   */
  public void calculateSimilarityScore() {
    // initialize the variables x and y as integers
    int x, y;
    // conditional loop with increment of one
    // we make y = x, so as to be efficient
    // calculate similarity score for each different user
    // x represents user1 and y represents user2
    // we only don't need repeats of the same pair of users
    for (x = 0; x < userNum; x++) {
      for (y = x; y < userNum; y++) {
        similarityScore(x, y);
      }
    }
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */
  /**
   * Prints out the similarity scores of the userUserMatrix, with each row and
   * column representing each/single user and the cell position (i,j)
   * representing the similarity score between user i and user j.
   */

  public void printUserUserMatrix() {
    // initialize variables x and y as integers
    int x, y;
    // print the opening message
    System.out.println("userUserMatrix is:");
    // conditional loop with increment of one
    for (x = 0; x < userNum; x++) {
      // print the [ part
      System.out.print("[");
      // conditional loop, go through each element in each row
      for (y = 0; y < userNum; y++) {
        // convert what was calculated into 0.000 format
        float f1 = userUserMatrix[x][y];
        DecimalFormat df = new DecimalFormat("0.0000");
        System.out.print(df.format(f1) + ',' + ' ');
      }
      // print the closing ]
      System.out.println("]");
    }

  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */

  /**
   * Determines the max similarity score inside the userUserMatrix. To keep
   * track of the number of repeats, we add the number of repeats to the max
   * score. This means that max score is actually from 0.000 to 1.000. If it is
   * greater, it means we minus how much to get how many repeated.
   */
  private float findMaxScore() {
    // initialize variables x and y as integers
    // the base value as 0.0, since we are finding the max score
    // z is used to count how many repeats
    int x, y;
    float base = 0.0f;
    float z = 0.0f;
    // go throuah each new similarity score
    for (x = 0; x < userNum; x++) {
      for (y = x + 1; y < userNum; y++) {
        // if greater, change the base value, the counter as 0
        if (userUserMatrix[x][y] > base) {
          base = userUserMatrix[x][y];
          z = 0;
          // if same, add one to counter
        } else if (userUserMatrix[x][y] == base) {
          z++;
        }
      }
    }
    // add z to the base
    base += z;
    return base;
  }

  /**
   * This function finds and prints similarity scores
   *
   * @param base this is the max or minimum score
   */

  private void printSimilarityScores(float base) {
    // initialize variables x, y and z
    int x, y, z;
    // z is the counter for how many repeats there are
    z = 0;
    // find how many repeats there are
    while (base > 1) {
      base--;
      z++;
    }
    // go through each pair of users
    for (x = 0; x < userNum; x++) {
      for (y = x + 1; y < userNum; y++) {
        // only print the user if they are of the same similarity score
        if (userUserMatrix[x][y] == base) {
          System.out.print("User" + (x + 1) + " and " + "User" + (y + 1));
          // next line, if no repeat
          if (z == 0) {
            System.out.println();
            // add a comma, then new line if repeats
          } else if (z > 0) {
            System.out.println(',');
            z--;
          }
        }
      }
    }
    // add the last part about similarity score
    DecimalFormat df = new DecimalFormat("0.0000");
    System.out.println("with similarity score of " + df.format(base));
  }

  /**
   * This function finds and prints the most similar pair of users.
   *
   */

  public void findAndprintMostSimilarPairOfUsers() {
    // print the opening message
    System.out.println(
        "The most similar pairs of users from above" + " userUserMatrix are:");
    // find the maximum score
    float base = findMaxScore();
    // print the similarity score
    printSimilarityScores(base);
  }

  /*
   * TODO:COMPLETE THIS YOU ARE FREE TO CHANGE THE FUNCTION SIGNATURE BUT DO NOT
   * CHANGE THE FUNCTION NAME AND DO NOT MAKE THIS FUNCTION STATIC
   */

  /**
   * Determines the min similarity score inside the userUserMatrix. To keep
   * track of the number of repeats, we add the number of repeats to the min
   * score. This means that min score is actually from 0.000 to 1.000. If it is
   * greater, it means we minus how much to get how many repeated.
   */
  private float findMinScore() {
    // initialize variables x and y as integers
    int x, y;
    // intialize the base score as 2, so as to find the right min
    float base = 2.0f;
    // initialize z as the counter for number of repeats
    float z = 0.0f;
    // loop through each pair of users, x as user1, y as user2
    for (x = 0; x < userNum; x++) {
      for (y = x + 1; y < userNum; y++) {
        // if simlarity score is less than, replace base, and counter z as 0
        if (userUserMatrix[x][y] < base) {
          base = userUserMatrix[x][y];
          z = 0;
          // if equal, add one to the counter z
        } else if (userUserMatrix[x][y] == base) {
          z++;
        }
      }
    }
    // add z to the base
    base += z;
    return base;
  }

  /**
   * This function finds and prints the most dissimilar pair of users in the
   * userUserMatrix.
   * 
   */

  public void findAndprintMostDissimilarPairOfUsers() {
    // print opening message
    System.out.println("The most dissimilar pairs of users from above"
        + " userUserMatrix are:");
    // find minimum score
    float base = findMinScore();
    // print minimum score
    printSimilarityScores(base);
  }

}

