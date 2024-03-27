class 올바른_괄호 {
    boolean solution(String s) {
        char[] arr = s.toCharArray();
        int count = 0;

        for (char c : arr) {
            if (c == '(')
                count++;
            else {
                count--;

                if (count < 0)
                    return false;
            }
        }

        return count == 0;
    }
}