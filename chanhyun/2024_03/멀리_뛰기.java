class 멀리_뛰기 {
    public long solution(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] save = new int[n+1];
        save[1] = 1;
        save[2] = 2;

        for (int i = 3; i <= n; i++)
            save[i] = (save[i-1] + save[i-2]) % 1234567;

        return save[n];
    }
}