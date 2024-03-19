#include <string>
#include <vector>
#include <queue>

using namespace std;
int delta[4][2] = { {0, -1}, {0, 1}, {-1, 0}, {1, 0} };

int solution(vector<string> maps) {
    int N = maps.size();
    int M = maps[0].size();
    vector<int> startPos = { -1, -1 };
    vector<vector<vector<int>>> visit(N);
    
    for (int n = 0; n < N; n++) {
        visit[n] = vector<vector<int>>(M);
        
        for (int m = 0; m < M; m++) {
            visit[n][m] = vector<int>(2);
            
            if (maps[n][m] == 'S') 
                startPos = {n, m};
        }
    }
    
    queue<vector<int>> que; // 0: n | 1: m | 2: lever (0: no | 1: yes) | 3: time
    que.push({startPos[0], startPos[1], 0, 1 });
    visit[startPos[0]][startPos[1]][0] = 1;
    
    while(!que.empty()) {
        int n = que.front()[0];
        int m = que.front()[1];
        int lever = que.front()[2];
        int time = que.front()[3];
        que.pop();
        
        if (maps[n][m] == 'E' && lever == 1)
            return time - 1;
        
        for (int d = 0; d < 4; d++) {
            int dn = n + delta[d][0];
            int dm = m + delta[d][1];
            
            if (dn < 0 || dm < 0 || dn >= N || dm >= M ||
               maps[dn][dm] == 'X') 
                continue;
            
            if (maps[dn][dm] == 'L') {
                if (lever == 1) continue;
                
                visit[dn][dm][1] = time + 1;
                que.push({dn, dm, 1, time + 1});
            }
            else {
                if (visit[dn][dm][lever] > 0)
                    continue;
                
                visit[dn][dm][lever] = time + 1;
                que.push({dn, dm, lever, time + 1});
            }
        }
    }
    
    return -1;
}