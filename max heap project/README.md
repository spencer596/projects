# Project 2: Stardew Valley using a Priority Queue

* Author: Spencer Pattillo
* Class: CS321 Section #002
* Semester: Fall 2022

## Overview


It basically runs a simulation based on the game Stardew Valley.

## Reflection



This project was definitely hard and confusing. The MaxHeap class was the most confusing part.
I couldn't figure out why test case 1 wasn't matching the output, so I thought it was MyPriorityQueue.
It wasn't MyPriorityQueue it was the MaxHeap heapify method that wasn't working. After running a few tests I finally fixed the method.

There was also a other bug that was frustrating at the very start. I kept getting a array out of bounds exception. 
I figured out that MaxHeap needs to start at 1 instead of 0, so I fixed that. I ran it again and didn't get the error.

## Compiling and Using


You need to run the program with the following command line arguments. 

java MyLifeInStarDew
<max-priority-level> <time-to-increment-priority>
<total simulation-time in days>
<task-generation-probability> [<seed>]

## Results 

Using the test case inputs, it matched the test case outputs. 

## Sources used

https://en.wikipedia.org/wiki/Min-max_heap
https://www.geeksforgeeks.org/max-heap-in-java/

----------

## Notes

* This README template is using Markdown. Here is some help on using Markdown: 
[markdown cheatsheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)


* Markdown can be edited and viewed natively in most IDEs such as Eclipse and VS Code. Just toggle
between the Markdown source and preview tabs.

* To preview your README.md output online, you can copy your file contents to a Markdown editor/previewer
such as [https://stackedit.io/editor](https://stackedit.io/editor).