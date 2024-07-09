import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] N = br.readLine().toCharArray();
        
        int last_Idx = N.length - 1;

        int pivot_Idx = 0;
        for (int i = last_Idx - 1; i >= 0; i--) {
            if (N[i] < N[i + 1]) {
                pivot_Idx = i;
                break;
            }
        }

        int swap_Idx = last_Idx;
        for (int i = last_Idx; i > pivot_Idx; i--) {
            if (N[i] > N[pivot_Idx]) {
                swap_Idx = i;
                break;
            }
        }

        swap(N, pivot_Idx, swap_Idx);
        Arrays.sort(N, pivot_Idx + 1, N.length);

        System.out.println(new String(N));
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}