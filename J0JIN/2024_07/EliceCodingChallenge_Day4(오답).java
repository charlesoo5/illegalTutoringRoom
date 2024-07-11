import java.util.*;
import java.io.*;

class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> list;
    static int[][] table;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        indegree = new int[N + 1];
        table = new int[N + 1][2];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            list.get(start).add(end);
            list.get(end).add(start);
            indegree[start]++;
            indegree[end]++;
        }

        topologicalSort();

        for (int i = 1; i <= N; i++) {
            if (table[i][0] >= table[i][1]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    private static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 1) {
                q.add(i);
                table[i][0] = i;
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            int maxDist = Integer.MIN_VALUE;
            int bestChild = -1;

            for (int child : list.get(node)) {
                if (indegree[child] > 0) {
                    int dist = table[node][1] + child - table[node][0];
                    if (dist > maxDist) {
                        maxDist = dist;
                        bestChild = child;
                    }
                }
            }

            if (bestChild != -1) {
                table[bestChild][0] = table[node][1] + bestChild;
                table[bestChild][1] = table[node][0];
                if (--indegree[bestChild] == 1) {
                    q.add(bestChild);
                }
            }

            indegree[node] = 0; 
        }
    }
}
