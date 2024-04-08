class Solution {
    public long solution(int n) {
        long answer = 0;
        
        long[] dp = new long[n + 2];
        dp[1] = 1;
        dp[2] = 2;
        
        for (int i = 3; i < n + 1; i++) {
			dp[i] = (dp[i-1] + dp [i-2])  % 1234567;
		} 
        
        return dp[n];
    }
}