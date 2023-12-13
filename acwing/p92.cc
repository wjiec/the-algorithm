//
// https://www.acwing.com/problem/content/94/
//

#include <iostream>
#include <vector>
#include <functional>

using namespace std;

void enumerate(int n) {
    vector<int> list;
    function<void (int)> dfs = [&] (int curr) {
        if (curr > n) {
            for (auto &v : list) {
                cout << v << " ";
            }
            cout << endl;
            return;
        }

        dfs(curr + 1);

        list.push_back(curr);
        dfs(curr + 1);
        list.pop_back();
    };

    dfs(1);
}

int main(int argc, char *argv[]) {
    int n = 0;
    cin >> n;

    enumerate(n);
    return 0;
}
