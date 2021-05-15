package weekly.biweekly52.p0;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Solution {

    public String sortSentence(String s) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, String> mapping = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '1' && c <= '9') {
                mapping.put(c - '0', sb.toString());
                sb = new StringBuilder();
            } else if ((c >= 'a' && c <= 'z') || c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 1; i < 10; i++) {
            if (mapping.containsKey(i)) {
                sj.add(mapping.get(i));
            }
        }

        return sj.toString();
    }

    public static void main(String[] args) {
        assert new Solution().sortSentence("sentence4 a3 is2 This1").equals("This is a sentence");
    }

}
