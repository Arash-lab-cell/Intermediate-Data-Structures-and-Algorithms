# Minimum Swaps to Bring All Elements Less Than or Equal to B Together

## Problem Statement

You are given:

- An integer array `A`.
- An integer `B`.

Your task is to find the **minimum number of swaps** required to bring **all elements less than or equal to `B` together** in the array.

A swap can be performed between **any two elements**; they do **not** have to be adjacent.

Return the minimum number of swaps required.

---

## Example

### Input

```text
A = [1, 12, 10, 3, 14, 10, 5]

B = 8
```

### Output

```text
2
```

### Explanation

Elements less than or equal to `8` are:

```text
1, 3, 5
```

There are **3 good elements**, so we need a window of size **3**.

Possible windows:

| Window | Good Elements | Bad Elements |
|---------|--------------:|-------------:|
| [1, 12, 10] | 1 | 2 |
| [12, 10, 3] | 1 | 2 |
| [10, 3, 14] | 1 | 2 |
| [3, 14, 10] | 1 | 2 |
| [14, 10, 5] | 1 | 2 |

The best window contains **1 good element**, meaning **2 bad elements** must be swapped.

Minimum swaps:

```text
2
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

Good elements:

```text
1, 2
```

They are already together.

No swaps are required.

---

## Approach

Instead of trying every possible sequence of swaps, observe the following:

- Elements **less than or equal to `B`** are called **good elements**.
- All good elements must eventually be grouped together.
- Therefore, the size of the required group is fixed.

### Step 1

Count the total number of good elements.

```text
Window Size = Number of Good Elements
```

### Step 2

Create a sliding window of this size.

Inside every window, count how many good elements are already present.

The window containing the **maximum number of good elements** requires the **fewest swaps**.

Finally,

```text
Minimum Swaps
=
Total Good Elements
-
Maximum Good Elements in Any Window
```

---

## Algorithm

1. Count the number of elements less than or equal to `B`.
2. Let this count be the window size.
3. Count the good elements inside the first window.
4. Store this as the current maximum.
5. Slide the window across the array.
6. While sliding:
   - Remove the outgoing element if it is good.
   - Add the incoming element if it is good.
   - Update the maximum number of good elements found in any window.
7. Return:

```text
Window Size - Maximum Good Elements
```

---

## Dry Run

### Input

```text
A = [1, 12, 10, 3, 14, 10, 5]

B = 8
```

Good elements:

```text
1, 3, 5
```

Window size:

```text
3
```

---

### First Window

```text
[1, 12, 10]
```

Good elements:

```text
1
```

Maximum Good Elements:

```text
1
```

---

### Slide Window

Window:

```text
[12, 10, 3]
```

Good elements:

```text
1
```

Maximum remains:

```text
1
```

---

Window:

```text
[10, 3, 14]
```

Good elements:

```text
1
```

---

Window:

```text
[3, 14, 10]
```

Good elements:

```text
1
```

---

Window:

```text
[14, 10, 5]
```

Good elements:

```text
1
```

Maximum Good Elements:

```text
1
```

Final Answer:

```text
3 - 1 = 2
```

---

## Why Does This Work?

If there are `c` good elements in the entire array, the final grouped section must have a length of exactly `c`.

Instead of performing actual swaps, we examine every window of size `c`.

Each window contains:

- Some **good elements** (already in the correct group).
- Some **bad elements** (greater than `B`).

Every bad element inside the chosen window must be swapped with a good element outside the window.

Therefore,

```text
Bad Elements
=
Window Size
-
Good Elements
```

To minimize swaps, choose the window containing the **maximum number of good elements**.

---

## Complexity Analysis

### Time Complexity

Counting good elements:

```text
O(N)
```

Building the first window:

```text
O(C)
```

Sliding the window:

```text
O(N)
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

        int c = 0;

        for (int i = 0; i < n; i++) {
            if (A[i] <= B) {
                c++;
            }
        }

        int count = 0;

        for (int i = 0; i < c; i++) {
            if (A[i] <= B) {
                count++;
            }
        }

        int maxSwipe = count;

        for (int i = c; i < n; i++) {

            if (A[i - c] <= B) {
                count--;
            }

            if (A[i] <= B) {
                count++;
            }

            maxSwipe = Math.max(maxSwipe, count);
        }

        return c - maxSwipe;
    }
}
```

---

## Key Learning

- Problems involving grouping elements can often be transformed into a **fixed-size sliding window** problem.
- The window size is determined by the total number of elements satisfying the given condition (the "good" elements).
- Instead of simulating swaps, count how many good elements are already present in each window.
- The window with the **maximum number of good elements** requires the **minimum number of swaps**, since only the remaining positions contain bad elements.
- This approach reduces the problem from a brute-force solution to an efficient **O(N)** algorithm using **O(1)** extra space.
