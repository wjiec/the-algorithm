package daily.d210625p752openthelock;

import java.util.*;

/**
 * 752. Open the Lock
 *
 * https://leetcode-cn.com/problems/open-the-lock/
 *
 * You have a lock in front of you with 4 circular wheels.
 * Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 *
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 *
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 *
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) return 0;

        Set<String> blocked = new HashSet<>(Arrays.asList(deadends));
        if (blocked.contains("0000")) return -1;

        int step = 0;
        Queue<String> queue = new ArrayDeque<>(); queue.add("0000");
        Set<String> visited = new HashSet<>(); visited.add("0000");
        while (!queue.isEmpty()) {
            ++step;
            for (int i = 0, l = queue.size(); i < l; i++) {
                String state = queue.remove();
                for (var next : roll(state)) {
                    if (!visited.contains(next) && !blocked.contains(next)) {
                        if (next.equals(target)) {
                            return step;
                        }

                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }

        return -1;
    }

    private List<String> roll(String state) {
        char[] chars = state.toCharArray();
        List<String> states = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char v = chars[i];

            chars[i] = (char) (v == '9' ? '0' : (v + 1));
            states.add(new String(chars));

            chars[i] = (char) (v == '0' ? '9' : (v - 1));
            states.add(new String(chars));

            chars[i] = v;
        }
        return states;
    }

    public static void main(String[] args) {
        assert new Solution().openLock(new String[]{"0201","0101","0102","1212","2002"}, "0202") == 6;
        assert new Solution().openLock(new String[]{"8888"}, "0009") == 1;
        assert new Solution().openLock(new String[]{
            "8887","8889","8878","8898","8788","8988","7888","9888"
        }, "8888") == -1;
    }

}
