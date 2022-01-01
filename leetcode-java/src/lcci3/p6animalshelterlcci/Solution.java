package lcci3.p6animalshelterlcci;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 面试题 03.06. 动物收容所
 *
 * https://leetcode-cn.com/problems/animal-shelter-lcci/
 *
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 *
 * 在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，
 * 或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
 *
 * 换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，
 *
 * 比如enqueue、dequeueAny、dequeueDog和dequeueCat。允许使用Java内置的LinkedList数据结构。
 *
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 *
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 */

public class Solution {

    private static class AnimalShelf {

        private int index = 0;
        private final Queue<int[]> cats = new ArrayDeque<>();
        private final Queue<int[]> dogs = new ArrayDeque<>();

        public AnimalShelf() { }

        public void enqueue(int[] animal) {
            switch (animal[1]) {
                case 0: // cat
                    cats.add(new int[]{index++, animal[0]});
                    break;
                case 1: // dog
                    dogs.add(new int[]{index++, animal[0]});
                    break;
            }
        }

        public int[] dequeueAny() {
            if (cats.isEmpty() && dogs.isEmpty()) {
                return new int[]{-1, -1};
            }

            if (!cats.isEmpty() && !dogs.isEmpty()) {
                if (cats.peek()[0] < dogs.peek()[0]) {
                    return dequeueCat();
                }
                return dequeueDog();
            }

            if (cats.isEmpty()) return dequeueDog();
            return dequeueCat();
        }

        public int[] dequeueDog() {
            if (dogs.isEmpty()) {
                return new int[]{-1, -1};
            }
            return new int[]{dogs.remove()[1], 1};
        }

        public int[] dequeueCat() {
            if (cats.isEmpty()) {
                return new int[]{-1, -1};
            }
            return new int[]{cats.remove()[1], 0};
        }
    }

    public static void main(String[] args) {
        AnimalShelf shelf = new AnimalShelf();
        shelf.enqueue(new int[]{0, 0});
        shelf.enqueue(new int[]{1, 0});
        assert Checker.check(shelf.dequeueCat(), new int[]{0, 0});
        assert Checker.check(shelf.dequeueDog(), new int[]{-1, -1});
        assert Checker.check(shelf.dequeueAny(), new int[]{1, 0});
    }

}
