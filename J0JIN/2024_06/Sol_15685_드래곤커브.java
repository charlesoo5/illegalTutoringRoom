import java.io.*;
import java.util.*;

public class Main {

    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, getDirection(d, g));
        }

        System.out.println(getAns());
    }

    private static ArrayList<Integer> getDirection(int d, int g) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);

        for (int i = 0; i < g; i++) {
            for (int idx = list.size() - 1; idx >= 0; idx--) {
                int dir = (list.get(idx) + 1) % 4;
                list.add(dir);
            }
        }

        return list;
    }

    public static void draw(int x, int y, ArrayList<Integer> d) {
        map[x][y] = true;

        for (int dir : d) {
            switch (dir) {
                case 0:
                    map[++x][y] = true;
                    break;
                case 1:
                    map[x][--y] = true;
                    break;
                case 2:
                    map[--x][y] = true;
                    break;
                case 3:
                    map[x][++y] = true;
                    break;
            }
        }
    }

    private static int getAns() {
        int count = 0;

        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
                    count++;
            }
        }
        return count;
    }
}
