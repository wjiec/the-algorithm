package daily.d210705p726numberofatoms;

import java.util.*;

/**
 * 726. Number of Atoms
 *
 * https://leetcode-cn.com/problems/number-of-atoms/
 *
 * Given a chemical formula (given as a string), return the count of each atom.
 *
 * The atomic element always starts with an uppercase character,
 * then zero or more lowercase letters, representing the name.
 *
 * One or more digits representing that element's count may follow if the count is greater than 1.
 * If the count is 1, no digits will follow. For example, H2O and H2O2 are possible, but H1O2 is impossible.
 *
 * Two formulas concatenated together to produce another formula. For example, H2O2He3Mg4 is also a formula.
 *
 * A formula placed in parentheses, and a count (optionally added) is also a formula.
 * For example, (H2O2) and (H2O2)3 are formulas.
 *
 * Given a formula, return the count of all elements as a string in the following form:
 * the first name (in sorted order), followed by its count (if that count is more than 1),
 * followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 */

public class Solution {

    private static class Atom {
        private int count = 0;
        private String name = "";
        private int prev = 0;

        public boolean isOpen() { return name.equals("("); }
        public boolean isClose() { return name.equals(")"); }
        public int getCount() { return count == 0 ? 1 : count; }
        public void close(int v) { prev = v; }

        public void append(char c) {
            if (c >= '0' && c <= '9') {
                count = (count * 10) + (c - '0');
            } else {
                name += c;
            }
        }

        public void multiply(int times) {
            if (count == 0) count = times;
            else count *= times;
        }
    }

    public String countOfAtoms(String formula) {
        Stack<Integer> pairs = new Stack<>();
        List<Atom> atoms = new ArrayList<>();
        for (var c : formula.toCharArray()) {
            if ((c >= 'A' && c <= 'Z') || (c == '(' || c == ')')) atoms.add(new Atom());
            atoms.get(atoms.size() - 1).append(c);

            if (c == '(') pairs.push(atoms.size() - 1);
            if (c == ')') atoms.get(atoms.size() - 1).close(pairs.pop());
        }

        for (int i = 0, l = atoms.size(); i < l; i++) {
            var curr = atoms.get(i);
            if (curr.isClose()) {
                int times = curr.getCount();
                for (int j = curr.prev + 1; j < i; j++) {
                    if (!atoms.get(j).isClose()) atoms.get(j).multiply(times);
                }
            }
        }

        Map<String, Integer> values = new HashMap<>();
        for (var atom : atoms) {
            if (!atom.isOpen() && !atom.isClose()) {
                values.put(atom.name, values.getOrDefault(atom.name, 0) + atom.getCount());
            }
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(Map.Entry.comparingByKey());
        queue.addAll(values.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            var atom = queue.remove();
            sb.append(atom.getKey());
            if (atom.getValue() != 1) sb.append(atom.getValue());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().countOfAtoms("H2O").equals("H2O");
        assert new Solution().countOfAtoms("Mg(OH)2").equals("H2MgO2");
        assert new Solution().countOfAtoms("K4(ON(SO3)2)2").equals("K4N2O14S4");
    }

}
