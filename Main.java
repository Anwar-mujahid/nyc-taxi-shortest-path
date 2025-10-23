import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        String csvFile = "predicted_weights.csv"; 

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                // split on comma but allow for quotes around coordinates
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (parts.length < 3) continue;

                String from = parts[0].replace("\"", "").trim();
                String to = parts[1].replace("\"", "").trim();
                double weight = Double.parseDouble(parts[2].trim());
                if (from.equals(to)) {
                    weight = 0.0;  
                   
                }

                graph.addEdge(from, to, weight);
            }
        } catch (Exception e) {
            System.out.println(" Error reading CSV: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter source coordinate (e.g., 40.77,-73.87): ");
            String from = scanner.nextLine().replace("\"", "").trim();

            System.out.print("Enter destination coordinate (e.g., 40.76,-73.98): ");
            String to = scanner.nextLine().replace("\"", "").trim();

            if (from.equals(to)) {
                System.out.println(" Source and destination are the same.");
                System.out.println("Shortest path: " + from);
                return;
            }

            List<String> path = graph.dijkstra(from, to);

            if (path == null || path.isEmpty()) {
                System.out.println(" No path found.");
            } else {
                System.out.println(" Shortest path:");
                for (String node : path) {
                    System.out.println(" → " + node);
                }
            }
        } catch (Exception e) {
            System.out.println(" Invalid input.");
        }

        scanner.close();
    }
}

/*source = '40.72,-74.00'
destination = '40.76,-73.96' no path */
/* 40.77,-73.87)
 *  40.76,-73.98)direct path*/