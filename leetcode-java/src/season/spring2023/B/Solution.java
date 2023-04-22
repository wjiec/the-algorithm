package season.spring2023.B;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 2. 探险营地
 *
 * https://leetcode.cn/contest/season/2023-spring/problems/0Zeoeg/
 *
 * 探险家小扣的行动轨迹，都将保存在记录仪中。expeditions[i] 表示小扣第 i 次探险记录，用一个字符串数组表示。
 * 其中的每个「营地」由大小写字母组成，通过子串 -> 连接。
 *
 * 例："Leet->code->Campsite"，表示到访了 "Leet"、"code"、"Campsite" 三个营地。
 *
 * expeditions[0] 包含了初始小扣已知的所有营地；对于之后的第 i 次探险(即 expeditions[i] 且 i > 0)，如果
 * 记录中包含了之前均没出现的营地，则表示小扣 新发现 的营地。
 *
 * 请你找出小扣发现新营地最多且索引最小的那次探险，并返回对应的记录索引。如果所有探险记录都没有发现新的营地，返回 -1
 *
 * 注意：
 *
 * 大小写不同的营地视为不同的营地；
 * 营地的名称长度均大于 0。
 */

public class Solution {

    public int adventureCamp(String[] expeditions) {
        int ans = -1, cnt = 0;
        Set<String> seen = new HashSet<>(Arrays.asList(expeditions[0].split("->")));
        for (int i = 1; i < expeditions.length; i++) {
            if (expeditions[i].length() == 0) continue;

            Set<String> curr = new HashSet<>();
            for (var s : expeditions[i].split("->")) {
                if (seen.add(s)) curr.add(s);
            }
            if (curr.size() > cnt) { cnt = curr.size(); ans = i; }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().adventureCamp(new String[]{
            "xO->xO->xO","xO->BKbWDH","xO->BKbWDH","BKbWDH->mWXW","LSAYWW->LSAYWW","oAibBvPdJ","LSAYWW->u","LSAYWW->LSAYWW"
        }) == 1;
    }

}
