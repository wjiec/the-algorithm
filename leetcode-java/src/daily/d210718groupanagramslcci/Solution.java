package daily.d210718groupanagramslcci;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 10.02. 变位词组
 *
 * https://leetcode-cn.com/problems/group-anagrams-lcci/
 *
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 */

public class Solution {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (var str : strs) {
            String serialized = serialize(str);
            if (!map.containsKey(serialized)) {
                map.put(serialized, new ArrayList<>());
            }

            map.get(serialized).add(str);
        }

        return new ArrayList<>(map.values());
    }

    private String serialize(String s) {
        int[] chars = new int[255];
        for (var c : s.toCharArray()) chars[c]++;

        StringBuilder sb = new StringBuilder();
        for (int i = 'a'; i <= 'z'; i++) {
            if (chars[i] != 0) {
                sb.append((char) i).append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

}
