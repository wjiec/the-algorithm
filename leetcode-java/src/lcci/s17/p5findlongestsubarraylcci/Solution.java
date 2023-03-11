package lcci.s17.p5findlongestsubarraylcci;

import common.PrettyPrinter;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 17.05.  字母与数字
 *
 * https://leetcode.cn/problems/find-longest-subarray-lcci/
 *
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 *
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int start = 0, len = 0, diff = 0;
        for (int i = 0; i < array.length; i++) {
            if (Character.isDigit(array[i].charAt(0))) diff++;
            else diff--;

            if (map.containsKey(diff)) {
                int prev = map.get(diff);
                if (i - prev > len) {
                    len = i - prev;
                    start = prev;
                }
            } else map.put(diff, i);
        }

        String[] ans = new String[len];
        System.arraycopy(array, start + 1, ans, 0, len);
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findLongestSubarray(new String[]{"A","1","B","2"}));

        PrettyPrinter.println(new Solution().findLongestSubarray(new String[]{
            "42","10","O","t","y","p","g","B","96","H","5","v","P","52","25",
            "96","b","L","Y","z","d","52","3","v","71","J","A","0","v","51",
            "E","k","H","96","21","W","59","I","V","s","59","w","X","33",
            "29","H","32","51","f","i","58","56","66","90","F","10","93",
            "53","85","28","78","d","67","81","T","K","S","l","L","Z","j",
            "5","R","b","44","R","h","B","30","63","z","75","60","m","61",
            "a","5","S","Z","D","2","A","W","k","84","44","96","96","y","M"
        }));

        PrettyPrinter.println(new Solution().findLongestSubarray(new String[]{
            "A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"
        }));
        PrettyPrinter.println(new Solution().findLongestSubarray(new String[]{"A","A"}));
    }

}
