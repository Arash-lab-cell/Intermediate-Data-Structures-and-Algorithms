# Sum of Even Indexed Elements in a Range

## Problem Statement

You are given:

- An integer array `A` of size `N`.
- A 2D array `B` of size `Q`, where each query is represented as `[L, R]`.

For each query, calculate the **sum of elements at even indices** within the range `[L, R]` (both inclusive).

Return an array containing the answer for each query.

---

## Example

### Input

```text
A = [1, 2, 3, 4, 5, 6]

B = [
    [0, 5],
    [1, 4],
    [2, 5]
]
```

### Output

```text
[9, 8, 8]
```

### Explanation

Even indices are:

```text
Index : 0  2  4
Value : 1  3  5
```

- Query `[0, 5]` → `1 + 3 + 5 = 9`
- Query `[1, 4]` → `3 + 5 = 8`
- Query `[2, 5]` → `3 + 5 = 8`

---

## Approach

A brute-force solution would iterate through every index in every query and check whether the index is even.

This would require **O(Q × N)** time in the worst case.

To optimize the solution, we build a **Prefix Sum Array for Even Indices**.

The prefix sum array stores the cumulative sum of elements located only at even indices.

For each index:

- If the index is even:

```text
PrefixEven[i] = PrefixEven[i - 1] + A[i]
```

- If the index is odd:

```text
PrefixEven[i] = PrefixEven[i - 1]
```

Once this array is built, every query can be answered in constant time.

---

## Algorithm

1. Create a prefix sum array for even indices.
2. Initialize:

```text
PrefixEven[0] = A[0]
```

3. Traverse the array.
   - If the current index is even, add the current element.
   - Otherwise, copy the previous prefix sum.
4. For every query:
   - If `L == 0`, answer is:

```text
PrefixEven[R]
```

   - Otherwise:

```text
PrefixEven[R] - PrefixEven[L - 1]
```

5. Store every answer in the result array.
6. Return the result array.

---

## Dry Run

### Input

```text
A = [1, 2, 3, 4, 5, 6]
```

### Step 1: Build Prefix Sum for Even Indices

| Index | Value | Even Index? | Prefix Sum |
|------:|------:|:-----------:|-----------:|
| 0 | 1 | Yes | 1 |
| 1 | 2 | No | 1 |
| 2 | 3 | Yes | 4 |
| 3 | 4 | No | 4 |
| 4 | 5 | Yes | 9 |
| 5 | 6 | No | 9 |

Prefix Sum Array:

```text
[1, 1, 4, 4, 9, 9]
```

---

### Query 1

```text
L = 0
R = 5
```

```text
Answer = PrefixEven[5]

= 9
```

---

### Query 2

```text
L = 1
R = 4
```

```text
Answer = PrefixEven[4] - PrefixEven[0]

= 9 - 1

= 8
```

---

### Query 3

```text
L = 2
R = 5
```

```text
Answer = PrefixEven[5] - PrefixEven[1]

= 9 - 1

= 8
```

Final Output

```text
[9, 8, 8]
```

---

## Why Does This Work?

The prefix sum array stores the cumulative sum of **only the elements at even indices**.

For any query `[L, R]`:

- `PrefixEven[R]` contains the sum of all even-indexed elements from index `0` to `R`.
- `PrefixEven[L - 1]` contains the sum of all even-indexed elements before the range.

Subtracting these values leaves only the sum of even-indexed elements within the required range.

Thus, each query can be answered in constant time.

---

## Complexity Analysis

### Time Complexity

Building the prefix sum array:

```text
O(N)
```

Answering each query:

```text
O(1)
```

For `Q` queries:

```text
O(Q)
```

Overall Time Complexity:

```text
O(N + Q)
```

---

### Space Complexity

```text
O(N)
```

An additional prefix sum array of size `N` is used.

---

## Java Solution

```java
public class Solution {

    public int[] sumOfEvenIndexedElements(int[] A, int[][] B) {

        int[] ps_even = new int[A.length];
        int[] ans = new int[B.length];

        ps_even[0] = A[0];

        for (int i = 1; i < A.length; i++) {

            if (i % 2 == 0) {
                ps_even[i] = ps_even[i - 1] + A[i];
            } else {
                ps_even[i] = ps_even[i - 1];
            }
        }

        for (int i = 0; i < B.length; i++) {

            int l = B[i][0];
            int r = B[i][1];

            if (l == 0) {
                ans[i] = ps_even[r];
            } else {
                ans[i] = ps_even[r] - ps_even[l - 1];
            }
        }

        return ans;
    }
}
```

---

## Key Learning

- Prefix Sum can be customized to store cumulative information for only specific indices or elements.
- By storing the cumulative sum of **even-indexed elements**, each range query can be answered in **O(1)** time.
- Preprocessing the array once significantly improves performance when multiple range queries need to be answered.
- This technique can be extended to odd indices, positive numbers, negative numbers, or any subset of elements based on a given condition.
