package problem.p341flattennestedlistiterator;

import common.NestedInteger;

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
            new  NestedInteger(1),
            new  NestedInteger(List.of(
                new  NestedInteger(2),
                new  NestedInteger(List.of()),
                new  NestedInteger(List.of(
                    new  NestedInteger(3),
                    new  NestedInteger(4),
                    new  NestedInteger(List.of(
                        new  NestedInteger(5),
                        new  NestedInteger(6)
                    )),
                    new  NestedInteger(7)
                )),
                new  NestedInteger(8)
            )),
            new  NestedInteger(9),
            new  NestedInteger(List.of(
                new  NestedInteger(10),
                new  NestedInteger(11),
                new  NestedInteger(List.of(
                    new  NestedInteger(12),
                    new  NestedInteger(13)
                )),
                new  NestedInteger(14)
            )),
            new  NestedInteger(15),
            new  NestedInteger(16),
            new  NestedInteger(List.of(
                new  NestedInteger(17),
                new  NestedInteger(List.of(
                    new  NestedInteger(18),
                    new  NestedInteger(List.of(
                        new  NestedInteger(19),
                        new  NestedInteger(List.of(
                            new  NestedInteger(20),
                            new  NestedInteger(List.of(
                                new  NestedInteger(21)
                            ))
                        ))
                    ))
                ))
            )),
            new  NestedInteger(22),
            new  NestedInteger(List.of())
        );

        NestedIterator iter = new NestedIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        iter = new NestedIterator(List.of());
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        iter = new NestedIterator(List.of(new  NestedInteger(List.of())));
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
