import java.util.ArrayDeque;

class 주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int index = stack.pop();
                answer[index] = i - index;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = prices.length - 1 - index;
        }

        return answer;
    }
}
