package daily.d210501p690employeeimportance;

import java.util.*;

/**
 * 690. Employee Importance
 *
 * https://leetcode-cn.com/problems/employee-importance/
 *
 * You are given a data structure of employee information, which includes the employee's unique id,
 * their importance value and their direct subordinates' id.
 *
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]],
 * and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []].
 *
 * Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 *
 * Now given the employee information of a company, and an employee id,
 * you need to return the total importance value of this employee and all their subordinates.
 */

public class Solution {

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> es = new HashMap<>();
        for (var e : employees) {
            es.put(e.id, e);
        }

        int rs = es.get(id).importance;
        Stack<Integer> subordinates = new Stack<>();
        subordinates.addAll(es.get(id).subordinates);

        while (!subordinates.empty()) {
            var subordinate = es.get(subordinates.pop());
            subordinates.addAll(subordinate.subordinates);
            rs += subordinate.importance;
        }

        return rs;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getImportance(new ArrayList<>() {{
            add(new Employee(1, 10, new ArrayList<>() {{ add(2); }}));
            add(new Employee(2, 5, new ArrayList<>() {{ add(3); add(5); }}));
            add(new Employee(3, 3, new ArrayList<>()));
            add(new Employee(4, 2, new ArrayList<>()));
            add(new Employee(5, 1, new ArrayList<>()));
        }}, 1));
    }

}
