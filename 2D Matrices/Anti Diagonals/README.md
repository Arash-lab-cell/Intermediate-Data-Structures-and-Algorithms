# Anti Diagonals

## Problem Description

Given a `N x N` square matrix `A`, return a 2D array containing its anti-diagonals.

## Constraints

- `1 <= N <= 1000`
- `1 <= A[i][j] <= 1e9`

## Input Format

A single 2D array `A` of size `N x N`.

## Output Format

A 2D integer array of size `(2*N - 1) x N`, where each row represents one anti-diagonal of the input matrix. Positions with no corresponding element are filled with `0`.

## Examples

**Input 1:**
```
1 2 3
4 5 6
7 8 9
```

**Output 1:**
```
1 0 0
2 4 0
3 5 7
6 8 0
9 0 0
```

**Input 2:**
```
1 2
3 4
```

**Output 2:**
```
1 0
2 3
4 0
```

### Explanation

Each anti-diagonal is collected by walking the matrix along diagonals running from top-right to bottom-left. For Input 1, the diagonals are `[1]`, `[2, 4]`, `[3, 5, 7]`, `[6, 8]`, and `[9]`, each padded with zeros to match the row length `N`.

## Approach

1. Compute the number of anti-diagonal rows in the result: `2*N - 1`, each of length `N`.
2. First pass: for every starting column `j` from `0` to `N-1`, walk diagonally down-left starting at `(row=0, col=j)`, collecting elements into the current output row until indices go out of bounds.
3. Second pass: for every starting row `i` from `1` to `N-1`, walk diagonally down-left starting at `(row=i, col=N-1)`, collecting elements into the next output row.
4. Unused cells in each output row remain `0` by default (Java array initialization).

### Complexity

- **Time Complexity:** `O(N^2)` - every element of the matrix is visited exactly once.
- **Space Complexity:** `O(N^2)` - for the output array (input size is not counted as extra space).

## Solution (Java 8)

```java
public class Solution {
    public int[][] diagonal(int[][] A) {
        int n = A.length;
        int[][] ans = new int[2 * n - 1][n];
        int ansRow = 0;

        // Diagonals starting from the top row (columns 0 to n-1)
        for (int j = 0; j < n; j++) {
            int ansCol = 0;
            for (int r = 0, c = j; r < n && c >= 0; r++, c--) {
                ans[ansRow][ansCol] = A[r][c];
                ansCol++;
            }
            ansRow++;
        }

        // Diagonals starting from the last column (rows 1 to n-1)
        for (int i = 1; i < n; i++) {
            int ansCol = 0;
            for (int r = i, c = n - 1; r < n && c >= 0; r++, c--) {
                ans[ansRow][ansCol] = A[r][c];
                ansCol++;
            }
            ansRow++;
        }

        return ans;
    }
}
```

## How to Run

Compile and run using any Java 8+ compiler. Call `new Solution().diagonal(matrix)` with your `N x N` integer matrix to get the array of anti-diagonals.
