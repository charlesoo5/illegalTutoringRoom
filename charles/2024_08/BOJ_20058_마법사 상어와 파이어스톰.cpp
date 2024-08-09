#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N, Q;
vector<vector<int>> board;
vector<int> query;
int delta[4][2] = { {-1, 0}, {0, -1}, {0, 1}, {1, 0} };

void init() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> Q;
	board = vector<vector<int>>(1 << N, vector<int>(1 << N));
	for (int n = 0; n < board.size(); n++) {
		for (int m = 0; m < board[n].size(); m++) {
			cin >> board[n][m];
		}
	}

	query = vector<int>(Q);
	for (int q = 0; q < Q; q++) {
		cin >> query[q];
	}
}

void rotate(int L) {
	if (L < 1) return;

	vector<vector<int>> temp = vector<vector<int>>(1 << N, vector<int>(1 << N));

	for (int i = 0; i < (1 << (N - L)); i++) {
		for (int j = 0; j < (1 << (N - L)); j++) {
			int sn = (1 << L) * i;
			int sm = (1 << L) * j;

			for (int n = 0; n < (1 << L); n++) {
				for (int m = 0; m < (1 << L); m++) {
					temp[sn + m][sm + (1 << L) - 1 - n] = board[sn + n][sm + m];
				}
			}
		}
	}

	board = temp;
}

void melt() {
	vector<vector<bool>> melted = vector<vector<bool>>(1 << N, vector<bool>(1 << N));

	for (int n = 0; n < (1 << N); n++) {
		for (int m = 0; m < (1 << N); m++) {
			if (board[n][m] < 1) continue;

			int count = 0;
			for (int d = 0; d < 4; d++) {
				int dn = n + delta[d][0];
				int dm = m + delta[d][1];

				if (dn >= 0 && dm >= 0 && dn < (1 << N) && dm < (1 << N) && board[dn][dm] > 0)
					count++;
			}

			if (count < 3) melted[n][m] = true;
		}
	}

	for (int n = 0; n < (1 << N); n++) {
		for (int m = 0; m < (1 << N); m++) {
			if (melted[n][m]) board[n][m]--;
		}
	}
}

int main() {
	init();

	for (int q = 0; q < Q; q++) {
		rotate(query[q]);
		melt();
	}

	int count = 0;
	for (int n = 0; n < (1 << N); n++) {
		for (int m = 0; m < (1 << N); m++) {
			count += board[n][m];
		}
	}

	int result = 0;
	queue<vector<int>> que;
	vector<vector<bool>> visit = vector<vector<bool>>(1 << N, vector<bool>(1 << N));
	for (int n = 0; n < (1 << N); n++) {
		for (int m = 0; m < (1 << N); m++) {
			if (visit[n][m] || board[n][m] < 1) continue;

			int rc = 1;
			que.push({ n, m });
			visit[n][m] = true;

			while (!que.empty()) {
				int qn = que.front()[0];
				int qm = que.front()[1];
				que.pop();

				for (int d = 0; d < 4; d++) {
					int dn = qn + delta[d][0];
					int dm = qm + delta[d][1];

					if (dn < 0 || dm < 0 || dn >= (1 << N) || dm >= (1 << N) ||
						visit[dn][dm] || board[dn][dm] < 1)
						continue;

					que.push({ dn, dm });
					visit[dn][dm] = true;
					rc++;
				}
			}

			if (rc > result) {
				result = rc;
			}
		}
	}

	cout << count << '\n' << result;

	return 0;
}