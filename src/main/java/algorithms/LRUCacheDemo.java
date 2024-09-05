//package algorithms;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.PriorityQueue;
//
//class Node implements Comparable<Node> {
//    Integer key;
//    Integer priority;
//
//    public Node(Integer key, Integer priority) {
//        this.key = key;
//        this.priority = priority;
//    }
//
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(key, priority);
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        return Integer.compare(this.priority, o.priority);
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" + "key=" + key + ", priority=" + priority + '}';
//    }
//}
//
//class LRUCache {
//    private PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
//    private Map<Integer, String> cache = new HashMap<>();
//    private Map<Integer, Integer> currentPriority = new HashMap<>();
//    private Integer capacity;
//    private Integer time;
//
//    public LRUCache(Integer capacity) {
//        this.capacity = capacity;
//        this.time = 0;
//    }
//
//    private void evict() {
//        // Evict least recently used keyxa
//        Integer key = priorityQueue.remove().value;
//        cache.remove(key);
//        System.out.println("Evicting key=" + key);
//    }
//
//    private void increasePriority(Integer key) {
//        Node1 node = new Node1(key, currentPriority.get(key));
//        priorityQueue.remove(node);
//        this.time++;
//        node.index = this.time;
//        priorityQueue.add(node);
//        currentPriority.put(key, this.time);
//    }
//
//    public String get(Integer key) {
//        String value;
//        if (cache.containsKey(key)) {
//            value = cache.get(key);
//            // Update priority of key
//            increasePriority(key);
//        }
//        else {
//            // Fetch value from db
//            value = "FETCHED FROM DB";
//            // Put value in cache
//            put(key, value);
//        }
//        System.out.println("\nGet: " + key);
//        print();
//        return value;
//    }
//
//    public void put(Integer key, String value) {
//        // Check cache size
//        if (cache.size() >= capacity) {
//            // Evict from cache and priorityQueue
//            evict();
//        }
//        cache.put(key, value);
//        this.time++;
//        priorityQueue.add(new Node1(key, this.time));
//        currentPriority.put(key, this.time);
//        System.out.println("\nAdd key: " + key + ", value: " + value);
//        print();
//    }
//
//    private void print() {
//        System.out.println("Cache: " + cache);
//        System.out.println("Priority Queue: " + priorityQueue);
//    }
//}
//
//public class LRUCacheDemo {
//    public void execute() {
//        LRUCache cache = new LRUCache(5);
//        cache.put(1, "Harry");
//        cache.put(2, "Ron");
//        cache.put(3, "Hermoine");
//        cache.put(4, "Hagrid");
//        System.out.println("Value of 3: " + cache.get(3));
//        cache.put(5, "Dumbledore");
//        System.out.println("Value of 1: " + cache.get(1));
//        System.out.println("Value of 4: " + cache.get(4));
//        cache.put(6, "Tom");
//        cache.put(7, "Malfoy");
//    }
//}
