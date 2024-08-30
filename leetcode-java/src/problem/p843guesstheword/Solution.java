package problem.p843guesstheword;

import java.util.ArrayList;
import java.util.List;

/**
 * 843. Guess the Word
 *
 * https://leetcode.cn/problems/guess-the-word/
 *
 * You are given an array of unique strings words where words[i] is six letters long.
 * One word of words was chosen as a secret word.
 *
 * You are also given the helper object Master. You may call Master.guess(word) where
 * word is a six-letter-long string, and it must be from words. Master.guess(word) returns:
 *
 * -1 if word is not from words, or
 * an integer representing the number of exact matches (value and position) of your guess to the secret word.
 * There is a parameter allowedGuesses for each test case where allowedGuesses is the
 * maximum number of times you can call Master.guess(word).
 *
 * For each test case, you should call Master.guess with the secret word without
 * exceeding the maximum number of allowed guesses. You will get:
 *
 * "Either you took too many guesses, or you did not find the secret word." if you
 * called Master.guess more than allowedGuesses times or if you did not call Master.guess
 * with the secret word, or
 * "You guessed the secret word correctly." if you called Master.guess with the secret
 * word with the number of calls to Master.guess less than or equal to allowedGuesses.
 * The test cases are generated such that you can guess the secret word with a reasonable
 * strategy (other than using the bruteforce method).
 * @noinspection unchecked
 */

public class Solution {

    interface Master { int guess(String word); }

    private int[][] H;
    public void findSecretWord(String[] wordlist, Master master) {
        int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                        match++;
                H[i][j] = H[j][i] = match;
            }
        }

        List<Integer> possible = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList<>();
            for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
            possible = possible2;
            path.add(guess);
        }

    }

    public int solve(List<Integer> possible, List<Integer> path) {
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;
        int ansguess = -1;

        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<>();
                for (Integer j: possible) if (j != guess) {
                    groups[H[guess][j]].add(j);
                }

                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i)
                    if (groups[i].size() > maxgroup.size())
                        maxgroup = groups[i];

                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }

    public static void main(String[] args) {
    }

}
