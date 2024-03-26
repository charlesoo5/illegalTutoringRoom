import java.util.*;
import java.util.Collections;

/**
 * sort
 */
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        Integer[] C = new Integer[A.length];


        for(int i=0; i<A.length; i++){
            C[i] = B[i];
        }

        // comparator는 primitive type은 안됨
        Arrays.sort(A);
        Arrays.sort(C, Collections.reverseOrder());

        for(int i=0; i< A.length; i++){
            answer += A[i] * C[i];
        }


        return answer;
    }
}