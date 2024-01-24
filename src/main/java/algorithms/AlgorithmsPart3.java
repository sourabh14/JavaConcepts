package algorithms;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    public int data;
    public TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class AlgorithmsPart3 {
    /*
        Tree Traversal
		Binary Tree can be traversed in two ways:
		Depth First Traversal:
			Inorder (Left-Root-Right),
			Preorder (Root-Left-Right) and
			Postorder (Left-Right-Root)
		Breadth First Traversal: Level Order Traversal
		Time Complexity of Tree Traversal: O(n)

		- In the case of binary search trees (BST), Inorder traversal gives nodes in non-decreasing order.
		- Preorder traversal is used to create a copy of the tree. Preorder traversal is also used to get prefix expression on an expression tree.
		- Postorder traversal is used to delete the tree.
     */

    public void execute() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);

        /* Tree structure
                       1
                    /     \
                   2       3
                  / \     / \
                 4  NULL 5  NULL
                / \
             NULL NULL
        */

        System.out.print("Inorder traversal: ");
        inorderTraversal(root);
        System.out.print("\nPreorder traversal: ");
        preorderTraversal(root);
        System.out.print("\nPostorder traversal: ");
        postorderTraversal(root);

        System.out.print("\nLevel order traversal: ");
        levelOrderTraversal(root);
    }

    private void levelOrderTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            TreeNode front = queue.remove();
            if (queue.isEmpty())
                break;

            if (front == null) {
                queue.add(null);
            } else {
                System.out.print(front.data + ", ");
                if (front.left != null)
                    queue.add(front.left);
                if (front.right != null)
                    queue.add(front.right);
            }
        }
    }

    private void postorderTraversal(TreeNode root) {
        // left, right, root
        if (root == null) return;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.data + ", ");
    }

    private void preorderTraversal(TreeNode root) {
        // root, left, right
        if (root == null) return;

        System.out.print(root.data + ", ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }

    private void inorderTraversal(TreeNode root) {
        // left, root, right
        if (root == null) return;

        inorderTraversal(root.left);
        System.out.print(root.data + ", ");
        inorderTraversal(root.right);
    }

}
