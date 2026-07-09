# Minor Diagonal Sum

## Problem Statement
You are given an N x N integer matrix `A`. Return the sum of all the minor diagonal elements of `A`.

The minor diagonal of an M x M matrix is the collection of elements `A[i][j]` such that `i + j = M + 1` (1-based indexing).

Constraints:
- 1 <= N <= 10^3
- -1000 <= A[i][j] <= 1000

Input Format: A single 2D integer matrix A.
Output Format: A single integer representing the sum of minor diagonal elements.

## Example
Input 1:
```
A = [[1,-2,-3],[-4,5,-6],[-7,-8,9]]
```
Output 1: -5 (A[1][3] + A[2][2] + A[3][1] = -3 + 5 + -7 = -5, 1-based)

Input 2:
```
A = [[3,2],[2,3]]
```
Output 2: 4 (A[1][2] + A[2][1] = 2 + 2 = 4, 1-based)

## Approach
The minor diagonal runs from the top-right corner to the bottom-left corner of the matrix. A single loop can walk the row index upward from 0 while simultaneously walking the column index downward from the last column, accumulating each visited element into a running total.

## Algorithm
1. Determine `n` (rows) and `m` (columns) from `A`.
2. Initialize `sum` to 0.
3. For `i` starting at 0 and `j` starting at m-1, incrementing `i` and decrementing `j` together while `i < n`, add `A[i][j]` to `sum`.
4. Return `sum`.

## Dry Run
Input (0-based): A = [[1,-2,-3],[-4,5,-6],[-7,-8,9]]

- i=0, j=2: sum += A[0][2] = -3 -> sum = -3
- i=1, j=1: sum += A[1][1] = 5 -> sum = 2
- i=2, j=0: sum += A[2][0] = -7 -> sum = -5

Final Answer: -5

## Why Does This Work?
By definition, the minor diagonal (1-based i + j = M + 1) corresponds, in 0-based indexing, to every position where the row index and the mirrored column index (m-1-i) coincide. Walking row index up while column index moves down in lockstep visits exactly these positions, one time each, so the running total correctly equals the minor diagonal sum.

## Complexity Analysis
### Time Complexity
O(N) - the loop visits exactly N diagonal elements.

### Space Complexity
O(1) - only a single accumulator variable is used.

## Solution (Java 8)
```java
public class Solution {
    // DO NOT MODIFY THE ARGUMENTS WITH "final" PREFIX. IT IS READ ONLY
    public int solve(final int[][] A) {
        int sum = 0;
        int n = A.length;
        int m = A[0].length;
        for (int i = 0, j = m - 1; i < n; i++, j--) {
            sum = sum + A[i][j];
        }
        return sum;
    }
}
```

## Key Learning
- The minor diagonal is the mirror image of the main diagonal: instead of both indices increasing together, one increases while the other decreases.
- This is another example of an O(N) lockstep traversal, avoiding any need for nested loops.
- Recognizing diagonal patterns (main vs minor, anti-diagonal) as index relationships makes these problems quick to solve once the underlying index formula is identified.
