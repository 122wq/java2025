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
    public void put(String key) {
        int hash = key.hashCode();
        hash = hash % arr.length;

        if (arr[hash] == null)
        {
            arr[hash] = new ArrayList<String>();
        }
        arr[hash].add(key);
        hashSize++;

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

    public String get(String key){
        int hash = key.hashCode() % arr.length;
    
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

    public String remove(String key){
        int hash = key.hashCode() % arr.length;
        //look through the arr[hash] arrayList (if one exists) and return key if you find it. return null if it's not in there
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
//
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
            posList = -1;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            int currPos = posArr;
            if (posArr < arr.length)
            {
                if (arr[posArr] != null && (posList + 1) < arr[posArr].size())
                {
                    return true;
                } 
                else
                {
                    //increase currPos if the next array element is null, until it finds a non-null element or it searches the entire array (no next value)
                    while ((currPos + 1) < arr.length && (arr[currPos + 1] == null || arr[currPos + 1].isEmpty()))
                    {
                        currPos++;
                    }
                    //if currPos + 1 is the arr length, it means that the entire array was search and there is no next value that exist, so returns true
                    return((currPos + 1) != arr.length);
                }
            }
            return false;
        }

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
                        //if currPos + 1 is the arr length, it means that the entire array was search and there is no next value that exist, so returns true
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
        
    }
}
