import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        boolean left = true;

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            throwBar(left, height);
            left = !left;
        }

        printMap();
    }

    public static void throwBar(boolean left, int height) {
        int row = R - height;
        if (left) {
            for (int col = 0; col < C; col++) {
                if (map[row][col] == 'x') {
                    map[row][col] = '.';
                    break;
                }
            }
        } else {
            for (int col = C - 1; col >= 0; col--) {
                if (map[row][col] == 'x') {
                    map[row][col] = '.';
                    break;
                }
            }
        }
        dropClusters();
    }

    public static void dropClusters() {
        visited = new boolean[R][C];
        for (int i = 0; i < C; i++) {
            if (map[R - 1][i] == 'x' && !visited[R - 1][i]) {
                bfs(R - 1, i);
            }
        }

        List<int[]> cluster = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    cluster.add(new int[]{i, j});
                    map[i][j] = '.';
                }
            }
        }

        int dropDist = Integer.MAX_VALUE;
        for (int[] mineral : cluster) {
            int x = mineral[0];
            int y = mineral[1];
            int dist = 0;
            while (x + dist + 1 < R && map[x + dist + 1][y] == '.') {
                dist++;
            }
            dropDist = Math.min(dropDist, dist);
        }

        for (int[] mineral : cluster) {
            map[mineral[0] + dropDist][mineral[1]] = 'x';
        }
    }

    public static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int cx = pos[0];
            int cy = pos[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 'x') {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    public static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
