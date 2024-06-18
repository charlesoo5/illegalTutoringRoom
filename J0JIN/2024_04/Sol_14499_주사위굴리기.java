package 빡구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14499_주사위굴리기 {

    static int N, M, x, y, K;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static int[][] map;
    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int j = 0; j < N; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        dice = new int[7];

        st = new StringTokenizer(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            play(Integer.parseInt(st.nextToken()));
        }
    }

    private static void play(int d) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
            switch (d) {
                case 1:
                    right();
                    break;
                case 2:
                    left();
                    break;
                case 3:
                    up();
                    break;
                case 4:
                    down();
                    break;
            }

            x = nx;
            y = ny;

            stamp();

            System.out.println(dice[1]);
        }
    }

    private static void stamp() {
        // 이동한 칸에 쓰여있는 숫자가 0이면
        if (map[x][y] == 0) {
            // 주사위의 바닥면에 쓰여 있는 수가 칸에 복사
            map[x][y] = dice[6];
        } else { //  0이 아닌 경우에는
            // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며
            dice[6] = map[x][y];
            // 칸에 쓰여 있는 수는 0이 된다.
            map[x][y] = 0;
        }
    }

    private static void down() {
        int[] newDice = {0, dice[2], dice[6], dice[3], dice[4], dice[1], dice[5]};
        arrayCopy(dice, newDice);
    }

    private static void up() {
        int[] newDice = {0, dice[5], dice[1], dice[3], dice[4], dice[6], dice[2]};
        arrayCopy(dice, newDice);
    }

    private static void left() {
        int[] newDice = {0, dice[3], dice[2], dice[6], dice[1], dice[5], dice[4]};
        arrayCopy(dice, newDice);
    }

    private static void right() {
        int[] newDice = {0, dice[4], dice[2], dice[1], dice[6], dice[5], dice[3]};
        arrayCopy(dice, newDice);
    }

    private static void arrayCopy(int[] dist, int[] src) {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = src[i];
        }
    }
}
