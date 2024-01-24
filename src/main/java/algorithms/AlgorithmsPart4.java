package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlgorithmsPart4 {
    /*
        Graph
            - BFS
            - DFS
     */

    public static final int MAX_VERTICES = 1000;

    public void execute() {
        // Create a graph
        List<List<Integer>> graph = new ArrayList<>(MAX_VERTICES);
        init(graph);

        join(graph, 0, 1);
        join(graph, 0, 2);
        join(graph, 0, 3);
        join(graph, 1, 4);
        join(graph, 2, 4);
        join(graph, 4, 5);
        join(graph, 4, 7);
        join(graph, 7, 8);

        // BFS traversal
        breadthFirstTraverse(graph, 0);

        // DFS traversal
        boolean[] discovered = new boolean[MAX_VERTICES];
        boolean[] visited = new boolean[MAX_VERTICES];
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        System.out.println("DFS: ");
        depthFirstTraverse(graph, 0, discovered, visited);
        System.out.println("");


        Date d=new Date(1997,3,10);
        Date d2=null;
        System.out.println("Date 'd' is before Date 'd2' : " + d.before(d2));
    }

    private void depthFirstTraverse(List<List<Integer>> graph, int source, boolean[] discovered, boolean[] visited) {
        discovered[source] = true;
        for (int i=0; i<graph.get(source).size(); i++) {
            if (!discovered[graph.get(source).get(i)]) {
                depthFirstTraverse(graph, graph.get(source).get(i), discovered, visited);
            }
        }

        // visit node
        visited[source] = true;
        System.out.print(source + ", ");
    }


    private void breadthFirstTraverse(List<List<Integer>> graph, int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] discovered = new boolean[MAX_VERTICES];
        boolean[] visited = new boolean[MAX_VERTICES];
        int[] minDistance = new int[MAX_VERTICES];

        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        Arrays.fill(minDistance, -1);

        queue.add(source);
        queue.add(null);
        discovered[source] = true;
        minDistance[0] = 0;

        System.out.println("BFS: ");

        while (true) {
            Integer front = queue.remove();
            if (queue.isEmpty()) break;

            if (front == null) {
                queue.add(null);
            } else {
                // visit node
                System.out.print(front + ", ");
                visited[front] = true;
                // add all its adjacent nodes which are not discovered
                for (int i=0; i<graph.get(front).size(); i++) {
                    if (!discovered[graph.get(front).get(i)]) {
                        queue.add(graph.get(front).get(i));
                        discovered[graph.get(front).get(i)] = true;
                        minDistance[graph.get(front).get(i)] = minDistance[front] + 1;
                    }
                }
            }
        }

        System.out.println("");
        System.out.println("MinDistance: ");
        for (int i=0; i<=8; i++) {
            System.out.print(minDistance[i] + ", ");
        }
        System.out.println("");
    }

    private void join(List<List<Integer>> graph, int v1, int v2) {
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
    }


    private void init(List<List<Integer>> graph) {
        for (int i=0; i<MAX_VERTICES; i++) {
            graph.add(new ArrayList<>());
        }
    }
}
