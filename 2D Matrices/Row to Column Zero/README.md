# Row to Column Zero

## Problem Statement
You are given a 2D integer matrix `A`. Whenever an element `A[i][j]` is 0, the entire i-th row and the entire j-th column must be set to zero. Return the modified matrix.

Constraints:
- 1 <= A.size() <= 10^3
- 1 <= A[i].size() <= 10^3
- 0 <= A[i][j] <= 10^3

Input Format: A single 2D integer matrix A.
Output Format: The matrix after zeroing the required rows/columns.

## Example
Input:
```
A = [[1,2,3,4],[5,6,7,0],[9,2,0,4]]
```

Output:
```
[[1,2,0,0],[0,0,0,0],[0,0,0,0]]
```

Explanation:
A[1][3] = 0 and A[2][2] = 0, so rows 1 and 2, and columns 2 and 3, are all set to zero.

## Approach
Rather than zeroing cells while still scanning the matrix (which would incorrectly cascade extra zeros), the solution first records which rows and which columns contain at least one zero, using two boolean marker arrays. Once every zero has been located, a second pass zeroes out every cell whose row or column was marked.

## Algorithm
1. Determine the number of rows `n` and columns `m`.
2. Create boolean arrays `row` (size n) and `col` (size m), all initialized to false.
3. First pass - for every cell `A[i][j]`: if `A[i][j] == 0`, mark `row[i] = true` and `col[j] = true`.
4. Second pass - for every cell `A[i][j]`: if `row[i]` is true or `col[j]` is true, set `A[i][j] = 0`.
5. Return the modified matrix `A`.

## Dry Run
Input: A = [[1,2,3,4],[5,6,7,0],[9,2,0,4]]

- First pass detects a zero at (1,3) -> row[1]=true, col[3]=true
- First pass detects a zero at (2,2) -> row[2]=true, col[2]=true
- Second pass: Row 0 -> columns 2 and 3 become 0 -> [1, 2, 0, 0]
- Second pass: Row 1 -> entire row is marked -> [0, 0, 0, 0]
- Second pass: Row 2 -> entire row is marked -> [0, 0, 0, 0]

Final Answer: [[1,2,0,0],[0,0,0,0],[0,0,0,0]]

## Why Does This Work?
Zeroing cells directly during the scanning pass would create new zeros that could be mistaken for original zeros, incorrectly cascading further row/column wipes. By separating detection from mutation into two distinct passes, the algorithm guarantees that only rows and columns containing an original zero are wiped, regardless of the order cells are visited.

## Complexity Analysis
### Time Complexity
O(N x M) - the matrix is scanned twice, each pass visiting every cell once.

### Space Complexity
O(N + M) - for the two boolean marker arrays.

## Solution (Java 8)
```java
public class Solution {
    public int[][] solve(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        boolean[] row = new boolean[n];
        boolean[] col = new boolean[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    A[i][j] = 0;
                }
            }
        }
        return A;
    }
}
```

## Key Learning
- Detect-then-mutate is a common and important pattern whenever an in-place update could interfere with the detection condition itself.
- Marking rows/columns with boolean arrays keeps extra space linear in the matrix dimensions rather than needing a full second copy of the matrix.
- Note: the second nested loop in this solution bounds the inner index by `n` (row count) rather than `m` (column count). On non-square matrices where there are more columns than rows, the last few columns would not get zeroed correctly. Worth revisiting with non-square test cases.
