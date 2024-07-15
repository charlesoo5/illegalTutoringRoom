import java.util.*;
import java.io.*;

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }

    public boolean union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV) return false;

        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
            size[rootU] += size[rootV];
        }
        return true;
    }

    public int getSize(int u) {
        int rootU = find(u);
        return size[rootU];
    }
}

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        System.out.println(minRedEdges(N, st));
    }

    public static int minRedEdges(int n, StringTokenizer colors) {
        UnionFind uf = new UnionFind(n);
        int redEdgeCount = 0;

        for (int t = 0; t < n - 1; t++) {
            int color = Integer.parseInt(colors.nextToken());

            if (color == 0) {
                int minSize = Integer.MAX_VALUE;
                int secondMinSize = Integer.MAX_VALUE;
                int minIndex = -1;
                int secondMinIndex = -1;

                for (int i = 0; i < n; i++) {
                    if (uf.find(i) == i) {
                        int size = uf.getSize(i);
                        if (size < minSize) {
                            secondMinSize = minSize;
                            secondMinIndex = minIndex;
                            minSize = size;
                            minIndex = i;
                        } else if (size < secondMinSize) {
                            secondMinSize = size;
                            secondMinIndex = i;
                        }
                    }
                }

                if (minIndex != -1 && secondMinIndex != -1) {
                    redEdgeCount += uf.getSize(minIndex) * uf.getSize(secondMinIndex);
                    uf.union(minIndex, secondMinIndex);
                }
            } else {
                int u = -1, v = -1;
                for (int i = 0; i < n; i++) {
                    if (uf.find(i) == i) {
                        if (u == -1) {
                            u = i;
                        } else {
                            v = i;
                            break;
                        }
                    }
                }

                if (u != -1 && v != -1) {
                    uf.union(u, v);
                }
            }
        }

        return redEdgeCount;
    }
}
