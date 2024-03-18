#include <iostream>
#include <string>
#include <vector>
using namespace std;

string preprocess(string s) {
    string result = "#";
    for (int i = 0; i < s.size(); i++) {
        result += s[i];
        result += "#";
    }
    
    return result;
}

int solution(string s) {
    s = preprocess(s);
    
    int center = 0, right = 0;
    vector<int> dp(s.size());
    
    for (int i = 0; i < dp.size(); i++) {
        if (i <= right) {
            dp[i] = (dp[2 * center - i] < right - i ? dp[2 * center - i] : right - i);
        }
        
        while (i - dp[i] - 1 >= 0 &&
              i + dp[i] + 1 < s.size() &&
              s[i - dp[i] - 1] == s[i + dp[i] + 1]) {
            dp[i]++;
        }
        
        if (i + dp[i] > right) {
            right = i + dp[i];
            center = i;
        }
    }
    
    int result = 0;
    for (int d : dp) {
        if (d > result) result = d;
    }
    
    return result;
}