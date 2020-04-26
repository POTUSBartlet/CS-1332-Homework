import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Stack;


/**
 * Your implementation of various different graph algorithms.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     * <p>
     * When exploring a vertex, make sure to explore in the order that the
     * adjacency list returns the neighbors to you. Failure to do so may cause
     * you to lose points.
     * <p>
     * You may import/use {@code java.util.Set}, {@code java.util.List},
     * {@code java.util.Queue}, and any classes that implement the
     * aforementioned interfaces, as long as it is efficient.
     * <p>
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input
     * is null, or if {@code start} doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start,
                                                         Graph<T> graph) {
        Map<Vertex<T>, List<Edge<T>>> list = graph.getAdjList();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> visited = new ArrayList<>();

        if (start == null) {
            throw new IllegalArgumentException("Start cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else {
            queue.add(start);
            visited.add(start);
            while (queue.size() > 0) {
                for (Edge<T> i : list.get(queue.remove())) {
                    if (!visited.contains(i.getV())) {
                        queue.add(i.getV());
                        visited.add(i.getV());

                    }
                }
            }
        }
        return visited;
    }


    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * {@code start} which represents the starting vertex.
     * <p>
     * When deciding which neighbors to visit next from a vertex, visit the
     * vertices in the order presented in that entry of the adjacency list.
     * <p>
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * most if not all points for this method.
     * <p>
     * You may import/use {@code java.util.Set}, {@code java.util.List}, and
     * any classes that implement the aforementioned interfaces, as long as it
     * is efficient.
     * <p>
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input
     * is null, or if {@code start} doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start,
                                                       Graph<T> graph) {
        Map<Vertex<T>, List<Edge<T>>> list = graph.getAdjList();
        Stack<Vertex<T>> stack = new Stack<>();
        List<Vertex<T>> visited = new ArrayList<>();

        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else {
            depthFirstHelper(list, start, visited, stack);
        }
        return visited;

    }

    /**
     * depth first helper method
     * @param list list of vertices
     * @param start starting node
     * @param visited the visited set
     * @param stack the stack for elements
     * @param <T> generic
     */

    private static <T> void depthFirstHelper(Map<Vertex<T>,
            List<Edge<T>>> list, Vertex<T> start, List<Vertex<T>> visited,
                                             Stack<Vertex<T>> stack) {
        stack.push(start);
        visited.add(start);
        for (Edge<T> x : list.get(stack.pop())) {
            if (!visited.contains(x.getV())) {
                depthFirstHelper(list, x.getV(), visited, stack);
            }
        }
    }


    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     * <p>
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing infinity)
     * if no path exists.
     * <p>
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Map}, and {@code java.util.Set} and any class that
     * implements the aforementioned interfaces, as long as it's efficient.
     * <p>
     * You should implement the version of Dijkstra's where you terminate the
     * algorithm once either all vertices have been visited or the PQ becomes
     * empty.
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start index representing which vertex to start at (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every other node
     * in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     * doesn't exist in the graph.
     */
        public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                Graph<T> graph) {
            Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();
            Map<Vertex<T>, Integer> visited = new HashMap<>();
            Queue<Edge<T>> queue = new PriorityQueue<>();
            Set<Vertex<T>> visitedSet = new HashSet<>();

        if (start == null) {
            throw new IllegalArgumentException("Start vertex cannot be null");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Graph cannot be null");
        } else {
            for (Vertex<T> vertex : adjList.keySet()) {
                visited.put(vertex, vertex.hashCode());
            }
            visited.put(start, 0);
            queue.add(new Edge<>(start, start, 0));
            while (!queue.isEmpty()) {
                Edge<T> currentEdge = queue.remove();
                for (Edge<T> nextEdge : adjList.get(currentEdge.getV())) {
                    int distance = currentEdge.getWeight() + nextEdge.getWeight();
                    if (visited.get(nextEdge.getV()) > distance) {
                        visited.put(nextEdge.getV(), distance);
                        queue.add(new Edge<>(nextEdge.getU(), nextEdge.getV(), distance));
                    }
                }
            }
        }
        return visited;
    }
    /**
     * Runs Prim's algorithm on the given graph and return the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     * <p>
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     * <p>
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * opposite edge to the set as well. This is for testing purposes.
     * <p>
     * You may assume that there will only be one valid MST that can be formed.
     * <p>
     * You should NOT allow self-loops into the MST.
     * <p>
     * You may import/use {@code java.util.PriorityQueue},
     * {@code java.util.Set}, and any class that implements the aforementioned
     * interface, as long as it's efficient.
     * <p>
     * The only instance of {@code java.util.Map} that you may use is the
     * adjacency list from {@code graph}. DO NOT create new instances of Map
     * for this method (storing the adjacency list in a variable is fine).
     * <p>
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin Prims on
     * @param graph the graph we are applying Prims to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input
     * is null, or if {@code start} doesn't exist in the graph
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {

        if (start == null || graph == null) {
            throw new IllegalArgumentException("cannot be null");
        }
        Map<Vertex<T>, List<Edge<T>>> adjList = graph.getAdjList();
        if (adjList.get(start) == null) {
            throw new IllegalArgumentException("cannot be null");
        }
        HashSet<Edge<T>> minSpanSet = new HashSet<>();
        PriorityQueue<Edge<T>> priorityQueue = new PriorityQueue<>();
        List<Vertex<T>> visitedList = new ArrayList<>();
        // add the adjacent edges of the start node to the priority queue
        for (Edge<T> edge : adjList.get(start)) {
            priorityQueue.add(edge);
        }
        //add start node to visited list
        visitedList.add(start);
        while (!priorityQueue.isEmpty()) {
            Edge<T> minEdge = priorityQueue.remove();
            Vertex<T> currentVertex = minEdge.getU();
            Vertex<T> nextVertex = minEdge.getV();
            if (!(visitedList.contains(currentVertex)
                    && visitedList.contains(nextVertex))) {
                //add the edge to the minimum span
                //because it is undirected, add the edge in both directions
                minSpanSet.add(minEdge);
                minSpanSet.add(new Edge<>(minEdge.getV(), minEdge.getU(),
                        minEdge.getWeight()));
                // add the next vertex to the visited list
                visitedList.add(nextVertex);
                //add the adjacent edges of the next node to the priority queue
                //if they haven't been visited
                for (Edge<T> edge : adjList.get(nextVertex)) {
                    // if we haven't visited the node before
                    if (!visitedList.contains(edge.getV())) {
                        // add it and the corresponding edge to priority queue
                        priorityQueue.add(edge);
                    }
                }
            }
        }
        // check that mst is valid or return null
        // check that it's size is equal to
        if((minSpanSet.size() / 2) + 1 != adjList.keySet().size()){
            return null;
        }
    return minSpanSet;
    }
}