# Rotate Matrix

## Problem Statement
Given an `n x n` 2D matrix `A` representing an image, rotate the image by 90 degrees (clockwise), in place. If an additional array is used instead of rotating in place, only partial credit is awarded.

**Constraints:** `1 <= n <= 1000`

**Input Format:** A 2D matrix `A` of integers.

**Output Format:** The 2D matrix rotated 90 degrees clockwise, in place.

## Example(s)

| Input | Output (rotated 90° clockwise) |
|---|---|
| `[[1,2],[3,4]]` | `[[3,1],[4,2]]` |
| `[[1,2,3],[4,5,6],[7,8,9]]` | `[[7,4,1],[8,5,2],[9,6,3]]` |

For the 3x3 example: 1→position(0,2), 3→(0,0), 9→(2,0), 7→(2,2) — each corner cycles to the next corner clockwise, and the same pattern holds for every ring of the matrix.

## Approach
A 90-degree clockwise rotation can be decomposed into two simpler, well-known in-place operations: first **transpose** the matrix (swap `A[i][j]` with `A[j][i]` for all `i < j`, flipping it across the main diagonal), then **reverse each row** left-to-right. Doing both, in that order, produces exactly the clockwise-rotated matrix without needing any extra matrix.

## Algorithm
1. **Transpose:** for `i` from `0` to `n-1`, for `j` from `i+1` to `n-1`, swap `A[i][j]` and `A[j][i]`.
2. **Reverse each row:** for each row `i`, use two pointers `l = 0` and `r = n-1`; while `l < r`, swap `A[i][l]` and `A[i][r]`, then increment `l` and decrement `r`.

## Dry Run
`A = [[1,2,3],[4,5,6],[7,8,9]]`

Step 1 — Transpose (swap across diagonal):
```
1 4 7
2 5 8
3 6 9
```

Step 2 — Reverse each row:
```
7 4 1
8 5 2
9 6 3
```
This matches the expected 90°-clockwise-rotated matrix.

## Why Does This Work?
Transposing swaps rows and columns, turning what was column `j` into row `j`. Reversing each row afterward reorders the elements within that row from left-to-right into right-to-left order. The combination of "swap rows/columns" then "reverse each row" is mathematically equivalent to a 90-degree clockwise rotation of the whole grid — this is a standard, well-known decomposition, not a coincidence specific to this example.

## Complexity Analysis
**Time Complexity:** O(N²) — every cell of the matrix is visited a constant number of times (once during transpose, once during row reversal).
**Space Complexity:** O(1) — the rotation happens entirely in place, using only a temporary swap variable.

## Solution
```java
public class Solution {
    public int[][] transpose(int[][] A){
        int n = A.length;
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
        return A;
    }
    public void solve(int[][] A) {
        int n = A.length;
        transpose(A);
        for(int i = 0; i < n; i++){
            int l = 0;
            int r = n-1;
            while(l < r){
                int temp = A[i][l];
                A[i][l] = A[i][r];
                A[i][r] = temp;
                l++;
                r--;
            }
        }
    }
}
```

## Key Learning
Many "rotate the matrix" problems reduce to composing two simpler primitive operations (transpose + reverse rows for clockwise; transpose + reverse columns, or reverse rows + transpose, for counter-clockwise) — recognizing this decomposition avoids having to reason about complex index math directly, and both primitives are themselves reusable building blocks (the `transpose` helper here is identical to the one used in the Matrix Transpose problem).
