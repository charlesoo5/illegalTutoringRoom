import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    final static long MOD = 1_000_000_007;
    public static long[][] A1 = {{1, 1}, {1, 0}};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        long[][] A =  {{1, 1}, {1, 0}};

        System.out.println(pow(A, N - 1)[0][0]);
    }

    public static long[][] pow(long[][] A, long exp){

        if(exp ==1 || exp == 0){
            return A;
        }

        long[][] ret = pow(A,exp/2);
        ret = multiply(ret, ret);

        if(exp%2 == 1L){
            ret = multiply(ret, A1);
        }

        return ret;
    }

    public static long[][] multiply(long[][] A1, long[][] A2) {

        long[][] ret = new long[2][2];

        ret[0][0] = ((A1[0][0] * A2[0][0]) + (A1[0][1] * A2[1][0])) % MOD;
        ret[0][1] = ((A1[0][0] * A2[0][1]) + (A1[0][1] * A2[1][1])) % MOD;
        ret[1][0] = ((A1[1][0] * A2[0][0]) + (A1[1][1] * A2[1][0])) % MOD;
        ret[1][1] = ((A1[1][0] * A2[0][1]) + (A1[1][1] * A2[1][1])) % MOD;

        return ret;
    }
}
