import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_테트로미노 {

    private static int N, M;
    private static int[][] board;
    private static boolean[][] visited;

    private static int[] di = {-1, 1, 0, 0};
    private static int[] dj = {0, 0, -1, 1};
    private static int ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input

        //1. ㅜ 모양 처리 (DFS로 불가능)
        ans = Math.max(ans, cross());

        //2. DFS
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 0);
                visited[i][j] = false;
            }
        }

        System.out.println(ans);

    }

    public static int cross() {
        int max = 0;
        int sum;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    sum = board[i][j];
                    for (int d = 0; d < 4; d++) {
                        if (d == k) continue;
                        int ni = i + di[d];
                        int nj = j + dj[d];

                        if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            sum += board[ni][nj];

                        } else {
                            sum = 0;
                            break;
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        return max;
    }


    public static void dfs(int si, int sj, int sum, int depth) {
        if (depth == 3) {
            ans = Math.max(sum, ans);
            return;

        }
        for (int d = 0; d < 4; d++) {
            int ni = si + di[d];
            int nj = sj + dj[d];
            if (ni >= 0 && ni < N && nj >= 0 && nj < M && !visited[ni][nj]) {
                visited[ni][nj] = true;
                dfs(ni, nj, sum + board[ni][nj], depth + 1);
                visited[ni][nj] = false;
            }
        }
    }
}
