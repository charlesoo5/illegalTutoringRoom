#include <string>
#include <vector>
using namespace std;
typedef long long ll;
vector<ll> dp;

int func(int n) {
    if (n < 2) return n;
    
    if (dp[n] > 0) return dp[n];
    
    return dp[n] = (func(n - 1) + func(n - 2)) % 1234567;
}

int solution(int n) {
    dp = vector<ll>(n + 1);
    return func(n);
}