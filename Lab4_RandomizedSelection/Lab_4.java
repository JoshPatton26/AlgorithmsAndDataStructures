import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Lab_4{

    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path of the test case: ");
        String inputFilePath = scanner.nextLine();
        int lineCount = 0, totalLines = 0; 

        File inputFile = new File(inputFilePath);
        Scanner fileScanner = new Scanner(inputFile);

        ArrayList<Integer> statuses = new ArrayList<Integer>();

        while (fileScanner.hasNextLine()) {
            int line = fileScanner.nextInt();
            if (lineCount == 0) {
                totalLines = line;
            } else {
                statuses.add(line);
            }
            lineCount++;
        }

        Integer[] input = statuses.toArray(new Integer[statuses.size()]);
        Integer[] quickSortInput = input.clone();
        Integer[] randomInput = input.clone();

        int n = input.length, k = input.length;

        long startTime1 = System.nanoTime();
        int insertionMedian = insertionSort(input);
        long endTime1 = System.nanoTime();
        long executionTime1 = endTime1 - startTime1;
        System.out.println("Insertion sort took: " + executionTime1 + " nanoseconds.");
        System.out.println("Lower median of insertion sort is: " + insertionMedian);
        System.out.println("________________________________________");

        long startTime2 = System.nanoTime();
        int quickMedian = quickSort(quickSortInput, 0, n - 1);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Quick sort took: " + executionTime2 + " nanoseconds.");
        System.out.println("Lower median of quick sort is: " + quickMedian);
        System.out.println("________________________________________");

        long startTime3 = System.nanoTime();
        int randomMedian = randomizedSelect(randomInput, 0, n - 1, k);
        long endTime3 = System.nanoTime();
        long executionTime3 = endTime3 - startTime3;
        System.out.println("Random selection took: " + executionTime3 + " nanoseconds.");
        System.out.println("Lower median of randomized selection is: " + randomMedian);
        System.out.println("________________________________________");

        //print(input);

        fileScanner.close();
        scanner.close();

    }

    public static void print(Integer[] input){
        for(int i = 0; i < input.length; ++i){
            System.out.print(input[i] + " ");
        }
    }
    /*
        Imported from my previous assignment: Lab_2
        Sources for insertionSort()
        https://www.geeksforgeeks.org/insertion-sort/
        https://www.javatpoint.com/insertion-sort-in-java
        https://www.educative.io/courses/algorithms-coding-interviews-java/B62q79Pn2AJ
    */
    public static int insertionSort(Integer[] input){
        
        for(int i = 1; i < input.length; ++i){
            int target = input[i];
            int j = i - 1;           
            while(j >= 0 && input[j] > target){
                input[j + 1] = input[j];
                j = j - 1;
            }
            input[j + 1] = target;
        }
        int median = input[(input.length / 2) - 1];       
        return median;
    }
    /*
        Imported from my previous assignment: Lab_3
        Sources for quickSort(), partition(), and swap(): 
        https://www.geeksforgeeks.org/quick-sort/
        https://www.softwaretestinghelp.com/quicksort-in-java/
    */
    public static int quickSort(Integer[] input, int l, int r){

        if(l < r){

            int index = partition(input, l, r);

            quickSort(input, l, index - 1);
            quickSort(input, index + 1, r);
        }   
        int median = input[(input.length / 2) - 1];
        return median;
    }
    /*
        Imported from my previous assignment: Lab_3
        Sources cited above quickSort()
    */
    public static int partition(Integer []input, int l, int r){

        int pivot = input[r];
        int i = (l-1);

        for(int j = l; j <= r; j++){

            if(input[j] < pivot){
                i++;
                swap(input, i, j);
            }               
        }
        swap(input, i + 1, r);
        return (i+1);
    } 
    /*
        Imported from my previous assignment: Lab_3
        Sources cited above quickSort()
    */
    public static void swap(Integer[] input, int i, int j){

        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
    /*
        randomizedSelect(), and swap() modeled from my quicksort functions
        randomPartition() created from Pseudocode in chapter 9, with a little help from TA.
    */
    public static int randomizedSelect(Integer[] input, int l, int r, int k){ 
        if(l < r){
            int pivot = randomPartition(input, l, r, k);

            randomizedSelect(input, l, pivot - 1, k);
            randomizedSelect(input, pivot + 1, r, k);
        }

        int median = input[(input.length / 2) - 1];
        return median;
    }
    /*
        Sources cited above randomizedSelect().
    */
    public static int randomPartition(Integer[] input, int l, int r, int k){
        int pivot = input[l];
        int i = l;
        int j = r + 1;

        while(true){
            while(input[++i] < pivot){
                if(i == r){
                    break;
                }
            }
            while(input[--j] > pivot){
                if(j == l){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            rSwap(input, i, j);
        }
        rSwap(input, l, j);

        return j;
    }
    /*
        Sources cited above randomizedSelect().
    */
    public static void rSwap(Integer[] input, int i, int j){
        int t = input[i];
        input[i] = input[j];
        input[j] = t;
    }
}