#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int H, W;
vector<int> world;

int main() {
	int result = 0;
	cin >> H >> W;

	int input;
	for (int w = 0; w < W; w++) {
		cin >> input;
		world.push_back(input);
	}

	int water = 0;
	int top = world[0];
	for (int w = 1; w < W; w++) {
		if (world[w] < top) {
			water += (top - world[w]);
		}
		else {
			result += water;
			top = world[w];
			water = 0;
		}
	}
	
	water = 0;
	top = world[W - 1];
	for (int w = W - 2; w >= 0; w--) {
		if (world[w] <= top) {
			water += (top - world[w]);
		}
		else {
			result += water;
			top = world[w];
			water = 0;
		}
	}

	cout << result;

	return 0;
}