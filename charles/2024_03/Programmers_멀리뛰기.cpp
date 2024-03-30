#include <string>
#include <vector>
using namespace std;
typedef long long ll;
vector<ll> dp;

ll jump(int n) {
    if (n == 1 || n == 2) return n;
    if (dp[n] > 0) return dp[n];
    return dp[n] = (jump(n - 1) + jump(n - 2)) % 1234567;
}

long long solution(int n) {
    dp = vector<ll>(n + 1);
    return jump(n);
}