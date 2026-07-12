# Check Anagrams

## Problem Statement
Given two lowercase strings `A` and `B`, each of length `N`, return `1` if they are anagrams of each other, and `0` if not. Two strings `A` and `B` are anagrams if `A` can be formed by rearranging the letters of `B`.

**Constraints:**
`1 <= N <= 10^5`
`A` and `B` are lowercase strings.

**Input Format:** Two strings `A` and `B`.

**Output Format:** `1` if they are anagrams, `0` if not.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `A = "cat"`, `B = "bat"` | `0` | The letters can't be rearranged to form the same word |
| `A = "secure"`, `B = "rescue"` | `1` | Both strings contain exactly the same letters, just in a different order |

## Approach
Two strings are anagrams exactly when they contain the same multiset of characters. The most direct way to check this is to sort the characters of both strings and compare the sorted results — if two strings are permutations of each other, sorting their characters produces identical sequences. As a cheap early exit, first check the lengths: strings of different length can never be anagrams.

## Algorithm
1. If `A.length() != B.length()`, return `0` immediately.
2. Convert `A` to a character array and sort it.
3. Convert `B` to a character array and sort it.
4. Compare the two sorted arrays position by position; if any character differs, return `0`.
5. If every position matches, return `1`.

## Dry Run
`A = "secure"`, `B = "rescue"`
- Lengths equal (6 == 6) → continue
- Sorted `A` → ['c','e','e','r','s','u']
- Sorted `B` → ['c','e','e','r','s','u']
- Compare position by position — all match
- Return 1

## Why Does This Work?
Sorting is a canonical form for the multiset of characters in a string — any two strings with identical character counts sort to exactly the same sequence, regardless of their original order. So comparing the sorted forms is equivalent to comparing character frequencies directly, just expressed more simply.

## Complexity Analysis
**Time Complexity:** O(N log N) — dominated by sorting both character arrays.
**Space Complexity:** O(N) — two character arrays are created to hold the sorted copies.

## Solution
```java
import java.util.Arrays;

public class Solution {
    public int solve(String A, String B) {
        int n = A.length();
        int m = B.length();
        if(n != m){
            return 0;
        }
        char[] chA = A.toCharArray();
        Arrays.sort(chA);
        char[] chB = B.toCharArray();
        Arrays.sort(chB);
        for(int i = 0; i < n; i++){
            if(chA[i] != chB[i]){
                return 0;
            }
        }
        return 1;
    }
}
```

## Key Learning
Sorting-based comparison is a simple, reliable way to check for anagrams, though it's not the fastest possible approach — an O(N) alternative exists using a fixed-size frequency array (26 counters for lowercase letters), incrementing for one string and decrementing for the other, then checking all counts return to zero. The sort-based approach trades a bit of speed for simplicity and is well worth it when N is small to moderate.
