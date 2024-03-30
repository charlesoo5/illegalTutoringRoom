import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        
        char[] arr = s.toCharArray();
        
        for (int i = 0; i < arr.length; i++) {			
			if(stack.isEmpty()) {
				stack.push(arr[i]);
			}else {
				char temp = stack.peek();
				if(arr[i] == ')') {
					stack.pop();
				}else {
					stack.push(arr[i]);
				}
			}
		}
        
        if(!stack.isEmpty()) {
        	answer = false;
        }
        
        return answer;
    }
}