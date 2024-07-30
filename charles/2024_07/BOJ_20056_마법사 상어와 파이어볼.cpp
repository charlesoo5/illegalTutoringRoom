#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

int maxNum = 1;
int delta[8][2] = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1} };

struct info {
	int num = -1;
	int r = -1, c = -1;
	int mass = -1, s = -1, d = -1;

	void print() {
		cout << r << ' ' << c << ' ' << mass << ' ' << s << ' ' << d << '\n';
	}

	info() {

	}

	info(int num, int r, int c, int mass, int s, int d) {
		this->num = num;
		this->r = r;
		this->c = c;
		this->mass = mass;
		this->s = s;
		this->d = d;
	}

	info& operator = (const info& a) const {
		info* b = new info(a.num, a.r, a.c, a.mass, a.s, a.d);
		return *b;
	}
};

int N, M, K;
vector<vector<vector<int>>> board, temp;
unordered_map<int, info> fireballs;

void init() {
	ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	cin >> N >> M >> K;
	board = vector<vector<vector<int>>>(N, vector<vector<int>>(N));

	for (int m = 0; m < M; m++) {
		int r, c, mass, s, d;
		cin >> r >> c >> mass >> s >> d;
		r--; c--;

		board[r][c].push_back(maxNum);
		fireballs.insert({ maxNum, info(maxNum, r, c, mass, s, d) });
		maxNum++;
	}
}

int main() {
	init();

	for (int k = 0; k < K; k++) {
		temp = vector<vector<vector<int>>>(N, vector<vector<int>>(N));

		// 1
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (board[r][c].size() == 0) continue;

				for (int i = 0; i < board[r][c].size(); i++) {
					int num = fireballs[board[r][c][i]].num;
					int d = fireballs[board[r][c][i]].d;
					int s = fireballs[board[r][c][i]].s;

					fireballs[num].r += (delta[d][0] * s);
					fireballs[num].c += (delta[d][1] * s);
					fireballs[num].r %= N;
					fireballs[num].c %= N;
					if (fireballs[num].r < 0) fireballs[num].r += N;
					if (fireballs[num].c < 0) fireballs[num].c += N;

					temp[fireballs[num].r][fireballs[num].c].push_back(num);
				}
			}
		}

		// 2
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (temp[r][c].size() < 2) continue;

				bool flag = true;
				int mod = fireballs[temp[r][c][0]].d % 2;
				int resultMass = 0, resultSpeed = 0;
				for (int i = 0; i < temp[r][c].size(); i++) {
					int num = temp[r][c][i];
					resultMass += fireballs[num].mass;
					resultSpeed += fireballs[num].s;

					if (mod != (fireballs[num].d % 2))
						flag = false;

					fireballs.erase(num);
				}
				resultMass /= 5;
				resultSpeed /= temp[r][c].size();

				
				temp[r][c].clear();
				if (resultMass <= 0) continue; // 해당 비교 연산 시 '미만'으로 비교하여 2회 오답 처리 되었음.

				vector<int> resultDelta = vector<int>(4);
				flag ? resultDelta = { 0, 2, 4, 6 } : resultDelta = {1, 3, 5, 7};

				for (int d = 0; d < 4; d++) {
					info fireball = info(maxNum, r, c, resultMass, resultSpeed, resultDelta[d]);
					temp[r][c].push_back(maxNum);
					fireballs.insert({ maxNum, fireball });
					maxNum++;
				}
			}
		}

		board = temp;
	}

	int result = 0;
	for (int r = 0; r < N; r++) {
		for (int c = 0; c < N; c++) {
			for (int i = 0; i < board[r][c].size(); i++) {
				result += fireballs[board[r][c][i]].mass;
			}
		}
	}

	cout << result << '\n';

	return 0;
}