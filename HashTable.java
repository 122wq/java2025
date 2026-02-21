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
            //To access the first element of, the posList starts at -1, so when the first element is accessed, it will be increased to 0, the other aeeays will start at 0 as they will be already accessed when the postArr changed
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
        HashTable hashTable = new HashTable();

        String key1 = "FB";
        String key2 = "Ea";

        System.out.println("key1 hash: " + key1.hashCode());
        System.out.println("key2 hash: " + key2.hashCode());

        if (key1.hashCode() != key2.hashCode())
        {
            System.out.println("[FAIL] keys do not collide");
            return;
        }

        hashTable.put(key1);
        hashTable.put(key2);

        boolean get1Ok = key1.equals(hashTable.get(key1));
        boolean get2Ok = key2.equals(hashTable.get(key2));

        if (get1Ok && get2Ok)
        {
            System.out.println("[PASS] collision get test");
        }
        else
        {
            System.out.println("[FAIL] collision get test");
        }

        boolean sameArrayListOk = verifySameCollisionBucket(hashTable, key1, key2);
        if (sameArrayListOk)
        {
            System.out.println("[PASS] collision same-bucket ArrayList test");
        }
        else
        {
            System.out.println("[FAIL] collision same-bucket ArrayList test");
        }

        Iterator iterator = hashTable.keys();
        Set<String> iteratedKeys = new HashSet<String>();
        while (iterator.hasNext())
        {
            iteratedKeys.add((String) iterator.next());
        }

        boolean iteratorOk = iteratedKeys.contains(key1) && iteratedKeys.contains(key2);
        if (iteratorOk)
        {
            System.out.println("[PASS] collision iterator test");
        }
        else
        {
            System.out.println("[FAIL] collision iterator test");
        }

        HashTable iteratorTestTable = new HashTable();
        iteratorTestTable.put(key1);
        iteratorTestTable.put(key2);
        iteratorTestTable.put(key1);

        boolean iteratorPrintsEveryElementOk = verifyIteratorPrintsEveryElementInCollisionBucket(iteratorTestTable, key1, key2);
        if (iteratorPrintsEveryElementOk)
        {
            System.out.println("[PASS] iterator prints every element in collision ArrayList test");
        }
        else
        {
            System.out.println("[FAIL] iterator prints every element in collision ArrayList test");
        }

        String removed = hashTable.remove(key1);
        boolean removeOk = key1.equals(removed) && hashTable.get(key1) == null && key2.equals(hashTable.get(key2));

        if (removeOk)
        {
            System.out.println("[PASS] collision remove test");
        }
        else
        {
            System.out.println("[FAIL] collision remove test");
        }
    }

    private static boolean verifySameCollisionBucket(HashTable hashTable, String key1, String key2)
    {
        try
        {
            java.lang.reflect.Field arrayField = HashTable.class.getDeclaredField("arr");
            arrayField.setAccessible(true);

            ArrayList<String>[] buckets = (ArrayList<String>[]) arrayField.get(hashTable);
            int index1 = Math.floorMod(key1.hashCode(), buckets.length);
            int index2 = Math.floorMod(key2.hashCode(), buckets.length);

            if (index1 != index2)
            {
                return false;
            }

            ArrayList<String> collisionBucket = buckets[index1];
            if (collisionBucket == null)
            {
                return false;
            }

            return collisionBucket.contains(key1) && collisionBucket.contains(key2) && buckets[index1] == buckets[index2];
        }
        catch (Exception exception)
        {
            return false;
        }
    }

    private static boolean verifyIteratorPrintsEveryElementInCollisionBucket(HashTable hashTable, String key1, String key2)
    {
        try
        {
            java.lang.reflect.Field arrayField = HashTable.class.getDeclaredField("arr");
            arrayField.setAccessible(true);

            ArrayList<String>[] buckets = (ArrayList<String>[]) arrayField.get(hashTable);
            int bucketIndex = Math.floorMod(key1.hashCode(), buckets.length);
            ArrayList<String> collisionBucket = buckets[bucketIndex];
            if (collisionBucket == null)
            {
                return false;
            }

            int expectedKey1Count = 0;
            int expectedKey2Count = 0;
            for (String value : collisionBucket)
            {
                if (key1.equals(value))
                {
                    expectedKey1Count++;
                }
                if (key2.equals(value))
                {
                    expectedKey2Count++;
                }
            }

            int iteratedKey1Count = 0;
            int iteratedKey2Count = 0;
            Iterator iterator = hashTable.keys();
            while (iterator.hasNext())
            {
                String nextValue = (String) iterator.next();
                if (key1.equals(nextValue))
                {
                    iteratedKey1Count++;
                }
                if (key2.equals(nextValue))
                {
                    iteratedKey2Count++;
                }
            }

            return iteratedKey1Count == expectedKey1Count && iteratedKey2Count == expectedKey2Count;
        }
        catch (Exception exception)
        {
            return false;
        }
    }
}
