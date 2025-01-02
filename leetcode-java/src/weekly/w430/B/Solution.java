package weekly.w430.B;

/**
 * 3403. Find the Lexicographically Largest String From the Box I
 *
 * https://leetcode.cn/contest/weekly-contest-430/problems/find-the-lexicographically-largest-string-from-the-box-i/
 *
 * You are given a string word, and an integer numFriends.
 *
 * Alice is organizing a game for her numFriends friends. There are multiple rounds in the game, where in each round:
 *
 * word is split into numFriends non-empty strings, such that no previous round has had the exact same split.
 * All the split words are put into a box.
 * Find the lexicographically largest string from the box after all the rounds are finished.
 *
 * A string a is lexicographically smaller than a string b if in the first position
 * where a and b differ, string a has a letter that appears earlier in the alphabet
 * than the corresponding letter in b.
 *
 * If the first min(a.length, b.length) characters do not differ, then the shorter
 * string is the lexicographically smaller one.
 */

public class Solution {

    public String answerString(String word, int numFriends) {
        if (numFriends == 1) return word;
        char[] chars = word.toCharArray();

        char first = 'a'; int n = chars.length;
        // 因为要字典序最大的, 所以第一位肯定是需要找到整个字符串中最大的那个字母
        for (var c : chars) first = (char) Math.max(first, c);
        if (numFriends == n) return String.valueOf(first);

        // 然后找到所有以 first 开头的子字符串, 找到最大的那个
        //  - 要注意保证分出 numFriends 个子字符串
        //      - 我们直接使答案至多为 n - numFriends - 1 来保证这一点
        String ans = "";
        for (int i = 0; i < n; i++) {
            if (chars[i] == first) {
                String curr = String.valueOf(chars, i, Math.min(n - i, n - numFriends + 1));
                if (curr.compareTo(ans) > 0) ans = curr;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().answerString("dbca", 2).equals("dbc");
        assert new Solution().answerString("gggg", 4).equals("g");
    }

}
