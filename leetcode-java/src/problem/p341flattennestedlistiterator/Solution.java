package problem.p341flattennestedlistiterator;

import java.util.*;

/**
 * 341. Flatten Nested List Iterator
 *
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 *
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 *
 * Implement the NestedIterator class:
 *
 * NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * int next() Returns the next integer in the nested list.
 * boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 */

public class Solution {

    private interface NestedInteger {
        boolean isInteger();
        Integer getInteger();
        List<NestedInteger> getList();
    }

    private static class NestedIntegerImpl implements NestedInteger {
        private final Integer value;
        private final List<NestedInteger> list;
        public NestedIntegerImpl(Integer v) { this(v, null); }
        public NestedIntegerImpl(List<NestedInteger> l) { this(null, l); }
        public NestedIntegerImpl(Integer v, List<NestedInteger> l) { value = v; list = l; }

        @Override public boolean isInteger() { return value != null; }
        @Override public Integer getInteger() { return value; }
        @Override public List<NestedInteger> getList() { return list; }
    }

    private static class NestedIterator implements Iterator<Integer> {
        private final Deque<Iterator<NestedInteger>> stack = new ArrayDeque<>();
        public NestedIterator(List<NestedInteger> nestedList) { stack.add(nestedList.iterator()); }

        @Override public Integer next() { return stack.peek().next().getInteger(); }
        @Override public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> iter = stack.peek();
                if (!iter.hasNext()) { stack.pop(); continue; }

                NestedInteger next = iter.next();
                if (next.isInteger()) {
                    List<NestedInteger> list = new ArrayList<>();
                    list.add(next); stack.push(list.iterator());
                    return true;
                }
                stack.push(next.getList().iterator());
            }
            return false;
        }
    }

    public static void main(String[] args) {
        List<NestedInteger> list = List.of(
            new NestedIntegerImpl(1),
            new NestedIntegerImpl(List.of(
                new NestedIntegerImpl(2),
                new NestedIntegerImpl(List.of()),
                new NestedIntegerImpl(List.of(
                    new NestedIntegerImpl(3),
                    new NestedIntegerImpl(4),
                    new NestedIntegerImpl(List.of(
                        new NestedIntegerImpl(5),
                        new NestedIntegerImpl(6)
                    )),
                    new NestedIntegerImpl(7)
                )),
                new NestedIntegerImpl(8)
            )),
            new NestedIntegerImpl(9),
            new NestedIntegerImpl(List.of(
                new NestedIntegerImpl(10),
                new NestedIntegerImpl(11),
                new NestedIntegerImpl(List.of(
                    new NestedIntegerImpl(12),
                    new NestedIntegerImpl(13)
                )),
                new NestedIntegerImpl(14)
            )),
            new NestedIntegerImpl(15),
            new NestedIntegerImpl(16),
            new NestedIntegerImpl(List.of(
                new NestedIntegerImpl(17),
                new NestedIntegerImpl(List.of(
                    new NestedIntegerImpl(18),
                    new NestedIntegerImpl(List.of(
                        new NestedIntegerImpl(19),
                        new NestedIntegerImpl(List.of(
                            new NestedIntegerImpl(20),
                            new NestedIntegerImpl(List.of(
                                new NestedIntegerImpl(21)
                            ))
                        ))
                    ))
                ))
            )),
            new NestedIntegerImpl(22),
            new NestedIntegerImpl(List.of())
        );

        NestedIterator iter = new NestedIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        iter = new NestedIterator(List.of());
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        iter = new NestedIterator(List.of(new NestedIntegerImpl(List.of())));
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
