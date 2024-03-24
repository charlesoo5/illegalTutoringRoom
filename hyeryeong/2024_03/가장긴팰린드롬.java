class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        char[] arr = s.toCharArray();
        int length = arr.length;
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<length; i++) {
            int left = i;
            int right = length-1;
            int palindromeLength = 0;
            int count = 1;
            
            while(left<right) {
                if(arr[left] == arr[right]) {
                    left++;
                    right--;
                    palindromeLength += 2;
                } else {
                    palindromeLength = 0;
                    left = i;
                    right = length-1-count;
                    count++;
                }
            }
            
            if(left == right) {
                palindromeLength++;
            }
            
            max = max > palindromeLength ? max : palindromeLength;
        }
        
        answer = max;

        return answer;
    }
}