# Row Sum

## Problem Statement
You are given a 2D matrix `A` of integers. Compute the sum of elements in each row and return a 1D array where each element represents the sum of the corresponding row.

Constraints:
- 1 <= A.size() <= 10^3
- 1 <= A[i].size() <= 10^3
- 1 <= A[i][j] <= 10^3

Input Format: A single 2D array of integers.
Output Format: An array containing row-wise sums of the matrix.

## Example
Input:
```
A = [[1,2,3,4],[5,6,7,8],[9,2,3,4]]
```

Output:
```
[10,26,18]
```

Explanation:
- Row 1 = 1+2+3+4 = 10
- Row 2 = 5+6+7+8 = 26
- Row 3 = 9+2+3+4 = 18

## Approach
The solution iterates over each row index, and for that fixed row, walks across every column while maintaining a running sum. Once every column has been added for a given row, the accumulated sum is stored in the answer array at that row's position.

## Algorithm
1. Determine the number of rows `n` and columns `m`.
2. Create an answer array `ans` of size `n`.
3. For every row index `i` from 0 to n-1:
   1. Initialize `sum` to 0.
   2. For every column index `j` from 0 to m-1, add `A[i][j]` to `sum`.
   3. Store `sum` in `ans[i]`.
4. Return `ans`.

## Dry Run
Input: A = [[1,2,3,4],[5,6,7,8],[9,2,3,4]]

- Row i=0: sum = 1+2+3+4 = 10 -> ans[0] = 10
- Row i=1: sum = 5+6+7+8 = 26 -> ans[1] = 26
- Row i=2: sum = 9+2+3+4 = 18 -> ans[2] = 18

Final Answer: [10, 26, 18]

## Why Does This Work?
Each row sum depends only on the elements within that row. By fixing the row and scanning every column exactly once, every element of the matrix is visited exactly once overall, so every row total is accumulated correctly and completely.

## Complexity Analysis
### Time Complexity
O(N x M) - every element is visited exactly once, where N is the number of rows and M is the number of columns.

### Space Complexity
O(N) - only the output array of size N is used beyond the input.

## Solution (Java 8)
```java
public class Solution {
    public int[] solve(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum = sum + A[i][j];
            }
            ans[i] = sum;
        }
        return ans;
    }
}
```

## Key Learning
- Row-wise aggregation mirrors the column-sum pattern, just with the loop order swapped: outer loop over rows, inner loop over columns.
- Maintaining a running sum per row avoids any repeated traversal of the same elements.
- This is the simplest of the traversal-based matrix aggregation patterns, and a good baseline before tackling diagonal or boundary-based variants.
