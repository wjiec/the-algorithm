package lcci.s17.p8circustowerlcci;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 面试题 17.08. 马戏团人塔
 *
 * https://leetcode.cn/problems/circus-tower-lcci/
 *
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 */

public class Solution {

    private record Person(int height, int weight) {}

    public int bestSeqAtIndex(int[] height, int[] weight) {
        if (height.length == 0) return 0;
        Person[] people = new Person[height.length];
        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(height[i], weight[i]);
        }
        Arrays.sort(people, (a, b) -> a.height == b.height ? b.weight - a.weight : a.height - b.height);

        int ans = 1, n = people.length;
        int[] dp = new int[n + 1];
        dp[ans] = people[0].weight;

        for (int i = 1; i < n; ++i) {
            if (people[i].weight > dp[ans]) {
                dp[++ans] = people[i].weight;
            } else {
                int l = 1, r = ans, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (dp[mid] < people[i].weight) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                dp[pos + 1] = people[i].weight;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().bestSeqAtIndex(new int[]{65,70,56,75,60,68}, new int[]{100,150,90,190,95,110}) == 6;
    }

}
