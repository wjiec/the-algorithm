package daily.d211113p520detectcapital;

import java.util.HashMap;
import java.util.Map;

/**
 * 520. Detect Capital
 *
 * https://leetcode-cn.com/problems/detect-capital/
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 *
 * Given a string word, return true if the usage of capitals in it is right.
 */

public class Solution {

    private enum State {
        STATE_INITIAL,
        STATE_CAPITALS_FIRST,
        STATE_CAPITALS_ALL,
        STATE_NOT_CAPITALS,
        STATE_INVALID,
    }

    private enum CharType {
        CHAR_LOWER,
        CHAR_UPPER,
    }

    public boolean detectCapitalUse(String word) {
        Map<State, Map<CharType, State>> machine = new HashMap<>() {{
            put(State.STATE_INITIAL, new HashMap<>() {{
                put(CharType.CHAR_LOWER, State.STATE_NOT_CAPITALS);
                put(CharType.CHAR_UPPER, State.STATE_CAPITALS_FIRST);
            }});

            put(State.STATE_CAPITALS_FIRST, new HashMap<>() {{
                put(CharType.CHAR_LOWER, State.STATE_NOT_CAPITALS);
                put(CharType.CHAR_UPPER, State.STATE_CAPITALS_ALL);
            }});

            put(State.STATE_CAPITALS_ALL, new HashMap<>() {{
                put(CharType.CHAR_LOWER, State.STATE_INVALID);
                put(CharType.CHAR_UPPER, State.STATE_CAPITALS_ALL);
            }});

            put(State.STATE_NOT_CAPITALS, new HashMap<>() {{
                put(CharType.CHAR_LOWER, State.STATE_NOT_CAPITALS);
                put(CharType.CHAR_UPPER, State.STATE_INVALID);
            }});
        }};

        State current = State.STATE_INITIAL;
        for (var c : word.toCharArray()) {
            current = machine.get(current).get(toCharType(c));
            if (current == State.STATE_INVALID) {
                return false;
            }
        }
        return true;
    }

    private CharType toCharType(char c) {
        return ('a' <= c && c <= 'z') ? CharType.CHAR_LOWER : CharType.CHAR_UPPER;
    }

    public static void main(String[] args) {
        assert new Solution().detectCapitalUse("USA");
        assert !new Solution().detectCapitalUse("FlaG");
    }

}
