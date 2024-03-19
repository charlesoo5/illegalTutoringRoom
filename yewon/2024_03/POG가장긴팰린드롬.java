public class POG가장긴팰린드롬 {
    public int solution(String s)
    {
        int answer = 0;
        int len = s.length();
        char[] arr = s.toCharArray();
        int[][] dp = new int[len][len];

        for(int i=0; i<len; i++){
            dp[i][i] = 1;
            answer = 1;
        }

        for(int i=0; i<len-1; i++){
            if(arr[i] == arr[i+1]){
                dp[i][i+1] = 1;
                answer = 2;
            }
        }

        //length >= 3

        for(int i=1; i<len; i++){
            for(int j=0; j< len-i; j++){
                if(dp[j+1][j+i-1] == 1 && arr[j] == arr[j+i]) {
                    dp[j][j+i] = 1;
                    answer = Math.max(answer, i+1);
                }
            }
        }

        return answer;
    }
}
