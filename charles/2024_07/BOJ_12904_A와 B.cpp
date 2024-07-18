#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

string S, T;

int main() {
	cin >> S >> T;
	
	while (T.size() > S.size()) {
		if (T.back() == 'A') 
			T.pop_back();
		else {
			T.pop_back();
			reverse(T.begin(), T.end());
		}
	}

	if (T == S) cout << "1";
	else cout << "0";

	return 0;
}