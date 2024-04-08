class Solution {
    int solution(String s){
    	int answer = 0;
        if (s.length() < 1) return answer;
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int odd = check(s, i, i);
            int even = check(s, i, i + 1);
            answer = Math.max(answer, Math.max(odd, even));
        }
        return answer;
    }
    
    private int check(String str, int left, int right) {
        while (left >= 0 && right < str.length()) {
        	if(str.charAt(left) != str.charAt(right)) {
        		break;
        	}
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    public static void main(String[] args) {
    	Solution solution = new Solution();
    	solution.solution("abcdcba");
    }
}