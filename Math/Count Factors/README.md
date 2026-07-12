# Count Factors

## Problem Statement

Given an integer **A**, return the total number of factors (divisors) of `A`.

### Example

**Input**
```text
A = 12
```

**Output**
```text
6
```

**Explanation**

The factors of `12` are:

```text
1, 2, 3, 4, 6, 12
```

Therefore, the answer is **6**.

---

## Approach

A straightforward approach is to check every number from `1` to `A` and count how many divide `A`. However, this takes **O(A)** time.

A more efficient approach is to iterate only up to **√A**.

The idea is that factors always occur in pairs.

For example, for `A = 36`:

```text
1 × 36
2 × 18
3 × 12
4 × 9
6 × 6
```

If `i` is a factor of `A`, then `A / i` is also a factor.

- If `i` and `A / i` are different, count both factors.
- If they are the same (perfect square), count only one factor.

This reduces the time complexity from **O(A)** to **O(√A)**.

---

## Algorithm

1. Initialize a variable `fact_count` to `0`.
2. Iterate from `i = 1` while `i * i <= A`.
3. If `A % i == 0`:
   - If `i == A / i`, increment `fact_count` by `1`.
   - Otherwise, increment `fact_count` by `2`.
4. Return `fact_count`.

---

## Dry Run

### Input

```text
A = 12
```

| i | `A % i == 0` | Factors Found | Count |
|---|--------------|---------------|------:|
| 1 | Yes | (1, 12) | 2 |
| 2 | Yes | (2, 6) | 4 |
| 3 | Yes | (3, 4) | 6 |

Final Answer:

```text
6
```

---

## Complexity Analysis

**Time Complexity:** `O(√A)`

Only numbers up to the square root of `A` are checked.

**Space Complexity:** `O(1)`

No extra space is used except a few variables.

---

## Java Solution

```java
public class Solution {
    public int solve(int A) {
        int fact_count = 0;

        for (int i = 1; i * i <= A; i++) {
            if (A % i == 0) {
                if (i == A / i) {
                    fact_count++;
                } else {
                    fact_count += 2;
                }
            }
        }

        return fact_count;
    }
}
```

---

## Key Learning

- Factors always occur in pairs.
- It is sufficient to iterate only up to **√A**.
- Perfect squares need special handling because the square root should be counted only once.
- This optimization improves the time complexity from **O(A)** to **O(√A)**.
