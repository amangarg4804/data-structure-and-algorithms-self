package data_structures.trees.bst;

public class MyBinarySearchTree {

    Node root;

    public static class Node {
        public int key;
        public Node left;
        public Node right;

        Node(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void insert(int key) {
        this.root =insert(key, root);
    }

    public Node insert(int key, Node root) {
        if(root ==null) {
            root = new Node(key);
            return root;
        }
        if(key < root.key ) {
            root.left =insert(key, root.left);
        } else {
            root.right =insert(key, root.right);
        }
        return root;
    }

    public Node search(int key) {
        return search(key, this.root);
    }

    public Node search(int key, Node root) {
        if (root == null || root.key == key) {
            return root;
        }
        if (key < root.key) {
            return search(key, root.left);
        }
        return search(key, root.right);
    }

    void inorder()  {
        inorderRec(root);
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    public static void main(String[] args) {
        MyBinarySearchTree tree = new MyBinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // print inorder traversal of the BST
        tree.inorder();

        System.out.println(tree.search(40));
        System.out.println(tree.search(30));
    }

}
