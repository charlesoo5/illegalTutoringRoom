class Solution {

    static final int MOD = 1_234_567;

    public long solution(int n) {
        long answer = 0;

        int[] dp = new int[2001];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = (dp[i-1] + dp[i-2])%MOD;
        }

        answer = dp[n];

        return answer;
    }
}