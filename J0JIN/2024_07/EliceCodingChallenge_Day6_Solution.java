import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] a = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            a[i] = scanner.nextInt();
        }
        Map<List<Integer>, Integer> dp = new HashMap<>();
        dp.put(new ArrayList<>(Collections.nCopies(N, 1)), 0);
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<>(Collections.nCopies(N, 1)));
        while (!queue.isEmpty()) {
            List<Integer> v = queue.pollFirst();
            for (int i = 0; i < v.size(); i++) {
                for (int j = i + 1; j < v.size(); j++) {
                    List<Integer> u = new ArrayList<>();
                    for (int k = 0; k < v.size(); k++) {
                        if (k == i) {
                            u.add(v.get(i) + v.get(j));
                        } else if (k != j) {
                            u.add(v.get(k));
                        }
                    }
                    Collections.sort(u);
                    if (!dp.containsKey(u)) {
                        queue.add(u);
                        dp.put(u, dp.get(v) + (1 - a[N - v.size()]) * v.get(i) * v.get(j));
                    } else {
                        dp.put(u, Math.min(dp.get(u), dp.get(v) + (1 - a[N - v.size()]) * v.get(i) * v.get(j)));
                    }
                }
            }
        }
        System.out.println(dp.get(Collections.singletonList(N)));
    }
}