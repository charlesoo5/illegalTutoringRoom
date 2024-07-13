import java.util.*;

public class Main {

    static ArrayList<Long> res = new ArrayList<>();
    static ArrayList<Long> now = new ArrayList<>();

    public static void dfs(ArrayList<Long> res, int x, long sum_, ArrayList<Long> now, long m) {
        if (x == res.size()) {
            now.add(sum_ + m);
            return;
        }
        dfs(res, x + 1, sum_, now, m);
        dfs(res, x + 1, sum_ + res.get(x), now, m);
    }

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Long> v = new ArrayList<>();

        for (int i = 0; i < (1 << n); i++) {
            long a = scanner.nextLong();
            v.add(a);
        }

        Collections.sort(v);

        for (int i = 1; i < v.size(); i++) {
            if (!now.contains(v.get(i))) {
                long m = v.get(i);
                dfs(res, 0, 0, now, m);
                res.add(v.get(i));
            }
            now.remove(v.get(i));
        }

        for (long nxt : res) {
            System.out.print(nxt + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        solve();
    }
}