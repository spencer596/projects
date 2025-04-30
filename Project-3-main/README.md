# Project 3: Turing Machine Simulator

* **Author:** Joe Shields & Spencer Pattillo
* **Class:** CS361 Section #001
* **Semester:** Spring 2025  

## Overview

This program allows for the simulation of a Turing Machine. 

---

## Reflection

The project was an interesting challenge. In elected to build most of the structures to support the simulation internally and relied on array indexing to speed up the run-times where possible. This only works because the inputs are all sequential and integer-based. Reworking it to accept any input wouldn't be difficult but would slow down the simulation.

### **What Worked Well?**
- I was concerned about using the array indexing at first, particularly using the three arrays in TMState to track transitions, but I think the final implementation is faster than using hashtables and parsing parameters out of a Transition object every time.

### **Challenges & Struggles**
- I accidentally put a recursive infinite loop into the TapeNode class... and an infinite while loop in the TapeNode class... but I found and fixed them.
- There was an issue with the readFile() method correctly parsing certain files, although why some files worked and some did not was never ascertained. Replacing Scanner.useDelimeter() with String.trim() solved the issue despite the fact that the error, when it did occur, happened before any delimeter was reached. Mysteries abound.

### **Design Process & Improvements**
- The design process was fairly smooth from start to finish. I made the double linked nodes self-iterating to save time/memory. I considered finding a way to make fewer calls to the timer but everything I came up with involved just as many function calls. 
- The piece I added that might slow down the tests a bit was the check for a reject state. In the event that a state only has self-loops, it is added to a condition check that will cause the simulation to fail before the 5 minutes has ellapsed. I'm hoping when the time comes to compare who has the faster program, saving 4 minutes 57 seconds on one test gives us an edge on anything we lose elsewhere.
---

## Compiling and Using

### **Compiling the Code (Onyx)**
From the **top directory** of the project files, compile using:

```
javac tm/*.java
```

To run a simulation, use:

```
java tm/TMSimulator.java <filepath>
```