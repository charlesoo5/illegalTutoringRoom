import java.util.Stack;

public class POG올바른괄호 {
    boolean solution(String s){
        boolean answer = true;

        Stack<Character> stack = new Stack<Character>();

        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if(arr[i] == '(') stack.push('(');
            else if(arr[i] == ')'){
                if(!stack.isEmpty()) stack.pop();
                else return false;
            }
        }

        if(!stack.isEmpty()) return false;


        return answer;
    }
}
