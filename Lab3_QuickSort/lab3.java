import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

    public class lab3{

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
    
            int n = input.length;
    
            long startTime2 = System.nanoTime();
            quickSort(input, 0, n - 1);
            long endTime2 = System.nanoTime();
            long executionTime2 = endTime2 - startTime2;
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

        public static void quickSort(int[] input, int l, int r){

            if(l < r){

                int index = partition(input, l, r);

                quickSort(input, l, index - 1);
                quickSort(input, index + 1, r);
            }   

        }

        public static int partition(int []input, int l, int r){

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

        public static void swap(int[] input, int i, int j){

            int t = input[i];
            input[i] = input[j];
            input[j] = t;
        }

    }