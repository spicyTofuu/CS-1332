import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Your implementation of various graph algorithms.
 *
 * @author Jiaxuan Chen
 * @version 1.0
 * @userid jchen813
 * @GTID 903425077
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("The input vertex or graph is null");
        }
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        if (!adjList.containsKey(start)) {
            throw new IllegalArgumentException("Start is not in the graph");
        }
        Queue<Vertex<T>> q = new LinkedList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        q.add(start);
        visitedSet.add(start);
        List<Vertex<T>> vertices = new ArrayList<>();
        while (!q.isEmpty()) {
            Vertex<T> v = q.remove();
            vertices.add(v);
            for (VertexDistance<T> w : adjList.get(v)) {
                if (!visitedSet.contains(w.getVertex())) {
                    q.add(w.getVertex());
                    visitedSet.add(w.getVertex());
                }
            }
        }
        return vertices;

    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("The input start or graph is null");
        }
        List<Vertex<T>> vertices = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("The start in not in the graph");
        }
        dfs(start, visitedSet, vertices, adjList);
        return vertices;
    }

    /**
     * A helper method for DFS recurse
     * @param u the starting vector to search for it's neighbors
     * @param visited a set that stores all the visited vectors
     * @param vertices A list to store all the vertices of the graph
     * @param adjList list of the vertices with distance pair to other vertices
     * @param <T> generic data type
     */
    private static <T> void dfs(Vertex<T> u, Set<Vertex<T>> visited,
                                List<Vertex<T>> vertices, Map<Vertex<T>, List<VertexDistance<T>>> adjList) {
        visited.add(u);
        vertices.add(u);
        for (VertexDistance<T> w : adjList.get(u)) {
            Vertex<T> temp = w.getVertex();
            if (!visited.contains(temp)) {
                dfs(temp, visited, vertices, adjList);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("The input start or graph is null");
        }
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        Queue<VertexDistance<T>> priorityQueue = new PriorityQueue<>();
        Map<Vertex<T>, Integer> shortest = new HashMap<>();
        if (!adjList.containsKey(start)) {
            throw new IllegalArgumentException("The start vertex in no in the graph");
        }
        for (Vertex<T> v : adjList.keySet()) {
            if (v.equals(start)) {
                shortest.put(v, 0);
            } else {
                shortest.put(v, Integer.MAX_VALUE);
            }
        }
        VertexDistance<T> s = new VertexDistance<>(start, 0);
        priorityQueue.add(s);
        while (!priorityQueue.isEmpty() && visitedSet.size() != adjList.size()) {
            VertexDistance<T> temp = priorityQueue.remove();
            if (!visitedSet.contains(temp.getVertex())) {
                visitedSet.add(temp.getVertex());
                shortest.put(temp.getVertex(), temp.getDistance());
                for (VertexDistance<T> w : adjList.get(temp.getVertex())) {
                    if (!visitedSet.contains(w.getVertex())) {
                        VertexDistance<T> newT = new VertexDistance<>(w.getVertex(),
                                w.getDistance() + temp.getDistance());
                        priorityQueue.add(newT);
                    }
                }
            }
        }
        return shortest;
    }

}
