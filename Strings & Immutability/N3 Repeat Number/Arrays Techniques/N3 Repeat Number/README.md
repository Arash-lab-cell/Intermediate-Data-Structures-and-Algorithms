# N3 Repeat Number

## Problem Statement
You're given a read-only array `A` of `N` integers. Find out if any integer occurs more than `N/3` times in the array, in linear time and constant additional space. If so, return that integer. If not, return `-1`. If there are multiple valid answers, return any one. The array must not be modified while solving the problem.

**Constraints:**
`1 <= N <= 7 * 10^5`
`1 <= A[i] <= 10^9`

**Input Format:** A single integer array `A`.

**Output Format:** An integer — an element occurring more than `N/3` times, or `-1` if none exists.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `A = [1, 2, 3, 1, 1]` | `1` | `1` appears 3 times, n=5, n/3=1, and 3 > 1 |
| `A = [1, 2, 3]` | `-1` | Every element appears once; n/3=1, and 1 is not > 1 |

## Approach
At most two elements can occur more than `N/3` times in any array (since three such elements would require more than `N` total items). This is the extended Boyer–Moore Voting algorithm: track up to two "candidate" majority elements and their running counts in a single pass, cancelling out counts whenever an element matches neither candidate. Because the algorithm only guarantees the true answer is *among* the final candidates (not that the candidates are guaranteed correct), a second pass verifies each candidate's actual frequency against the `N/3` threshold before returning.

## Algorithm
1. If `N == 1`, return `A[0]` immediately (a single element trivially exceeds N/3 = 0).
2. Initialize two candidate slots `(num1, count1)` and `(num2, count2)`, both starting at count 0.
3. Walk through the array once. For each element:
   - If it matches `num1`, increment `count1`.
   - Else if it matches `num2`, increment `count2`.
   - Else if `count1 == 0`, adopt this element as `num1` with `count1 = 1`.
   - Else if `count2 == 0`, adopt this element as `num2` with `count2 = 1`.
   - Else (it matches neither candidate and both slots are occupied), decrement both `count1` and `count2`.
4. After the pass, do a second full scan to count the *actual* frequency of `num1` and `num2` in the array.
5. Return `num1` if its true frequency exceeds `N/3`; else return `num2` if its true frequency exceeds `N/3`; else return `-1`.

## Dry Run
`A = [1, 2, 3, 1, 1]` (n=5)
- i=0 (val=1): count1=0 → num1=1, count1=1
- i=1 (val=2): doesn't match num1(1); count2=0 → num2=2, count2=1
- i=2 (val=3): matches neither num1(1) nor num2(2); both counts nonzero → count1=0, count2=0
- i=3 (val=1): count1=0 → num1=1, count1=1
- i=4 (val=1): matches num1 → count1=2
- Verification pass: freq of 1 = 3, freq of 2 = 1
- `3 > 5/3(=1)` → return 1

## Why Does This Work?
Since at most two elements can exceed the N/3 threshold simultaneously, tracking two candidates with cancellation (any element that isn't a current candidate "cancels" one vote from each existing candidate) guarantees that if a true majority element exists, it survives as a candidate — a true >N/3 element can never be fully cancelled out because there aren't enough "other" elements to outnumber it three-to-one across the whole array. The verification pass is required because candidates can survive the voting phase without actually meeting the threshold (e.g. `[1,2,3]` leaves stale candidates that don't really occur > N/3 times).

## Complexity Analysis
**Time Complexity:** O(N) — one pass for voting, one pass for verification, both linear and sequential (not nested).
**Space Complexity:** O(1) — only a fixed number of counters and candidate variables are used, independent of input size.

## Solution
```java
public class Solution {
    public int repeatedNumber(int[] A) {
       int n = A.length;
       if(n == 1){
           return A[0];
       }
       int num1 = 0;
       int count1 = 0;
       int num2 = 0;
       int count2 = 0;
       int i = 0;
       while(i < n){
           if(A[i] == num1){
               count1++;
           }else if(A[i] == num2){
               count2++;
           }else if(count1 == 0){
               num1 = A[i];
               count1 = 1;
           }else if(count2 == 0){
               num2 = A[i];
               count2 = 1;
           }else if(A[i] != num1 && A[i] != num2){
               count1--;
               count2--;
           }
           i++;
       }
       int freqNum1 = 0;
       int freqNum2 = 0;
       for(int j = 0; j < n; j++){
           if(A[j] == num1){
               freqNum1++;
           }else if(A[j] == num2){
               freqNum2++;
           }
       }
       if(freqNum1 > n/3){
           return num1;
       }else if(freqNum2 > n/3){
           return num2;
       }else{
           return -1;
       }
    }
}
```

## Key Learning
The Boyer–Moore Voting algorithm generalizes cleanly from "majority element" (>N/2, one candidate) to ">N/3" (two candidates) — the pattern is: bound how many elements could possibly satisfy the threshold, track that many candidates with mutual cancellation, then always verify with a second pass since the voting phase only narrows down *candidates*, it doesn't prove correctness on its own.
