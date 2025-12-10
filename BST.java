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
            if (child.left == null && child.right == null)
            {
                root = null;
            }
            else if (child.left == null)
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

    //precondition: none
    //postcondition: returns a sideways representation of the tree
    public String toString(){
        return diagram(root, "");
    }

    //precondition: none
    //postcondition: creates a sideways representation of the tree
    private String diagram(Node node, String space)
    {
        if (node == null)
        {
            return "";
        }

        String result = "";

        // right subtree
        result += diagram(node.right, space + "     ");

        // root
        result += space;
        //start of the node has a plus
        if ((space.length() == 0))
        {
            result += "+";
        }
        else if (node.key == 67)
        {
            result += "!!!";
        }
        else if (!(node.right == null && node.right == null))
        {
            result += "--";
        }
        result += node.key + "\n";

        // left subtree
        result += diagram(node.left, space + "     ");

        return result;
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