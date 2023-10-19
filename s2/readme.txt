/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name: Marteinn Lundi Kjartansson            
Login: marteinn22@ru.is         

Partner name: Óðinn karl Skúlason   
Partner login: odinns20@ru.is  

Hours to complete assignment (optional): 30 hours or more


/**********************************************************************
 *  Step 1.  Explain *briefly* how you implemented brute force.
 *           Describe how you implemented compareTo() and the
 *           slopeTo() methods in the Point data type.
 **********************************************************************/
    compareTo():
        This method compares two points by their y-coordinates and breaks ties by x-coordinates. It returns -1 if this point is smaller, 1 if larger, and 0 if equal.
    slopeTo():
        Calculates the slope between this point and that point, considering the following:
            Returns +0.0 if the line segment connecting the two points is horizontal.
            Returns +∞ if the line segment is vertical.
            Returns -∞ if this point and that point are equal.
            Otherwise, it computes the slope using the formula: (that.y - this.y) / (that.x - this.x).

The brute force approach involves examining every possible combination of points and checking for collinearity.

    Nested loops iterate through every combination of 4 points.
    It calculates the slopes between points and checks if they're equal.
    If they are, a line segment with these points is stored.

/**********************************************************************
 *  Step 2.  Explain *briefly* how you implemented the sorting solution.
 *           What steps did you do to avoid outputting permutations
 *           and subsegments?
 **********************************************************************/
    Avoiding Output Permutations and Subsegments:
        Arrays.sort(points): Sorts the array in natural order to identify and handle smaller endpoints distinctly.

    Sorting & Finding Collinear Points:
        The algorithm iterates through each point p and sorts a copy of the points array according to the slopes they make with p (Arrays.sort(pointsCopy, points[p].slopeOrder())).
        It then looks for sequences of 3 or more adjacent points that have equal slopes concerning p, implying collinearity.
        Checks are in place to verify if p is the smallest point in a segment to avoid subsegment logging and permutation issues.

    Storing Segments:
        When a segment is identified, it is stored in a queue. Each segment itself is a queue of points.

/**********************************************************************
 *  Empirical    Fill in the table below with actual running times in
 *  Analysis     seconds when reasonable (say 180 seconds or less).
 *               You can round to the nearest tenth of a second.
 *
 *  Estimate (using tilde notation) the running time (in seconds) of
 *  your two main functions as a function of the number of points N.
 *
 *  Explain how you derive any exponents.
 **********************************************************************/

    
     N       brute       sorting
 ---------------------------------
    150        0.183     0.014
    200        0.547     0.036
    300        3.205     0.053
    400        10.378    0.101
    800        161.428   0,365
   1600        1863.682  1.119
   3200                  3.728
   6400                  15.112
  12800                  51.926


Brute:    ~ a * N^1.583

Sorting:  ~ a * N^0.344



/**********************************************************************
 *  Theoretical   Give the order of growth of the worst-case running
 *                time of your programs as a function of N. Justify
 *                your answer briefly.
 **********************************************************************/

Brute:
     The worst-case running time of the brute force solution is O(N^4).
     This is because we check all combinations of 4 points from N points, resulting in O(N^4) comparisons.

Sorting:
     The worst-case running time of the sorting solution is O(N^2 * log N).
     This is because for each point, we sort the remaining points based on their slopes to the reference point, which takes O(N * log N) time.
     We do this for each of the N points, resulting in O(N^2 * log N) comparisons.


/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/
     Both Brute and Fast classes might not handle the input with duplicate points adequately.
     When there are 5 or more points on a line segment, the programs may output different representations of the same line segment, 
     essentially treating sub-segments as distinct, which might not be the desired output.   


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/
     We received help from Daði Rúnarsson when implemeting the Fast.java class because we misunderstood the requirements and spend many hours trying somethings that where not working. 
     He helped us understand best practice of how Fast should work and after that everything began to come together.
     
/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/
     We will descripe the problems in next section.

/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/
     We had many problems trying to understand the description. The checklist was not helpful at all and there was a break in the links on it.
     We spend many hours doing stuff that we thought we were suppose to do because of how we understood the description.
     It was also very hard trying to keep up with all the changes and new information that came through Piazza.
     We are both dyslexic so the format on the description is very undfriendly for us.