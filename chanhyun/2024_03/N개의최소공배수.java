class N개의최소공배수 {
    public int solution(int[] arr) {
        int[] primnumbers = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
        int[] maxCounts = new int[25];

        for (int number : arr) {
            int[] count = new int[25];

            for (int i = 0; i < 25 && primnumbers[i] <= number; i++) {
                while (number % primnumbers[i] == 0) {
                    number /= primnumbers[i];
                    count[i]++;
                }
            }

            for (int i = 0; i < 25; i++)
                maxCounts[i] = Math.max(maxCounts[i], count[i]);
        }

        int answer = 1;
        for (int i = 0; i < 25; i++)
            answer *= (int) Math.pow(primnumbers[i], maxCounts[i]);

        return answer;
    }
}
