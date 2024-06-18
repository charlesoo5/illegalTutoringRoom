import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] degree;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i = 0; i <= N; i++){
            list.add(new ArrayList<Integer>());
        }

        degree = new int[N+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for(int j = 1; j < num; j++ ){
                int after = Integer.parseInt(st.nextToken());
                list.get(before).add(after);
                degree[after]++;

                before = after;
            }
        }

        String ans = topology();
        System.out.println(ans);
    }

    public static String topology(){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 1; i <= N; i++){
            if(degree[i] == 0){
                q.offer(i);
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();
            ans.add(cur);

            for(int next : list.get(cur)){
                degree[next]--;

                if(degree[next] == 0){
                    q.offer(next);
                }
            }
        }

        if (ans.size() != N) {
            return "0\n";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append("\n");
        }

        return sb.toString();
    }
}
