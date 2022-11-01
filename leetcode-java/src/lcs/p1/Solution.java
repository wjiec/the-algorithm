package lcs.p1;

/**
 * LCS 01. 下载插件
 *
 * https://leetcode-cn.com/problems/Ju9Xwi/
 *
 * 小扣打算给自己的 VS code 安装使用插件，初始状态下带宽每分钟可以完成 1 个插件的下载。假定每分钟选择以下两种策略之一:
 *
 * 使用当前带宽下载插件
 * 将带宽加倍（下载插件数量随之加倍）
 * 请返回小扣完成下载 n 个插件最少需要多少分钟。
 *
 * 注意：实际的下载的插件数量可以超过 n 个
 */

public class Solution {

    public int leastMinutes(int n) {
        int base = 1, ans = 0;
        for (; n > base; ans++) base <<= 1;
        return ans + 1;
    }

    public static void main(String[] args) {
        assert new Solution().leastMinutes(2) == 2;
        assert new Solution().leastMinutes(4) == 3;
    }

}
