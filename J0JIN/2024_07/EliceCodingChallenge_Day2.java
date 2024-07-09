import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] a = new int[n];
        for(int i = 0; i < n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        for(int t = 0; t < m; t++){
            st = new StringTokenizer(br.readLine());

            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] list = getList(a, i - 1, j - 1);

            Arrays.sort(list);

            System.out.println(list[k - 1]);
        }
    }

    private static int[] getList(int[] a, int i, int j){
        int[] list = new int[j - i + 1];

        int idx = 0;
        for(int t = i; t <= j; t++){
            list[idx++] = a[t];
        }

        return list;
    }
}
