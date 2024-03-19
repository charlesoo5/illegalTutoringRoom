import java.util.*;

// https://www.acmicpc.net/problem/1208 부분수열의 합 문제랑 비슷하다고 느낌.
class Solution {

    static int maxCount;

    public int[] solution(int[][] dice) {
        int N = dice.length;
        maxCount = 0;

        boolean[] select = new boolean[N];
        int[] answer = new int[N/2];
        selectDice(select, 0, 0, N/2, dice, answer);

        return answer;
    }

    private void selectDice(boolean[] select, int idx, int cnt, int end, int[][] dice, int[] answer) {
        if (cnt == end) {
            int[][] diceA = new int[end][6];
            int[][] diceB = new int[end][6];

            int idxA = 0;
            int idxB = 0;
            int[] selectedDice = new int[end];

            for(int i = 0; i < select.length; i++){
                if(select[i]){
                    diceA[idxA] = dice[i];
                    selectedDice[idxA] = i;
                    idxA++;
                }else{
                    diceB[idxB] = dice[i];
                    idxB++;
                }
            }

            List<Integer> sumsA = getAllSums(diceA);
            List<Integer> sumsB = getAllSums(diceB);

            int count = 0;
            for (int sumA : sumsA) {
                count += count(sumsB, sumA);
            }

            int selectIdx = 0;
            if(count > maxCount){
                for(int i = 0; i < selectedDice.length; i++){
                    answer[i] = selectedDice[i]+1;
                }
                maxCount = count;
            }

            return;
        }

        if (idx >= select.length) {
            return;
        }

        select[idx] = true;
        selectDice(select, idx + 1, cnt + 1, end, dice, answer);
        select[idx] = false;
        selectDice(select, idx + 1, cnt, end, dice, answer);
    }

    private int count(List<Integer> sumsB, int target) {
        int left = 0, right = sumsB.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sumsB.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private List<Integer> getAllSums(int[][] diceGroup) {
        List<Integer> sums = new ArrayList<>();
        sums.add(0);
        for (int[] dice : diceGroup) {
            List<Integer> newSums = new ArrayList<>();
            for (int side : dice) {
                for (int existingSum : sums) {
                    newSums.add(existingSum + side);
                }
            }
            sums = newSums;
        }
        Collections.sort(sums);
        return sums;
    }

}