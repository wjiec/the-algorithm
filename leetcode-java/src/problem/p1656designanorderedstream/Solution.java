package problem.p1656designanorderedstream;

import java.util.*;

/**
 * 1656. Design an Ordered Stream
 *
 * https://leetcode-cn.com/problems/design-an-ordered-stream/
 *
 * There is a stream of n (idKey, value) pairs arriving in an arbitrary order,
 * where idKey is an integer between 1 and n and value is a string. No two pairs have the same id.
 *
 * Design a stream that returns the values in increasing order of their IDs
 * by returning a chunk (list) of values after each insertion.
 *
 * The concatenation of all the chunks should result in a list of the sorted values.
 *
 * Implement the OrderedStream class:
 *
 * OrderedStream(int n)
 *  Constructs the stream to take n values.
 * String[] insert(int idKey, String value)
 *  Inserts the pair (idKey, value) into the stream,
 *  then returns the largest possible chunk of currently inserted values that appear next in the order.
 */

public class Solution {

    static class OrderedStream {
        private int current = 1;
        private final int limited;
        private final Map<Integer, String> words = new HashMap<>();

        public OrderedStream(int n) { limited = n; }

        public List<String> insert(int idKey, String value) {
            if (idKey == current) {
                List<String> ans = new ArrayList<>(); ans.add(value);
                for (int i = ++current; i <= limited && words.containsKey(i); i++, current++) {
                    ans.add(words.remove(i));
                }
                return ans;
            } words.put(idKey, value);
            return Collections.emptyList();
        }
    }


    public static void main(String[] args) {
        OrderedStream stream = new OrderedStream(5);
        assert stream.insert(3, "ccccc").equals(List.of());
        assert stream.insert(1, "aaaaa").equals(List.of("aaaaa"));
        assert stream.insert(2, "bbbbb").equals(List.of("bbbbb", "ccccc"));
        assert stream.insert(5, "eeeee").equals(List.of());
        assert stream.insert(4, "ddddd").equals(List.of("ddddd", "eeeee"));
    }

}
