package ability;

import common.Tag;

import java.util.Arrays;

// 回文字符串相关算法
/** @noinspection FieldCanBeLocal, unused */
public class Palindrome {

    // 马拉车算法的核心是利用回文串的对称性使得可以 O(1) 的方式计算对称位置的回文性, 有以下案例:
    //      0   1   2   3   4   5   6   7   8
    //      a   b   c   b   x   b   c   b   z
    //                      ^
    //  首先我们定义 P[m] 表示以 m 为回文中心(奇数长度)的最长回文长度
    //      - 我们从左向右不断以中心 i 的位置向两边扩展, 如下
    //      0   1   2   3   4   5   6   7   8
    //      a   b   c   b   x   b   c   b   z
    //  P   1   1   3   1   7
    //
    //  当我们计算到回文中心 m = 4 时, 回文字串是 [1, 7], 当我们向右移动使得回文中心 m = 5 时
    //  我们实际上并不需要枚举以 m = 5 为中心的回文串的长度, 而是基于 m = 4 的回文串, 我们可以得知
    //  m = 5 这个位置其实是和 m = 3 这个位置于镜像 m = 4 位置对称的.
    //
    //  对于偶数长度的回文串, 回文中心实际上是两个字符的中间, 我们通过在每两个字符之间插入相同的字符的方式
    //  来将偶数长度的算法统一到奇数长度的算法中, 如下:
    //      a   #   b   #   c   #   b   #   x   #   b   #   c   #   b   #   z
    //
    //  最终我们能实现以 O(n) 的时间复杂度计算所有位置的最长回文长度, 同时可以以 O(1) 的时间复杂度计算
    //  每个子字符串的是否是回文串.
    @Tag({"马拉车算法", "在O(1)时间内判断字串是否是回文"})
    public static class Manacher {
        // pattern 中保存的是经过插入 # 和首尾增加不同字符(^$)扩展之后的字符串
        //  - 在每两个字符之间插入 #, 是为了统一奇数和偶数长度回文串的实现逻辑
        //  - 在字符串首尾插入两个不同的符号, 是为了在暴力扩展时免于判断越界问题(必然不相同)
        //
        //  原始字符串位置 x 与扩展之后字符串的位置 y 之间的关系是一个线性关系, 满足 y = kx + b
        //      0   1   2   3   4   5   6
        //      a   b   c   b   a   b   c
        //
        //      0   1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16
        //      ^   #   a   #   b   #   c   #   b   #   a   #   b   #   c   #   $
        //  代入计算得知 y = 2x + 2
        private final char[] pattern;

        // halfLen 中保存的是以 pattern 中每个字符为中心的回文子串的单边扩展长度(包括回文中心)
        private final int[] halfLen;

        public Manacher(String s) { this(s.toCharArray()); }
        public Manacher(char[] chars) {
            pattern = new char[chars.length * 2 + 3];
            Arrays.fill(pattern, '#'); // 先全部填充 #
            // 然后将首尾字符填充为不同的字符
            pattern[0] = '^'; pattern[pattern.length - 1] = '$';
            // 最后将原始字符串填充进去
            for (int i = 0; i < chars.length; i++) {
                pattern[2 * i + 2] = chars[i];
            }

            halfLen = new int[pattern.length];
            // 从左到右枚举每个回文中心并计算得到扩展长度
            //  - m 表示回文中心, r 表示当前回文中心可以扩展的最长右边界
            for (int i = 1, m = 0, r = 0; i < pattern.length - 1; i++) {
                int len = 1; // 默认情况下无法进行扩展

                // 检查我们是否可以根据回文中心找到对称位置已经计算好的最长回文长度
                if (i < r) {
                    // 如果以 m 为中心对称的两个位置 a 和 b, 我们可以通过
                    //      a = m - l, b = m + l
                    // 计算得到, 此时我们已知 b 和 m, 则有如下推导:
                    //      l = b - m
                    //      a = m - (b - m)
                    //        = 2 * m - b
                    //
                    // 同时对于位置 i 来说, 我们有着右边界的限制, 我们最长只能扩展到边界位置(r)
                    len = Math.min(halfLen[2 * m - i], r - i);
                }

                // 暴力枚举以当前回文中心位置 i 可以扩展的最远距离
                while (pattern[i - len] == pattern[i + len]) {
                    len++;

                    // 如果此时右边界可以扩展到更远的距离, 那么我们倾向于移动镜像中间以覆盖更大的距离
                    // 由于 r 是只增不减的, 所以是 O(n) 的时间复杂度
                    m = i; r = i + len;
                }

                // 保存半扩展长度
                halfLen[i] = len;
            }
        }

        // 计算原始子字符串区间 [l, r] 是否是回文串
        public boolean isPalindrome(int l, int r) {
            // 经过扩展之后子串的位置是: [2 * l + 2, 2 * r + 2]
            //  此时对于扩展之后的子串, 回文中心是:
            //      m = ((2 * l + 2) + (2 * r + 2)) / 2
            //        = l + r + 2
            //
            // 我们只需要计算该回文中心的最长半扩展长度是否包含该子字符串即可
            return halfLen[l + r + 2] > r - l + 1;
        }
    }

}
