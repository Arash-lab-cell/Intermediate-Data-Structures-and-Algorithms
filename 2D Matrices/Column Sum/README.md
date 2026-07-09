# Column Sum

## Problem Statement
You are given a 2D integer matrix `A`. Return a 1D integer array containing the column-wise sums of the matrix.

Constraints:
- 1 <= A.size() <= 10^3
- 1 <= A[i].size() <= 10^3
- 1 <= A[i][j] <= 10^3

Input Format: A single 2D array of integers.
Output Format: An array containing column-wise sums of the matrix.

## Example
Input:
```
A = [[1,2,3,4],[5,6,7,8],[9,2,3,4]]
```

Output:
```
[15,10,13,16]
```

Explanation:
- Column 1 = 1+5+9 = 15
- Column 2 = 2+6+2 = 10
- Column 3 = 3+7+3 = 13
- Column 4 = 4+8+4 = 16

## Approach
The solution iterates over each column index, and for that fixed column, walks down every row while maintaining a running sum. Once every row has been added for a given column, the accumulated sum is stored in the answer array at that column's position.

## Algorithm
1. Determine the number of columns `n` from the first row of `A`.
2. Create an answer array `ans` of size `n`.
3. For every column index `j` from 0 to n-1:
   1. Initialize `sum` to 0.
   2. For every row index `i` from 0 to A.length-1, add `A[i][j]` to `sum`.
   3. Store `sum` in `ans[j]`.
4. Return `ans`.

## Dry Run
Input: A = [[1,2,3,4],[5,6,7,8],[9,2,3,4]]

- Column j=0: sum = 1+5+9 = 15 -> ans[0] = 15
- Column j=1: sum = 2+6+2 = 10 -> ans[1] = 10
- Column j=2: sum = 3+7+3 = 13 -> ans[2] = 13
- Column j=3: sum = 4+8+4 = 16 -> ans[3] = 16

Final Answer: [15, 10, 13, 16]

## Why Does This Work?
Every column sum is independent of every other column, since it only depends on the elements sharing that column index. By fixing the column and scanning every row exactly once, each element of the matrix is visited exactly once across the full run of the algorithm, guaranteeing a correct and complete accumulation for every column.

## Complexity Analysis
### Time Complexity
Every element of the matrix is visited exactly once: O(N x M), where N is the number of rows and M is the number of columns.

### Space Complexity
Only the output array of size M is used beyond the input: O(M).

## Solution (Java 8)
```java
public class Solution {
    public int[] solve(int[][] A) {
        int n = A[0].length;
        int[] ans = new int[n];
        for (int j = 0; j < n; j++) {
            int sum = 0;
            for (int i = 0; i < A.length; i++) {
                sum = sum + A[i][j];
            }
            ans[j] = sum;
        }
        return ans;
    }
}
```

## Key Learning
- Column-wise aggregation can be computed cleanly by swapping the traversal order: outer loop over columns, inner loop over rows.
- No extra space is needed beyond the output array, since each column sum can be computed independently with a single running total.
- This pattern generalizes directly to row-wise sums by simply swapping the loop order (outer over rows, inner over columns).
