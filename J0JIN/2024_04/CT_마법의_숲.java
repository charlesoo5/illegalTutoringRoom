package 빡구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_마법의_숲 {

    static int R, C, K, ci, di;
    static int[][] map;
    static int[] cp = new int[2];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[C + 1][R + 4];

        int ans = 0;
        int fill = 3;
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            ci = Integer.parseInt(st.nextToken());
            di = Integer.parseInt(st.nextToken());

            cp[0] = ci;
            cp[1] = 2;

            boolean moved = true;
            while (moved) {
                moved = false;
                if (down()) {
                    moved = true;
                    continue;
                }
                if (left()) {
                    moved = true;
                    continue;
                }
                if (right()) {
                    moved = true;
                }
            }

            mapFill(fill);
            fill += 2;

            if (checkReset()) {
                reset();
            } else {
                System.out.println(bfs());
                print();
                ans += bfs();
            }
        }
        System.out.println(ans);

    }

    private static int bfs() {
        boolean[][] visited = new boolean[C + 1][R + 4];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(cp);
        visited[cp[0]][cp[1]] = true;

        int highest = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx >= 1 && nx <= C && ny >= 1 && ny <= R + 3 && !visited[nx][ny]) {
                    if (map[current[0]][current[1]] % 2 == 1) {
                        if (map[nx][ny] == map[current[0]][current[1]] || map[nx][ny] == map[current[0]][current[1]] - 1) {
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                            highest = Math.max(highest, ny);
                        }
                    } else {
                        if(map[nx][ny] >0) {
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                            highest = Math.max(highest, ny);
                        }
                    }

                }
            }
        }
        return highest - 3;
    }

    private static boolean checkReset() {
        for (int i = 1; i <= C; i++) {
            if (map[i][3] > 0) {
                return true;
            }
        }
        return false;
    }

    private static void reset() {
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R + 3; j++) {
                map[i][j] = 0;
            }
        }
    }

    private static void print() {
        for (int i = 1; i <= C; i++) {
            for (int j = 1; j <= R + 3; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("================");
    }

    private static void mapFill(int fill) {
        map[cp[0]][cp[1]] = fill;
        map[cp[0] + 1][cp[1]] = fill;
        map[cp[0]][cp[1] + 1] = fill;
        map[cp[0] - 1][cp[1]] = fill;
        map[cp[0]][cp[1] - 1] = fill;

        map[cp[0] + dx[di]][cp[1] + dy[di]] = fill - 1;
    }

    private static boolean right() {
        if (1 < cp[0] && cp[0] < C - 1 && cp[1] + 2 < R + 4) {
            if (map[cp[0] + 1][cp[1] - 1] == 0 && map[cp[0] + 1][cp[1] + 1] == 0 && map[cp[0] + 1][cp[1] + 2] == 0
                    && map[cp[0] + 2][cp[1]] == 0 && map[cp[0] + 2][cp[1] + 1] == 0) {
                cp[0]++;
                cp[1]++;
                di = (di + 1) % 4;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean left() {
        if (0 < cp[0] - 2 && cp[1] + 2 < R + 4) {
            if (map[cp[0] - 1][cp[1] - 1] == 0 && map[cp[0] - 1][cp[1] + 1] == 0 && map[cp[0] - 1][cp[1] + 2] == 0
                    && map[cp[0] - 2][cp[1]] == 0 && map[cp[0] - 2][cp[1] + 1] == 0) {
                cp[0]--;
                cp[1]++;
                di = (di + 3) % 4;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static boolean down() {
        if (cp[1] + 2 < R + 4) {
            if (map[cp[0] + 1][cp[1] + 1] == 0 && map[cp[0] - 1][cp[1] + 1] == 0 && map[cp[0]][cp[1] + 2] == 0) {
                cp[1]++;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
