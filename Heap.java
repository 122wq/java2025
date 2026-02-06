public class Heap {

//the actual storage structure for your heap

private int[] arr;

 

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
    if (arr.length == 0)
    {
        arr[0] = toAdd;
    }
    else
    {
        arr[arr.length-1] = toAdd;
    }
}

 

//remove the largest element of the heap (the root) and re-heapafy

//5 points

public void removeMax() {}

 

//this should check and alter the tree after an item is inserted

//3 points

private void siftUp(int index) {}

 

//this should check and alter the tree after an item is deleted.

//3 points

private void siftDown(int index) {}

 

//4 points for syntax conventions.

 

}