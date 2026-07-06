# Subarray with Given Length and Sum

## Problem Statement

You are given:

- An integer array `A` of length `N`.
- An integer `B` representing the required subarray length.
- An integer `C` representing the required subarray sum.

Determine whether there exists a **contiguous subarray** of length `B` whose sum is exactly equal to `C`.

Return:

- `1` if such a subarray exists.
- `0` otherwise.

---

## Example

### Input

```text
A = [4, 3, 2, 6, 1]

B = 3

C = 11
```

### Output

```text
1
```

### Explanation

The subarray:

```text
[3, 2, 6]
```

has:

```text
Sum = 3 + 2 + 6 = 11
```

Since a valid subarray exists, the answer is:

```text
1
```

---

## Another Example

### Input

```text
A = [1, 2, 3, 4]

B = 2

C = 10
```

### Output

```text
0
```

### Explanation

All subarrays of length `2` are:

```text
[1, 2] → 3

[2, 3] → 5

[3, 4] → 7
```

None of them has a sum equal to `10`.

Therefore, the answer is:

```text
0
```

---

## Approach

A brute-force solution would generate every subarray of length `B` and calculate its sum separately.

This would require **O(N × B)** time.

A more efficient approach is to use the **Sliding Window Technique**.

### Sliding Window

1. Compute the sum of the first window of size `B`.
2. Compare it with `C`.
3. Move the window one position to the right by:
   - Removing the leftmost element.
   - Adding the new rightmost element.
4. Repeat until all windows have been checked.

This allows each new window sum to be calculated in constant time.

---

## Algorithm

1. If `B` is greater than the array length, return `0`.
2. Compute the sum of the first `B` elements.
3. If the sum equals `C`, return `1`.
4. Slide the window across the array.
5. For each new window:
   - Remove the outgoing element.
   - Add the incoming element.
   - Compare the new sum with `C`.
6. If a matching sum is found, return `1`.
7. If no matching window exists, return `0`.

---

## Dry Run

### Input

```text
A = [4, 3, 2, 6, 1]

B = 3

C = 11
```

---

### Initial Window

```text
[4, 3, 2]
```

Sum:

```text
9
```

Not equal to `11`.

---

### Slide Window

Remove:

```text
4
```

Add:

```text
6
```

New window:

```text
[3, 2, 6]
```

Sum:

```text
11
```

The required sum is found.

Return:

```text
1
```

---

## Another Dry Run

### Input

```text
A = [1, 2, 3, 4]

B = 2

C = 10
```

Windows:

| Window | Sum |
|---------|----:|
| [1, 2] | 3 |
| [2, 3] | 5 |
| [3, 4] | 7 |

No window has a sum of `10`.

Return:

```text
0
```

---

## Why Does This Work?

Instead of recalculating the sum for every window from scratch, the sliding window technique reuses the previous window's sum.

When moving from one window to the next:

```text
New Sum = Previous Sum
          - Outgoing Element
          + Incoming Element
```

This update takes constant time.

Since every window of length `B` is checked exactly once, the algorithm correctly determines whether a valid subarray exists.

---

## Complexity Analysis

### Time Complexity

Computing the first window:

```text
O(B)
```

Sliding the window through the remaining elements:

```text
O(N - B)
```

Overall:

```text
O(N)
```

---

### Space Complexity

Only a few variables are used.

```text
O(1)
```

---

## Java Solution

```java
public class Solution {

    public int solve(int[] A, int B, int C) {

        if (B <= A.length) {

            int n = A.length;
            int sum = 0;

            for (int i = 0; i < B; i++) {
                sum += A[i];
            }

            if (sum == C) {
                return 1;
            }

            for (int i = 1, j = B; j < n; i++, j++) {

                sum = sum - A[i - 1] + A[j];

                if (sum == C) {
                    return 1;
                }
            }

            return 0;

        } else {

            return 0;
        }
    }
}
```

---

## Key Learning

- The **Sliding Window Technique** is ideal for problems involving fixed-size contiguous subarrays.
- Instead of recomputing each window's sum from scratch, update it by removing the outgoing element and adding the incoming element.
- This optimization reduces the time complexity from **O(N × B)** to **O(N)** while using only **O(1)** extra space.
- Sliding windows are widely used in problems involving fixed-length subarrays, averages, sums, maximums, minimums, and frequency-based computations.
