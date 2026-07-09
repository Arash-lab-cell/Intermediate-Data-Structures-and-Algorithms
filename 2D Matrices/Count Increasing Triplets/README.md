# Count Increasing Triplets

## Problem Statement
You are given an array `A` of N elements. Find the number of triplets `(i, j, k)` such that `i < j < k` and `A[i] < A[j] < A[k]`.

Constraints:
- 1 <= N <= 10^3
- 1 <= A[i] <= 10^9

Input Format: An array of integers A.
Output Format: A single integer representing the count of valid increasing triplets.

Note: this problem was listed under the '2D Matrices' additional problems set on Scaler, though it operates on a 1D array rather than a matrix.

## Example
Input 1:
```
A = [1, 2, 4, 3]
```
Output 1: 2 (triplets: [1,2,3] and [1,2,4])

Input 2:
```
A = [2, 1, 2, 3]
```
Output 2: 1 (triplet: [1,2,3])

## Approach
For every index `i` treated as the middle element of a triplet, count how many elements to its left are strictly smaller (leftSmaller) and how many elements to its right are strictly larger (rightLarger). Every combination of one such left element and one such right element forms a valid increasing triplet with `A[i]` as the middle value, so the contribution of index `i` is `leftSmaller * rightLarger`.

## Algorithm
1. Initialize `count` to 0.
2. For every middle index `i` from 1 to n-2:
   1. Count `leftSmaller`: number of indices `j < i` with `A[j] < A[i]`.
   2. Count `rightLarger`: number of indices `k > i` with `A[k] > A[i]`.
   3. Add `leftSmaller * rightLarger` to `count`.
3. Return `count`.

## Dry Run
Input: A = [1, 2, 4, 3]

- i=1 (A[i]=2): leftSmaller (from [1]) = 1, rightLarger (from [4,3]) = 2 -> contributes 1*2 = 2
- i=2 (A[i]=4): leftSmaller (from [1,2]) = 2, rightLarger (from [3]) = 0 -> contributes 2*0 = 0

Final Answer: 2

## Why Does This Work?
Every valid increasing triplet has exactly one middle element, so summing over every possible middle index and counting compatible left/right partners (using the multiplication principle) enumerates every triplet exactly once without duplication.

## Complexity Analysis
### Time Complexity
O(N^2) - for each of the N middle indices, both the left and right scans take O(N) in the worst case.

### Space Complexity
O(1) - only a few counter variables are used beyond the input array.

## Solution (Java 8)
```java
public class Solution {
    public int solve(int[] A) {
        int count = 0;
        int n = A.length;
        for (int i = 1; i < n - 1; i++) {
            int leftSmaller = 0;
            int rightLarger = 0;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    leftSmaller++;
                }
            }
            for (int k = i + 1; k < n; k++) {
                if (A[k] > A[i]) {
                    rightLarger++;
                }
            }
            count += leftSmaller * rightLarger;
        }
        return count;
    }
}
```

## Key Learning
- Fixing the middle element of a triplet and counting compatible left/right partners is a common pattern for triplet-counting problems.
- The multiplication principle (leftSmaller * rightLarger) avoids the need to enumerate each triplet individually.
- This O(N^2) approach can be further optimized to O(N log N) using Binary Indexed Trees (Fenwick Trees) or merge-sort-based counting, since counting "smaller to the left" and "larger to the right" are both classic BIT/order-statistics problems.
