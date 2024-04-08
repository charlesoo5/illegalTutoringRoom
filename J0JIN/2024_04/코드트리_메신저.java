import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 코드트리_메신저 {

    private static class Node {
        int cur;
        int parent;
        int lc;
        int rc;
        int power;
        boolean off;

        public Node(int cur, int parent, int lc, int rc, int power, boolean off) {
            this.cur = cur;
            this.parent = parent;
            this.lc = lc;
            this.rc = rc;
            this.power = power;
            this.off = off;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "cur=" + cur +
                    ", parent=" + parent +
                    ", lc=" + lc +
                    ", rc=" + rc +
                    ", power=" + power +
                    ", off=" + off +
                    '}';
        }
    }

    static int N, Q;

    static Node[] nodeList;
    static int[] curList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < Q; tc++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "100":
                    init(st);
                    break;
                case "200":
                    setAlarm(st);
                    break;
                case "300":
                    setChangePower(st);
                    break;
                case "400":
                    setChangeParent(st);
                    break;
                case "500":
                    System.out.println(findChat(st));
                    break;
            }
        }


    }

    private static int findChat(StringTokenizer st) {
        int c = Integer.parseInt(st.nextToken());

        Queue<Node> q = new ArrayDeque<>();
        q.offer(nodeList[curList[c]]);

        int depth = 1;
        int ret = 0;

        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Node cur = q.poll();

                if (!nodeList[curList[cur.lc]].off && cur.lc != 0) {
                    q.offer(nodeList[curList[cur.lc]]);
                    if(nodeList[curList[cur.lc]].power >= depth) {
                        ret++;
                    }
                }
                if (!nodeList[curList[cur.rc]].off && cur.rc != 0 ) {
                    q.offer(nodeList[curList[cur.rc]]);
                    if( nodeList[curList[cur.rc]].power >= depth){
                        ret++;
                    }
                }

            }
            depth++;
        }

        return ret;
    }

    private static void setChangeParent(StringTokenizer st) {
        int c1 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());

        Node node1 = nodeList[curList[c1]];
        Node node2 = nodeList[curList[c2]];

        // 각 노드의 부모 노드 찾기
        Node parent1 = nodeList[node1.parent];
        Node parent2 = nodeList[node2.parent];

        // 부모 노드의 자식 노드 포인터를 업데이트
        if (parent1 != null) {
            if (parent1.lc == c1) {
                parent1.lc = c2;
            } else if (parent1.rc == c1) {
                parent1.rc = c2;
            }
        }

        if (parent2 != null) {
            if (parent2.lc == c2) {
                parent2.lc = c1;
            } else if (parent2.rc == c2) {
                parent2.rc = c1;
            }
        }

        // 노드의 부모 포인터를 서로 교환
        int tmp = node1.parent;
        node1.parent = node2.parent;
        node2.parent = tmp;

    }

    private static void setChangePower(StringTokenizer st) {
        int c = Integer.parseInt(st.nextToken());
        int power = Integer.parseInt(st.nextToken());

        nodeList[curList[c]].power = power;
    }

    private static void setAlarm(StringTokenizer st) {
        int c = Integer.parseInt(st.nextToken());
        if (nodeList[curList[c]].off) {
            nodeList[curList[c]].off = false;
        } else {
            nodeList[curList[c]].off = true;
        }
    }

    private static void init(StringTokenizer st) {
        nodeList = new Node[N + 1];
        curList = new int[N + 1];

        for (int i = 0; i < N+1; i++) {
            nodeList[i] = new Node(0, 0, 0, 0, 0, false);
        }

        int[] input = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            insert(i, input[i - 1], input[i + N - 1]);
        }

        for (int i = 1; i <= N; i++) {
            curList[i] = i;
        }
    }

    private static void insert(int idx, int parent, int power) {
        if (nodeList[parent].lc == 0) {
            nodeList[parent].lc = idx;
            nodeList[idx] = new Node(idx, parent, 0, 0, power, false);
        } else {
            nodeList[parent].rc = idx;
            nodeList[idx] = new Node(idx, parent, 0, 0, power, false);
        }

    }
}
