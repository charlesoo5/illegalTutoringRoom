import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for(int i = N + 1; i < 100_000_000; i++){
            if(count(i) == K){
                System.out.println(i);
                return;
            }
        }
    }

    public static int count(int num) {
        boolean[] digits = new boolean[10];
        int uniqueCount = 0;

        while (num > 0) {
            int digit = num % 10;
            if (!digits[digit]) {
                digits[digit] = true;
                uniqueCount++;
            }
            num /= 10;
        }

        return uniqueCount;
    }    
}