import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab_2 {
    
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path of the test case: ");
        String inputFilePath = scanner.nextLine();

        File inputFile = new File(inputFilePath);
        Scanner fileScanner = new Scanner(inputFile);
    
        String[] split = fileScanner.nextLine().split(" ");
        int[] input = new int[split.length];
        
        for(int i = 0; i < split.length; i++){
            input[i] = Integer.parseInt(split[i]);
        }

        int[] mergeInput = input.clone();
        int[] inversionInput = input.clone();

        long startTime1 = System.nanoTime();
        insertionSort(input);
        long endTime1 = System.nanoTime();
        long executionTime1 = endTime1 - startTime1;
        invCountInsertion(inversionInput);
        System.out.println("Insertion sort took: " + executionTime1 + " nanoseconds.");

        long startTime2 = System.nanoTime();
        mergeSort(mergeInput, 0, input.length - 1);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("Merge sort has "+String.valueOf(mergeNumInv)+" inversions");
        System.out.println("Merge sort took: " + executionTime2 + " nanoseconds.");

        print(input);

        fileScanner.close();
        scanner.close(); 
    }

    public static void print(int[] input){
        for(int i = 0; i < input.length; ++i){
            System.out.print(input[i] + " ");
        }
    }

    public static void insertionSort(int[] input){
        
        for(int i = 1; i < input.length; ++i){
            int target = input[i];
            int j = i - 1;           
            while(j >= 0 && input[j] > target){
                input[j + 1] = input[j];
                j = j - 1;
            }
            input[j + 1] = target;
        }       
        return;
    }

    public static void mergeSort(int[] input, int left, int right){

        if(left < right){

            int mid = left+(right-left)/2;

            mergeSort(input, left, mid);
            mergeSort(input, mid + 1, right);

            merge(input, left, mid, right);
        }
    }
    public static long mergeNumInv = 0;

    public static void merge(int[] input, int left, int mid, int right){

        int size1 = mid - left + 1;
        int size2 = right - mid;
        int[] lSide = new int[size1];
        int[] rSide = new int[size2];

        for(int i = 0; i < size1; ++i){
            lSide[i] = input[left + i];
        }for(int j = 0; j < size2; ++j){
            rSide[j] = input[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while(i < size1 && j < size2){
            if(lSide[i] <= rSide[j]){
                input[k] = lSide[i];
                i++;
            }else{
                input[k] = rSide[j];
                mergeNumInv += lSide.length - i;
                j++; 
            }
            k++;
        }

        while(i < size1){
            input[k] = lSide[i];
            i++;
            k++;
        }while(j < size2){
            input[k] = rSide[j];
            mergeNumInv += lSide.length - i;
            j++;
            k++;
        }
    return;
}

    public static long invCountInsertion(int[] input){
        long numInv = 0;
        for(int i = 0; i < input.length - 1; i++){
            for(int j = i + 1; j < input.length; j++){
                if(input[i] > input[j]){
                    numInv++;
                }
            }
        }
        System.out.println("Insertion sort has "+numInv+" inversions");
        return numInv;
    }

}
