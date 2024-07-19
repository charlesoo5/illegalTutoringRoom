import java.util.*;
import java.io.*;

class Solution {
    
    static int N;
    
    public int solution(String[] friends, String[] gifts) {
        HashMap<String, Integer> hash = new HashMap<>();
        
        N = friends.length;
        
        for(int i = 0; i < N; i++){
            hash.put(friends[i], i);
        }
        
        int[][] giftArray = new int[N][N];
        int[] giftScore = new int[N];
        
        for(int i = 0; i < gifts.length; i++){
            String[] input = gifts[i].split(" ");
            int A = hash.get(input[0]);
            int B = hash.get(input[1]);
            
            giftArray[A][B]++;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                giftScore[i] += giftArray[i][j];
                giftScore[j] -= giftArray[i][j];
            }
        }
        
        int ret = 0;
        
        for(int i = 0; i < N; i++){
            int ans = 0;
            for(int j = 0; j < N; j++){
                if(giftArray[i][j] > giftArray[j][i]){
                    ans++;
                }
                
                if(giftArray[i][j] == giftArray[j][i]){
                    if(giftScore[i] > giftScore[j]){
                        ans++;
                    }
                }
            }
            
            ret = Math.max(ret, ans);
        }
        
        return ret;
    }
}