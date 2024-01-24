package algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class DisjointSet {
    private List<Integer> parent;

    public DisjointSet(int n) {
        this.parent = new ArrayList<>(n);
        for (int i=0; i<n; i++) this.parent.add(i);
    }

    private void compressPath(int v) {
        int parent = this.parent.get(v);
        int grandparent = this.parent.get(parent);
        while (grandparent != parent) {     // for root of tree parent and grandparent are equals
            parent = grandparent;
            grandparent = this.parent.get(parent);
        }
        this.parent.set(v, parent);
    }

    public boolean isConnected(int v1, int v2) {
        compressPath(v1);
        compressPath(v2);
        return (Objects.equals(this.parent.get(v1), this.parent.get(v2)));
    }

    public void mergeSet(int v1, int v2) {
        compressPath(v1);
        compressPath(v2);
        int parent1 = this.parent.get(v1);
        int parent2 = this.parent.get(v2);
        this.parent.set(parent1, parent2);
    }
}

public class AlgorithmsPart5 {
    /*
        Disjoint set data structure:
         *  -a structure that maintains a collection S1, S2, S3, …, Sn of
         * dynamic disjoint sets. Two sets are disjoint if their intersection is null.
         *
         * Operations : merge-set(x,y) - O(1) - merge into one set the set that contains
         * 									element x and the set that contains element y
         * 			  - connected(x,y) - O(1) - check connectivity of x and y
         *
         * Complexity : O(m+n) : m-no of operations and n- no of nodes
         * 			  - O(n) : space complexity
         *
         * Application : To determine if two nodes are connected in a graph
         * 			   - Better than bfs in two aspects
         * 					- Memory requirement is less
         * 					- When merge-set() and connected() operations are mixed
         *
         * Heuristics :
         *    Union by rank - the root of the tree with fewer nodes point to the root of the tree with more nodes.
         * 					- this avoids tall trees- for this we keep track of tree size sz[i]
         *    Path compression - make each node on the find path point directly to the root
         * 					   - this keeps tree almost flat
     */
    public void execute() {
        DisjointSet disjointSet = new DisjointSet(10);
        disjointSet.mergeSet(0, 1);
        disjointSet.mergeSet(0, 2);
        disjointSet.mergeSet(1, 3);
        System.out.println("2 and 3 are connected : " + disjointSet.isConnected(2, 3));
        disjointSet.mergeSet(4, 5);
        System.out.println("1 and 5 are connected : " + disjointSet.isConnected(1, 5));
        disjointSet.mergeSet(4, 3);
        System.out.println("1 and 5 are connected : " + disjointSet.isConnected(1, 5));
    }
}
