import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] A, map;
    static ArrayList<Tree> tree;
    static ArrayDeque<Integer> death;

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static class Tree {
        int row;        // 나무가 있는 행
        int col;        // 나무가 있는 열
        int age;        // 나무의 나이
        boolean dead;    // 나무 생존 여부

        public Tree(int row, int col, int age) {
            this.row = row;
            this.col = col;
            this.age = age;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = 5;
            }
        }

        tree = new ArrayList<>();
        death = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            tree.add(new Tree(x, y, z));
        }
        Collections.sort(tree, (a,b)-> a.age - b.age);

        for(int tc = 0; tc < K; tc++){
            Q1();
            Q2();
            Q3();
            Q4();
        }

        System.out.println(tree.size());
    }

    public static void Q1() {
        for(int i = 0 ; i < tree.size(); i++){
            Tree cur = tree.get(i);
            if(map[cur.row][cur.col] < cur.age){
                death.add(i);
            }else{
                map[cur.row][cur.col] -= cur.age;
                cur.age++;
            }
        }
    }

    public static void Q2() {
        while(!death.isEmpty()){
            int idx = death.poll();
            Tree cur = tree.get(idx);
            map[cur.row][cur.col] += cur.age/2;
            cur.dead=true;
        }
    }

    public static void Q3(){
        ArrayList<Tree> born = new ArrayList<>();
        for(Tree cur : tree){
            if(cur.dead){
                continue;
            }

            if(cur.age % 5 == 0){
                for(int d = 0; d < 8; d++){
                    int nc = cur.col +dc[d];
                    int nr = cur.row +dr[d];
                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
                        continue;
                    }
                    born.add(new Tree(nr, nc, 1));
                }
            }
        }

        for(Tree cur : tree){
            if(!cur.dead){
                born.add(cur);
            }
        }

        tree = born;
    }

    public static void Q4() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] += A[r][c];
            }
        }
    }
}
