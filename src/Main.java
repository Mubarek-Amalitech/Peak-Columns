import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter  the number of rows for the matrix :");
        int Rows = scanner.nextInt();
        System.out.println("enter  the number of columns for the matrix");
        int Columns = scanner.nextInt();
        int[][] Matrix = new int[Rows][Columns];
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                /*
                 * the deliberate addition of 1 to the indexes is to make it more human inherent or readable.
                 * thus for instance "(0,0)"   will become " (1,1)" which is more inherent to the
                 * way humans  read matrices.
                 */
                System.out.println(" enter value at (" + (i + 1) + "," + (j + 1) + ")");
                Matrix[i][j] = scanner.nextInt();
            }


        }
        System.out.println(" the matrix you entered is ");
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                System.out.print(Matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("The peak columns are :");


        peakColumns(Matrix, Rows, Columns);
    }

    public static void peakColumns(int[][] Matrix, int nRows, int nColumns) {
        List<Integer> peakIndexes = new ArrayList<>(); // an empty arraylist to store indexes for peak columns.
        List<Integer> peakNumbers = new ArrayList<>(); // an empty arraylist to store values for peal columns.


        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nColumns; j++) {
                /* Here we start by checking if a value is the maximum in its column
                 * by default, we assume the value at the beginning index is the largest
                 * until we encounter any value greater than it during the iteration then
                 * the flag is set to false. if we go though all the iterations  and do
                 * not encounter any values larger than it,then it is evidently the largest
                 *  in the column.
                 * Note that inner loop  where we  have another "col" is
                 * to make sure for each value we  begin comparing it the column value from the beginning index to the
                 * end.

                 *
                 */
                boolean isMaxInColumn = true;

                for (int col = 0; col < nColumns; col++) {



                    if (Matrix[i][j] < Matrix[i][col]) {
                        isMaxInColumn = false;
                        break;
                    }
                }
                // same logic for checking the maximum column is applied here.
                boolean isMaxInRow = true;
                for (int row = 0; row < nRows; row++) {
                    if ((Matrix[i][j] > Matrix[row][j])) {
                        isMaxInRow = false;
                        break;
                    }

                }


                if (isMaxInRow && isMaxInColumn) {
                    /* if the two flags managed to stay  true throughout the iteration, then it is
                     * a peak column and hence added to the list.
                     * NB: it appears in the question, the matrix indexing was
                     * considered in the mathematical context and hence the indexing starts
                     * at 1, in code however ,  indexing starts at 0
                     * so what you see here is 1 being appended to account for the mismatch in indexes.
                     */
                    peakIndexes.add(i + 1);
                    peakIndexes.add(j + 1);
                    peakNumbers.add(Matrix[i][j]);

                }


            }
        }
        if (peakIndexes.isEmpty()) {
            System.out.println("no peak columns were found");
        } else {
            /*
             * so beginning  at the position k=0 here we iterate through the stored  peak indexes in the array list,
             * since we are aware each peak index is a combination of two pairs, i,j
             * we traverse the  list in step sizes of two. the next index pair will be the
             * current position k+1  and so on.
             * notice that for every  peak index pair , there is only one entry
             * for the corresponding peak  value
             * and hence  the reason why we divide the  position k/2 to fetch the  peak value.
             */
            for (int k = 0; k < peakIndexes.size(); k += 2) {
                int row = peakIndexes.get(k);
                int col = peakIndexes.get(k + 1);
                int value = peakNumbers.get(k / 2);
                System.out.println("(" + row + "," + col + ")" + " = " + value);
            }

        }
    }
}