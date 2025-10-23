import java.util.*;

public class Graph {
    private Map<String, List<Edge>> adj = new HashMap<>();

    public void addEdge(String from, String to, double weight) {
        adj.putIfAbsent(from, new ArrayList<>());
        adj.get(from).add(new Edge(to, weight));
        // If undirected, add reverse edge:
        // adj.putIfAbsent(to, new ArrayList<>());
        // adj.get(to).add(new Edge(from, weight));
    }

    public List<String> dijkstra(String start, String end) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(n -> n.dist));

        for (String node : adj.keySet()) {
            dist.put(node, Double.MAX_VALUE);
        }
        dist.put(start, 0.0);
        pq.add(new Node(start, 0.0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.name.equals(end)) break;

            if (!adj.containsKey(curr.name)) continue;

            for (Edge edge : adj.get(curr.name)) {
                double newDist = dist.get(curr.name) + edge.weight;
                if (newDist < dist.getOrDefault(edge.to, Double.MAX_VALUE)) {
                    dist.put(edge.to, newDist);
                    prev.put(edge.to, curr.name);
                    pq.add(new Node(edge.to, newDist));
                }
            }
        }

        // Build path
        List<String> path = new LinkedList<>();
        String step = end;
        while (step != null) {
            path.add(0, step);
            step = prev.get(step);
        }

        return path.size() == 1 && !path.get(0).equals(start) ? new ArrayList<>() : path;
    }

    static class Edge {
        String to;
        double weight;

        Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node {
        String name;
        double dist;

        Node(String name, double dist) {
            this.name = name;
            this.dist = dist;
        }
    }
}

