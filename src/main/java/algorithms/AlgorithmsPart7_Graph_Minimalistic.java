package algorithms;

import java.util.*;

public class AlgorithmsPart7_Graph_Minimalistic {

    private static void dfs(int u, List<List<Integer>> graph, boolean[] discovered, boolean[] visited) {
        discovered[u] = true;
//        System.out.print(u + ", ");
//        System.out.println("discovered = " + Arrays.toString(discovered));
        for (int i=0; i<graph.get(u).size(); i++) {
            int v = graph.get(u).get(i);
            if (!discovered[v]) {
                dfs(v, graph, discovered, visited);
            }
        }
        visited[u] = true;
    }

    private static boolean dfsDetectCycle(int u, List<List<Integer>> graph, boolean[] discovered, boolean[] visited, boolean[] recStack) {
        discovered[u] = true;
        recStack[u] = true;
        boolean isCyclic = false;
        for (int i=0; i<graph.get(u).size(); i++) {
            int v = graph.get(u).get(i);
            if (!discovered[v]) {
                isCyclic = dfsDetectCycle(v, graph, discovered, visited, recStack);
            } else if (recStack[v]) {
                // cycle detected
                isCyclic = true;
            }
        }
        visited[u] = true;
        recStack[u] = false;
        return isCyclic;
    }

    private static void bfs(int u, List<List<Integer>> graph, boolean[] discovered, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        discovered[u] = true;

        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i=0; i<qsize; i++) {
                int u1 = queue.remove();
                visited[u1] = true;
                System.out.print("u1 = " + u1 + " ");
                for (int j=0; j<graph.get(u1).size(); j++) {
                    int v1 = graph.get(u1).get(j);
                    if (!discovered[v1]) {
                        queue.add(v1);
                        discovered[v1] = true;
                    }
                }
            }
        }
    }

    private static void bfsMinDistance(int u, List<List<Integer>> graph, boolean[] discovered, boolean[] visited, int[] minDistance) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        discovered[u] = true;
        minDistance[u] = 0;

        while (!queue.isEmpty()) {
            int qsize = queue.size();
            for (int i=0; i<qsize; i++) {
                int u1 = queue.remove();
                visited[u1] = true;
                for (int j=0; j<graph.get(u1).size(); j++) {
                    int v1 = graph.get(u1).get(j);
                    if (!discovered[v1]) {
                        queue.add(v1);
                        discovered[v1] = true;
                        minDistance[v1] = minDistance[u1] + 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 10; // size of graph
        int[][] edges = {{0, 1}, {0,2}, {1, 2}, {2, 4}, {4, 5}, {5, 2}};

        // graph structure
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] discovered = new boolean[n];
        boolean[] visited = new boolean[n];
        for (int i=0; i<n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        System.out.println();

        // dfs traverse
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                dfs(i, graph, discovered, visited);
            }
        }
        System.out.println();

        // connected components
        int connectedComponents = 0;
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                dfs(i, graph, discovered, visited);
                connectedComponents++;
            }
        }
        System.out.println();
        System.out.println("connectedComponents = " + connectedComponents);

        // detect cycle in graph (works for both directed and undirected)
        boolean[] recStack = new boolean[n];
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        boolean cycleExists = false;
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                cycleExists = dfsDetectCycle(i, graph, visited, discovered, recStack);
                if (cycleExists) break;
            }
        }
        System.out.println("cycleExists = " + cycleExists);


        // bfs
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfs(i, graph, discovered, visited);
            }
        }
        System.out.println();

        // min distance
        Arrays.fill(discovered, false);
        Arrays.fill(visited, false);
        int[] minDistance = new int[n];
        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                bfsMinDistance(i, graph, discovered, visited, minDistance);
            }
        }
        System.out.println("minDistance: " + Arrays.toString(minDistance));
        System.out.println();
    }

}
