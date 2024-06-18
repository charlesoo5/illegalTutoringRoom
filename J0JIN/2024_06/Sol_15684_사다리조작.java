import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, H;
    static boolean ladders[][];
    static boolean findAns;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new boolean[H + 1][N + 1];
        findAns = false;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ladders[a][b] = true;
        }

        for (int i = 0; i <= 3; i++) {
           comb(1, 0, i);
        }

        if(!findAns) {
            System.out.println(-1);
        }
    }

    public static void comb(int h, int cnt, int size) {
        if(findAns){
            return;
        }

        if (cnt == size) {
            if (check()) {
                System.out.println(size);
                findAns = true;
            }
            return;
        }

        for (int i = h; i <= H; i++) {
            for (int j = 1; j < N; j++) {
                if (ladders[i][j] || ladders[i][j - 1] || ladders[i][j + 1]) {
                    continue;
                }
                ladders[i][j] = true;
                comb(i, cnt + 1, size);
                ladders[i][j] = false;
            }
        }
    }

    static boolean check() {
        for (int i = 1; i <= N; i++) {
            int currPosition = i;
            for (int start = 1; start <= H; start++) {
                // 좌우로 이동 조건을 수정합니다.
                if (currPosition < N && ladders[start][currPosition]) {
                    currPosition++;
                } else if (currPosition > 1 && ladders[start][currPosition - 1]) {
                    currPosition--;
                }
            }

            if (i != currPosition) {
                return false;
            }
        }
        return true;
    }
}
