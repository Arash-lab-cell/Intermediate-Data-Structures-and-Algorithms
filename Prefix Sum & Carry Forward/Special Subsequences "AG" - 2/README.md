# Special Subsequences "AG"

## Problem Statement

Given a string `A` consisting of uppercase English letters, count the number of subsequences equal to **"AG"**.

A subsequence is formed by deleting zero or more characters from the original string without changing the order of the remaining characters.

Return the total number of `"AG"` subsequences.

---

## Example

### Input

```text
A = "ABCGAG"
```

### Output

```text
3
```

### Explanation

The subsequences `"AG"` are formed by:

- `A(0) → G(3)`
- `A(0) → G(5)`
- `A(4) → G(5)`

Hence, the answer is:

```text
3
```

---

## Another Example

### Input

```text
A = "GAGA"
```

### Output

```text
1
```

### Explanation

Only one valid `"AG"` subsequence exists:

```text
A(1) → G(2)
```

---

## Approach

Instead of checking every possible pair of characters, traverse the string **from right to left**.

Maintain:

- `g` → Number of `'G'` characters encountered so far.
- `ag` → Total number of `"AG"` subsequences.

While traversing:

- If the current character is `'G'`, increment `g`.
- If the current character is `'A'`, every `'G'` already seen to its right can pair with this `'A'` to form an `"AG"` subsequence.

Therefore:

```text
ag += g
```

This allows us to count all valid subsequences in a single traversal.

---

## Algorithm

1. Initialize:
   - `g = 0`
   - `ag = 0`
2. Traverse the string from right to left.
3. If the current character is `'G'`, increment `g`.
4. If the current character is `'A'`, add `g` to `ag`.
5. Return `ag`.

---

## Dry Run

### Input

```text
A = "ABCGAG"
```

Traverse from right to left.

| Character | G Count | AG Count |
|-----------|--------:|---------:|
| G | 1 | 0 |
| A | 1 | 1 |
| G | 2 | 1 |
| C | 2 | 1 |
| B | 2 | 1 |
| A | 2 | 3 |

Final Answer:

```text
3
```

---

## Another Dry Run

### Input

```text
A = "AGAG"
```

| Character | G Count | AG Count |
|-----------|--------:|---------:|
| G | 1 | 0 |
| A | 1 | 1 |
| G | 2 | 1 |
| A | 2 | 3 |

Final Answer:

```text
3
```

The valid subsequences are:

- A(0) → G(1)
- A(0) → G(3)
- A(2) → G(3)

---

## Why Does This Work?

Every `'A'` can form a valid `"AG"` subsequence with **every `'G'` appearing after it**.

By traversing from right to left:

- We always know how many `'G'` characters lie to the right of the current position.
- Whenever an `'A'` is encountered, it contributes exactly `g` new subsequences.

This eliminates the need for nested loops.

---

## Complexity Analysis

### Time Complexity

```text
O(N)
```

The string is traversed only once.

---

### Space Complexity

```text
O(1)
```

Only two variables are maintained regardless of the string length.

---

## Java Solution

```java
public class Solution {
    public long solve(String A) {

        long g = 0;
        long ag = 0;

        for (int i = A.length() - 1; i >= 0; i--) {

            if (A.charAt(i) == 'G') {
                g++;
            } else if (A.charAt(i) == 'A') {
                ag += g;
            }
        }

        return ag;
    }
}
```

---

## Key Learning

- A subsequence does not require consecutive characters; only the relative order matters.
- Traversing from **right to left** makes it easy to know how many `'G'` characters are available after each `'A'`.
- Every `'A'` contributes as many `"AG"` subsequences as the number of `'G'` characters to its right.
- This approach avoids nested loops and reduces the time complexity from **O(N²)** to **O(N)** while using **O(1)** extra space.
