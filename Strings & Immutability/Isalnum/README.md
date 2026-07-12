# Isalnum()

## Problem Statement

- Given a character array `A`, return `1` if **all** characters of the array are alphanumeric (`a`-`z`, `A`-`Z`, and `0`-`9`), else return `0`.
- Constraints: `1 <= |A| <= 10^5`.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `['S','c','a','l','e','r','A','c','a','d','e','m','y','2','0','2','0']` | `1` | Every character is a letter or digit |
| `['S','c','a','l','e','r','#','2','0','2','0']` | `0` | `'#'` is not a letter or digit |

## Approach

Scan the array once. As soon as a character is found that is **not** a letter or digit, the whole array fails the check and `0` can be returned immediately. If the scan finishes without finding such a character, every character must be alphanumeric, so return `1`.

## Algorithm

1. Iterate over every character in `A`.
2. For each character, check `Character.isLetterOrDigit(ch)`. If it's `false`, return `0` immediately.
3. If the loop completes without returning, return `1`.

## Dry Run

Input: `A = ['S','c','a','l','e','r','#','2','0','2','0']`

| i | char | isLetterOrDigit? |
|---|---|---|
| 0-5 | S,c,a,l,e,r | true (continue) |
| 6 | # | false → return `0` immediately |

Result: `0` ✅ (matches expected output)

## Why Does This Work?

The property "all characters are alphanumeric" is a universal (AND) condition across the whole array — it is false as soon as a single counter-example exists. Checking each character in order and short-circuiting on the first failure is both correct and efficient, since there's no need to inspect the rest of the array once it's already known to fail.

## Complexity Analysis

- **Time Complexity:** `O(N)` in the worst case (no early exit, e.g. an all-alphanumeric array); much faster on average if a non-alphanumeric character appears early.
- **Space Complexity:** `O(1)` — no extra data structures used.

## Solution

```java
public class Solution {
    public int solve(char[] A) {
        int n = A.length;
        for(int i = 0; i < n; i++){
            if(!Character.isLetterOrDigit(A[i])){
                return 0;
            }
        }
        return 1;
    }
}
```

## Key Learning

- `Character.isLetterOrDigit()` is a clean, built-in way to check alphanumeric status without manually comparing against `'a'-'z'`, `'A'-'Z'`, and `'0'-'9'` ranges — it also correctly handles non-ASCII letters/digits, which a manual range check would miss.
- Returning immediately on the first failing case ("fail fast") avoids unnecessary work and keeps the logic simple to reason about.
- This "scan and short-circuit" pattern is the standard approach for any all-must-satisfy (universal quantifier) check over a collection.
