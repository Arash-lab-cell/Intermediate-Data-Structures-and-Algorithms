# Rotate Array to the Right

## Problem Statement

Given an integer array `A` and an integer `B`, rotate the array to the **right** by `B` positions.

The rotation should be performed **in-place**, using **O(1)** extra space.

---

## Example

### Input

```text
A = [1, 2, 3, 4, 5]
B = 2
```

### Output

```text
[4, 5, 1, 2, 3]
```

### Explanation

After rotating the array two positions to the right:

- `4` and `5` move to the front.
- The remaining elements shift to the right.

---

## Approach

Instead of shifting the array one position at a time, we use the **Reversal Algorithm**, which performs the rotation efficiently.

The algorithm consists of three steps:

1. Reverse the entire array.
2. Reverse the first `B` elements.
3. Reverse the remaining `N - B` elements.

Before performing the rotation, compute:

```text
B = B % N
```

This handles cases where `B` is greater than the size of the array.

For example:

```text
N = 5
B = 7

7 % 5 = 2
```

Rotating by `7` positions is equivalent to rotating by `2` positions.

---

## Algorithm

1. Compute `B = B % N`.
2. Reverse the complete array.
3. Reverse the first `B` elements.
4. Reverse the remaining elements.
5. Return the rotated array.

---

## Dry Run

### Input

```text
A = [1, 2, 3, 4, 5]
B = 2
```

### Step 1

Reverse the entire array.

```text
[5, 4, 3, 2, 1]
```

### Step 2

Reverse the first `B = 2` elements.

```text
[4, 5, 3, 2, 1]
```

### Step 3

Reverse the remaining elements.

```text
[4, 5, 1, 2, 3]
```

Final Answer:

```text
[4, 5, 1, 2, 3]
```

---

## Another Example

### Input

```text
A = [10, 20, 30, 40, 50, 60]
B = 4
```

Reverse entire array:

```text
[60, 50, 40, 30, 20, 10]
```

Reverse first 4 elements:

```text
[30, 40, 50, 60, 20, 10]
```

Reverse remaining elements:

```text
[30, 40, 50, 60, 10, 20]
```

Final Answer:

```text
[30, 40, 50, 60, 10, 20]
```

---

## Why Does This Work?

Reversing the entire array changes the order of all elements.

The first reversal places the elements that should appear at the beginning of the rotated array in the correct section, but in reverse order.

The second reversal restores the correct order of the first `B` elements.

The third reversal restores the correct order of the remaining elements.

This results in the array being rotated without using any extra array.

---

## Complexity Analysis

### Time Complexity

- Reverse entire array → **O(N)**
- Reverse first `B` elements → **O(B)**
- Reverse remaining `N - B` elements → **O(N - B)**

Overall:

```text
O(N)
```

---

### Space Complexity

```text
O(1)
```

The rotation is performed in-place using only a few temporary variables.

---

## Java Solution

```java
public class Solution {

    int[] reverse(int[] A, int i, int j) {
        while (i < j) {
            int temp = A[i];
            A[i] = A[j];
            A[j] = temp;
            i++;
            j--;
        }
        return A;
    }

    public int[] solve(int[] A, int B) {
        B = B % A.length;

        reverse(A, 0, A.length - 1);
        reverse(A, 0, B - 1);
        reverse(A, B, A.length - 1);

        return A;
    }
}
```

---

## Key Learning

- The **Reversal Algorithm** is one of the most efficient methods to rotate an array.
- Always compute `B % N` before rotating to avoid unnecessary operations.
- Performing three reversals rotates the array in-place.
- This approach achieves **O(N)** time complexity with **O(1)** extra space.
- Understanding this algorithm is useful for array manipulation problems frequently asked in coding interviews.
