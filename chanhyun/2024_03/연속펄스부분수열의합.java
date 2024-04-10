class 연속펄스부분수열의합 {
    public long solution(int[] sequence) {
        long min = 0;
        long max = 0;
        long sum = 0;
        
        for(int i = 0; i < sequence.length; i++) {
            if(i % 2 == 0)
                sum -= sequence[i];
            else
                sum += sequence[i];
            
            if(min > sum)
                min = sum;
            if(max < sum)
                max = sum;
        }
        
        return max - min;
    }
}