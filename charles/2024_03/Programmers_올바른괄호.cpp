#include <string>
#include <iostream>
#include <stack>
using namespace std;

bool solution(string s) {
    stack<char> stk;
    for (int n = 0; n < s.size(); n++) {
        if (s[n] == '(') {
            stk.push(s[n]);
        } else {
            if (stk.size() == 0 || stk.top() == ')') {
                return false;
            }
            else if (stk.top() == '(') {
                stk.pop();
            }
        }
    }
    
    if (stk.size() > 0) return false;
    
    return true;
}