#!/usr/bin/env bash

:<<LEETCODE
195. Tenth Line

https://leetcode-cn.com/problems/tenth-line/

Given a text file file.txt, print just the 10th line of the file.
LEETCODE

set -ex

awk '{if (NR == 10) { print $0 } }' file.txt
