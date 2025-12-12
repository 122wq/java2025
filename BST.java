import java.util.ArrayList;
import java.util.Arrays;

public class BST {
    Node root;

    public BST()
    {
         root = null;
    }

   
    //Precondition: key is not already in the tree
    //Postcondition: tree is updated
    public void insert(int key)
    {
        root = insertHelper(key, root);
    }

    //Precondition: key is not already in the tree, root is not null
    //Postcondition: key is added to the tree
    private Node insertHelper(int key, Node root)
    {
        if (root == null)
        {
            root = new Node(key);
        }
        else if (root.key > key)
        {
            root.left = insertHelper(key, root.left);
        }
        else
        {
           root.right = insertHelper(key, root.right);
        }
        return root;
    }

    //Precondition: none
    //Postcondition: returns true if key is in the tree, false otherwise
    public boolean search(int key){
        return searchHelper(key, root);

    }

    //Precondition: none
    //Postcondition: returns true if key is in the tree, false otherwise
    private boolean searchHelper(int key, Node root)
    {
        if (root == null)
        {
            return false;
        }
        if (root.key == key)
        {
            return true;
        }
        else if (root.key > key)
        {
            return searchHelper(key, root.left);
        }
        else
        {
           return searchHelper(key, root.right);
        }
        
        
    }
    //Precondition: key is in the tree
    //Postcondition: key is removed from the tree and tree is updated as needed
    public void remove(int key){
        //find the parent of the node you want removed
        //figure out which of the three worlds you live in 1) removed has no children
        //2) remove has one child
        //3) remove has 2 children
        Node child;
        
        // root delete case
        if (root.key == key)
        {
            child = root;
            Node middle = middle(child, key);
            
            if (child.left == null)
            {
                root = child.right;
            }
            else if (child.right == null)
            {
                root = child.left;
            }
            else
            {
                if (middle != null)
                {
                    int mid = middle.key;
                    remove(middle.key);
                    root.key = mid;
                }
            }
            return;
        }
        
        Node parent = removeHelper(null, root, key);
        
        
        if (parent.right != null && parent.right.key == key)
        {
            child = parent.right;
            Node middle = middle(child, key);
            // has only 1 child or 0 child
            if (child.left == null)
            {
                parent.right = child.right;
            }
            else if (child.right == null)
            {
                parent.right = child.left;
            }
            // has 2 children
            else
            {
                if (middle != null)
                {
                    int mid = middle.key;
                    remove(middle.key);
                    child.key = mid;
                }
            }
        }
        else if (parent.left != null && parent.left.key == key)
        {
            child = parent.left;
            Node middle = middle(child, key);
            if (child.left == null)
            {
                parent.left = child.right;
            }
            else if (child.right == null)
            {
                parent.left = child.left;
            }
            else
            {
                if (middle != null)
                {
                    int mid = middle.key;
                    remove(middle.key);
                    child.key = mid;
                }
            }
        }
    }

    //Precondition: key is in the tree
    //Postcondition: key is removed from the tree and tree is updated as needed
    private Node removeHelper(Node prev, Node curr,int key)
    {
        if (curr == null)
        {
            return prev;
        }
        if (curr.key == key)
        {
            return prev;
        }
        if (curr.key < key)
        {
            return removeHelper(curr, curr.right, key);
        }
        else
        {
           return removeHelper(curr, curr.left, key);
        }
    }
    //Precondition: key is in the tree, root is not null
    //Postcondition: finds the node that is the leftmost of the right subtree
    private Node middle (Node root, int key)
    {
        // find the smallest in right subtree
        if (root.right == null)
            return null;
        Node mid = root.right;
        while (mid.left != null)
        {
            mid = mid.left;
        }
        return mid;
    }

    private ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
    private void fill(Node node, int height)
    {
        if (node == null)
        {
            return;
        }
        if (height == levels.size())
        {
            levels.add(new ArrayList<Integer>());
        }
        levels.get(height).add(node.key);
        fill(node.left, height + 1);
        fill(node.right, height + 1);
    }
    //precondition: none
    //postcondition: returns representation of the tree
    public String toString()
    {
        if (root == null)
        {
            return "[]";
        }   
        levels.clear();
        fill(root, 0);

        String result = "";
        for (int i = 0; i < levels.size(); i++)
        {
            result += levels.get(i).toString();
            result += ",\n";
            
        }
    
        return result;
    }

    public boolean isBSTOrNot() {
        return isBSTOrNot(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTOrNot(Node root, int minValue, int maxValue) {
        // check for root is not null or not
        if (root == null) {
            return true;
        }
        // check for current node value with left node value and right node value and recursively check for left sub tree and right sub tree
        if(root.key >= minValue && root.key <= maxValue && isBSTOrNot(root.left, minValue, root.key) && isBSTOrNot(root.right, root.key, maxValue)){
            return true;
        }
        return false;
    }

 

   // please use the following pieces of code to display your tree in a more easy to follow style (Note* you'll need to place the Trunk class in it's own file)
    public static void showTrunks(Trunk p)
    {
        if (p == null) {
            return;
        }
 
        showTrunks(p.prev);
        System.out.print(p.str);
    }
 
    public void printTree(){
        printTree(root, null, false);
    }

    private void printTree(Node root, Trunk prev, boolean isLeft)
    {
        if (root == null) {
            return;
        }
 
        String prev_str = "    ";
        Trunk trunk = new Trunk(prev, prev_str);
 
        printTree(root.right, trunk, true);
 
        if (prev == null) {
            trunk.str = "———";
        }
        else if (isLeft) {
            trunk.str = ".———";
            prev_str = "   |";
        }
        else {
            trunk.str = "`———";
            prev.str = prev_str;
        }
 
        showTrunks(trunk);
        System.out.println(" " + root.key);
 
        if (prev != null) {
            prev.str = prev_str;
        }
        trunk.str = "   |";
 
        printTree(root.left, trunk, false);
    }
    

}
class Test
{
    public static void main(String[] args) {
        BST tree = new BST();
        
        // Create a BST with height 4
        tree.insert(8);  
        tree.insert(4);    
        tree.insert(12); 
        tree.insert(2);   
        tree.insert(6);   
        tree.insert(10); 
        tree.insert(14);  
        tree.insert(1);   
        tree.insert(3);    
        tree.insert(5);    
        tree.insert(7);    
        tree.insert(9);   
        tree.insert(11);   
        tree.insert(13);   
        tree.insert(15);  
        tree.insert(67);   
        
        System.out.println(tree);
        tree.remove(4);
        System.out.println("After removing 4:");
        System.out.println(tree);
        System.out.println(tree.search(4));
        System.out.println(tree.search(6)); 
    }
}