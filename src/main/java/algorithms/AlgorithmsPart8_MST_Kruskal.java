package algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class AlgorithmsPart8_MST_Kruskal {
    /*
        Spanning Tree:
            Given an undirected and connected graph G = (V, E), a spanning tree of the graph G is a tree that spans
            G (that is, it includes every vertex of G) and is a subgraph of G (every edge in the tree belongs to G)

        Minimum Spanning Tree
            The cost of the spanning tree is the sum of the weights of all the edges in the tree. There can be many
            spanning trees. Minimum spanning tree is the spanning tree where the cost is minimum among all the spanning
            trees. There also can be many minimum spanning trees.

            Minimum spanning tree has direct application in the design of networks. It is used in algorithms
            approximating the travelling salesman problem, multi-terminal minimum cut problem and minimum-cost
            weighted perfect matching. Other practical applications are:
                Cluster Analysis
                Handwriting recognition
                Image segmentation

        Kruskal’s Algorithm
            Kruskal’s Algorithm builds the spanning tree by adding edges one by one into a growing spanning tree.
            Kruskal's algorithm follows greedy approach as in each iteration it finds an edge which has least weight
            and add it to the growing spanning tree.

            Algorithm Steps:
                - Sort the graph edges with respect to their weights.
                - Start adding edges to the MST from the edge with the smallest weight until the edge of the largest weight.
                - Only add edges which doesn't form a cycle , edges which connect only disconnected components.
     */

    class Edge {
        int v1;
        int v2;
        int weight;

        public Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", weight=" + weight +
                    "}\n";
        }
    }

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

        public int getParent(int v) {
            compressPath(v);
            return this.parent.get(v);
        }

    }

    public void execute() {
        int n = 9;
        List<Edge> edgeList = new ArrayList<>();
        List<Edge> minimumSpanningTree = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet(n);

        edgeList.add(new Edge(0, 1, 4));
        edgeList.add(new Edge(1, 2, 8));
        edgeList.add(new Edge(2, 3, 7));
        edgeList.add(new Edge(3, 4, 9));
        edgeList.add(new Edge(4, 5, 10));
        edgeList.add(new Edge(5, 6, 2));
        edgeList.add(new Edge(6, 7, 1));
        edgeList.add(new Edge(7, 0, 8));
        edgeList.add(new Edge(1, 7, 11));
        edgeList.add(new Edge(7, 8, 7));
        edgeList.add(new Edge(8, 2, 2));
        edgeList.add(new Edge(8, 6, 6));
        edgeList.add(new Edge(2, 5, 4));
        edgeList.add(new Edge(3, 5, 14));

        // sort edges based on weight
        edgeList.sort(Comparator.comparingInt(a -> a.weight));

        for (Edge edge : edgeList) {
            // if vertices of edge are not connected then add to spanning tree
            if (!disjointSet.isConnected(edge.v1, edge.v2)) {
                disjointSet.mergeSet(edge.v1, edge.v2);
                minimumSpanningTree.add(edge);
            }
        }
        int totalWeight = minimumSpanningTree.stream()
                .map(e -> e.weight)
                .reduce(0, Integer::sum);
        System.out.println("Minimum spanning tree: " + minimumSpanningTree);
        System.out.println("Total weight: " + totalWeight);

    }

}
