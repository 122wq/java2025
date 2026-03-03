import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class PrimeThread{
    
    private static int total = 0;
    //this provided thread calls functions you will need to run in order to calculate the number of threads between the provided min and max values.
    public static class CountPrimesThread extends Thread {
        int count = 0;
        int min1, max1, min2, max2;
        public CountPrimesThread(int min1, int max1, int min2, int max2) {
            this.min1 = min1;
            this.max1 = max1;
            this.min2 = min2;
            this.max2 = max2;
        }
        public void run() {
            count = countPrimes(min1,max1, min2, max2);
            System.out.println("There are " + count + 
                    " primes between " + min1 + " and " + max1 + " plus " + min2 + " and " + max2);
            sendBack(count);
        }
    }
    
    /**
     *Notice how this is a synchronized method! This allows multiple threads to access it safely.
     *We use this to keep track of the total number of primes found.
     */
    synchronized private static void sendBack(int count) {
        total += count;
    }
    /**
     * Count the primes between min and max, inclusive.
     * you need to implement this!
     */
    private static int countPrimes(int min1, int max1, int min2, int max2) {
        int count = 0;
        for (int i = min1; i <= max1; i++)
        {
            if (isPrime(i))
            {
                count++;
            }
        }
        for (int j = min2; j <= max2; j++)
        {
           if (isPrime(j))
            {
                count++;
            } 
        }
        return count;
    }

    /**
     * Test whether x is a prime number.
     * x is assumed to be greater than 1.
     * You need to implement this!
     */
    private static boolean isPrime(int x) {
        if (x == 0 || x == 1)
        {
            return false;
        }
        for (int i = 2; i <= x / 2; i++)
        {
            if (x % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    public void test(){
        int num = 0;
        for(int i = 0; i <= 50; i++){  
           num =num+i;
           System.out.print(num+", ");
        }  
     }
    public static void main(String args[]) {  
        int processors = Runtime.getRuntime().availableProcessors();
        if (processors == 1)
            System.out.println("Your computer has only 1 available processor.\n");
        else
            System.out.println("Your computer has " + processors + " available processors.\n");
        //uncomment the following once you're ready to take user input for the number of threads and max value to test
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter the number for which you wish to know how many primes are smaller than it: ");
        int max = reader.nextInt();
        System.out.println("Enter the number of threads you wish to use in your program");
        int threads = reader.nextInt();
        
        
        
        
       //Starting time
       Instant start = Instant.now();
       
       //comment the following line out once you've seen how this works.
       CountPrimesThread[] threadArr = new CountPrimesThread[threads];
       for (int i = 0; i < threadArr.length; i++)
       {
            //the threads would have half of its numbers been the smallest, the other half being largest
            threadArr[i] = new CountPrimesThread((i*max)/(2*threads), ((i+1)*max)/(2*threads), max - (i+1)*max/(2*threads), max - (i)*max/(2*threads));
            threadArr[i].start();
       }
       for (int i = 0; i< threads; i++)
       {
            try {
                threadArr[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }
       //new PrimeThread().test();
       
       
       //Write the code for spawning the desired number of CountPrimeThreads
       //be sure to divide the work among the specified number of threads efficiently. Use .join() to check to see if a thread is finished.
       
       
       //End time
       Instant end = Instant.now();
       long time = Duration.between(start, end).toMillis();
       System.out.println();
       System.out.println(time+" Milli seconds");
    }
}

