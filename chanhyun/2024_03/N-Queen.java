import java.util.HashMap;
import java.util.HashSet;

class N-Queen {
    public int solution(int n) {
        HashMap<String, HashSet<Integer>> checked = new HashMap<>();
        checked.put("45", new HashSet<>());
        checked.put("90", new HashSet<>());
        checked.put("135", new HashSet<>());

        return DFS(n, checked, 0);
    }

    public int DFS(int n, HashMap<String, HashSet<Integer>> checked, int col) {
        int sum = 0;

        for (int row = 0; row < n; row++) {
            if (checked.get("45").contains(row - col) || checked.get("90").contains(row) || checked.get("135").contains(row + col))
                continue;

            checked.get("45").add(row - col);
            checked.get("90").add(row);
            checked.get("135").add(row + col);

            if (col == n - 1)
                sum++;
            else
                sum += DFS(n, checked, col + 1);

            checked.get("45").remove(row - col);
            checked.get("90").remove(row);
            checked.get("135").remove(row + col);
        }

        return sum;
    }
}