// 노드번호 최대값을 구해주지 않아서 틀렸었음.
// 당연히 순서대로 올 줄?
class Solution {
    public int[] solution(int[][] edges) {
        // 노드 번호의 최대값
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[][] inout = new int[maxNode + 1][2];
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            inout[start][1]++;
            inout[end][0]++;
        }

        int[] answer = new int[4];
        findRoot(inout, answer);

        return answer;
    }

    private void findRoot(int[][] inout, int[] answer){
        // 총 그래프의 수
        int graph = 0;

        for(int i = 0; i < inout.length; i++){
            int in = inout[i][0];
            int out = inout[i][1];

            // 시작점 구하기
            if(in == 0 && out >= 2){
                graph = out;
                answer[0] = i;
            }

            // 막대 모양 구하기
            if(in >= 1 && out == 0){
                answer[2]++;
            }

            // 8자 모양 구하기
            if(in >= 2 && out == 2){
                answer[3]++;
            }

        }
        answer[1] = graph - answer[2] - answer[3];

    }

}