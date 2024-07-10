import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int ans = length(input, 0, input.length);
        System.out.println(ans);
    }

    private static int length(char[] s, int start, int end) {
        int ret = 0;

        for (int i = start; i < end; i++) {
            if (s[i] == '(') {
                int K = s[i - 1] - '0'; // 이전 문자는 항상 숫자입니다.
                int subStart = i + 1;
                int brackets = 1;
                int j = i + 1;

                while (j < end && brackets > 0) {
                    if (s[j] == '(') brackets++;
                    else if (s[j] == ')') brackets--;
                    j++;
                }

                int subEnd = j - 1;
                ret--;
                ret += (K * length(s, subStart, subEnd));

                i = subEnd; 
            } else {
                ret++;
            }
        }
        return ret;
    }
}
