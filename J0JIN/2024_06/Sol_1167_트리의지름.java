import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;

    static class Node{
        int weight;
        int vertex;

        public Node(int weight, int vertex){
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < V; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                list.get(start).add(new Node(weight, end));
            }

        }

        bfs(1);
        int startVertex = 1;
        for(int i = 2; i <=V; i++){
            if(dist[i] > dist[startVertex]){
                startVertex = i;
            }
        }

        bfs(startVertex);
        int ans = 0;
        for(int i = 1; i <=V; i++){
            if(dist[i] > ans){
                ans = dist[i];
            }
        }

        System.out.println(ans);
    }

    public static void bfs(int start){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[V+1];
        dist = new int[V+1];

        visited[start] = true;
        q.add(start);

        while(!q.isEmpty()){
            int cur = q.poll();
            for(Node next : list.get(cur)){
                if (!visited[next.vertex]) {
                    visited[next.vertex] = true;
                    dist[next.vertex] = dist[cur] + next.weight;
                    q.add(next.vertex);
                }
            }
        }
    }
}
