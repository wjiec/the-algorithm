#!/usr/bin/env bash
#
# 194. Transpose File
#
# https://leetcode-cn.com/problems/transpose-file/
#
# Given a text file file.txt, transpose its content.
#
# You may assume that each row has the same number of columns,
# and each field is separated by the ' ' character.
#
COLS=$(head -1 file.txt | wc -w)
for ((i = 1; i <= $COLS; i++)); do
  awk -v idx=$i '{print $idx}' file.txt | xargs
done
