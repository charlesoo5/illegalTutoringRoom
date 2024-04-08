import java.awt.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

class 미로탈출 {
    class Node {
        int y;
        int x;
        int cost;

        Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    int[] dy = {-1, 0, 1, 0};
    int[] dx = {0, 1, 0, -1};

    public int solution(String[] maps) {
        Point start = null, end = null, lever = null;

        int[][] map = new int[maps.length][maps[0].length()];
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'S')
                    start = new Point(j, i);
                else if (maps[i].charAt(j) == 'E')
                    end = new Point(j, i);
                else if (maps[i].charAt(j) == 'L')
                    lever = new Point(j, i);

                if (maps[i].charAt(j) == 'X')
                    map[i][j] = 1;
                else
                    map[i][j] = 0;
            }
        }

        int leverCost = findPath(map, start, lever);
        if (leverCost == -1)
            return -1;

        int endCost = findPath(map, lever, end);
        if (endCost == -1)
            return -1;

        return leverCost + endCost;
    }

    private int findPath(int[][] map, Point start, Point end) {
        if (start.equals(end))
            return 0;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        queue.add(new Node(start.y, start.x, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.y == end.y && current.x == end.x)
                return current.cost;

            for (int i = 0; i < 4; i++) {
                int ny = current.y + dy[i];
                int nx = current.x + dx[i];

                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map[0].length)
                    continue;

                if(map[ny][nx] == 1 || visited[ny][nx])
                    continue;

                visited[ny][nx] = true;
                queue.add(new Node(ny, nx, current.cost + 1));
            }
        }

        return -1;
    }
}