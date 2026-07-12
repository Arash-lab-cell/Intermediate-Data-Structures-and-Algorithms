# Add the Matrices

## Problem Statement

Given two matrices `A` and `B` of the same size (same number of rows and same number of columns), return a new matrix that is the element-wise sum of `A` and `B`.

**Constraints:**
- `1 <= A.size(), B.size() <= 1000`
- `1 <= A[i].size(), B[i].size() <= 1000`
- `1 <= A[i][j], B[i][j] <= 1000`

**Input Format:** The first argument is the 2D integer array `A`. The second argument is the 2D integer array `B`.

**Output Format:** Return a 2D integer array — the element-wise sum of `A` and `B`.

## Example(s)

**Input 1:**
```
A = [[1, 2, 3],
     [4, 5, 6],
     [7, 8, 9]]

B = [[9, 8, 7],
     [6, 5, 4],
     [3, 2, 1]]
```

**Output 1:**
```
[[10, 10, 10],
 [10, 10, 10],
 [10, 10, 10]]
```

| Cell | Computation | Result |
|------|-------------|--------|
| (0,0) | 1 + 9 | 10 |
| (1,1) | 5 + 5 | 10 |
| (2,2) | 9 + 1 | 10 |

**Input 2:**
```
A = [[1, 2, 3],
     [4, 1, 2],
     [7, 8, 9]]

B = [[9, 9, 7],
     [1, 2, 4],
     [4, 6, 3]]
```

**Output 2:**
```
[[10, 11, 10],
 [5,   3,  6],
 [11, 14, 12]]
```

## Approach

Matrix addition is a purely positional operation — the value at position `(i, j)` in the result depends only on the values at `(i, j)` in `A` and `B`. Since both matrices are guaranteed to be the same size, there's no need for bounds-checking between them: a single nested loop over every `(i, j)` pair is enough to build the answer.

## Algorithm

1. Read `n` (number of rows) from `A.length` and `m` (number of columns) from `A[0].length`.
2. Create a new result matrix `ans` of size `n x m`.
3. For each row `i` from `0` to `n-1`:
   - For each column `j` from `0` to `m-1`:
     - Set `ans[i][j] = A[i][j] + B[i][j]`.
4. Return `ans`.

## Dry Run

Using Input 1:
```
A = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
B = [[9, 8, 7], [6, 5, 4], [3, 2, 1]]
```
- i=0: ans[0] = [1+9, 2+8, 3+7] = [10, 10, 10]
- i=1: ans[1] = [4+6, 5+5, 6+4] = [10, 10, 10]
- i=2: ans[2] = [7+3, 8+2, 9+1] = [10, 10, 10]
- Result: `[[10,10,10],[10,10,10],[10,10,10]]` ✅ matches Output 1.

## Why Does This Work?

Matrix addition is defined element-wise, so visiting every `(i, j)` position exactly once and summing the corresponding entries from `A` and `B` produces the complete, correct result by definition — there's no dependency between cells, so a simple double loop fully covers the problem.

## Complexity Analysis

**Time Complexity:** O(N × M) — every cell of the N×M matrix is visited and computed exactly once.
**Space Complexity:** O(N × M) — a new output matrix of the same size as the input is allocated.

## Solution

```java
public class Solution {
    public int[][] solve(int[][] A, int[][] B) {
        int n = A.length;
        int m = A[0].length;
        int[][] ans = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                ans[i][j] = A[i][j] + B[i][j];
            }
        }
        return ans;
    }
}
```

## Key Learning

Element-wise matrix operations (addition, subtraction, Hadamard product) all share this same shape: a double loop over positions with no cross-cell dependency, which keeps them at O(N × M) time and trivially parallelizable. The only real risk is calling `.length` on the wrong array (e.g. reading `m` from `B` instead of `A`) — since the problem guarantees equal dimensions, either works, but being consistent avoids bugs if that guarantee is ever relaxed.
