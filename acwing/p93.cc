//
// https://www.acwing.com/problem/content/95/
//

#include <iostream>
#include <vector>
#include <functional>

using namespace std;

void enumerate(int n, int m) {
    vector<int> list;
    function<void (int)> dfs = [&] (int curr) {
        if (list.size() > m || list.size() + n - curr + 1 < m) return;

        if (curr > n) {
            for (auto &v : list) {
                cout << v << " ";
            }
            cout << endl;
            return;
        }

        list.push_back(curr);
        dfs(curr + 1);
        list.pop_back();
        
        dfs(curr + 1);
    };

    dfs(1);
}

int main(int argc, char *argv[]) {
    int n = 0, m = 0;
    cin >> n >> m;

    enumerate(n, m);
    return 0;
}
