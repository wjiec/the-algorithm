package problem.p1700numberofstudentsunabletoeatlunch;

/**
 * 1700. Number of Students Unable to Eat Lunch
 *
 * https://leetcode-cn.com/problems/number-of-students-unable-to-eat-lunch/
 *
 * The school cafeteria offers circular and square sandwiches at lunch break,
 * referred to by numbers 0 and 1 respectively.
 *
 * All students stand in a queue. Each student either prefers square or circular sandwiches.
 *
 * The number of sandwiches in the cafeteria is equal to the number of students.
 *
 * The sandwiches are placed in a stack. At each step:
 *
 * If the student at the front of the queue prefers the sandwich on the top of the stack,
 * they will take it and leave the queue.
 * Otherwise, they will leave it and go to the queue's end.
 * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
 *
 * You are given two integer arrays students and sandwiches where sandwiches[i]
 * is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack)
 * and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue).
 *
 * Return the number of students that are unable to eat.
 */

public class Solution {

    public int countStudents(int[] students, int[] sandwiches) {
        int l = students.length, ans = l, r = 0;
        for (boolean unlike = true; unlike; ) {
            unlike = false;
            for (int i = 0; i < l; i++) {
                if (students[i] != -1) {
                    if (students[i] == sandwiches[r]) {
                        r++; ans--;
                        students[i] = -1;
                        unlike = true;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countStudents(new int[]{1,1,0,0}, new int[]{0,1,0,1}) == 0;
        assert new Solution().countStudents(new int[]{1,1,1,0,0,1}, new int[]{1,0,0,0,1,1}) == 3;
    }

}
