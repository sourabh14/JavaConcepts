package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Graph {
    private List<List<Integer>> graph;
    private int size;
    private boolean[] discovered;
    private boolean[] visited;
    private boolean[] recStack;

    public Graph(int size) {
        this.size = size;
        graph = new ArrayList<>(size);
        for (int i=0; i<size; i++) {
            graph.add(new ArrayList<>());
        }
        this.discovered = new boolean[size];
        this.visited = new boolean[size];
        this.recStack = new boolean[size];
    }

    public void join(int v1, int v2, boolean directed) {
        graph.get(v1).add(v2);
        if (!directed) {
            graph.get(v2).add(v1);
        }
    }

    public void print() {
        System.out.println("Graph Adjacency list");
        for (int i=0; i<size; i++) {
            System.out.print(i + ": " + graph.get(i));
        }
        System.out.println("--");
    }

    // -------------------------------------- BFS --------------------------------------------------

    public void bfs(int source) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);

        queue.add(source);
        discovered[source] = true;

        // System.out.println("BFS: ");
        while (!queue.isEmpty()) {
            int qsize = queue.size();

            for (int k=0; k<qsize; k++) {
                int curr = queue.remove();
                // visit node
                // System.out.print(curr + ", ");
                visited[curr] = true;
                // add all its adjacent nodes which are not discovered
                for (int i=0; i<graph.get(curr).size(); i++) {
                    int adj = graph.get(curr).get(i);
                    if (!discovered[adj]) {
                        queue.add(adj);
                        discovered[adj] = true;
                    }
                }
            }
        }
    }

    /*                                 BFS Shortest Path - Undirected graph
    ---------------------------------------------------------------------------------------------------------------*/

    public void bfs_shortest_path(int source, int destination) {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);

        // For shortest path
        int[] shortestPath = new int[size];
        int[] parent = new int[size];
        Arrays.fill(shortestPath, -1);
        Arrays.fill(parent, -1);

        queue.add(source);
        discovered[source] = true;
        shortestPath[source] = 0;

        while (!queue.isEmpty()) {
            int qsize = queue.size();

            for (int k=0; k<qsize; k++) {
                int curr = queue.remove();
                visited[curr] = true;
                for (int i=0; i<graph.get(curr).size(); i++) {
                    int adj = graph.get(curr).get(i);
                    if (!discovered[adj]) {
                        queue.add(adj);
                        discovered[adj] = true;
                        shortestPath[adj] = shortestPath[curr] + 1;
                        parent[adj] = curr;
                    }
                }
            }
        }

        System.out.println("");
        System.out.println("Shortest path from " + source + ": " + Arrays.toString(shortestPath));
        System.out.println("Shortest path from " + source + " to " + destination + ": " + printPath(parent, destination));
        System.out.println("");
    }

    public List<Integer> printPath(int[] parent, int dest) {
        List<Integer> path = new ArrayList<>();
        while (parent[dest] != -1) {
            path.add(dest);
            dest = parent[dest];
        }
        path.add(dest);
        Collections.reverse(path);
        return path;
    }



    /*---------------------------------------------------------------------------------------------------------------
                                                    DFS
    ---------------------------------------------------------------------------------------------------------------*/

    public void dfs(int source) {
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        // System.out.println("DFS: ");
        for (int i=0; i<size; i++) {
            if (!visited[i]) {
                depthFirstTraverse(i, this.graph);
            }
        }
        // System.out.println();
    }

    public void depthFirstTraverse(int source, List<List<Integer>> graph) {
        // discover node
        discovered[source] = true;
        // for all adjacent nodes not discovered, call dfs
        for (int i=0; i<graph.get(source).size(); i++) {
            int adj = graph.get(source).get(i);
            // dfs all adjacent nodes which are not discovered
            if (!discovered[adj]) {
                depthFirstTraverse(adj, graph);
            }
        }

        // visit node
        visited[source] = true;
        // System.out.print(source + ", ");
    }

    /*                                 DFS on matrix
    ---------------------------------------------------------------------------------------------------------------*/


    private void dfs(int i, int j, int[][] board, boolean[][] discovered) {
        discovered[i][j] = true;
        board[i][j] = 2;

        if (isValidCell(i-1, j, board) && !discovered[i-1][j] && board[i-1][j] == 1) {
            dfs(i-1, j, board, discovered);
        }
        if (isValidCell(i+1, j, board) && !discovered[i+1][j] && board[i+1][j] == 1) {
            dfs(i+1, j, board, discovered);
        }
        if (isValidCell(i, j-1, board) && !discovered[i][j-1] && board[i][j-1] == 1) {
            dfs(i, j-1, board, discovered);
        }
        if (isValidCell(i, j+1, board) && !discovered[i][j+1] && board[i][j+1] == 1) {
            dfs(i, j+1, board, discovered);
        }
    }

    private boolean isValidCell(int i, int j, int[][] board) {
        return (i>=0 && i<board.length && j>=0 && j<board[0].length);
    }




    /*                                 Connected components
    ---------------------------------------------------------------------------------------------------------------*/
    public void connectedComponents() {
        int cnt = 1;
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);

        System.out.println("Connected Components: ");
        for (int i=0; i<size; i++) {
            if (!visited[i]) {
                System.out.print("Component " + cnt + ": [");
                depthFirstTraverse(i, this.graph);
                System.out.println("]");
                cnt++;
            }
        }
    }

    /*                                 Detect cycle in graph
    ---------------------------------------------------------------------------------------------------------------*/
    public void detectCycle() {
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        Arrays.fill(recStack, false);
        boolean isCyclic = false;

        for (int i=0; i<size; i++) {
            if (!visited[i]) {
                isCyclic = dfsDetectCycle(i, this.graph);
                if (isCyclic) break;
            }
        }
        System.out.println("Graph contains cycle: " + isCyclic);
    }

    private boolean dfsDetectCycle(int source, List<List<Integer>> graph) {
        discovered[source] = true;
        // push source to stack
        recStack[source] = true;
        // for all adjacent nodes
        for (int i=0; i<graph.get(source).size(); i++) {
            int adj = graph.get(source).get(i);
            // dfs all adjacent nodes which are not discovered
            if (!discovered[adj]) {
                return dfsDetectCycle(adj, graph);
            } else if (recStack[adj]) {
                // if adjacent node is present in the stack, means this is a back edge and cycle exists
                return true;
            }
        }
        // pop source from stack
        recStack[source] = false;
        // visit node
        visited[source] = true;
        return false;
    }

    /*                                Topological Sort
    ---------------------------------------------------------------------------------------------------------------*/
    public void topologicalSort() {
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        LinkedList<Integer> arr = new LinkedList<>();

        for (int i=0; i<size; i++) {
            if (!visited[i]) {
                depthFirstTraverse_topologicalSort(i, arr);
            }
        }
        System.out.println("Topological list: " + arr + "\n");
    }

    public void depthFirstTraverse_topologicalSort(int source, LinkedList<Integer> arr) {
        discovered[source] = true;
        for (int i=0; i<graph.get(source).size(); i++) {
            int adj = graph.get(source).get(i);
            if (!discovered[adj]) {
                depthFirstTraverse_topologicalSort(adj, arr);
            }
        }
        visited[source] = true;
        // After visiting source, insert it in the front of arrayList
        arr.addFirst(source);
    }

    /*                                 Strongly connected components
    ---------------------------------------------------------------------------------------------------------------*/
    public void stronglyConnectedComponent() {
        // DFS traversal - similar to topological sort
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        LinkedList<Integer> arr = new LinkedList<>();

        for (int i=0; i<size; i++) {
            if (!visited[i]) {
                depthFirstTraverse_topologicalSort(i, arr);
            }
        }

        // Create a transpose graph
        List<List<Integer>> graphTranspose = new ArrayList<>();
        for (int i=0; i<size; i++) {
            graphTranspose.add(new ArrayList<>());
        }

        for (int i=0; i<size; i++) {
            for (int j=0; j<graph.get(i).size(); j++) {
                int v = graph.get(i).get(j);
                graphTranspose.get(v).add(i);
            }
        }

        // DFS on the transpose graph
        System.out.println("Connected Components: ");
        int cnt = 1;
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        for (int i: arr) {
            if (!visited[i]) {
                System.out.print("SCC " + cnt + ": ");
                depthFirstTraverse(i, graphTranspose);
                System.out.println();
                cnt++;
            }
        }
    }




}

public class AlgorithmsPart7_Graph_DFS_BFS {

    public void execute() {
        /*
            Graph, BFS, DFS

            Graph
            ------
                Intro:
                    - A graph is a data structure used to represent relationships between pairs of objects. It is composed
                        of nodes (also called vertices) and edges (also called links or arcs)
                    - Graphs are used to represent networks. The networks may include paths in a city or telephone
                        network or circuit network.

                Applications
                    - Social Networks: Modeling relationships between users.
                    - Web Graphs: Representing links between web pages.
                    - Transport Networks: Mapping routes and connections between cities.
                    - Recommendation Systems: Suggesting products or content based on user interactions.
                    - Biological Networks: Modeling interactions between proteins or genes.

                Basic Terminology
                    - Vertex (Node): A fundamental part of a graph that represents an object.
                    - Edge (Link): A connection between two vertices that represents a relationship or interaction.
                    - Degree: The number of edges connected to a vertex.
                    - Path: A sequence of vertices where each consecutive pair is connected by an edge.
                    - Cycle: A path that starts and ends at the same vertex, with no other vertex repeated.
                    - Connected Graph: A graph where there is a path between every pair of vertices.
                    - Disconnected Graph: A graph where some vertices are not connected by paths.
                    - Subgraph: A graph formed from a subset of vertices and edges of a larger graph.
                    - Directed Graph (Digraph): A graph where edges have a direction, indicating a one-way relationship.
                    - Undirected Graph: A graph where edges have no direction, indicating a two-way relationship.
                    - Weighted Graph: A graph where edges have weights representing the cost or distance of the relationship.

                Types of Graphs
                    - Simple Graph: A graph with no loops (edges connected at both ends to the same vertex) and no more
                        than one edge between any pair of vertices.
                    - Multigraph: A graph that allows multiple edges between the same pair of vertices.
                    - Complete Graph: A graph where there is an edge between every pair of vertices.
                    - Bipartite Graph: A graph whose vertices can be divided into two disjoint sets such that every edge connects a vertex in one set to a vertex in the other set.
                    - Tree: A connected acyclic graph.
                    - Forest: A disjoint set of trees.

                Graph Representation

                    Adjacency list representation:
                        - An array of lists where each list contains the adjacent vertices of a particular vertex.
                        - Appropriate for sparse graphs - where edges are much less than (vertices)^2

                    Adjacency matrix representation:
                        - A 2D array where each element indicates whether an edge exists between a pair of vertices.
                        - Appropriate when graph is dense or when we need to tell if there is an edge
                            connecting two different vertices
                        - It's more simple. Can be used when graph is reasonably small.
         */

        int MAX_VERTICES = 10;
        Graph graph = new Graph(MAX_VERTICES);

        graph.join(0, 1, false);
        graph.join(0, 2, false);
        graph.join(0, 3, false);
        graph.join(1, 4, false);
        graph.join(2, 4, false);
        graph.join(4, 5, false);
        graph.join(4, 7, false);
        graph.join(7, 8, false);

        /*
            Graph Traversal
                BFS traversal
                    - systematically explore the edge to discover every vertex that is reachable from source
                    - vertex is discovered first time it is encountered
                    - vertex is visited when all its adjacent vertices are discovered
                    - Time complexity: O(V + E)

                Shortest path distance
                    - BFS can be used to find shortest-path - minimum no of edges (works for both directed and
                        undirected - unweighted graph)
         */

        graph.bfs(0);
        graph.bfs_shortest_path(0, 4);

        /*
            DFS traversal
                - Search deeper in the graph whenever possible.
                - Explore edges out of most recently discovered vertex that still has unexplored edges
                - Once all edges have been explored of v, it backtracks
                - The process continues till we have discovered all vertices reachable from source
                - Time complexity: O(V + E)

            Connected Components
                - A set of nodes forms a connected component in an undirected graph if any node from the set of
                    nodes can reach any other node by traversing edges.

            Detect cycle in graph
                - there is a cycle in a graph only if there is a back edge [i.e., a node points to one of its
                    ancestors] present in the graph.
                - To detect a back edge, we need to keep track of the nodes visited till now and the nodes that are
                    in the current recursion stack [i.e., the current path that we are visiting]. If during recursion,
                    we reach a node that is already in the recursion stack, there is a cycle present in the graph.

            Topological sort for DAG
                - linear ordering of all vertices along horizontal line such that all directed edges
                    go from left to right
                - application : to indicate precedence among events
                - Algo
                    - DFS traversal
                    - After completion of each node insert it in front of a linked list

            Strongly connected components
                - maximal set of vertices 'C' such that for every pair of vertices (u,v) in 'C' we have path from
                    u to v and v to u. That is u and v are reachable from each other
                - Algo
                    - DFS traversal
                    - after completion of each node insert it in front of a linked list
                    - On transpose graph, perform dfs starting from vertices given in linked list.
                    - each traversal will give SCC
         */
        graph.dfs(0);
        graph.connectedComponents();
        graph.detectCycle();
        graph.topologicalSort();
        graph.stronglyConnectedComponent();

        Graph g2 = new Graph(10);
        g2.join(0, 1, true);
        g2.join(1, 2, true);
        g2.join(0, 2, true);
        g2.join(4, 3, true);

        System.out.println("\nGraph 2");
        g2.detectCycle();

        /*
            Graph short summary
            - Graph structure
            - DFS
                - Connected components
                - Detect cycle
            - BFS
                -
         */
    }
}
