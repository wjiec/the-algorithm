package lcci.s5.p2bianrynumbertostringlcci;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 面试题 05.02. 二进制数转字符串
 *
 * https://leetcode.cn/problems/bianry-number-to-string-lcci/
 *
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
 * 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 */

public class Solution {

    public String printBin(double num) {
        // 0.1 = 1/2
        // 0.01 = 1/4
        // 0.001 = 1/8
        TreeMap<Double, Integer> map = new TreeMap<>();
        for (long base = 2, i = 0; i < 32; i++, base *= 2) {
            map.put(1.0 / base, (int) i);
        }
        map.put(0., -1);

        TreeSet<Integer> bits = new TreeSet<>();
        while (num != 0) {
            double floor = map.floorKey(num);
            if (floor == 0.) return "ERROR";

            num -= floor;
            bits.add(map.get(floor));
        }

        char[] ans = new char[bits.last() + 3];
        ans[0] = '0'; ans[1] = '.';
        for (int i = 2; i < ans.length; i++) {
            ans[i] = bits.contains(i - 2) ? '1' : '0';
        }

        return new String(ans);
    }

    private static class Native {
        public String printBin(double num) {
            StringBuilder sb = new StringBuilder("0.");
            while (sb.length() < 32 && num != 0) {
                if ((num *= 2) >= 1) {
                    sb.append('1'); num -= 1;
                } else sb.append('0');
            }
            if (sb.length() >= 32 && num != 0) return "ERROR";
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        assert new Solution().printBin(0.625).equals("0.101");
        assert new Solution().printBin(0.1).equals("ERROR");

        assert new Native().printBin(0.625).equals("0.101");
        assert new Native().printBin(0.1).equals("ERROR");
    }

}
