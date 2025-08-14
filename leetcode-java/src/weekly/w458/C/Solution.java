package weekly.w458.C;

import ability.Benchmark;

/**
 * Q3. Process String with Special Operations II
 *
 * https://leetcode.cn/contest/weekly-contest-458/problems/process-string-with-special-operations-ii/
 *
 * You are given a string s consisting of lowercase English letters and the special characters: '*', '#', and '%'.
 *
 * You are also given an integer k.
 *
 * Build a new string result by processing s according to the following rules from left to right:
 *
 * If the letter is a lowercase English letter append it to result.
 * A '*' removes the last character from result, if it exists.
 * A '#' duplicates the current result and appends it to itself.
 * A '%' reverses the current result.
 *
 * Return the kth character of the final string result. If k is out of the bounds of result, return '.'
 */

public class Solution {

    public char processStr(String s, long k) {
        int n = s.length();
        // 计算每次操作之后的字符串长度
        long[] lens = new long[n];
        lens[0] = s.charAt(0) >= 'a' ? 1 : 0;
        for (int i = 1; i < n; i++) {
            switch (s.charAt(i)) {
                case '*' -> lens[i] = Math.max(lens[i - 1] - 1, 0);
                case '#' -> lens[i] = lens[i - 1] << 1;
                case '%' -> lens[i] = lens[i - 1];
                default -> lens[i] = lens[i - 1] + 1;
            }
        }
        // 检查 k 是否越界
        if (k >= lens[n - 1]) return '.';

        // 剩下肯定是有解的, 从后往前还是处理
        for (int i = n - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                // 删除最后一个元素实际上不会影响我们计算, 因为在删除到 k 索引之前我们肯定已经找到答案了
                case '*' -> {}

                // 在执行翻倍之后得到的长度是 lens[i], 则需要判断是在原始字符串的哪个位置
                //  - 如果是在后半部分, 那就是 k - lens[i] / 2, 否则就是原本位置
                case '#' -> k = k % (lens[i] >> 1);

                // 如果当前是反转操作, 则对应前一个位置应该是镜像的位置
                case '%' -> k = lens[i] - k - 1;

                // 如果是字母, 检查 lens[i] 是否等于 k + 1, 如果是的话那么我们就找到了
                default -> {
                    if (lens[i] == k + 1) return s.charAt(i);
                }
            }
        }

        return '?'; // unreached
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().processStr("#####y#ox#%%#t**%k%#*qzv#*ui%%i#k##*%gc#" +
                "*e###bu#*ly%d#%ji#ulq##%###o#vjzx%e#uusegbxi*glua%u##semb#u#%#%#%#bv*%#*" +
                "h#*%%va#bc#%bz%%%##%idlv#i%%c#h%#%b%s#p#*#qdpvqq#b%n%%k##g%qn#ezc##ket%z" +
                "#%%g%j%cn%%n%jb%dlu%*mm%lk%ssleg%i%%%r%e*asn%y%u%%j%%%zr%p%ewhtvco%rt%%%" +
                "%%cx%%h*m%ljds%*%pi*%%%%isbyria*vz%y**x%cxcac%%%%keq%%%%w%**%%o%%f%%r%%%" +
                "d%%fh%%xx%%%%%h%%c%n%*%fgll**b*s%pxx%%o%*x%w%nv%ppabkl**b%%%gbqmf%%*n*m%" +
                "teh*%%%%qt%pjslnk%xv%%*%%czt%%*%j%s%%j%%%*a%%%fjsg*", 14750) == 'y';
        });

        assert new Solution().processStr("cd%#*#", 3) == 'd';
        assert new Solution().processStr("a#b%*", 1) == 'a';
        assert new Solution().processStr("z*#", 0) == '.';
    }

}
