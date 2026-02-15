public class Heap {

//the actual storage structure for your heap

private int[] arr;
private int height;

 

//constructor for your heap

//feel free to make one that takes in an array if you prefer for your testing purposes.

//note that we will not be inserting more than 100 elements into our array so you need not worry about re-sizing

//the array

public Heap() {

    arr = new int[100];

}

 

 

//create this function to add elements to your heap

//all heap properties must be preserved

// 5 points
//precondition: int is greater than 0
//postcondition: return a heap following heap rules
public void add(int toAdd) 
{
    arr[height] = toAdd;
    siftUp(height);
    height++;
    
}

 

//remove the largest element of the heap (the root) and re-heapafy

//5 points
//precondition: the heap follows all heap rules.
//postcondition: remove the largest (top) value and adjust the heap as needed
public void removeMax() 
{
    arr[0] = arr[height - 1];
    arr[height - 1] = 0;
    height--;
    siftDown(0);
}

 

//this should check and alter the tree after an item is inserted

//3 points
//precondition: index is positive and arr has less than 100 items
//postcondition: move the variable up until heap rules are satisfied
private void siftUp(int index) 
{
    while (index > 0 && arr[index] > arr[(index - 1) / 2])
    {
        int temp = arr[(index - 1) / 2];
        arr[(index - 1) / 2] = arr[index];
        arr[index] = temp;
        index = (index - 1) / 2;
    }
}

 

//this should check and alter the tree after an item is deleted.

//3 points
//precondition: index is positive and arr has less than 100 items
//postcondition: move the variable down until heap rules are satisfied
private void siftDown(int index) 
{
    while ((index + 1) * 2 < arr.length && index < height && (arr[index] < arr[2 * (index) + 1]) || (arr[index] < arr[2 * (index) + 2]))
    {
        int temp = Math.max(arr[2 * (index) + 1], arr[2 * (index) + 2]);
        if (temp == arr[2 * (index) + 2])
        {
            arr[2 * (index) + 2] = arr[index];
            arr[index] = temp;
            index = 2 * (index) + 2;
        }
        else
        {
            arr[2 * (index) + 1] = arr[index];
            arr[index] = temp;
            index = 2 * (index) + 1;
        }
    }
}
//precondition: none
//postcondition: print the heap
public void printArr()
{
    for (int i = 0; i < height; i++)
    {
        System.out.print(arr[i] + " ");
    }
    System.out.println("");
}

 

//4 points for syntax conventions.

 

}
class Main 
{
    public static void main(String[] args) {
        Heap a = new Heap();
        
        a.add(44);
        a.add(14);
        a.add(34);
        a.add(64);
        a.add(33);
        a.add(13);
        a.add(24);
        a.add(54);
        a.add(43);
        a.add(23);
        a.add(324);
        a.add(15);
        a.add(33);
        
        
        a.printArr();
        a.removeMax();
        a.printArr();
    }
    
}