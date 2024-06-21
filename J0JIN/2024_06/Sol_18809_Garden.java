import java.io.*;
import java.util.*;

public class Main {

    static int N, M, G, R;
    static int[][] map;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayList<Node> available = new ArrayList<>();

    public static class Node {
        int n, m;
        int time;
        char color;

        public Node(int n, int m, int time, char color) {
            this.n = n;
            this.m = m;
            this.time = time;
            this.color = color;
        }

        public Node(int n, int m) {
            this.n = n;
            this.m = m;
            this.time = 0;
            this.color = ' ';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    available.add(new Node(i, j));
                }
            }
        }

        List<int[]> result = new ArrayList<>();
        comb(0, available.size(), R, G, new int[R + G], 0, result);

        int maxFlowers = 0;

        for (int[] comb : result) {
            int[] redZone = new int[R];
            int[] greenZone = new int[G];

            int r = 0;
            int g = 0;
            for (int i = 0; i < R + G; i++) {
                if (comb[i] > 0) {
                    redZone[r] = comb[i] - 1;
                    r++;
                } else {
                    greenZone[g] = -comb[i] - 1;
                    g++;
                }
            }

            maxFlowers = Math.max(maxFlowers, countFlowers(available, redZone, greenZone));
        }

        System.out.println(maxFlowers);
    }

    public static void comb(int cur, int size, int r, int g, int[] combination, int index, List<int[]> result) {
        if (r == 0 && g == 0) {
            result.add(combination.clone());
            return;
        }

        if (cur >= size) {
            return;
        }

        // 현재 숫자를 빨간색으로 선택하는 경우
        if (r > 0) {
            combination[index] = cur + 1;
            comb(cur + 1, size, r - 1, g, combination, index + 1, result);
        }

        // 현재 숫자를 녹색으로 선택하는 경우
        if (g > 0) {
            combination[index] = -(cur + 1);
            comb(cur + 1, size, r, g - 1, combination, index + 1, result);
        }

        // 현재 숫자를 선택하지 않는 경우
        comb(cur + 1, size, r, g, combination, index, result);
    }

    public static int countFlowers(ArrayList<Node> available, int[] redZone, int[] greenZone) {
        Queue<Node> redQueue = new LinkedList<>();
        Queue<Node> greenQueue = new LinkedList<>();
        char[][] visitColor = new char[N][M];
        int[][] visitTime = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(visitColor[i], ' ');
            Arrays.fill(visitTime[i], -1);
        }

        for (int r : redZone) {
            Node node = available.get(r);
            redQueue.add(new Node(node.n, node.m, 0, 'R'));
            visitColor[node.n][node.m] = 'R';
            visitTime[node.n][node.m] = 0;
        }

        for (int g : greenZone) {
            Node node = available.get(g);
            greenQueue.add(new Node(node.n, node.m, 0, 'G'));
            visitColor[node.n][node.m] = 'G';
            visitTime[node.n][node.m] = 0;
        }

        int flowers = 0;

        while (!redQueue.isEmpty() || !greenQueue.isEmpty()) {
            int redSize = redQueue.size();
            int greenSize = greenQueue.size();

            while (redSize-- > 0) {
                Node node = redQueue.poll();
                if (visitColor[node.n][node.m] == 'F') continue;

                for (int[] direction : directions) {
                    int newN = node.n + direction[0];
                    int newM = node.m + direction[1];

                    if (newN >= 0 && newN < N && newM >= 0 && newM < M && map[newN][newM] != 0) {
                        if (visitColor[newN][newM] == ' ') {
                            visitColor[newN][newM] = 'R';
                            visitTime[newN][newM] = node.time + 1;
                            redQueue.add(new Node(newN, newM, node.time + 1, 'R'));
                        } else if (visitColor[newN][newM] == 'G' && visitTime[newN][newM] == node.time + 1) {
                            visitColor[newN][newM] = 'F';
                            flowers++;
                        }
                    }
                }
            }

            while (greenSize-- > 0) {
                Node node = greenQueue.poll();
                if (visitColor[node.n][node.m] == 'F') continue;

                for (int[] direction : directions) {
                    int newN = node.n + direction[0];
                    int newM = node.m + direction[1];

                    if (newN >= 0 && newN < N && newM >= 0 && newM < M && map[newN][newM] != 0) {
                        if (visitColor[newN][newM] == ' ') {
                            visitColor[newN][newM] = 'G';
                            visitTime[newN][newM] = node.time + 1;
                            greenQueue.add(new Node(newN, newM, node.time + 1, 'G'));
                        } else if (visitColor[newN][newM] == 'R' && visitTime[newN][newM] == node.time + 1) {
                            visitColor[newN][newM] = 'F';
                            flowers++;
                        }
                    }
                }
            }
        }

        return flowers;
    }
}
