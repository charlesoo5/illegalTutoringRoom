class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = i + 1; j < n; j++) {
                temp++;
                if (prices[j] < prices[i]) break;
            }
            answer[i] = temp;
        }
        
        return answer;
    }

}