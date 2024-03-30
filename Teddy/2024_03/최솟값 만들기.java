import java.util.Arrays;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;

        int[] a = A;
        int[] b = B;
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        for (int i = 0; i < a.length; i++) {
			answer += a[i] * b[B.length - i - 1];
		}

        return answer;
    }
}