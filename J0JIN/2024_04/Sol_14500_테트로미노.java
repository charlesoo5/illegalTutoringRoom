package 빡구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14500_테트로미노 {

    static int X, Y;
    static int[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int ans;

    static boolean[][] visited;

    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new int[X][Y];
        visited = new boolean[X][Y];
        ans = 0;

        for (int i = 0; i < Y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < X; j++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                visited[j][i] = true;
                dfs(j, i, 0, map[j][i], visited);
                visited[j][i] = false;
                exCase(j, i);
            }
        }

        System.out.println(ans);

    }

    private static void exCase(int x, int y) {

        for (int start = 0; start < 4; start++) {
            int ret = map[x][y];
            for (int i = 0; i < 3; i++) {
                int nx = x + dx[(start + i) % 4];
                int ny = y + dy[(start + i) % 4];

                if (0 <= nx && nx < X && 0 <= ny && ny < Y) {
                    ret += map[nx][ny];
                }else{
                    ret = 0;
                    i = 4;
                }
            }
            ans = Math.max(ans, ret);
        }
    }

    private static void dfs(int x, int y, int depth, int sum, boolean[][] visited) {
        if (depth >= 3) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (0 <= nx && nx < X && 0 <= ny && ny < Y && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny, depth + 1, sum + map[nx][ny], visited);
                visited[nx][ny] = false;
            }
        }
    }
}
