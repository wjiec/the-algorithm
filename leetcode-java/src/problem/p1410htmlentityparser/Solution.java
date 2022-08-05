package problem.p1410htmlentityparser;

import java.util.HashMap;
import java.util.Map;

/**
 * 1410. HTML Entity Parser
 *
 * https://leetcode.cn/problems/html-entity-parser/
 *
 * HTML entity parser is the parser that takes HTML code as input and replace all the
 * entities of the special characters by the characters itself.
 *
 * The special characters and their entities for HTML are:
 *
 * Quotation Mark: the entity is &quot; and symbol character is ".
 * Single Quote Mark: the entity is &apos; and symbol character is '.
 * Ampersand: the entity is &amp; and symbol character is &.
 * Greater Than Sign: the entity is &gt; and symbol character is >.
 * Less Than Sign: the entity is &lt; and symbol character is <.
 * Slash: the entity is &frasl; and symbol character is /.
 * Given the input text string to the HTML parser, you have to implement the entity parser.
 *
 * Return the text after replacing the entities by the special characters.
 */

public class Solution {

    public String entityParser(String text) {
        text = text.replaceAll("&quot;", "\"");
        text = text.replaceAll("&apos;", "'");
        text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&frasl;", "/");
        text = text.replaceAll("&amp;", "&");
        return text;
    }

    private static class Linear {
        private static final Map<String, String> entites = new HashMap<>();
        static {
            entites.put("&gt;", ">");
            entites.put("&lt;", "<");
            entites.put("&amp;", "&");
            entites.put("&quot;", "\"");
            entites.put("&apos;", "'");
            entites.put("&frasl;", "/");
        }

        public String entityParser(String text) {
            int n = text.length();
            char[] chars = text.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (chars[i] == '&') {
                    int semi = i + 1;
                    while (semi < n && chars[semi] != ';') {
                        if (chars[semi] == '&') {
                            semi--; break;
                        } else semi++;
                    }

                    String k = text.substring(i, Math.min(n, semi + 1));
                    if (semi < n && chars[semi] == ';' && entites.containsKey(k)) {
                        sb.append(entites.get(k));
                    } else sb.append(k);

                    i = semi;
                } else sb.append(chars[i]);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        assert new Solution().entityParser("&amp;gt;").equals("&gt;");
        assert new Solution().entityParser("&amp; is an HTML entity but &ambassador; is not.")
            .equals("& is an HTML entity but &ambassador; is not.");
        assert new Solution().entityParser("and I quote: &quot;...&quot;")
            .equals("and I quote: \"...\"");
        assert new Solution().entityParser("Stay home! Practice on Leetcode :)")
            .equals("Stay home! Practice on Leetcode :)");
        assert new Solution().entityParser("x &gt; y &amp;&amp; x &lt; y is always false")
            .equals("x > y && x < y is always false");
        assert new Solution().entityParser("leetcode.com&frasl;problemset&frasl;all")
            .equals("leetcode.com/problemset/all");

        assert new Linear().entityParser("&&&").equals("&&&");
        assert new Linear().entityParser("&&gt;").equals("&>");
        assert new Linear().entityParser("&amp;gt;").equals("&gt;");
        assert new Linear().entityParser("&amp; is an HTML entity but &ambassador; is not.")
            .equals("& is an HTML entity but &ambassador; is not.");
        assert new Linear().entityParser("and I quote: &quot;...&quot;")
            .equals("and I quote: \"...\"");
        assert new Linear().entityParser("Stay home! Practice on Leetcode :)")
            .equals("Stay home! Practice on Leetcode :)");
        assert new Linear().entityParser("x &gt; y &amp;&amp; x &lt; y is always false")
            .equals("x > y && x < y is always false");
        assert new Linear().entityParser("leetcode.com&frasl;problemset&frasl;all")
            .equals("leetcode.com/problemset/all");
    }

}
