package lcp.p1;

/**
 * LCP 01. 猜数字
 *
 * https://leetcode-cn.com/problems/guess-numbers/
 *
 * 小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。
 *
 * 他们一共进行三次这个游戏，请返回 小A 猜对了几次？
 *
 * 输入的guess数组为 小A 每次的猜测，answer数组为 小B 每次的选择。guess和answer的长度都等于3。
 */

public class Solution {

    public int game(int[] guess, int[] answer) {
        int ans = 0;
        if (guess[0] == answer[0]) ans++;
        if (guess[1] == answer[1]) ans++;
        if (guess[2] == answer[2]) ans++;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().game(new int[]{1,2,3}, new int[]{1,2,3}) == 3;
        assert new Solution().game(new int[]{2,2,3}, new int[]{3,2,1}) == 1;
    }

}
