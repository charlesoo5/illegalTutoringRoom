#include <vector>
#include <stack>
using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> result(prices.size());
    stack<vector<int>> stk;
    stk.push({ prices[0], 0 });
    
    for (int i = 1; i < prices.size(); i++) {
        // 하락
        if (prices[i] < stk.top()[0]) {
            while(!stk.empty() && stk.top()[0] > prices[i]) {
                result[stk.top()[1]] = i - stk.top()[1];
                stk.pop();
            }
        }
        stk.push({ prices[i], i});
    }
    
    while(!stk.empty()) {
        result[stk.top()[1]] = prices.size() - 1 - stk.top()[1];
        stk.pop();
    }
    result[prices.size() - 1] = 0;
    
    return result;
}