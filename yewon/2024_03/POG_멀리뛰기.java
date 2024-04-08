import java.util.*;


/**
 * DP
 */
class Solution {
    public long solution(int n) {
        long answer = 0;

        if(n == 1) return 1;
        if(n == 2) return 2;

        long[] table = new long[n];

        table[0] = 1;
        table[1] = 2;


        for(int i=2; i<n; i++){
            table[i] = (table[i-1] + table[i-2]) % 1234567;
        }

        answer = table[n-1];
        return answer;
    }
}