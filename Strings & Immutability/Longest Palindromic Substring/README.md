# Longest Palindromic Substring

## Problem Statement

- Given a string `A` of size `N`, find and return the longest palindromic substring in `A`.
- A substring of `A` is `A[i...j]` where `0 <= i <= j < len(A)`.
- A palindrome is a string that reads the same backwards: `A` is a palindrome if `reverse(A) = A`.
- In case of conflict (multiple substrings of the same max length), return the one that occurs first (least starting index).
- Constraints: `1 <= N <= 6000`.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `"aaaabaaa"` | `"aaabaaa"` | Longest palindrome has length 7 |
| `"abba"` | `"abba"` | The whole string is a palindrome of length 4 |

## Approach

Use the **expand around center** technique. Every palindrome has a center — either a single character (odd length) or a gap between two characters (even length). For each possible center, expand outward while the characters on both sides match, and track the longest expansion seen so far.

## Algorithm

1. Write a helper `expand(A, l, r)` that grows `l` left and `r` right while `A.charAt(l) == A.charAt(r)`, then returns the palindrome length `r - l - 1`.
2. Loop over every index `i` from `1` to `n-1` treating `i` as an **odd-length center** (`l = i-1`, `r = i+1`), call `expand`, and update the best `(start, end)` window if this length is longer.
3. Loop over every index `i` from `0` to `n-1` treating the gap between `i` and `i+1` as an **even-length center** (`l = i`, `r = i+1`), call `expand`, and update the best window the same way.
4. Return `A.substring(s, e)` for the best window found.

## Dry Run

Input: `A = "abba"`, `n = 4`

Odd-center pass finds nothing longer than length 1 (no character has matching neighbors on both sides).

Even-center pass:
| i | l,r before expand | Match? | l,r after expand | len | Best so far |
|---|---|---|---|---|---|
| 0 | 0,1 | 'a' vs 'b' → no | 0,1 | 0 | ans=1 (unchanged) |
| 1 | 1,2 | 'b' vs 'b' → yes, expand to 0,3 → 'a' vs 'a' → yes, expand to -1,4 → stop | -1,4 | 4 | ans=4, s=0, e=4 |
| 2 | 2,3 | 'b' vs 'a' → no | 2,3 | 0 | ans=4 (unchanged) |
| 3 | 3,4 (out of bounds) | — | 3,4 | 0 | ans=4 (unchanged) |

Result: `A.substring(0, 4) = "abba"` ✅

## Why Does This Work?

Every palindrome, no matter its length, has exactly one center (a character or a gap between two characters). By expanding outward from every possible center and keeping the longest valid expansion, every candidate palindrome in the string is considered exactly once — so the true longest palindrome is guaranteed to be found.

## Complexity Analysis

- **Time Complexity:** `O(N^2)` — there are `O(N)` centers, and each expansion can take up to `O(N)` steps in the worst case (e.g. a string of all the same character).
- **Space Complexity:** `O(1)` — only a few pointers and counters are used besides the output substring.

## Solution

```java
public class Solution {
    public int expand(String A,int l,int r){
         while(l >= 0 && r < A.length() && A.charAt(l) == A.charAt(r)){
                l--;
                r++;
            }
        return r-l-1;
    }
    public String longestPalindrome(String A) {
        int n = A.length();
        int s = 0;
        int e = 1;
        int ans = 1;
        for(int i = 1; i < n; i++){
            int l = i-1;
            int r = i+1;
            int len = expand(A, l, r);
            if(len > ans){
                s = i - (len-1)/2;
                e = s+len;
                ans = len;
            }
        }
        for(int i = 0; i < n; i++){
            int l = i;
            int r = i+1;
            int len = expand(A, l, r);
            if(len > ans){
                s = i + 1 - (len)/2;
                e = s+len;
                ans = len;
            }
        }
        return A.substring(s, e);
    }
}
```

## Key Learning

- Any palindrome expands symmetrically from a single center point — odd-length palindromes center on a character, even-length ones center on a gap between two characters.
- Checking both center types (2N-1 total centers) guarantees no palindrome is missed.
- This brute-force-expansion approach is `O(N^2)`; it can be optimized further to `O(N)` using **Manacher's Algorithm**, which avoids re-expanding overlapping palindromes.
- Not suitable as-is for very large `N` (beyond low millions) where the `O(N)` Manacher's approach would be required instead.
