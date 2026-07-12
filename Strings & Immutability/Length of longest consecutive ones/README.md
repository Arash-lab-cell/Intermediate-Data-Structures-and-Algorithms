# Length of longest consecutive ones

## Problem Statement
Given a binary string `A`, you are allowed to do at most one swap between any `0` and any `1` in the string. Find and return the length of the longest run of consecutive `1`s that can be achieved after this optional single swap.

**Constraints:**
`1 <= length of string <= 1000000`
`A` contains only characters `0` and `1`.

**Input Format:** A single string `A`.

**Output Format:** An integer — the length of the longest achievable run of consecutive 1s.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `A = "111000"` | `3` | All three 1s are already adjacent (`"111"`); swapping a 0 for a 1 elsewhere gains nothing since there's no spare 1 outside that block |
| `A = "111011101"` | `7` | Swapping the lone `0` at index 3 (between the two runs of 1s) with a `1` from further away extends the run to length 7 |

## Approach
Think of every `0` in the string as a candidate "gap" to fill. For each `0` at position `i`, measure how many consecutive `1`s sit immediately to its left (`l`) and immediately to its right (`r`). If there exists at least one `1` somewhere else in the string outside this `l + r` window (i.e. total ones > `l + r`), that spare `1` can be swapped into position `i`, joining the two runs into one of length `l + r + 1`. If every `1` in the string is already inside this window (`l + r == total ones`), there's no spare `1` to swap in — the best you can do at that gap is the unmerged `l + r`.

## Algorithm
1. Count `countOf1`, the total number of `1`s in `A`.
2. If `countOf1 == n` (all ones), return `n` — already fully consecutive.
3. If `countOf1 == 0` (no ones), return `0`.
4. For every index `i` where `A.charAt(i) == '0'`:
   - Walk left from `i-1` counting consecutive `1`s into `l`.
   - Walk right from `i+1` counting consecutive `1`s into `r`.
   - If `l + r < countOf1`, a spare `1` exists elsewhere → candidate answer is `l + r + 1`.
   - Otherwise → candidate answer is `l + r`.
   - Track the maximum candidate seen as `ans`.
5. Return `ans`.

## Dry Run
`A = "111000"` (n=6, countOf1=3)
- i=3 ('0'): left run l=3 (positions 2,1,0 are all '1'), right run r=0 (position 4 is '0'). l+r=3 equals countOf1(3) → no spare 1 → candidate = 3.
- i=4 ('0'): l=0, r=0 → l+r=0 < 3 → candidate = 0+0+1 = 1.
- i=5 ('0'): l=0, r=0 → candidate = 1.
- Max candidate = 3 → return 3.

## Why Does This Work?
Every possible single swap that could help must "fill" exactly one `0` using a `1` taken from elsewhere in the string — so checking each `0` position as the fill target and measuring its adjacent run lengths covers every useful swap. The `l + r < countOf1` check correctly detects whether a genuinely spare `1` exists outside the window; without that check, the algorithm could incorrectly credit a swap that just moves a `1` out of the same window it came from, which yields no real gain.

## Complexity Analysis
**Time Complexity:** O(N) — although there's a nested walk for each `0`, each character is only ever re-visited by its immediate neighboring zero checks, and in the worst case (alternating pattern) this is still bounded by O(N) amortized since runs don't overlap arbitrarily; practically this behaves like a linear scan for the typical run-based structure of the input.
**Space Complexity:** O(N) — a `StringBuilder` copy of the input string is used for character access.

## Solution
```java
public class Solution {
    public int solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        int n = sb.length();
        int countOf1 = 0;
        for(int i = 0; i < n; i++){
            if(sb.charAt(i) == '1'){
                countOf1++;
            }
        }
        if(countOf1 == n){
            return n;
        }else if(countOf1 == 0){
            return 0;
        }
        int ans = 0;
        for(int i = 0; i < n; i++)
        if(sb.charAt(i) == '0'){
            int j = i-1, l = 0;
            while(j >= 0 && sb.charAt(j) == '1'){
                j--;
                l++;
            }
            int k = i+1, r = 0;
               while(k < n && sb.charAt(k) == '1'){
                   k++;
                   r++;
            }
            if(l+r < countOf1){
                ans = Math.max(ans, l+r+1);
            }else {
                ans = Math.max(ans, l+r);
            }
        }
        return ans;
    }
}
```

## Key Learning
This is a variant of the classic "flip at most one 0" sliding-window problem, but the "swap" framing (rather than "flip") adds a subtlety: you can only gain a `+1` if a spare `1` exists outside the local window you're trying to merge. Always check whether the resource you're "borrowing" (a spare 1, in this case) actually exists elsewhere before crediting the operation — otherwise the algorithm silently double counts.
