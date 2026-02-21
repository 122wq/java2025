import java.util.*;
import java.io.*;

public class HashTable {
    private ArrayList<String>[] arr;
    private int hashSize;

    public HashTable ()
    {
        arr = new ArrayList[67];
        hashSize = 0;
    }

// Methods you have to supply:
//
    //precondition: key is not null
    //postcondition: key is added to this HashTable, hashsize will increase, if the hashtable is more than 2/3 full (indicated by hashsize) it will double the size of the array and rehash all the keys
    public void put(String key) {
        //get the hashcode and mod it to the length of the array
        int hash = key.hashCode();
        hash = Math.abs(hash % arr.length);

        //make a new arrylist if there isn't anything at the arr[hash]
        if (arr[hash] == null)
        {
            arr[hash] = new ArrayList<String>();
        }
        arr[hash].add(key);
        hashSize++;

        //double the array length if it is more than 67% full and and rehash everything
        if ((hashSize / 100.00) > 0.67)
        {
            ArrayList<String>[] newArr = new ArrayList[arr.length * 2];
            hashSize = 0;
            Iterator i = new MyIterator();
            int newHash;
            String newKey;
            
            while (i.hasNext())
            {
                newKey = (String) i.next();
                newHash = (newKey.hashCode()) % arr.length;
                if (newArr[newHash] == null)
                {
                    newArr[newHash] = new ArrayList<String>();
                }
                newArr[newHash].add(key);
                hashSize++;
                
            }
            arr = newArr;   
            
        }
    }
    //precondition: key is not null
    //postcondition: if key is in the hashtable, return the key, return null otherwise
    public String get(String key){
        int hash = Math.abs(key.hashCode() % arr.length);
    
        //look through the arr[hash] arrayList (if one exists) and return key if you find it. return null if it's not in there
        if (arr[hash] != null)
        {
            for (int i = 0; i < arr[hash].size(); i++)
            {
                if (arr[hash].get(i).equals(key))
                {
                    return key;
                }
            }
        }
        return null;
    }
    //Precondition: key is not null
    //Postcondition: key is removed from this hashTable and returned. If key is not in this HashTable, null is returned.
    public String remove(String key){
        int hash = Math.abs(key.hashCode() % arr.length);
        //look through the arr[hash] arrayList (if one exists) and remove and return the key if you find it. return null if it's not in there
        if (arr[hash] != null)
        {
            for (int i = 0; i < arr[hash].size(); i++)
            {
                if (arr[hash].get(i).equals(key))
                {
                    arr[hash].remove(i);
                    hashSize--;
                    return key;
                }
            }
        }
        return null;

	}
//
    public Iterator keys() {
        return new MyIterator();
    }
//  //precondition: none
//  //postcondition: prints the keys in this hahstable by hashcode
    public void print(){
        Iterator i = new MyIterator();
        while (i.hasNext())
        {
            System.out.println(i.next());
        }
	}
	/**
	 * Loads this HashTable from a file named "Lookup.dat".
	 */
    public void load() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        
        // Open the file for reading
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException e) {
            System.err.println("Cannot find input file \"Lookup.dat\"");
        }
        
        // Read the file contents and save in the HashTable
        try {
            while (true) {
                String key = bufferedReader.readLine();
                if (key == null) return;
                String value = bufferedReader.readLine();
                if (value == null) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                String blankLine = bufferedReader.readLine();
                if (!"".equals(blankLine)) {
                    System.out.println("Error in input file");
                    System.exit(1);
                }
                put(key);
            }
        }
        catch (IOException e) {
            e.printStackTrace(System.out);
        }
        
        // Close the file when we're done
        try {
            bufferedReader.close( );
        }
        catch(IOException e) {
            e.printStackTrace(System.out);
        }
    }

	/**
	 * Saves this HashTable onto a file named "Lookup.dat".
	 */
	public void save() {
        FileOutputStream stream;
        PrintWriter printWriter = null;
        Iterator iterator;
        
        // Open the file for writing
        try {
            File f = new File(System.getProperty("user.home"), "Lookup.dat");
            stream = new FileOutputStream(f);
            printWriter = new PrintWriter(stream);
        }
        catch (Exception e) {
            System.err.println("Cannot use output file \"Lookup.dat\"");
        }
       
        // Write the contents of this HashTable to the file
        iterator = keys();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            printWriter.println(key);
            String value = (String)get(key);
            value = removeNewlines(value);
            printWriter.println(value);
            printWriter.println();
        }
       
        // Close the file when we're done
        printWriter.close( );
    }
    
    /**
     * Replaces all line separator characters (which vary from one platform
     * to the next) with spaces.
     * 
     * @param value The input string, possibly containing line separators.
     * @return The input string with line separators replaced by spaces.
     */
    private String removeNewlines(String value) {
        return value.replaceAll("\r|\n", " ");
    }



    private class MyIterator implements Iterator
    {
        private int posArr;
        private int posList; 

        public MyIterator()
        {
            posArr = 0;
            //To access the first element of, the posList starts at -1, so when the first element is accessed, it will be increased to 0, the other aeeays will start at 0 as they will be already accessed when the postArr changed
            posList = -1;
        }
        //precondition: none
        //postcondition: returns true if there is another key in this hashtable, false otherwise
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            int currPos = posArr;
            if (posArr < arr.length)
            {
                //check if the array is no null, then make sure the arrayList has not been fully traversed
                if (arr[posArr] != null && (posList + 1) < arr[posArr].size())
                {
                    return true;
                } 
                else
                {
                    //increase currPos if the next array element is null or everything was deleted, until it finds a non-null element or it searches the entire array (no next value)
                    while ((currPos + 1) < arr.length && (arr[currPos + 1] == null || arr[currPos + 1].isEmpty()))
                    {
                        currPos++;
                    }
                    //if currPos + 1 is the arr length, it means that the entire array was search and  no next value that exist, so returns false
                    return((currPos + 1) != arr.length);
                }
            }
            return false;
        }
        //precondition: none
        //postcondition: returns the next key in this hashtable, return null if there is no next key
        @Override
        public Object next() {
            // TODO Auto-generated method stub
            int currPos = posArr;
            if (hasNext())
            {
                if (posArr < arr.length)
                {
                    if (arr[posArr] != null && (posList + 1) < arr[posArr].size())
                    {
                        posList++;
                        return arr[posArr].get(posList);
                    } 
                    else
                    {
                        //increase currPos if the next array element is null, until it finds a non-null element or it searches the entire array (no next value)
                        while ((currPos + 1) < arr.length && (arr[currPos + 1] == null || arr[currPos + 1].isEmpty()))
                        {
                            currPos++;
                        }
                        posArr = currPos + 1;
                        posList = 0; 
                        //returns the first element of the next non-null and non-empty arrayList elements if the entire array is not traversed
                        if (posArr < arr.length)
                        {
                            return arr[posArr].get(0);
                        }
                    }
                }
            }
            return null;
        }

        @Override
        public void remove()
        {
            return;
        }
    }

}

 class HashTableTest
{
    public static void main(String[] args)
    {
        HashTable hashTable = new HashTable();
        hashTable.put("Hello");
        hashTable.put("World");
        hashTable.put("Java");
        hashTable.put("HashTable");
        hashTable.put("Test");
        System.out.println(hashTable.get("Hello"));
        System.out.println(hashTable.get("World"));
        System.out.println(hashTable.get("Java"));
        System.out.println(hashTable.get("HashTable"));
        System.out.println(hashTable.get("Test"));
        System.out.println(hashTable.get("NotExist"));
        System.out.println(hashTable.remove("Hello"));
        System.out.println(hashTable.get("Hello"));
        hashTable.print();
    }
}
