/*************************************************************************
*****
* Canvas Group:
* Name & Kt: Marteinn Lundi Kjartansson kt.250202-2960
* Name & Kt: Óðinn Karl Skúlason kt.300399-
*
* Operating system: Windows
* Compiler: Visual Studio Code Compiler
* Text editor / IDE: Visual Studio Code
*
* Have you taken (part of) this course before: No neither one of us have taken part of this course.
*
* Hours to complete assignment (optional): Part A: 10 hours, Part B: 3 hours.
*
*************************************************************************
*****/
/*************************************************************************
*****
* Describe how you implemented Percolation.java. How did you check
* whether the system percolates?
*************************************************************************
****/
Our implementation of the Percolation system represents a model for an N by N grid using Weighted Quick Union Find 
Data structure. This allowed us to determine whether the system percolates, which means that there is a path of open sites 
from the top row to the bottom row.
The code initalizes the grid and implements operations to maipulate and analyze it. 
The percolation status is determined by checking wheter the virtual top node(source) is connected to the bottom node(sink) 
in the union-find structure.
/*************************************************************************
*****
* Using Percolation with QuickFindUF.java, fill in the table below such
that
* the N values are multiples of each other.
* Give a formula (using tilde notation) for the running time (in
seconds) of
* PercolationStats.java as a function of both N and T. Be sure to give
both
* the coefficient and exponent of the leading term. Your coefficients
should
* be based on empirical data and rounded to two significant digits, such
as
* 5.3*10^-8 * N^5.0 T^1.5.
*************************************************************************
****/
T = 100
N time (seconds)
------------------------------
50
100
200
400
800
1600
------------------------------

N = 100
T time (seconds)
------------------------------
50
100
200
400
800
1600
Running time as a function of N and T: ~
Reasoning for this running time:
/*************************************************************************
*****
* Repeat the previous question, but use WeightedQuickUnionUF.java.
*************************************************************************
****/

T = 100
N time (seconds)
------------------------------
50
100
200
400
800
1600

N = 100
T time (seconds)
------------------------------
50
100
200
400
800
1600

Running time as a function of N and T: ~
Reasoning for this running time:
/**********************************************************************
* How much memory (in bytes) does a Percolation object use to store
* an N-by-N grid? Use the 64-bit memory cost model from Section 1.4
* of the textbook and use tilde notation to simplify your answer.
* Briefly justify your answers.
*
* Include the memory for all referenced objects (deep memory).
**********************************************************************/
/*************************************************************************
*****
* Known bugs / limitations.
*************************************************************************
****/
/*************************************************************************
*****
* Describe whatever help (if any) that you received.
* Don't include readings, lectures, and classes, but do
* include any help (or discussions) from people (including course staff,
lab TAs,
* classmates, and friends) and attribute them by name.
*************************************************************************
****/
/*************************************************************************
*****
* List any other comments here. Feel free to provide any feedback
* on how much you learned from doing the assignment, and whether
* you enjoyed doing it.
*************************************************************************
****/