# Reverse the String

## Problem Statement
Given a string `A` of size N, return the string after reversing it word by word. A sequence of non-space characters constitutes a word. The reversed string should not contain leading or trailing spaces, even if they are present in the input, and if there are multiple spaces between words, they should be reduced to a single space in the output.

**Constraints:** `1 <= N <= 3 * 10^5`

**Input Format:** A single string `A`.

**Output Format:** The string `A` with word order reversed.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `A = "the sky is blue"` | `"blue is sky the"` | Words are reversed end-to-start: blue, is, sky, the |
| `A = "this is ib"` | `"ib is this"` | Words reversed: ib, is, this |

## Approach
Split the string into words on whitespace (using `trim()` first to discard leading/trailing spaces), then rebuild the string by walking the word array from both ends inward, swapping pairs (a two-pointer reversal of the array), and finally joining the words back together with single spaces.

## Algorithm
1. Trim `A` and split on spaces into an array `arr` of words.
2. Set `l = 0`, `r = arr.length - 1`.
3. While `l < r`: swap `arr[l]` and `arr[r]`, then increment `l` and decrement `r`.
4. Build the result by appending each word in `arr` to a `StringBuilder`, separated by single spaces (no trailing space after the last word).
5. Return the built string.

## Dry Run
`A = "the sky is blue"`
- Split → `["the", "sky", "is", "blue"]`
- l=0, r=3: swap → `["blue", "sky", "is", "the"]`; l=1, r=2
- l=1, r=2: swap → `["blue", "is", "sky", "the"]`; l=2, r=1 → loop ends
- Join with spaces → `"blue is sky the"`

## Why Does This Work?
Splitting on whitespace naturally discards duplicate spaces and leading/trailing spaces since Java's `split(" ")` on a trimmed string produces exactly the words with no empty tokens between them. The two-pointer array reversal swaps the word order in place in O(word count) swaps, and rejoining guarantees single-space separation regardless of the original spacing.

## Complexity Analysis
**Time Complexity:** O(N) — splitting, reversing, and rejoining are all linear in the length of the string.
**Space Complexity:** O(N) — the word array and the `StringBuilder` output both hold a copy of the string's content.

## Solution
```java
public class Solution {
    public String solve(String A) {
        String[] arr = A.trim().split(" ");
        int l = 0;
        int r = arr.length-1;
        while(l < r){
            String temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++){
            if(i != arr.length-1){
                sb.append(arr[i] + " ");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }
}
```

## Key Learning
Word-level reversal is a different problem from character-level reversal — splitting on whitespace, reversing the resulting array with two pointers, then rejoining is the standard pattern, and it conveniently normalizes irregular spacing (multiple spaces, leading/trailing spaces) as a side effect of the split/join round trip.
