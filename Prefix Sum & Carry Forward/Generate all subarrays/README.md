# Generate All Subarrays

## Problem Statement

You are given an integer array `A` of size `N`.

Your task is to return a **2D array containing all possible subarrays** of the given array.

> **Note:** The order of the subarrays in the resulting 2D array does not matter.

---

## Example

### Input

```text
A = [1, 2, 3]
```

### Output

```text
[
  [1],
  [1, 2],
  [1, 2, 3],
  [2],
  [2, 3],
  [3]
]
```

### Explanation

All possible contiguous subarrays of the array are returned.

---

## What is a Subarray?

A **subarray** is a contiguous sequence of elements from an array.

For example, if

```text
A = [1, 2, 3]
```

The valid subarrays are:

```text
[1]
[1, 2]
[1, 2, 3]
[2]
[2, 3]
[3]
```

The following are **not** subarrays because they are not contiguous:

```text
[1, 3]
[2, 1]
```

---

## Approach

An array of size `N` has

```text
N × (N + 1) / 2
```

possible subarrays.

The solution first calculates this number and creates a 2D array capable of storing all subarrays.

It then uses **three nested loops**:

- The first loop selects the starting index.
- The second loop selects the ending index.
- The third loop copies the elements between the start and end indices into a new array.

Each generated subarray is stored in the result array.

---

## Algorithm

1. Let `N` be the size of the array.
2. Calculate the total number of subarrays:

```text
N × (N + 1) / 2
```

3. Create a 2D array to store all subarrays.
4. For every starting index:
   - For every ending index:
     - Create a new array of appropriate size.
     - Copy all elements between the two indices.
     - Store the subarray.
5. Return the 2D array.

---

## Dry Run

### Input

```text
A = [1, 2, 3]
```

Number of subarrays:

```text
3 × 4 / 2 = 6
```

Generated subarrays:

| Start | End | Subarray |
|------:|----:|----------|
| 0 | 0 | [1] |
| 0 | 1 | [1, 2] |
| 0 | 2 | [1, 2, 3] |
| 1 | 1 | [2] |
| 1 | 2 | [2, 3] |
| 2 | 2 | [3] |

Final Output

```text
[
  [1],
  [1, 2],
  [1, 2, 3],
  [2],
  [2, 3],
  [3]
]
```

---

## Why Does This Work?

Every subarray is uniquely identified by:

- A starting index.
- An ending index.

The first two loops generate every possible `(start, end)` pair.

The third loop copies the elements lying between these two indices into a new array.

Since every valid pair is considered exactly once, every possible contiguous subarray is generated.

---

## Complexity Analysis

### Time Complexity

There are:

```text
N × (N + 1) / 2
```

subarrays.

Each subarray may contain up to `N` elements.

Therefore, the total time complexity is:

```text
O(N³)
```

---

### Space Complexity

The algorithm stores every subarray.

The total number of elements stored across all subarrays is:

```text
O(N³)
```

Hence,

```text
Space Complexity = O(N³)
```

---

## Java Solution

```java
public class Solution {

    public int[][] solve(int[] A) {

        int n = A.length;

        int sub_array = (n * (n + 1)) / 2;

        int[][] sa = new int[sub_array][];

        int index = 0;

        for (int i = 0; i < n; i++) {

            for (int j = i; j < n; j++) {

                sa[index] = new int[j - i + 1];

                for (int k = i, m = 0; k <= j; k++, m++) {
                    sa[index][m] = A[k];
                }

                index++;
            }
        }

        return sa;
    }
}
```

---

## Key Learning

- A subarray is always a **contiguous** part of an array.
- An array of size `N` contains exactly **N × (N + 1) / 2** subarrays.
- Two loops are sufficient to identify every possible subarray using start and end indices.
- A third loop is required to copy the elements into the output array.
- Since every element of every subarray is copied, both the time and space complexity are **O(N³)**.
- Understanding subarray generation is fundamental for solving many array-based problems involving prefix sums, sliding windows, and dynamic programming.
