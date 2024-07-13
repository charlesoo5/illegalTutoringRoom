import java.util.*;

class Main {
    static long n, a, b;
    static ArrayList<Long>[] v = new ArrayList[100050];
    static long[] dp = new long[100050];
    static final long inf = (long)1e12;

    static void dfs(long x, long par) {
        long ret = inf;
        for (Long nxt : v[(int)x]) {
            if (nxt == par) continue;
            dfs(nxt, x);
            ret = Math.min(ret, dp[nxt.intValue()]);
        }
        if (ret == inf) ret = 0;
        dp[(int)x] = x - ret;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);  
        
        n=scanner.nextLong();
        
        for(int i=0;i<100050;i++)
            v[i]=new ArrayList<>();
            
        for(int i=1;i<n;i++){
            a=scanner.nextLong();
            b=scanner.nextLong();
            
            v[(int)a].add(b);
            v[(int)b].add(a);
        }
        
       dfs(1,0);
       
       for(int i=1;i<=n;i++){
           if(dp[i]>=0)
               System.out.println("1");
           else
               System.out.println("0");
       }
   } 
}