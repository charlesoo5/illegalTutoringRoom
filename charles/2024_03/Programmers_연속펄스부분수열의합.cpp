#include <string>
#include <vector>

using namespace std;

long long solution(vector<int> sequence) {
    vector<int> seq1, seq2;
    for (int i = 0; i < sequence.size(); i++) {
        if (i % 2 == 0) {
            seq1.push_back(sequence[i]);
            seq2.push_back(sequence[i] * -1);
        }
        else {
            seq1.push_back(sequence[i] * -1);
            seq2.push_back(sequence[i]);
        }
    }
    
    vector<long long> dp1, dp2;
    dp1.push_back(seq1[0]);
    dp2.push_back(seq2[0]);
    for (int i = 1; i < sequence.size(); i++) {
        if (dp1[i - 1] > 0) 
            dp1.push_back(dp1[i - 1] + seq1[i]);
        else 
            dp1.push_back(seq1[i]);
        
        if (dp2[i - 1] > 0) 
            dp2.push_back(dp2[i - 1] + seq2[i]);
        else 
            dp2.push_back(seq2[i]);
    }
    
    long long result = dp1[0];
    for (int i = 0; i < sequence.size(); i++) {
        if (dp1[i] > result) result = dp1[i];
        if (dp2[i] > result) result = dp2[i];
    }
    
    return result;
}