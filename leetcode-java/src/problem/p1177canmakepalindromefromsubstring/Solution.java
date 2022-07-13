package problem.p1177canmakepalindromefromsubstring;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1177. Can Make Palindrome from Substring
 *
 * https://leetcode.cn/problems/can-make-palindrome-from-substring/
 *
 * You are given a string s and array queries where queries[i] = [lefti, righti, ki].
 * We may rearrange the substring s[lefti...righti] for each query and then choose
 * up to ki of them to replace with any lowercase English letter.
 *
 * If the substring is possible to be a palindrome string after the operations above, the
 * result of the query is true. Otherwise, the result is false.
 *
 * Return a boolean array answer where answer[i] is the result of the ith query queries[i].
 *
 * Note that each letter is counted individually for replacement, so if, for
 * example s[lefti...righti] = "aaa", and ki = 2, we can only replace two of the letters.
 *
 * Also, note that no query modifies the initial string s.
 */

public class Solution {

    // preooddeven,answer=[0],[]
    //        for i in range(len(s)):
    //            preooddeven.append(preooddeven[-1]^(1<<(ord(s[i])-ord("a"))))
    //        for left,right,k in queries:
    //            if bin(preooddeven[right+1]^preooddeven[left]).count("1")>2*k+1:
    //                answer.append(False)
    //            else:
    //                answer.append(True)
    //        return answer

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int[] sum = new int[chars.length + 1];
        for (int i = 0; i < chars.length; i++) {
            sum[i + 1] = sum[i] ^ (1 << (chars[i] - 'a'));
        }

        List<Boolean> ans = new ArrayList<>();
        for (var query : queries) {
            int l = query[0], r = query[1], k = query[2];
            int diff = sum[r + 1] ^ sum[l], count = 0;
            for (; diff != 0; diff >>= 1) {
                if ((diff & 1) == 1) count++;
            }
            ans.add(count <= 2 * k + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().canMakePaliQueries("abcda", new int[][]{
            {3,3,0},{1,2,0},{0,3,1},{0,3,2},{0,4,1}
        }), List.of(true,false,false,true,true));

        assert Checker.check(new Solution().canMakePaliQueries("lyb", new int[][]{
            {0,1,0},{2,2,1}
        }), List.of(false,true));
    }

}
