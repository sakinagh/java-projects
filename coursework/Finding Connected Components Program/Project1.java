import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project1 class for finding connected components in binary images.
 */
public class Project1 {

    // a 2-D array to store each image 
    private static char[][] image;
    private static char currentPixel;
    private static Map<Character, Integer> pixelCount;

    /**
     * Main method to run the connected components labeling algorithm.
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int totalNum = Integer.parseInt(scan.nextLine());

        for(int imageIndex=0; imageIndex<totalNum; imageIndex++) {
            String[] dime = scan.nextLine().split(" ");
            int rows = Integer.parseInt(dime[0]);
            int cols = Integer.parseInt(dime[1]);

            // read and store the image
            if(rows>0 && cols>0) {
                image = new char[rows][cols];
                for(int i=0; i<rows; i++) {
                    String row = scan.nextLine();
                    image[i] = row.toCharArray();
                }
            
                // skip blank line
                scan.nextLine();
                currentPixel = 'a';
                pixelCount = new HashMap<>();
                // process the image and print the result 
                System.out.println();
                processImage(rows, cols); 

            }
        }

        scan.close();

    }

    /**
     * Processes the binary image to find connected components.
     * @param rows The number of rows in the image.
     * @param cols The number of columns in the image.
     */
    private static void processImage(int rows, int cols) {

        // interate through each pixel in the image and if it is '*' then label it accordingly 
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(image[i][j] == '*') {
                    labelConnectedComponent(i, j);
                    currentPixel++;
                }
            }
        }

        printLabeledImage(rows, cols);
        printLabelCount();

    }

    /**
     * Recursively labels connected components in the binary image.
     * @param row The current row index.
     * @param col The current column index.
     */
    private static void labelConnectedComponent(int row, int col) {
        
        // base case: check if the current pixel is a part of the connected component 
        if(row<0 || row>=image.length || col<0 || col>=image[0].length || image[row][col]!='*') {
            return;
        }

        // label the current pixel with a label and update the count of pixels 
        image[row][col] = currentPixel;
        pixelCount.merge(currentPixel, 1, Integer::sum);

        // recursively label the connected pixels in all 4 directions
        labelConnectedComponent(row-1, col); // north
        labelConnectedComponent(row+1, col); // south
        labelConnectedComponent(row, col-1); // west
        labelConnectedComponent(row, col+1); // east

    }

    /**
     * Prints the labeled image.
     * @param rows The number of rows in the image.
     * @param cols The number of columns in the image.
     */
    private static void printLabeledImage(int rows, int cols) {
        
        // print labeled image in corresponding rows and columns 
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }

    }

    /**
     * Prints the count of pixels for each label.
     */
    private static void printLabelCount() {
        
        // created a count map for the pixels
        Map<Integer, Integer> countM = new HashMap<>();
        for(int count : pixelCount.values()) {
            countM.merge(count, 1, Integer::sum);
        }

        // sort the labels for the pixels for counting 
        List<Integer> sortedCount = new ArrayList<>(countM.keySet());
        Collections.sort(sortedCount);

        // print the count of pixels for each label 
        for(int count : sortedCount) {
            System.out.print(count + " " + countM.get(count) + " " + "\n");
        }
    }


}
