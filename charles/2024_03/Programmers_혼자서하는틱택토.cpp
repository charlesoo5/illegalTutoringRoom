#include <string>
#include <vector>

using namespace std;

bool winO(vector<string> board) {
    for (int n = 0; n < 3; n++) {
        if (board[n] == "OOO") return true;
        else if (board[0][n] == 'O' && board[1][n] == 'O' && board[2][n] == 'O') return true;
    }
    
    if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') return true;
    else if (board[2][0] == 'O' && board[1][1] == 'O' && board[0][2] == 'O') return true;
    
    return false;
}
bool winX(vector<string> board) {
    for (int n = 0; n < 3; n++) {
        if (board[n] == "XXX") return true;
        else if (board[0][n] == 'X' && board[1][n] == 'X' && board[2][n] == 'X') return true;
    }
    
    if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') return true;
    else if (board[2][0] == 'X' && board[1][1] == 'X' && board[0][2] == 'X') return true;
    
    return false;
}

int solution(vector<string> board) {
    int count_x = 0;
    int count_o = 0;
    
    for (int n = 0; n < 3; n++) {
        for (int m = 0; m < 3; m++) {
            if (board[n][m] == 'O') count_o++;
            else if (board[n][m] == 'X') count_x++;
        }
    }
    
    if (count_o - count_x > 1 ||
       count_o - count_x < 0) return 0;
    
    if (winO(board) && winX(board)) return 0;
    
    if (winO(board) && count_o - count_x != 1) return 0;
    
    if (winX(board) && count_o - count_x != 0) return 0;
    
    return 1;
}