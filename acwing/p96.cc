//
// https://www.acwing.com/problem/content/description/98/
//

#include <iostream>

#define MOVE(A, B) (1)

using namespace std;

// 将N个盘子从 from 移动到 to, 使用 temp 作为中转
int hanoi(int n, int from, int temp, int to) {
    if (n == 1) return 1;

    return hanoi(n - 1, from, to, temp) + hanoi(n - 1, temp, from, to) + MOVE('A', 'C');
}

int hanoi(int n) {
    // 如果只有1个, 那就直接移动到d就好了
    if (n == 1) return MOVE('A', 'D');

    int ans = 1 << 20;
    // 如果有很多, 那就暂存N个到B, 然后按照初始版本的汉诺塔计算, 最后再挪到D上
    for (int i = 1; i < n; i++) {
        // 先移动i个圆盘到B或者C上, 然后再三塔问题上移动剩下的n-i个圆盘到D上, 最后再把i个圆盘移动到D上
        ans = min(ans, 2 * hanoi(i) + hanoi(n - i, n - i, 0, 0));
    }
    return ans;
}

int main() {
    for (int n = 1; n <= 12; n++) {
        cout << hanoi(n) << endl;
    }

    return 0;
}
