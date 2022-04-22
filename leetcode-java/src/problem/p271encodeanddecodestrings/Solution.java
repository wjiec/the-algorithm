package problem.p271encodeanddecodestrings;

import java.util.ArrayList;
import java.util.List;

/**
 * 271. Encode and Decode Strings
 *
 * https://leetcode-cn.com/problems/encode-and-decode-strings/
 *
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over
 * the network and is decoded back to the original list of strings.
 */

public class Solution {

    private static class Codec {
        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            StringBuilder sb = new StringBuilder();
            for (var s : strs) sb.append(String.format("%04d", s.length())).append(s);
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < s.length(); ) {
                int n = 0;
                for (int v = 0; v < 4; v++, i++) {
                    n = n * 10 + (s.charAt(i) - '0');
                }

                if (n == 0) ans.add("");
                else ans.add(s.substring(i, i + n));
                i += n;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        List<String> list = List.of("a", "aa", "", "bb", "ccc");
        String enc = codec.encode(list);
        assert codec.decode(enc).equals(list);
    }

}
