package weekly.bw147.B;

import java.util.*;

/**
 * 3408. Design Task Manager
 *
 * https://leetcode.cn/contest/biweekly-contest-147/problems/design-task-manager/
 *
 * There is a task management system that allows users to manage their tasks, each associated with a priority.
 * The system should efficiently handle adding, modifying, executing, and removing tasks.
 *
 * Implement the TaskManager class:
 *
 * TaskManager(vector<vector<int>>& tasks) initializes the task manager with a list of user-task-priority triples.
 * Each element in the input list is of the form [userId, taskId, priority], which adds a task to the specified
 * user with the given priority.
 *
 * void add(int userId, int taskId, int priority) adds a task with the specified taskId and priority to the user
 * with userId. It is guaranteed that taskId does not exist in the system.
 *
 * void edit(int taskId, int newPriority) updates the priority of the existing taskId to newPriority.
 * It is guaranteed that taskId exists in the system.
 *
 * void rmv(int taskId) removes the task identified by taskId from the system.
 * It is guaranteed that taskId exists in the system.
 *
 * int execTop() executes the task with the highest priority across all users.
 * If there are multiple tasks with the same highest priority, execute the one with the highest taskId.
 * After executing, the taskId is removed from the system. Return the userId associated with the executed task.
 * If no tasks are available, return -1.
 *
 * Note that a user may be assigned multiple tasks.
 */

public class Solution {

    private static class TaskManager {
        private final Map<Integer, Integer> taskUser = new HashMap<>();
        private final Map<Integer, Integer> taskPriority = new HashMap<>();
        private final TreeMap<Integer, TreeSet<Integer>> priorityTask = new TreeMap<>();

        public TaskManager(List<List<Integer>> tasks) {
            for (var task : tasks) add(task.get(0), task.get(1), task.get(2));
        }

        // userId 是 taskId 的一个属性
        public void add(int userId, int taskId, int priority) {
            taskUser.put(taskId, userId);
            taskPriority.put(taskId, priority);
            priorityTask.computeIfAbsent(priority, k -> new TreeSet<>()).add(taskId);
        }

        public void edit(int taskId, int newPriority) {
            rmv(taskId);
            add(taskUser.get(taskId), taskId, newPriority);
        }

        public void rmv(int taskId) {
            int priority = taskPriority.get(taskId);
            priorityTask.get(priority).remove(taskId);
            if (priorityTask.get(priority).isEmpty()) priorityTask.remove(priority);
        }

        public int execTop() {
            if (priorityTask.isEmpty()) return -1;

            var last = priorityTask.lastEntry();
            int lastTask = last.getValue().last();
            rmv(lastTask);

            return taskUser.get(lastTask);
        }
    }

    public static void main(String[] args) {
    }

}
