//
// https://www.acwing.com/problem/content/97/
//

#include <iostream>
#include <vector>

using namespace std;

constexpr static int MASK = ((1 << 5) - 1);
constexpr static int INF = 1 << 20;

int bitlen(int state) {
    int ans = 0;
    for (; state != 0; state >>= 1) {
        if (state & 1) ans++;
    }
    return ans;
}

bool is_bit_set(int v, int i) {
    return i >= 0 && ((v >> i) & 1);
}

bool check(vector<int> &sw, int prev, int curr, int next) {
    for (int i = 0; i < sw.size(); i++) {
        int state = sw[i];
        if (is_bit_set(prev, i)) state ^= 1;
        if (is_bit_set(next, i)) state ^= 1;
        if (is_bit_set(curr, i)) state ^= 1;
        if (is_bit_set(curr, i - 1)) state ^= 1;
        if (is_bit_set(curr, i + 1)) state ^= 1;
        if (state == 0) return false;
    }
    return true;
}

int dfs(vector<vector<int>> &rects, int i, int pp, int p) {
    if (i == rects.size()) {
        return check(rects[i - 1], pp, p, 0) ? 0 : INF;
    }

    int ans = INF;
    for (int curr = 0; curr <= MASK; curr++) {
        // 当前如果选择了 curr 则可以判断前一行是否满足要求
        if (i > 0 && !check(rects[i - 1], pp, p, curr)) {
            continue;
        }

        ans = min(ans, dfs(rects, i + 1, p, curr) + bitlen(curr));
    }
    return ans;
}

int solution(vector<vector<int>> &rect) {
    return dfs(rect, 0, 0, 0);
}

int main() {
    int n = 0;
    vector<vector<vector<int>>> rects;

    cin >> n;
    for (int i = 0; i < n; i++) {
        vector<vector<int>> rect;
        for (int j = 0; j < 5; j++) {
            string s; cin >> s;

            vector<int> line;
            for (auto &c : s) {
                line.push_back(c - '0');
            }
            rect.push_back(line);
        }
        rects.push_back(rect);
    }

    for (auto &rect : rects) {
        int ans = solution(rect);
        cout << (ans > 6 ? -1 : ans) << endl;
    }

    return 0;
}
