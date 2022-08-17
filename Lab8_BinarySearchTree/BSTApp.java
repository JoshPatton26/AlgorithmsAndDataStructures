import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;

class Node{
    long key;
    Node left, right;
}

class BST{

    public Node createNewNode(long val){
        Node a = new Node();
        a.key = val;
        a.left = null;
        a.right = null;
        return a;
    }

    public Node insert(Node r, long k){
        if(r == null){
            return createNewNode(k);
        }
        if(k < r.key){
            r.left = insert(r.left, k);
        }else if(k > r.key){
            r.right = insert(r.right, k);
        }
        return r;
    }

    public void inorder(Node x){
        if(x != null){
            inorder(x.left);
            System.out.print(x.key+" ");
            inorder(x.right);
        }
    }

    public Node search(Node x, long k){
        if(x == null || k == x.key){
            return x;
        }
        if(k < x.key){
            return search(x.left, k);
        }else{
            return search(x.right, k);
        }       
    }

    public int max(int l, int r){
        if(r > l){
            return r;
        }else{
            return l;
        }
    }

    public int height(Node root){
        if(root != null){
            int l = height(root.left);
            int r = height(root.right);
            return max(l+1, r+1);
        }else{
            return -1;
        }
    }        
}

public class BSTApp{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner scanner = new Scanner(System.in);
        BST tree = new BST();
        BST tree2 = new BST();
        Node root = null;

        System.out.print("Enter the file path of the input case: ");
        String inputFilePath = scanner.nextLine();
        Scanner fileScanner = new Scanner(new File(inputFilePath));
        /*
        System.out.print("Enter the file path of the search case: ");
        String searchFilePath = scanner.nextLine();
        Scanner fileScanner2 = new Scanner(new File(searchFilePath));
        */
        ArrayList<Integer> arrList = new ArrayList<>();        

        long startTime = System.nanoTime();
        while (fileScanner.hasNextLong()) {
            long num = fileScanner.nextLong();
            root = tree.insert(root, num);
            arrList.add((int) num);            
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        System.out.println("\nBuilding time of BST: " + executionTime/1000000  + " miliseconds.");

        //tree.inorder(root);

        long height = tree.height(root);
        System.out.println("Height of BST: "+height);

        //Found from:
        //https://www.geeksforgeeks.org/collections-shuffle-method-in-java-with-examples/
        Collections.shuffle(arrList);

        long startTime2 = System.nanoTime();
        for(int i = 0; i < arrList.size(); i++){
            root = tree2.insert(root, i);
        }
        long endTime2 = System.nanoTime();
        long executionTime2 = endTime2 - startTime2;
        System.out.println("\nBuilding time of shuffled BST: " + executionTime2/1000000  + " miliseconds.");

        //tree2.inorder(root);
        
        //long shufHeight = tree2.height(root);
        //System.out.println("Height of shuffled BST: "+shufHeight);


        fileScanner.close();
        scanner.close();       
    }
}
