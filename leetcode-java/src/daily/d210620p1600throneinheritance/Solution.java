package daily.d210620p1600throneinheritance;

import java.util.*;

/**
 * 1600. Throne Inheritance
 *
 * https://leetcode-cn.com/problems/throne-inheritance/
 *
 * A kingdom consists of a king, his children, his grandchildren, and so on. Every once in a while,
 * someone in the family dies or a child is born.
 *
 * The kingdom has a well-defined order of inheritance that consists of the king as the first member.
 * Let's define the recursive function Successor(x, curOrder),
 * which given a person x and the inheritance order so far,
 * returns who should be the next person after x in the order of inheritance.
 *
 * For example, assume we have a kingdom that consists of the king,
 * his children Alice and Bob (Alice is older than Bob), and finally Alice's son Jack.
 *
 * In the beginning, curOrder will be ["king"].
 * Calling Successor(king, curOrder) will return Alice, so we append to curOrder to get ["king", "Alice"].
 * Calling Successor(Alice, curOrder) will return Jack, so we append to curOrder to get ["king", "Alice", "Jack"].
 * Calling Successor(Jack, curOrder) will return Bob, so we append to curOrder to get ["king", "Alice", "Jack", "Bob"].
 * Calling Successor(Bob, curOrder) will return null. Thus the order of inheritance will be ["king", "Alice", "Jack", "Bob"].
 * Using the above function, we can always obtain a unique order of inheritance.
 *
 * Implement the ThroneInheritance class:
 *
 * ThroneInheritance(string kingName) Initializes an object of the ThroneInheritance class.
 *                                      The name of the king is given as part of the constructor.
 * void birth(string parentName, string childName) Indicates that parentName gave birth to childName.
 * void death(string name) Indicates the death of name. The death of the person doesn't affect the
 *                         Successor function nor the current inheritance order.
 *                         You can treat it as just marking the person as dead.
 * string[] getInheritanceOrder() Returns a list representing the current order of inheritance excluding dead people.
 */

public class Solution {

    static class ThroneInheritance {
        private final String king;
        private final Set<String> deaded;
        private final Map<String, List<String>> childrens;

        public ThroneInheritance(String kingName) {
            king = kingName;
            deaded = new HashSet<>();
            childrens = new HashMap<>();

            childrens.put(king, new ArrayList<>());
        }

        public void birth(String parentName, String childName) {
            childrens.get(parentName).add(childName);
            childrens.put(childName, new ArrayList<>());
        }

        public void death(String name) {
            deaded.add(name);
        }

        public List<String> getInheritanceOrder() {
            return inheritance(king);
        }

        private List<String> inheritance(String name) {
            List<String> order = new ArrayList<>();
            if (!deaded.contains(name)) order.add(name);

            for (var children : childrens.get(name)) {
                if (!childrens.get(children).isEmpty()) {
                    order.addAll(inheritance(children));
                } else if (!deaded.contains(children)) {
                    order.add(children);
                }
            }
            return order;
        }
    }

    public static void main(String[] args) {
        ThroneInheritance inheritance = new ThroneInheritance("king");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("king", "andy");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("king", "bob");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("king", "catherine");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("andy", "matthew");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("bob", "alex");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.birth("bob", "asha");
        System.out.println(inheritance.getInheritanceOrder());
        inheritance.death("bob");
        System.out.println(inheritance.getInheritanceOrder());
    }

}
