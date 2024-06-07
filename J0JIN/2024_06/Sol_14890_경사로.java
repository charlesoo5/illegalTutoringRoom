import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (canGo(map[i])) count++;
            if (canGo(getColumn(map, i))) count++;
        }

        System.out.println(count);
    }

    private static boolean canGo(int[] line) {
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) continue;
            if (Math.abs(line[i] - line[i + 1]) > 1) return false;

            // 조건1. 평평한지
            // 조건2. 맵 밖을 안벗어나는지
            // 조건3. 이미 경사로가 놓여져 있는지
            if (line[i] < line[i + 1]) { // 올라가는 경사로
                for (int j = 0; j < L; j++) {
                    if (i - j < 0 || line[i] != line[i - j] || visited[i - j]) return false;
                    visited[i - j] = true;
                }
            } else { // 내려가는 경사로
                for (int j = 1; j <= L; j++) {
                    if (i + j >= N || line[i + 1] != line[i + j] || visited[i + j]) return false;
                    visited[i + j] = true;
                }
            }
        }
        return true;
    }

    private static int[] getColumn(int[][] map, int col) {
        int[] column = new int[N];
        for (int i = 0; i < N; i++) {
            column[i] = map[i][col];
        }
        return column;
    }
}