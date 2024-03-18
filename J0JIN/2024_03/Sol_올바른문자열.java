import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        char arr[] = s.toCharArray();

        Stack<Character> st = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '('){
                st.push('(');
            }

            if(arr[i] == ')'){
                if(st.size() == 0){
                    return false;
                }else{
                    st.pop();
                }
            }
        }

        if(st.size() > 0){
            answer = false;
        }

        return answer;
    }
}