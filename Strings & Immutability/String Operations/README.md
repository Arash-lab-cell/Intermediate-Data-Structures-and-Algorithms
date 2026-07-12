# String Operations

## Problem Statement

- Given a string `A` of size `N` (lowercase and uppercase alphabets), apply the following operations **in order**:
  1. Concatenate the string with itself.
  2. Delete all the uppercase letters.
  3. Replace each vowel (`a`, `e`, `i`, `o`, `u`) with `'#'`.
- Return the resultant string.
- Constraints: `1 <= N <= 100000`.

## Example(s)

| Input | Output | Why |
|---|---|---|
| `"aeiOUz"` | `"###z###z"` | Concatenate → `"aeiOUzaeiOUz"` → drop uppercase → `"aeizaeiz"` → replace vowels → `"###z###z"` |
| `"AbcaZeoB"` | `"bc###bc###"` | Concatenate → drop uppercase → `"bcaeobcaeo"` → replace vowels → `"bc###bc###"` |

## Approach

Apply the three operations exactly as described, one after another, using a mutable `StringBuilder` so the string can be edited in place rather than creating a new string at every step.

## Algorithm

1. Build a `StringBuilder` from `A` and append itself to it (concatenation).
2. Scan left to right; whenever an uppercase character is found, delete it at that index and step the index back by one (since everything shifts left after a deletion).
3. Scan left to right again; whenever a vowel (`a`,`e`,`i`,`o`,`u`) is found, overwrite it with `'#'`.
4. Return the resulting string.

## Dry Run

Input: `A = "aeiOUz"`

1. Concatenate: `"aeiOUzaeiOUz"` (length 12)
2. Delete uppercase (`O`, `U` appear twice each): `"aeizaeiz"` (length 8)
3. Replace vowels `a,e,i` with `#`: `"###z###z"`

Matches the expected output ✅

## Why Does This Work?

Each operation is applied to the full, already-transformed string before moving to the next — concatenation first (so uppercase/vowel rules apply to the doubled string), then uppercase removal, then vowel substitution. Doing the deletion with an in-place index decrement correctly accounts for the string shrinking as characters are removed, so no character is skipped.

## Complexity Analysis

- **Time Complexity:** `O(N)` — concatenation, the uppercase scan, and the vowel scan are each linear in the (doubled) string length.
- **Space Complexity:** `O(N)` — for the `StringBuilder` holding the doubled string.

## Solution

```java
public class Solution {
    public String solve(String A) {
        StringBuilder sb = new StringBuilder(A);
        sb.append(sb);
        int n = sb.length();
        for(int i = 0; i < n; i++){
            char ch = sb.charAt(i);
            if(ch >= 'A' && ch <= 'Z'){
                sb.deleteCharAt(i);
                i--;
                n = n-1; //because the length also decreases after deleting the char.
            }
        }
        for(int i = 0; i < n; i++){
            char ch = sb.charAt(i);
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                sb.setCharAt(i, '#');
            }
        }
        return sb.toString();
    }
}
```

## Key Learning

- `StringBuilder` allows in-place mutation (`deleteCharAt`, `setCharAt`), which is far more efficient than rebuilding a new `String` at every step — Java `String`s are immutable, so repeated concatenation would be `O(N^2)`.
- When deleting elements while iterating forward, decrementing the loop index after a deletion (and shrinking the tracked length) prevents skipping the character that shifted into the current position.
- This approach is well-suited for `N` up to `100000`; for much larger inputs, an approach that filters into a new buffer in a single pass (rather than deleting from the middle of a StringBuilder) would avoid the `O(N)` cost of `deleteCharAt` shifting elements.
