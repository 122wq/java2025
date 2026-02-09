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

public void add(int toAdd) {
    

    arr[height] = toAdd;
    siftUp(height);
    height++;
    
}

 

//remove the largest element of the heap (the root) and re-heapafy

//5 points

public void removeMax() {}

 

//this should check and alter the tree after an item is inserted

//3 points

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

private void siftDown(int index) {}

public void printArr()
{
    for (int i = 0; i < height; i++)
    {
        System.out.print(arr[i] + " ");
    }
}

 

//4 points for syntax conventions.

 

}
class Main 
{
    public static void main(String[] args) {
        Heap a = new Heap();
        
        a.add(344);
        a.add(14);
        a.add(34);
        a.add(564);
        a.add(33);
        
        
        a.printArr();
    }
    
}