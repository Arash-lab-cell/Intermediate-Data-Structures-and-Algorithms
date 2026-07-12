# Toggle Case

## Problem Statement

- Given a character string `A` of length `N`, consisting only of lowercase and uppercase Latin letters, toggle the case of every character (`'A'` → `'a'`, `'e'` → `'E'`, etc.).
- Constraints: `1 <= N <= 10^5`, `A[i]` is in `['a'-'z', 'A'-'Z']`.

## Example(s)

| Input | Output |
|---|---|
| `"Hello"` | `"hELLO"` |
| `"tHiSiSaStRiNg"` | `"ThIsIsAsTrInG"` |

## Approach

Every lowercase/uppercase letter pair is exactly 32 apart in ASCII (`'a'` is 97, `'A'` is 65). So toggling case is just adding or subtracting 32 depending on which case the character currently is — no need for library case-conversion calls.

## Algorithm

1. Copy `A` into a `StringBuilder` so characters can be modified in place.
2. For every character: if it's uppercase (`'A'`-`'Z'`), add 32 to shift it to lowercase; otherwise (it must be lowercase), subtract 32 to shift it to uppercase.
3. Return the built string.

## Dry Run

Input: `A = "Hello"`

| i | ch | Is uppercase? | New char |
|---|---|---|---|
| 0 | H | yes | h (72+32=104) |
| 1 | e | no | E (101-32=69) |
| 2 | l | no | L |
| 3 | l | no | L |
| 4 | o | no | O |

Result: `"hELLO"` ✅

## Why Does This Work?

ASCII places the 26 uppercase letters and 26 lowercase letters in two parallel, equally-spaced blocks exactly 32 codes apart. Adding 32 to an uppercase letter's code always lands on its lowercase counterpart, and subtracting 32 from a lowercase letter's code always lands on its uppercase counterpart — this arithmetic relationship holds for every letter, so no lookup table or case-conversion function is needed.

## Complexity Analysis

- **Time Complexity:** `O(N)` — one pass over the string.
- **Space Complexity:** `O(N)` — for the output `StringBuilder` (excluding the returned string itself).

## Solution

```java
public class Solution {
    public String solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        int n = sb.length();
        for(int i = 0; i < n; i++){
            char ch = sb.charAt(i);
            if(ch >= 'A' && ch <= 'Z'){
                sb.setCharAt(i, (char)(ch + 32));
            }else{
                sb.setCharAt(i, (char)(ch - 32));
            }
        }
        return sb.toString();
    }
}
```

## Key Learning

- ASCII arithmetic (`+32`/`-32`) is a fast, allocation-free way to toggle letter case, faster than calling `Character.toUpperCase`/`toLowerCase` for every character.
- This only works because the problem guarantees the input is restricted to `[a-z, A-Z]` — with digits, punctuation, or unicode characters present, this blind arithmetic would corrupt non-letter characters, and a guarded check (or `Character.isUpperCase`) would be required instead.
- A very fast, single-pass, constant-extra-space (aside from output) transformation — a good building block for larger string-processing pipelines.
