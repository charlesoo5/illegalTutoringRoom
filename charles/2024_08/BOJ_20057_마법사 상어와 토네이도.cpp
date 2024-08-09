#include <iostream>
#include <vector>
using namespace std;

int N;
vector<vector<int>> board;
int delta[4][2] = { {0, -1}, {1, 0}, {0, 1}, {-1, 0} };
vector<vector<int>> ratio[4];
int length = 1;
int result = 0;

void init() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	 
	cin >> N;
	board = vector<vector<int>>(N, vector<int>(N));
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			cin >> board[n][m];
			result += board[n][m];
		}
	}

	for (int i = 0; i < 4; i++) {
		ratio[i] = vector<vector<int>>(5, vector<int>(5));
	}
	ratio[0] = {
	{0,  0,  2, 0, 0},
	{0, 10,  7, 1, 0},
	{5, -1,  0, 0, 0},
	{0, 10,  7, 1, 0},
	{0,  0,  2, 0, 0} };

	for (int n = 0; n < 5; n++) {
		for (int m = 0; m < 5; m++) {
			ratio[1][n][m] = ratio[0][m][4 - n];
			ratio[2][n][m] = ratio[0][4 - n][4 - m];
			ratio[3][n][m] = ratio[0][4 - m][n];
		}
	}
}

void blow(int n, int m, int d, int l, bool last) {
	int blown = 0;
	int an, am;

	for (int rn = 0; rn < 5; rn++) {
		for (int rm = 0; rm < 5; rm++) {
			if (ratio[d][rn][rm] == 0) continue;
			if (ratio[d][rn][rm] == -1) {
				an = n + rn - 2;
				am = m + rm - 2;
				continue;
			}

			int dn = n + rn - 2;
			int dm = m + rm - 2;
			if (dn >= 0 && dm >= 0 && dn < N && dm < N) {
				board[dn][dm] += board[n][m] * ratio[d][rn][rm] / 100;
			}
			blown += board[n][m] * ratio[d][rn][rm] / 100;
		}
	}
	if (an >= 0 && am >= 0 && an < N && am < N) {
		board[an][am] += (board[n][m] - blown);
	}

	board[n][m] = 0;

	if (n == 0 && m == 0) return;

	if (l == 1) {
		if (last) {
			last = false;
			length++;
		}
		else {
			last = true;
		}

		d = (d + 1) % 4;
		l = length;
	}
	else {
		l--;
	}

	int dn = n + delta[d][0];
	int dm = m + delta[d][1];

	blow(dn, dm, d, l, last);
}

int main() {
	init();

	blow(N / 2, N / 2 - 1, 0, length, false);

	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			result -= board[n][m];
		}
	}
	cout << result;

	return 0;
}