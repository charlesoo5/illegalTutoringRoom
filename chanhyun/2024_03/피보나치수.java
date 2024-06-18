class 피보나치수 {
    public int solution(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] save = new int[n+1];
        save[0] = 0;
        save[1] = 1;

        for (int i = 2; i <= n; i++)
            save[i] = (save[i-1] + save[i-2]) % 1234567;

        return save[n];
    }
}