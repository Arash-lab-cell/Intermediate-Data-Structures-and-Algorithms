# Subarray with the Least Average

## Problem Statement

You are given:

- An integer array `A` of size `N`.
- An integer `B` representing the size of the subarray.

Your task is to find the **starting index** of the contiguous subarray of size `B` that has the **minimum average**.

If multiple subarrays have the same minimum average, return the **smallest starting index**.

---

## Example

### Input

```text
A = [3, 7, 5, 20, -10, 0, 12]

B = 2
```

### Output

```text
4
```

### Explanation

Subarrays of size `2`:

| Subarray | Sum | Average |
|----------|----:|--------:|
| [3, 7] | 10 | 5.0 |
| [7, 5] | 12 | 6.0 |
| [5, 20] | 25 | 12.5 |
| [20, -10] | 10 | 5.0 |
| [-10, 0] | -10 | -5.0 |
| [0, 12] | 12 | 6.0 |

The minimum average is:

```text
-5
```

which corresponds to the subarray:

```text
[-10, 0]
```

Starting index:

```text
4
```

---

## Another Example

### Input

```text
A = [1, 2, 3, 4]

B = 2
```

### Output

```text
0
```

### Explanation

Subarrays:

| Subarray | Average |
|----------|--------:|
| [1, 2] | 1.5 |
| [2, 3] | 2.5 |
| [3, 4] | 3.5 |

The minimum average belongs to the first subarray.

Therefore, the answer is:

```text
0
```

---

## Approach

Since every subarray has the **same length `B`**, comparing their averages is equivalent to comparing their sums.

Instead of calculating the average every time:

```text
Average = Sum / B
```

we simply compare the sums.

The solution uses the **Sliding Window Technique**.

### Sliding Window

1. Compute the sum of the first window of size `B`.
2. Assume it has the minimum average.
3. Slide the window one position to the right.
4. Update the window sum by:
   - Removing the outgoing element.
   - Adding the incoming element.
5. Whenever a smaller sum is found, update:
   - The minimum sum.
   - The starting index.

---

## Algorithm

1. Compute the sum of the first `B` elements.
2. Store this as the current minimum sum.
3. Initialize the answer as index `0`.
4. Slide the window across the array.
5. For every new window:
   - Remove the leftmost element.
   - Add the next element.
   - If the new sum is smaller than the current minimum:
     - Update the minimum sum.
     - Update the starting index.
6. Return the starting index.

---

## Dry Run

### Input

```text
A = [3, 7, 5, 20, -10, 0, 12]

B = 2
```

---

### First Window

```text
[3, 7]
```

Sum:

```text
10
```

Minimum Sum:

```text
10
```

Answer:

```text
0
```

---

### Slide 1

Window:

```text
[7, 5]
```

Sum:

```text
12
```

Minimum remains:

```text
10
```

---

### Slide 2

Window:

```text
[5, 20]
```

Sum:

```text
25
```

---

### Slide 3

Window:

```text
[20, -10]
```

Sum:

```text
10
```

---

### Slide 4

Window:

```text
[-10, 0]
```

Sum:

```text
-10
```

New minimum found.

Answer:

```text
4
```

---

### Slide 5

Window:

```text
[0, 12]
```

Sum:

```text
12
```

Final Answer:

```text
4
```

---

## Why Does This Work?

Every subarray has the same length `B`.

Since:

```text
Average = Sum / B
```

and `B` is constant for every subarray, comparing averages is identical to comparing sums.

The sliding window technique efficiently updates each new window sum by:

```text
New Sum
=
Previous Sum
- Outgoing Element
+ Incoming Element
```

This avoids recomputing the entire sum for every subarray and ensures that every possible window is processed exactly once.

---

## Complexity Analysis

### Time Complexity

Computing the first window:

```text
O(B)
```

Sliding through the remaining windows:

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

    public int solve(int[] A, int B) {

        int n = A.length;

        int index = 0;

        int ans = 0;

        for (int i = 0; i < B; i++) {
            ans += A[i];
        }

        int sum = ans;

        for (int s = 1, e = B; e < n; s++, e++) {

            sum = sum - A[s - 1] + A[e];

            if (sum < ans) {

                ans = sum;
                index = s;
            }
        }

        return index;
    }
}
```

---

## Key Learning

- When all subarrays have the same length, comparing their **averages** is equivalent to comparing their **sums**, eliminating unnecessary division operations.
- The **Sliding Window Technique** efficiently computes the sum of each fixed-size subarray by updating the previous window's sum instead of recalculating it from scratch.
- Each window is processed in **O(1)** time after the initial window, resulting in an overall **O(N)** time complexity.
- This approach uses only **O(1)** extra space and is a standard technique for solving fixed-size subarray problems efficiently.
