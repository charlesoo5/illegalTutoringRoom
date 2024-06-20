import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static int[][] graph;
    static int[][] reverseGraph;

    static class Node implements Comparable<Node> {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new int[N + 1][N + 1];
        reverseGraph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);
            Arrays.fill(reverseGraph[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from][to] = cost;
            reverseGraph[to][from] = cost;
        }

        int[] distFromX = dijkstra(X, graph);
        int[] distToX = dijkstra(X, reverseGraph);

        int maxDistance = 0;

        for (int i = 1; i <= N; i++) {
            if (distFromX[i] != Integer.MAX_VALUE && distToX[i] != Integer.MAX_VALUE) {
                maxDistance = Math.max(maxDistance, distFromX[i] + distToX[i]);
            }
        }

        System.out.println(maxDistance);
    }

    private static int[] dijkstra(int start, int[][] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentNode = current.vertex;

            if (visited[currentNode]) continue;
            visited[currentNode] = true;

            for (int i = 1; i <= N; i++) {
                if (graph[currentNode][i] != Integer.MAX_VALUE && !visited[i]) {
                    int newCost = current.cost + graph[currentNode][i];

                    if (dist[i] > newCost) {
                        dist[i] = newCost;
                        pq.offer(new Node(i, newCost));
                    }
                }
            }
        }

        return dist;
    }

}
