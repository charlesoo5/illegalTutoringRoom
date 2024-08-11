#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<vector<int>> map;
vector<vector<int>> query;
vector<vector<int>> clouds;
vector<vector<bool>> visit;
int delta[8][2] = { {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1} };

void print() {
	cout << '\n';
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			cout << map[n][m] << ' ';
		}
		cout << '\n';
	}
}

void init() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

	cin >> N >> M;

	map = vector<vector<int>>(N, vector<int>(N));
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			cin >> map[n][m];
		}
	}

	query = vector<vector<int>>(M, vector<int>(2));

	for (int m = 0; m < M; m++) {
		cin >> query[m][0] >> query[m][1];
		query[m][0]--;
	}

	clouds.push_back({ N - 1, 0 });
	clouds.push_back({ N - 1, 1 });
	clouds.push_back({ N - 2, 0 });
	clouds.push_back({ N - 2, 1 });
}

void flow(int q) {
	visit = vector<vector<bool>>(N, vector<bool>(N));

	while (!clouds.empty()) {
		clouds.back()[0] = (clouds.back()[0] + (delta[query[q][0]][0] * query[q][1] % N) + N) % N;
		clouds.back()[1] = (clouds.back()[1] + (delta[query[q][0]][1] * query[q][1] % N) + N) % N;

		map[clouds.back()[0]][clouds.back()[1]]++;
		visit[clouds.back()[0]][clouds.back()[1]] = true;

		clouds.pop_back();
	}
}

void rep() {
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			if (!visit[n][m]) continue;

			int count = 0;
			for (int d = 1; d < 8; d += 2) {
				int dn = n + delta[d][0];
				int dm = m + delta[d][1];

				if (dn < 0 || dm < 0 || dn >= N || dm >= N) continue;
				if (map[dn][dm] > 0) count++;
			}
			map[n][m] += count;
		}
	}

	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			if (visit[n][m] || map[n][m] < 2) continue;

			map[n][m] -= 2;
			clouds.push_back({ n, m });
		}
	}
}

int main() {
	init();

	for (int q = 0; q < M; q++) {
		flow(q);
		rep();
	}

	int result = 0;
	for (int n = 0; n < N; n++) {
		for (int m = 0; m < N; m++) {
			result += map[n][m];
		}
	}

	cout << result;

	return 0;
}