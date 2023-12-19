//
// https://www.acwing.com/problem/content/97/
//

#include <iostream>
#include <cstring>

using namespace std;
const int state_count = 1 << 5;
const int N = 5;

typedef char Rect[10][10];

const int dirs[][2] = {{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}};

void turn(Rect rect, int x, int y) {
    for (auto &dir : dirs) {
        int dx = x + dir[0], dy = y + dir[1];
        if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
            rect[dx][dy] ^= 1;
        }
    }
}

int solution(char rect[10][10]) {
    int ans = 1 << 20;
    for (int s = 0; s < state_count; s++) {
        Rect temp;
        memcpy(temp, rect, 100);

        int curr = 0;
        for (int j = 0; j < N; j++) {
            if (s & (1 << j)) {
                curr++;
                turn(temp, 0, j);
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j] == '0') {
                    if (++curr > 6) break;
                    turn(temp, i + 1, j);
                }
            }
        }

        bool ok = curr <= 6;
        for (int j = 0; ok && j < N; j++) {
            if (temp[N - 1][j] == '0') {
                ok = false;
                break;
            }
        }

        if (ok) ans = min(ans, curr);
    }

    return ans > 6 ? -1 : ans;
}

int main() {
    int n = 0;
    cin >> n;

    for (int i = 0; i < n; i++) {
        Rect rect;
        for (int j = 0; j < N; j++) cin >> rect[j];
        cout << solution(rect) << endl;
    }

    return 0;
}
