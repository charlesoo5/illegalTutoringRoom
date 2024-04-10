class Solution {
    
    int[] chess;
    int answer = 0;
    
    public int solution(int n) {
        
        chess = new int[n];
        dfs(0, n);
        
        return answer;
    }
    
    public void dfs(int count, int n) {
        if(count == n) {
            answer++;
            return ;
        }
        
        for(int i=0; i<n; i++) {
            chess[count] = i;
            if(check(count)) {
                dfs(count+1, n);
            }
        }
    }
    
    public boolean check(int i) {
        for(int j=0; j<i; j++) {
            if(chess[i] == chess[j]) {
                return false;
            }
            
            if(Math.abs(i-j) == Math.abs(chess[i]-chess[j])) {
                return false;
            }
        }
        return true;
    }
}