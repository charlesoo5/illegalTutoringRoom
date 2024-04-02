#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<int> v;
int result = 0;

bool isPossible(int n) {
    if (v.size() < 1) return true;
    
    for (int i = 0; i < v.size(); i++) {
        if (v[i] == n || abs(v[i] - n) == abs(i - (int)v.size())) 
            return false;
    }
    
    return true;
}

void dfs(int d) {
    if (d >= N) {
        result++;
        return;
    }
    
    for (int n = 0; n < N; n++) {
        if (isPossible(n)) {
            v.push_back(n);
            dfs(d + 1);
            v.pop_back();
        }
    }
}

int solution(int n) {
    N = n;
    dfs(0);
    return result;
}