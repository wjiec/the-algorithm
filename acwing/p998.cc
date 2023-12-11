//
// https://www.acwing.com/problem/content/description/1000/
//

#include <iostream>
#include <vector>
#include <string>

using namespace std;

int dfs(vector<int> &limits, vector<int> &ones, vector<int> &zeros, bool limited, int i, int curr) {
    if (i < 0) return curr;

    // 当前位受限
    if (limited) {
        switch (limits[i]) {
            case 0:
                // 如果当前位为0, 则我们只能选择0的位
                return dfs(limits, ones, zeros, true, i - 1, curr | (zeros[i] << i));
            case 1:
                // 如果当前位为1, 则我们可以选择0或者1
                int s0 = dfs(limits, ones, zeros, false, i - 1, curr | (zeros[i] << i));
                int s1 = dfs(limits, ones, zeros, true, i - 1, curr | (ones[i] << i));
                return max(s0, s1);
        }
    }

    // 当前位不受限
    if (zeros[i]) return dfs(limits, ones, zeros, limited, i - 1, curr | (zeros[i] << i));
    return dfs(limits, ones, zeros, limited, i - 1, curr | (ones[i] << i));
}

int solution(int m, vector<pair<string, int>> &doors) {
    vector<int> limits(32);
    for (int i = 0; i < 32; i++) {
        limits[i] = m & 1;
        m >>= 1;
    }

    vector<int> ones(32), zeros(32);
    for (int i = 0; i < 32; i++) {
        int curr0 = 0, curr1 = 1;
        for (auto &door : doors) {
            int bit = (door.second >> i) & 1;

            if (door.first == "AND") {
                curr0 &= bit; curr1 &= bit;
            } else if (door.first == "OR") {
                curr0 |= bit; curr1 |= bit;
            } else if (door.first == "XOR") {
                curr0 ^= bit; curr1 ^= bit;
            }
        }
        ones[i] = curr1; zeros[i] = curr0;
    }

    return dfs(limits, ones, zeros, true, 31, 0);
}

int main() {
    int n = 0, m = 0;
    cin >> n >> m;

    vector<pair<string, int>> doors;
    for (int i = 0; i < n; i++) {
        string op;
        int t = 0;
        cin >> op >> t;
        doors.emplace_back(op, t);
    }

    cout << solution(m, doors);
}
