package BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_13460_구슬탈출2 {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] startR = new int[2];
    static int[] startB = new int[2];
    static int minMoves = -1;

    static int[][] directions = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Position {
        int rX, rY, bX, bY, moveCount;

        public Position(int rX, int rY, int bX, int bY, int moveCount) {
            this.rX = rX;
            this.rY = rY;
            this.bX = bX;
            this.bY = bY;
            this.moveCount = moveCount;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') {
                    startR[0] = i;
                    startR[1] = j;
                    map[i][j] = '.';
                } else if (map[i][j] == 'B') {
                    startB[0] = i;
                    startB[1] = j;
                    map[i][j] = '.';
                }
            }
        }

        bfs();

        System.out.println(minMoves);
    }

    static void bfs() {
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(startR[0], startR[1], startB[0], startB[1], 0));
        visited[startR[0]][startR[1]][startB[0]][startB[1]] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.moveCount > 9) {
                break;
            }

            for (int d = 0; d < 4 ; d++) {
                int nRX = current.rX;
                int nRY = current.rY;
                int nBX = current.bX;
                int nBY = current.bY;
                int count = current.moveCount;

                boolean redHole = false;
                boolean blueHole = false;

                while (map[nBX + dx[d]][nBY + dy[d]] != '#') {
                    nBX += dx[d];
                    nBY += dy[d];
                    if (map[nBX][nBY] == 'O') {
                        blueHole = true;
                        break;
                    }
                }

                while (map[nRX + dx[d]][nRY + dy[d]] != '#') {
                    nRX += dx[d];
                    nRY += dy[d];
                    if (map[nRX][nRY] == 'O') {
                        redHole = true;
                        break;
                    }
                }

                if (blueHole) continue;
                if (redHole) {
                    minMoves = count + 1;
                    return;
                }

                if (nRX == nBX && nRY == nBY) {
                    int redDist = Math.abs(nRX - current.rX) + Math.abs(nRY - current.rY);
                    int blueDist = Math.abs(nBX - current.bX) + Math.abs(nBY - current.bY);
                    if (redDist > blueDist) {
                        nRX -= dx[d];
                        nRY -= dy[d];
                    } else {
                        nBX -= dx[d];
                        nBY -= dy[d];
                    }
                }

                if (!visited[nRX][nRY][nBX][nBY]) {
                    visited[nRX][nRY][nBX][nBY] = true;
                    queue.offer(new Position(nRX, nRY, nBX, nBY, count + 1));
                }
            }
        }
    }
}
