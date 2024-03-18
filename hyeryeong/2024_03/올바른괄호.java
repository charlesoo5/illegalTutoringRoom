import java.util.*;

// 올바른 괄호
class Solution {
    boolean solution(String s) {
        boolean answer = false;

        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                if(stack != null && !stack.isEmpty()) {
                    stack.pop();
                } else {
                    answer = false;
                    break;
                }
            }
            if(i==s.length()-1 && stack.size() == 0) {
                answer = true;
            }
        }
        
        return answer;
    }
}