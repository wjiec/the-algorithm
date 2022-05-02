package daily.d220502p591tagvalidator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 591. Tag Validator
 *
 * https://leetcode-cn.com/problems/tag-validator/
 *
 * Given a string representing a code snippet, implement a tag validator to parse
 * the code and return whether it is valid.
 *
 * A code snippet is valid if all the following rules hold:
 *
 * The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
 *
 * A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>.
 * Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag.
 *
 * The TAG_NAME in start and end tags should be the same. A closed tag is valid if and only
 * if the TAG_NAME and TAG_CONTENT are valid.
 *
 * A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. Otherwise, the TAG_NAME is invalid.
 *
 * A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) EXCEPT unmatched <,
 * unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise,
 * the TAG_CONTENT is invalid.
 *
 * A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However,
 * you also need to consider the issue of unbalanced when tags are nested.
 *
 * A < is unmatched if you cannot find a subsequent >. And when you find a < or </,
 * all the subsequent characters until the next > should be parsed as TAG_NAME (not necessarily valid).
 *
 * The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as the
 * characters between <![CDATA[ and the first subsequent ]]>.
 *
 * CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to
 * parse CDATA_CONTENT, so even it has some characters that can be parsed as tag (no matter valid or invalid),
 * you should treat it as regular characters.
 */

public class Solution {

    public boolean isValid(String code) {
        Deque<String> tags = new ArrayDeque<>();
        for (int i = 0, n = code.length(); i < n; ) {
            if (code.charAt(i) == '<') {
                if (i == n - 1) return false;

                if (code.charAt(i + 1) == '/') { // tag end
                    int endIdx = code.indexOf('>', i);
                    if (endIdx < 0) return false;

                    String tagName = code.substring(i + 2, endIdx);
                    if (tags.isEmpty() || !tags.peek().equals(tagName)) return false;
                    tags.pop();

                    i = endIdx + 1;
                    if (tags.isEmpty() && i != n) return false;
                } else if (code.charAt(i + 1) == '!') { // cdata start
                    if (tags.isEmpty()) return false;
                    if (i + 9 > n) return false;

                    String cdata = code.substring(i + 2, i + 9);
                    if (!"[CDATA[".equals(cdata)) return false;
                    int endIdx = code.indexOf("]]>", i);
                    if (endIdx < 0) return false;
                    i = endIdx + 1;
                } else { // ending
                    int endIdx = code.indexOf('>', i);
                    if (endIdx < 0) return false;
                    String tagName = code.substring(i + 1, endIdx);
                    if (tagName.length() < 1 || tagName.length() > 9) return false;

                    for (int j = 0; j < tagName.length(); ++j) {
                        if (!Character.isUpperCase(tagName.charAt(j))) return false;
                    }

                    tags.push(tagName);
                    i = endIdx + 1;
                }
            } else {
                if (tags.isEmpty()) return false;
                ++i;
            }
        }

        return tags.isEmpty();
    }

    public static void main(String[] args) {
        assert new Solution().isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>");
        assert new Solution().isValid("<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>");
        assert !new Solution().isValid("<A>  <B> </A>   </B>");
        assert !new Solution().isValid("<DIV>  div tag is not closed  <DIV>");
        assert !new Solution().isValid("<DIV>  unmatched <  </DIV>");
        assert !new Solution().isValid("<DIV> closed tags with invalid tag name  <b>123</b> </DIV>");
        assert !new Solution().isValid("<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>");
        assert !new Solution().isValid("<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>");

        assert !new Solution().isValid("<DIV><</DIV>");
        assert new Solution().isValid("<DIV>></DIV>");
        assert new Solution().isValid("<DIV>]>>]</DIV>");
    }
}
