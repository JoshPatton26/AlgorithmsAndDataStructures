import java.util.*;
import java.util.concurrent.ThreadLocalRandom;



class cs303HashMap{
	static LinkedList<Integer>keyArr[];
	static LinkedList<Integer>valArr[];
	static int size = 0;

		//Taken from code snippet in the announcment on Canvas.    
	    public cs303HashMap() {
			keyArr = new LinkedList[1000];
			valArr = new LinkedList[1000];

			for(int i=0; i < 1000; i++){
				keyArr[i] = new LinkedList<Integer>();
				valArr[i] = new LinkedList<Integer>();
			}
	    }
	    //Taken from code snippet in the announcment on Canvas.    
	    public void put(int key, int value) {
			int hashFunc = key % 1000;
			
			if(keyArr[hashFunc]==null){
				keyArr[hashFunc].add(key);
				valArr[hashFunc].add(value);
				//Increment size when adding a key.
				size++;
			}else if(keyArr[hashFunc].contains(key)==true){
				int index = keyArr[hashFunc].indexOf(key);
				valArr[hashFunc].set(index, value);
			}else{
				keyArr[hashFunc].add(key);
            	valArr[hashFunc].add(value); 
				//Increment size when adding a key.
				size++;  
			}
	    }
		    
	    public int get(int key) {
			//Checks to see in the key is empty
			if(valArr[key].isEmpty()){
				return -1;
			//If the key is empty it returns the value in that key. 
			}else{
				return valArr[key].getLast();
			}
		//return -1;		            
	    }
		        
	    public void remove(int key) {
			//Removes the key and value in that place.
			keyArr[key].remove();
			size--;
	    }

	    public int size(){
			//Returns the value of size.
			return size;
	    }

	    
	    public static void main(String []args) 
	    {
		    cs303HashMap hm = new cs303HashMap();

		    hm.put(1,1);
		    hm.put(1,2);
		    hm.put(1,3);
		    hm.put(1,7);
		    hm.put(1,9);
		    hm.put(1,10);
		    hm.put(1,10000);
		    hm.put(1,10002);
		    
		    hm.put(10000,91);
		    hm.put(1000,110);
		    hm.put(100,1123);
		    hm.put(10,1456);
		    hm.put(1,8765);
			
		    System.out.println(hm.get(1)); // should return 8765
		    System.out.println(hm.get(21)); // should return -1

		    System.out.println(hm.size()); // at this point it should return a size of 5 (corresponding to the keys: 1, 10, 100, 1000, 10000)

		    hm.remove(1);
		    System.out.println(hm.size()); // at this point it should return a size of 4 (corresponding to the keys:  10, 100, 1000, 10000)

		    for (int i=0; i < 1000; i++)
			    hm.put(ThreadLocalRandom.current().nextInt(0, 100000), ThreadLocalRandom.current().nextInt(0, 100000));

		   hm.size();
		

	    }
}


