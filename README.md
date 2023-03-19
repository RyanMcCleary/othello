# Othello

## BUILDING

This repository contains a gradle wrapper.
Assuming that you have installed the JDK, you can build the project using the command

	./gradlew build

on Mac and Linux, or

	gradlew build

on Windows. 
Run this command from the project's root directory (othello). 
After building, you can use the command './gradlew run' (or 'gradlew run' on Windows) 
to run the project.

## Useful Resources

To get started with the Monte Carlo Tree Search (MCTS) algorithm, have a look at
[this GeeksForGeeks page](https://www.geeksforgeeks.org/ml-monte-carlo-tree-search-mcts/) and 
[the Wikipedia article](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search).
Also, [this article](https://www.chessprogramming.org/Monte-Carlo_Tree_Search) on the Chess Programming Wiki is helpful.
These articles all mention the UCB (upper confidence bound) algorithm, which we will use to decide which positions to explore.
If you want to see the more technical details justifying UCB, [these lecture notes](https://ieor8100.github.io/mab/Lecture%203.pdf)
have a lot of information.
The notes do not mention MCTS in particular, but we can think of each board position as a multi-armed bandit, where each possible
move from that position is an arm, and the payoff is 1 if that move leads to a win and -1 otherwise.
If these lecture notes are unclear at first, you can also read [a previous set of notes](https://ieor8100.github.io/mab/Lecture%202.pdf)
from the same course introducing multi-armed bandits, and [the Wikipedia article](https://en.wikipedia.org/wiki/Multi-armed_bandit).

