package lcci.s16.p20t9lcci;

import common.Checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 16.20. T9键盘
 *
 * https://leetcode.cn/problems/t9-lcci/
 *
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。每个数字映射到0至4个字母。
 * 给定一个数字序列，实现一个算法来返回匹配单词的列表。你会得到一张含有有效单词的列表。映射如下图所示：
 */

public class Solution {

    private static final Map<Character, Character> mapped = new HashMap<>();

    static {
        mapped.put('a', '2'); mapped.put('b', '2'); mapped.put('c', '2');
        mapped.put('d', '3'); mapped.put('e', '3'); mapped.put('f', '3');
        mapped.put('g', '4'); mapped.put('h', '4'); mapped.put('i', '4');
        mapped.put('j', '5'); mapped.put('k', '5'); mapped.put('l', '5');
        mapped.put('m', '6'); mapped.put('n', '6'); mapped.put('o', '6');
        mapped.put('p', '7'); mapped.put('q', '7'); mapped.put('r', '7'); mapped.put('s', '7');
        mapped.put('t', '8'); mapped.put('u', '8'); mapped.put('v', '8');
        mapped.put('w', '9'); mapped.put('x', '9'); mapped.put('y', '9'); mapped.put('z', '9');
    }

    public List<String> getValidT9Words(String num, String[] words) {
        char[] chars = num.toCharArray();
        List<String> ans = new ArrayList<>();
        for (var word : words) {
            int idx = 0;
            for (var c : word.toCharArray()) {
                if (chars[idx] != mapped.get(c)) {
                    break;
                }
                idx++;
            }
            if (idx == chars.length) ans.add(word);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getValidT9Words("8733", new String[]{"tree", "used"}), List.of("tree", "used"));
        assert Checker.check(new Solution().getValidT9Words("2", new String[]{"a", "b", "c", "d"}), List.of("a", "b", "c"));
    }

}
