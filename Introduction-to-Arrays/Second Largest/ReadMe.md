# Second Largest Element

## Problem Statement

Given an integer array `A`, find the **second largest distinct element** in the array.

- Return the second largest element if it exists.
- Return `-1` if there is no second largest distinct element.

---

## Example

### Input

```text
A = [2, 1, 5, 4, 5]
```

### Output

```text
4
```

### Explanation

The largest element is `5`.

The second largest **distinct** element is `4`.

---

## Another Example

### Input

```text
A = [7, 7, 7]
```

### Output

```text
-1
```

### Explanation

All elements are the same, so there is no second largest distinct element.

---

## Approach

The solution finds the answer in **a single traversal** of the array without sorting.

Two variables are maintained:

- `max` – stores the largest element found so far.
- `sec_max` – stores the second largest distinct element.

For every element:

- If the current element is greater than `max`:
  - Update `sec_max` to the previous `max`.
  - Update `max` to the current element.
- Otherwise, if the current element is greater than `sec_max` but smaller than `max`, update `sec_max`.

After traversing the array:

- If `sec_max` was never updated, return `-1`.
- Otherwise, return `sec_max`.

---

## Algorithm

1. Initialize:
   - `max = Integer.MIN_VALUE`
   - `sec_max = Integer.MIN_VALUE`
2. Traverse the array.
3. If the current element is greater than `max`:
   - Assign `sec_max = max`
   - Assign `max = current element`
4. Else if the current element is between `sec_max` and `max`:
   - Update `sec_max`
5. If `sec_max` is still `Integer.MIN_VALUE`, return `-1`.
6. Otherwise, return `sec_max`.

---

## Dry Run

### Input

```text
A = [2, 1, 5, 4, 5]
```

| Element | Max | Second Max |
|---------:|----:|-----------:|
| 2 | 2 | MIN |
| 1 | 2 | 1 |
| 5 | 5 | 2 |
| 4 | 5 | 4 |
| 5 | 5 | 4 |

Final Answer:

```text
4
```

---

## Another Dry Run

### Input

```text
A = [10, 10, 10]
```

| Element | Max | Second Max |
|---------:|----:|-----------:|
| 10 | 10 | MIN |
| 10 | 10 | MIN |
| 10 | 10 | MIN |

Since `sec_max` was never updated, return:

```text
-1
```

---

## Why Does This Work?

The algorithm always keeps track of the two largest **distinct** elements seen so far.

Whenever a new maximum is found:

- The old maximum automatically becomes the second maximum.

Duplicate values of the maximum are ignored because the second largest element must be **distinct**.

This eliminates the need to sort the array.

---

## Complexity Analysis

### Time Complexity

```text
O(N)
```

The array is traversed only once.

---

### Space Complexity

```text
O(1)
```

Only two variables are used regardless of the input size.

---

## Java Solution

```java
public class Solution {
    public int solve(int[] A) {
        int max = Integer.MIN_VALUE;
        int sec_max = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {

            if (A[i] > max) {
                sec_max = max;
                max = A[i];
            } else if (A[i] > sec_max && A[i] < max) {
                sec_max = A[i];
            }
        }

        if (sec_max == Integer.MIN_VALUE) {
            return -1;
        } else {
            return sec_max;
        }
    }
}
```

---

## Key Learning

- The second largest element can be found without sorting.
- Maintain two variables to track the largest and second largest distinct elements.
- When a new maximum is found, the previous maximum automatically becomes the second maximum.
- Duplicate maximum values should not be considered as the second largest element.
- This approach achieves **O(N)** time complexity with **O(1)** extra space, making it more efficient than sorting, which takes **O(N log N)**.
