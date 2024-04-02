#include <string>
#include <vector>
using namespace std;

int GCD(int a, int b) {
    if (a < b) {
        int temp = a;
        a = b;
        b = temp;
    }
    
    while(b != 0) {
        int mod = (a % b);
        a = b;
        b = mod;
    }
    
    return a;
}

int LCM(int a, int b) {
    return a * b / GCD(a, b);
}

int solution(vector<int> arr) {
    if (arr.size() == 1) {
        return arr[0];
    }
    
    int result = LCM(arr[0], arr[1]);
    for (int i = 1; i < arr.size() - 1; i++) {
        result = LCM(result, arr[i + 1]);
    }
    
    return result;
}