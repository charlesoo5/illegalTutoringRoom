#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> A, vector<int> B) {
    int answer = 0;

    sort(A.begin(), A.end());
    sort(B.rbegin(), B.rend());
    
    for (int s = 0; s < A.size(); s++) {
        answer += (A[s] * B[s]);
    }

    return answer;
}