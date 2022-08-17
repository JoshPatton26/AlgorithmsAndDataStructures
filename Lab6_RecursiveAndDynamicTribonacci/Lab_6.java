import java.util.Arrays;
import java.util.Scanner;

public class Lab_6{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int n = scanner.nextInt();

        //Recursive Approach
        long startTime = System.nanoTime();
        int result = tribRecursion(n);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("***Recursive approach*** \nIndex: "+result+ 
                            "\nRuntime: "+executionTime +"\n");

        //Dynamic Programming Approach
        long startTime2 = System.nanoTime();
        int result2 = tribDynamic(n);
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("***Dynamic programming approach*** \nIndex: "+result2+ 
                            "\nRuntime: "+executionTime2 +"\n");
        
        System.out.println("Binary representation of input value: "+n);
        bitValue(n);
        
    }

    public static int tribDynamic(int n){
        //Base cases.
        if(n == 0){
            return 0;
        }else if(n == 1 || n == 2){
            return 1;
        }
        //New array for bottom up approach
        int[] array = new int[n+1];

        //initializing the first three numbers of tribonacci
        array[0] = 0;
        array[1] = 1;
        array[2] = 1;
        //Loop through array and get sequence for tribonacci
        for(int i = 3; i <= n; i++){
            array[i] = array[i-1] + array[i-2] + array[i-3];  
        }
        return array[n];
    }

    public static int tribRecursion(int n){
        //Base cases.
        if(n == 0){
            return 0;
        }else if(n == 1 || n == 2){
            return 1;
        }
        //Recursively call tribRecursion function to get the sequenece for tribonacci
        return tribRecursion(n-1) + tribRecursion(n-2) + tribRecursion(n-3);
    }

    public static void bitValue(int n){
        //New array to hold output values.
        int bitArray[] = new int[n+1];

        //Loops through array using method learned from CS330 to convert decimal to binary.
        for(int i=0; i <= n; i++){
            int count = 0;
            int j = i;
            //While j is greater than 0, check for remainders and add to count if true.
            while(j != 0){
                if(j % 2 == 1){
                    count++;
                }
                //Divide by 2 before going back through loop.
                j = j/2;
            }
            bitArray[i] = count;
        }
        for(int i=0; i < bitArray.length; i++){
            System.out.print(bitArray[i]+" ");
        }
    }
}
