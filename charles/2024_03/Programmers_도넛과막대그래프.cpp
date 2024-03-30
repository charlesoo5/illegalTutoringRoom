#include <string>
#include <vector>
#include <queue>
using namespace std;

vector<int> enter(1000001);
vector<int> out(1000001);
vector<vector<int>> infos(1000001);
int rootIndex = -1;

vector<int> solution(vector<vector<int>> edges) {
    int stick = 0, donut = 0, loop = 0;
    
    for (vector<int> edge : edges) {
        enter[edge[1]]++;
        out[edge[0]]++;
        infos[edge[0]].push_back(edge[1]);
    }
    for (int n = 1; n <= 1000000; n++) {
        if (enter[n] == 0 && out[n] >= 2) {
            rootIndex = n;
            break;
        }
    }
    
    queue<int> que;
    vector<bool> visit(1000001);
    que.push(rootIndex);
    visit[rootIndex] = true;
    
    while(!que.empty()) {
        int n = que.front();
        que.pop();
        
        for (int next : infos[n]) {
            if (out[next] == 0) {
                stick++;
                continue;
            }
            else if (out[next] == 2) {
                loop++;
                continue;
            }
            else if (visit[next]) {
                donut++;
                continue;
            }
            
            visit[next] = true;
            que.push(next);
        }
    }
    
    return {rootIndex, donut, stick, loop};
}