package algorithms;

public class AlgorithmsPart4_BST_AVL_RBT {
    /*
        Binary search tree, Red black tree, AVL tree

        Binary Search Tree:
        ------------------
            Intro:
                BST is a type of binary tree to organize and manage data in a way that allows for efficient searching,
                insertion, and deletion operations.

            Structure
                - Node: Each element in a binary search tree is stored in a node, which contains:
                        A key or value.
                        A reference (or link) to the left child node.
                        A reference (or link) to the right child node.
                - Children: Each node has at most two children, referred to as the left child and the right child.

            Properties
                - Ordering: For each node:
                    - All values in the left subtree (nodes under the left child) are less than the node’s value.
                    - All values in the right subtree (nodes under the right child) are greater than the node’s value.
                - No Duplicate Values: Typically, BSTs do not store duplicate values

            Operations
                - Insertion/ Search: To insert a new value, you start at the root and recursively traverse the tree,
                    comparing the new value with the current node’s value:
                        If the new value is less, go to the left child.
                        If the new value is greater, go to the right child.
                        Insert the new value as a leaf node when you reach an empty spot.

                - Deletion: Removing a node involves three cases:
                    Leaf Node: Simply remove the node.
                    Single Child: Remove the node and replace it with its child.
                    Two Children:
                        Find the node’s in-order successor (the smallest node in the right subtree),
                        replace the node with this successor,
                        and then remove the successor.

            Performance
                Average Case: O(log n) : Operations like search, insert, and delete assuming the tree is balanced.
                Worst Case: O(n) : If the tree becomes unbalanced (e.g., resembling a linked list),  .

            Variants
                Several variations of BSTs exist to maintain balance and optimize performance, including:
                - AVL Trees: Self-balancing BSTs where the height difference between left and right subtrees is at most one.
                - Red-Black Trees: Another type of self-balancing BST with specific coloring rules to ensure balance.



        AVL Trees
        ---------
            Intro:
                - An AVL tree is a self-balancing binary search tree
                - It maintains balance through rotations, which ensures that the tree remains approximately
                    balanced, leading to efficient operations.

            Key Properties
                - BST Properties: An AVL tree is a type of binary search tree, so it maintains the BST property:
                    For any given node, all values in its left subtree are less than the node’s value.
                    All values in its right subtree are greater than the node’s value.

                - Balance Factor: Each node in an AVL tree has a balance factor, which is defined as the difference
                    in height between its left and right subtrees:
                    Balance Factor = Height of Left Subtree - Height of Right Subtree
                    The balance factor must be -1, 0, or +1 for every node in the tree. This ensures that the tree remains balanced.

                - Rotations
                    To maintain balance after operations like insertion and deletion, AVL trees use rotations. There are four types of rotations:

                    Right Rotation (Single Rotation): Used when a left-heavy subtree needs balancing. It involves rotating the subtree to the right.

                        y                x
                       / \             /   \
                      x   C    ->     A     y
                     / \                   / \
                    A   B                 B   C

                    Left Rotation (Single Rotation): Used when a right-heavy subtree needs balancing. It involves rotating the subtree to the left.

                      x              y
                     / \           /   \
                    A   y   ->    x     C
                       / \       / \
                      B   C     A   B

                    Left-Right Rotation (Double Rotation): Used when a node is inserted into the left subtree of the right child of a left-heavy subtree. This is a combination of a left rotation on the left child followed by a right rotation on the root.

                        z                z               y
                       / \             / \            /  \
                      x   C    ->     y   C    ->    x      z
                     / \             / \           /  \    /  \
                    A   y           x   B         A    B  C
                       / \         / \
                      B   C       A   B

                    Right-Left Rotation (Double Rotation): Used when a node is inserted into the right subtree of the left child of a right-heavy subtree. This is a combination of a right rotation on the right child followed by a left rotation on the root.

                      z                z              y
                     / \              / \           /  \
                    A   x    ->      A   y    ->     z      x
                       / \            / \        / \    /  \
                      y   C         B   x      A   B  C
                     / \               / \
                    B   C             C   B

            Operations
                - Insertion/ Deletion:
                    Insert/Delete the new node like in a regular BST.
                    Update the height of the affected nodes.
                    Rebalance the tree by checking the balance factors and performing necessary rotations.

               - Search: similar to a regular BST

            Performance
                Time Complexity: AVL trees guarantee O(log n) time complexity for search, insertion, and deletion
                    operations due to their balanced nature.
                Space Complexity: Space complexity is O(n) where n is the number of nodes in the tree.

            Benefits
                AVL trees are more rigidly balanced compared to other self-balancing trees like Red-Black Trees, which ensures faster lookups in practice.
                They are particularly useful when the tree is subject to frequent lookups and less frequent insertions and deletions.



        Red-Black Trees
        ---------------

        Intro:
            A red-black tree is a type of self-balancing binary search tree, a data structure that maintains sorted
            data and allows for efficient insertion, deletion, and lookup operations.

        Properties
            - Node Color: Every node is either red or black.
            - Root Property: The root of the tree is always black.
            - Red Property: Red nodes cannot have red children (no two red nodes can be adjacent). This property ensures that the tree does not have long chains of red nodes, which would make it unbalanced.
            - Black Property: Every path from a given node to any of its descendant NULL nodes (leaves) has the same number of black nodes. This property ensures a uniform distribution of black nodes in the tree.
            - Leaf Property: All leaves (NULL nodes) are considered black.

        Advantages
            - Efficiency: Red-black trees offer O(logn) time complexity for search, insertion, and deletion operations.
            - Balance: They provide good performance guarantees by maintaining a balanced structure.

        Applications
            - Associative Containers: Standard Template Library (STL) in C++ uses red-black trees to implement the map, set, multimap, and multiset containers.
            - Java Collections: Java's TreeMap and TreeSet classes use red-black trees.
            - Memory Management: Used in Linux's kernel to manage memory allocation.


        Applications of Red-Black Trees:
            - Implementing maps and sets: Red-Black Trees are often used to implement maps and sets, where efficient
                search, insertion, and deletion are crucial.
            - Priority queues: Red-Black Trees can be used to implement priority queues, where elements are
                ordered based on their priority.
            - File systems: Red-Black Trees are used in some file systems to manage file and directory structures.
            - In-memory databases: Red-Black Trees are sometimes used in in-memory databases to store and
                retrieve data efficiently.
            - Graphics and game development: Red-Black Trees can be used in graphics and game development
                for tasks like collision detection and pathfinding.
     */
}
