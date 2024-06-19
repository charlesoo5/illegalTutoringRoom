import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1003_피보나치함수 {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[41][2];

        dp[0][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i < 41; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        for (int n = 0; n < N; n++) {
            int k = Integer.parseInt(br.readLine());
            System.out.println(dp[k][0] +" " + dp[k][1]);
        }
    }
}
