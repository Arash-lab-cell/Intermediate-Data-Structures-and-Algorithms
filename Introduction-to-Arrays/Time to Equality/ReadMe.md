# Time to Equality

## Problem Statement

Given an integer array `A`, in one second you can increment **any one element** of the array by `1`.

Determine the minimum number of seconds required to make all the elements of the array equal.

---

## Example

### Input

```text
A = [2, 4, 1, 3]
```

### Output

```text
6
```

### Explanation

The largest element is `4`.

To make every element equal to `4`:

- `2 → 4` requires `2` seconds.
- `1 → 4` requires `3` seconds.
- `3 → 4` requires `1` second.
- `4` already equals `4`.

Total seconds:

```text
2 + 3 + 1 = 6
```

---

## Approach

Since we are only allowed to **increase** elements, the final value must be the **maximum element** in the array.

Any value smaller than the maximum needs to be incremented until it becomes equal to the maximum.

The algorithm first sorts the array so that the maximum element is placed at the last index.

Then, for every other element, it calculates:

```text
Maximum Element - Current Element
```

The sum of these differences gives the minimum number of seconds required.

---

## Algorithm

1. Sort the array.
2. Store the last element as the maximum.
3. Traverse the array from the second-last element to the first.
4. Add `(maximum - current element)` to the answer.
5. Return the total number of seconds.

---

## Dry Run

### Input

```text
A = [2, 4, 1, 3]
```

### Step 1

Sort the array.

```text
[1, 2, 3, 4]
```

Maximum element:

```text
4
```

| Element | Difference from Maximum | Total Seconds |
|---------:|------------------------:|--------------:|
| 3 | 1 | 1 |
| 2 | 2 | 3 |
| 1 | 3 | 6 |

Final Answer:

```text
6
```

---

## Another Example

### Input

```text
A = [5, 5, 5]
```

Sorted array:

```text
[5, 5, 5]
```

Each element is already equal to the maximum.

Total seconds:

```text
0
```

---

## Why Does This Work?

Since decrementing elements is **not allowed**, every element must be increased until it reaches the largest element already present in the array.

Choosing any value larger than the maximum would require additional unnecessary increments.

Therefore, making every element equal to the current maximum always produces the minimum number of operations.

---

## Complexity Analysis

### Time Complexity

- Sorting the array: **O(N log N)**
- Traversing the array: **O(N)**

Overall:

```text
O(N log N)
```

---

### Space Complexity

```text
O(1)
```

Only a few variables are used (ignoring the space used internally by the sorting algorithm).

---

## Java Solution

```java
public class Solution {
    public int solve(int[] A) {
        int tot_sec = 0;

        Arrays.sort(A);

        for (int i = A.length - 2; i >= 0; i--) {
            tot_sec += (A[A.length - 1] - A[i]);
        }

        return tot_sec;
    }
}
```

---

## Optimization

Sorting is **not actually required** for this problem.

A more efficient approach is:

1. Find the maximum element in one traversal.
2. Traverse the array again and sum `(maximum - current element)`.

This improves the time complexity to:

```text
O(N)
```

while maintaining **O(1)** extra space.

---

## Key Learning

- If only increment operations are allowed, all elements must become equal to the current maximum element.
- Sorting makes it easy to locate the maximum but is not necessary.
- The minimum number of operations is simply the sum of the differences between the maximum element and every other element.
- An optimal solution can solve the problem in **O(N)** time by avoiding sorting.
