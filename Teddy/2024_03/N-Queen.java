class Solution {
	
	int[] visit;
	int answer;
	
	public int solution(int n) {
		answer = 0;
		visit = new int[n];
		
		search(0,n);
		
		return answer;
	}
	
	void search(int y, int n) {
		
		if(y==n) {
			answer++;
			return;
		}
		
		for (int i = 0; i < n; i++) {
			int dy = y;
			int dx = i;
			
			boolean check = true;
			// 가로줄 만큼 반복
			for (int j = 0; j < dy; j++) {
				// 세로줄 검사, 대각선 공식으로 검사
				if(visit[j] == dx || Math.abs(dy-j) == Math.abs(visit[j] - dx)) {
					check = false;
					break;
				}
			}
			
			if(check) {
				visit[dy] = i;
				search(dy+1, n);
				visit[dy] = -1;				
			}
		}
	}
}