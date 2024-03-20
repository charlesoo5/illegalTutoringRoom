class 가장 긴 팰린드롬 {
    int solution(String s) {
        char[] arr = s.toCharArray();
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, Palindrome(arr, i, i));
            max = Math.max(max, Palindrome(arr, i, i + 1));
        }

        return max;
    }

    int Palindrome(char[] arr, int left, int right) {
        while (left >= 0 && right < arr.length && arr[left] == arr[right]) {
            left--;
            right++;
        }

        return right - left - 1;
    }
}
