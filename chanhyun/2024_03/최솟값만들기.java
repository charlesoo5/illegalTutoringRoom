import java.util.Arrays;

class 최솟값_만들기 {
    public int solution(int []A, int []B) {
        int N = A.length;

        Arrays.sort(A);
        Arrays.sort(B);

        int sum = 0;
        for(int i = 0; i < N; i++)
            sum += A[i] * B[N - 1 - i];

        return sum;
    }
}