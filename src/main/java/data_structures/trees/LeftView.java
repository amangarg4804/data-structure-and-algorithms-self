package data_structures.trees;

import data_structures.trees.bst.MyBinarySearchTree.Node;

public class LeftView {

    void leftView(Node root)
    {
        while (root!=null) {
            System.out.print(root.key + " ");
            if(root.left!= null) {
                root = root.left;
            }else {
                root = root.right;
            }

        }
    }

}
