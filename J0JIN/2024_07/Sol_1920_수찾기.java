import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> hash = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());
            if(hash.containsKey(key)){
                hash.put(key, hash.get(key)+1);
            }else{
                hash.put(key, 1);
            }
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++){
            int key = Integer.parseInt(st.nextToken());
            if(hash.containsKey(key)){
                System.out.println(1);
            }else{
                System.out.println(0);
            }
        }

    }
}
