# Longest Common Prefix

## Problem Statement

- Given an array of strings `A`, find the longest string `S` which is a prefix of **all** strings in the array.
- The longest common prefix of two strings `S1` and `S2` is the longest string that is a prefix of both (e.g. the longest common prefix of `"abcdefgh"` and `"abcefgh"` is `"abc"`).
- Constraints: `0 <= sum of length of all strings <= 1000000`.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `["abcdefgh", "aefghijk", "abcefgh"]` | `"a"` | All three strings only share the first letter `'a'` |
| `["abab", "ab", "abcd"]` | `"ab"` | All three strings share the prefix `"ab"` |

## Approach

The common prefix of the whole array can never be longer than the **shortest string** in the array (a prefix can't exceed the length of any string it must be a prefix of). So find the shortest string first, then check how many of its leading characters match the same position in every other string.

## Algorithm

1. Scan the array once to find the shortest string — call it `shortestString`.
2. For every string in the array, walk character by character alongside `shortestString`, counting how many leading characters match.
3. As soon as a mismatch is found for any string, that position becomes the new upper bound (`k`) on the common prefix length, and the current best answer is returned immediately.
4. If every string matches all the way through `shortestString`, return `shortestString` itself (bounded by the smallest `k` seen).

## Dry Run

Input: `A = ["abab", "ab", "abcd"]`

1. Shortest string: `"ab"` (length 2).
2. Compare `"abab"` vs `"ab"`: `a==a`, `b==b` → full match, `l=2`.
3. Compare `"ab"` vs `"ab"`: full match, `l=2`.
4. Compare `"abcd"` vs `"ab"`: full match, `l=2`.
5. No mismatches found in any string within the shortest string's length, so the result is `shortestString.substring(0, 2)` = `"ab"` ✅

## Why Does This Work?

Since the answer can be at most as long as the shortest string, using it as the comparison baseline is both necessary and sufficient — any character position beyond the shortest string's length cannot possibly be part of a common prefix. Checking every other string against that baseline, and immediately stopping at the first mismatch found anywhere, guarantees the true longest common prefix is found without extra work.

## Complexity Analysis

- **Time Complexity:** `O(sum of length of all strings)` — the shortest-string scan and the character-by-character comparisons together touch each character at most once.
- **Space Complexity:** `O(1)` extra space (excluding the input and output strings).

## Solution

```java
public class Solution {
    public String longestCommonPrefix(String[] A) {
        int shortestStringLength = Integer.MAX_VALUE;
        String shortestString = new String();
        for(int i = 0; i < A.length; i++){
            String str = A[i];
            if(str.length() < shortestStringLength){
                shortestStringLength = str.length();
                shortestString = str;
            }
        }
        if(A.length == 0){
            return "";
        }
        int k = Integer.MAX_VALUE;
        for(int i = 0; i < A.length; i++){
            String str = A[i];
            int l = 0;
            for(int j = 0; j < shortestString.length(); j++){
                if(shortestString.charAt(j) == str.charAt(j)){
                    l++;
                }else{
                    k = Math.min(k, l);
                    return shortestString.substring(0, k);
                }
            }
            k = Math.min(k, l);
        }
        return shortestString.substring(0, k);
    }
}
```

## Key Learning

- Bounding the search by the shortest string in the array is a simple but powerful pruning trick — it avoids ever comparing beyond a position that could possibly be valid.
- Returning as soon as a mismatch is found (rather than finishing every comparison) saves unnecessary work once the answer is already determined.
- For an empty array, this needs a guard (`A.length == 0`) since there's no meaningful common prefix to compute.
- This approach reads well for small-to-medium arrays; for extremely large arrays of strings, a **trie**-based approach can also solve this and generalizes better to prefix-related queries beyond just "the" longest common prefix.
