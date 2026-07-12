# Amazing Subarrays

## Problem Statement

- Given a string `S`, find all the "amazing" substrings of `S`.
- An amazing substring is one that **starts with a vowel** (`a, e, i, o, u, A, E, I, O, U`).
- Return a single integer `X mod 10003`, where `X` is the total number of amazing substrings.
- Constraints: `1 <= length(S) <= 10^6`; `S` can contain special characters.

## Example(s)

Input: `"ABEC"` → Output: `6`

Amazing substrings: `A`, `AB`, `ABE`, `ABEC`, `E`, `EC` — 6 total, `6 % 10003 = 6`.

## Approach

Rather than generating every substring explicitly (which would be `O(N^2)` substrings), count them mathematically: **every substring that starts at a vowel position `i` and ends anywhere from `i` to the end of the string is amazing.** So for each vowel found at index `i`, there are exactly `(n - i)` amazing substrings starting there (one for each possible ending point).

## Algorithm

1. Iterate over every index `i` in the string.
2. If `S.charAt(i)` is a vowel (checked against both lowercase and uppercase), add `(n - i)` to the running count — this counts every substring starting at `i`.
3. Keep the running count modulo `10003` at every step to avoid overflow.
4. Return the final count.

## Dry Run

Input: `S = "ABEC"`, `n = 4`

| i | char | Vowel? | Substrings starting here | n - i | Running count |
|---|---|---|---|---|---|
| 0 | A | yes | A, AB, ABE, ABEC | 4 | 4 |
| 1 | B | no | — | — | 4 |
| 2 | E | yes | E, EC | 2 | 6 |
| 3 | C | no | — | — | 6 |

Result: `6 % 10003 = 6` ✅

## Why Does This Work?

Every substring is uniquely identified by its start and end index. A substring is amazing exactly when its **start** index lands on a vowel — the end index can be anything from the start to the last character. So counting `(n - i)` possible end points for every vowel position `i`, and summing across all vowel positions, counts every amazing substring exactly once without ever constructing the substrings themselves.

## Complexity Analysis

- **Time Complexity:** `O(N)` — a single pass over the string.
- **Space Complexity:** `O(1)` — only a running counter is used.

## Solution

```java
public class Solution {
public int solve(String A) {
int n = A.length();
int count = 0;
for(int i = 0; i < n; i++){
char ch = A.charAt(i);
if(ch == 'a' ||
ch == 'e' ||
ch == 'i' ||
ch == 'o' ||
ch == 'u' ||
ch == 'A' ||
ch == 'E' ||
ch == 'I' ||
ch == 'O' ||
ch == 'U'){
int m = n-i; //total substring from A in ABEC is 4 i.e. no. of characters from A till C including A and C
count = (count + m) % 10003;
}
}
return count;
}
}
```

## Key Learning

- Counting problems over substrings/subarrays often have a closed-form count per starting (or ending) position — avoiding brute-force enumeration turns an `O(N^2)` or `O(N^3)` problem into `O(N)`.
- Taking the modulo at every addition (not just at the end) is essential here, since the true count `X` can be astronomically large for big `N` and would overflow a standard `int`/`long` if summed directly.
- This pattern — "count substrings/subarrays satisfying property P at position i by counting how many valid endpoints exist" — generalizes to many similar substring-counting problems.
