package daily.d210520p692topkfrequentwords;

import java.util.*;

/**
 * 692. Top K Frequent Words
 *
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 *
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 */

public class Solution {

    static class Pair {
        private String word;
        private int count;

        public Pair(String word) {
            this.word = word;
        }
        public void incr() {
            this.count++;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Pair> pairs = new HashMap<>();
        for (var s : words) {
            if (!pairs.containsKey(s)) {
                pairs.put(s, new Pair(s));
            }
            pairs.get(s).incr();
        }
        LinkedList<Pair> ps = new LinkedList<>(pairs.values());
        ps.sort(Comparator.comparingInt((Pair o) -> o.count).reversed().thenComparing(o -> o.word));

        List<String> rs = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            rs.add(ps.removeFirst().word);
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2)
            .equals(List.of("i", "love"));
        assert new Solution().topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4)
            .equals(List.of("the", "is", "sunny", "day"));
    }

}
