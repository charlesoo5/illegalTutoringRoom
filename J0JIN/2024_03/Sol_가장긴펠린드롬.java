// 홀수, 짝수인 경우 생각할 것.
class Solution
{
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = palendrom(s, i, i);
            int len2 = palendrom(s, i, i + 1);

            int len = Math.max(len1, len2);
            answer = Math.max(answer, len);
        }

        return answer;
    }

    private int palendrom(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

}