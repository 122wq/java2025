public class BST {
    Node root;

    public BST()
    {
         root = null;
    }

   

    public void insert(int key)
    {
        insertHelper(key, root);
    }

    private void insertHelper(int key, Node root)
    {
        if (root == null)
        {
            root = new Node(key);
        }
        else if (root.key > key)
        {
            insertHelper(key, root.left);
        }
        else
        {
           insertHelper(key, root.right);
        }
    }

    public boolean search(int key){
        return searchHelper(key, root);

    }

    private boolean searchHelper(int key, Node root)
    {
        if (root == null)
        {
            return false;
        }
        else if (root.key > key)
        {
            searchHelper(key, root.left);
        }
        else if (root.key < key)
        {
           searchHelper(key, root.right);
        }
        return true;
    }

    public int remove(int key){
        return removeHelper(key, root);

    }

    private int removeHelper(Node prev, int key)
    {
        Node numRemove;
        if (prev == null)
        {
            return -1;
        }
        if (root.left.key < key)
        {
            removeHelper(root.right, root.right.key);
        }
       else if (root.left.key > key)
        {
           removeHelper(root.left, root.left.key);
        }
        if ((root.right.key == key) || (root.left.key == key)
        {
            root = numRemove;
        }
    }

    public String toString(){
        return "";
        
    }

}
class Test
{
    public static void main(String[] args) {
        
    }
}