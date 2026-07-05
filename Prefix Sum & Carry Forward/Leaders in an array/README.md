# Leaders in an Array

## Problem Statement

Given an integer array `A` containing `N` **distinct** integers, find all the **leaders** in the array.

An element is called a **leader** if it is **strictly greater than all the elements to its right**.

> **Note:** The rightmost element is always a leader.

Return an array containing all the leaders.

---

## Example

### Input

```text
A = [16, 17, 4, 3, 5, 2]
```

### Output

```text
[17, 5, 2]
```

### Explanation

- `17` is greater than all elements to its right.
- `5` is greater than `2`.
- `2` is the last element, so it is always a leader.

---

## Another Example

### Input

```text
A = [5, 4, 3, 2, 1]
```

### Output

```text
[5, 4, 3, 2, 1]
```

### Explanation

Every element is greater than all the elements to its right, so every element is a leader.

---

## Approach

The most efficient way to identify leaders is to traverse the array **from right to left**.

The algorithm maintains the **maximum element encountered so far** while moving backwards.

- The last element is always a leader.
- If the current element is greater than the maximum seen so far, it is also a leader.
- Update the maximum whenever a new leader is found.

Since the number of leaders is not known in advance, this solution performs two traversals:

1. The first traversal counts the total number of leaders.
2. The second traversal stores them in the result array.

This avoids using additional dynamic data structures.

---

## Algorithm

### Step 1: Count Leaders

1. Initialize:
   - `max` as the last element.
   - `count = 1` (last element is always a leader).
2. Traverse from right to left.
3. If the current element is greater than `max`:
   - Update `max`.
   - Increment `count`.

---

### Step 2: Store Leaders

1. Create an array of size `count`.
2. Store the last element as the final leader.
3. Traverse from right to left again.
4. Whenever a new leader is found:
   - Store it in the result array.
   - Update `max`.
5. Return the result array.

---

## Dry Run

### Input

```text
A = [16, 17, 4, 3, 5, 2]
```

### First Pass (Count Leaders)

| Element | Current Maximum | Leader? | Count |
|---------:|----------------:|:-------:|------:|
| 2 | 2 | Yes | 1 |
| 5 | 5 | Yes | 2 |
| 3 | 5 | No | 2 |
| 4 | 5 | No | 2 |
| 17 | 17 | Yes | 3 |
| 16 | 17 | No | 3 |

Number of leaders:

```text
3
```

---

### Second Pass (Store Leaders)

| Element | Current Maximum | Leader Added |
|---------:|----------------:|-------------|
| 2 | 2 | [2] |
| 5 | 5 | [5, 2] |
| 3 | 5 | — |
| 4 | 5 | — |
| 17 | 17 | [17, 5, 2] |
| 16 | 17 | — |

Final Output

```text
[17, 5, 2]
```

---

## Another Dry Run

### Input

```text
A = [5, 4, 3, 2, 1]
```

Every element is greater than all elements to its right.

Leaders:

```text
[5, 4, 3, 2, 1]
```

---

## Why Does This Work?

While traversing from right to left, the algorithm always knows the **largest element to the right** of the current position.

If the current element is greater than this maximum, then by definition it is greater than **every** element to its right, making it a leader.

Since the maximum is updated whenever a new leader is found, every leader is identified correctly.

---

## Complexity Analysis

### Time Complexity

- First traversal (count leaders): **O(N)**
- Second traversal (store leaders): **O(N)**

Overall:

```text
O(N)
```

---

### Space Complexity

```text
O(L)
```

where `L` is the number of leaders.

In the worst case (strictly decreasing array), every element is a leader.

```text
Worst Case Space Complexity = O(N)
```

---

## Java Solution

```java
public class Solution {

    int leader(int[] A) {

        int count = 1;
        int n = A.length;

        int max = A[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            if (A[i] > max) {
                max = A[i];
                count++;
            }
        }

        return count;
    }

    public int[] solve(int[] A) {

        int count = leader(A);

        int[] ans = new int[count];

        ans[count - 1] = A[A.length - 1];

        int n = A.length;

        int max = A[n - 1];

        for (int i = n - 2, m = ans.length - 2; i >= 0; i--) {

            if (A[i] > max) {
                max = A[i];
                ans[m] = A[i];
                m--;
            }
        }

        return ans;
    }
}
```

---

## Key Learning

- A leader is an element that is greater than every element to its right.
- Traversing from **right to left** is the most efficient way to solve this problem.
- Maintain the maximum value seen so far while moving backwards.
- The rightmost element is always a leader.
- This solution performs two linear traversals: one to count the leaders and another to store them, achieving an overall time complexity of **O(N)**.
- The right-to-left traversal technique is commonly used in array problems involving suffix maximums and leader elements.
