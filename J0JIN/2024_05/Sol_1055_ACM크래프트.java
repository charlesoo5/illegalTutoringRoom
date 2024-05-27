import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, W;
    static int[] D, indegree, result;
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            // N, K 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // D 입력
            D = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                D[i] = Integer.parseInt(st.nextToken());
            }

            // 초기화
            adjList = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                adjList.add(new ArrayList<>());
            }

            indegree = new int[N + 1];
            result = new int[N + 1];

            // 간선 정보 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                adjList.get(start).add(end);
                indegree[end]++;
            }

            // W 입력
            W = Integer.parseInt(br.readLine());

            // 위상 정렬 수행
            topologicalSort();

            // 결과 출력
            System.out.println(result[W]);
        }
    }

    private static void topologicalSort() {
        Queue<Integer> q = new ArrayDeque<>();

        // 초기 진입 차수가 0인 노드들 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                result[i] = D[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adjList.get(cur)) {
                result[next] = Math.max(result[next], result[cur] + D[next]);
                if (--indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}
