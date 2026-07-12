# Count Occurrences

## Problem Statement
Given a string `A` of lowercase English letters, count the number of times the substring `"bob"` appears in `A`. Overlapping matches are counted separately — for example, `"bobob"` contains two occurrences of `"bob"`: at positions 0 and 2.

**Constraints:** `1 <= |A| <= 1000`

**Input Format:** A single string `A`.

**Output Format:** A single integer — the number of times `"bob"` occurs in `A`.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `A = "abobc"` | `1` | `"bob"` appears once, at index 1 |
| `A = "bobob"` | `2` | `"bob"` appears at index 0 and again at index 2 (overlapping) |

## Approach
Since we only care about a fixed 3-character pattern, a single linear scan is enough — no need for a general string-matching algorithm. Walk through the string and, at every index `i` from `2` to `n-1`, check whether the three characters ending at `i` (`A[i-2] A[i-1] A[i]`) spell `"bob"`. Because overlapping matches count separately, we check every window independently rather than skipping ahead after a match.

## Algorithm
1. Read string `A` and let `n = A.length()`.
2. Initialize `ans = 0`.
3. Loop `i` from `2` to `n - 1`:
   - If `A.charAt(i) == 'b'` and `A.charAt(i-1) == 'o'` and `A.charAt(i-2) == 'b'`, increment `ans`.
4. Return `ans`.

## Dry Run
`A = "bobob"` (n = 5)
- i=2: A[0..2] = "bob" → match, ans=1
- i=3: A[1..3] = "obo" → no match
- i=4: A[2..4] = "bob" → match, ans=2
- Loop ends → return 2

## Why Does This Work?
Every possible 3-character window `[i-2, i-1, i]` in the string is checked exactly once, and because we don't skip the index after finding a match, overlapping occurrences (like the two "bob"s in "bobob") are both counted. This guarantees full, exact coverage of every valid window.

## Complexity Analysis
**Time Complexity:** O(N) — a single pass over the string, checking a constant amount of work per index.
**Space Complexity:** O(1) — only a counter and loop index are used.

## Solution
```java
public class Solution {
    public int solve(String A) {
        int n = A.length();
        int ans = 0;
        for(int i = 2; i < n; i++){
            if(A.charAt(i) == 'b' &&
               A.charAt(i-1) == 'o' &&
               A.charAt(i-2) == 'b'){
                    ans++;
            }
        }
        return ans;
    }
}
```

## Key Learning
Fixed-pattern substring counting doesn't need a full string-matching algorithm (like KMP) — a direct index-window check is simpler and still O(N) when the pattern length is constant. The key subtlety is overlapping matches: don't advance the index past a match, or you'll undercount cases like `"bobob"`.
