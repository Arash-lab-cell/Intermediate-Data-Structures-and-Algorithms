# Sum of Odd Indexed Elements in a Range

## Problem Statement

You are given:

- An integer array `A` of size `N`.
- A 2D array `B` of size `Q`, where each query is represented as `[L, R]`.

For each query, calculate the **sum of elements at odd indices** within the range `[L, R]` (both inclusive).

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
[6, 6, 4]
```

### Explanation

Odd indices are:

```text
Index : 1  3  5
Value : 2  4  6
```

- Query `[0, 5]` → `2 + 4 + 6 = 12`
- Query `[1, 4]` → `2 + 4 = 6`
- Query `[2, 5]` → `4 + 6 = 10`

Therefore, the correct output is:

```text
[12, 6, 10]
```

---

## Approach

A brute-force solution would iterate through every index in each query range and check whether the index is odd.

This would require **O(N × Q)** time in the worst case.

To optimize the solution, we build a **Prefix Sum Array for Odd Indices**.

The prefix sum array stores the cumulative sum of elements located only at odd indices.

For each index:

- If the index is odd:

```text
PrefixOdd[i] = PrefixOdd[i - 1] + A[i]
```

- If the index is even:

```text
PrefixOdd[i] = PrefixOdd[i - 1]
```

Once this prefix sum array is constructed, every query can be answered in constant time.

---

## Algorithm

1. Create a prefix sum array for odd indices.
2. Initialize:

```text
PrefixOdd[0] = 0
```

3. Traverse the array.
   - If the current index is odd, add the current element.
   - Otherwise, copy the previous prefix sum.
4. For every query:
   - If `L == 0`, answer is:

```text
PrefixOdd[R]
```

   - Otherwise:

```text
PrefixOdd[R] - PrefixOdd[L - 1]
```

5. Store every answer in the result array.
6. Return the result array.

---

## Dry Run

### Input

```text
A = [1, 2, 3, 4, 5, 6]
```

### Step 1: Build Prefix Sum for Odd Indices

| Index | Value | Odd Index? | Prefix Sum |
|------:|------:|:----------:|-----------:|
| 0 | 1 | No | 0 |
| 1 | 2 | Yes | 2 |
| 2 | 3 | No | 2 |
| 3 | 4 | Yes | 6 |
| 4 | 5 | No | 6 |
| 5 | 6 | Yes | 12 |

Prefix Sum Array:

```text
[0, 2, 2, 6, 6, 12]
```

---

### Query 1

```text
L = 0
R = 5
```

```text
Answer = PrefixOdd[5]

= 12
```

---

### Query 2

```text
L = 1
R = 4
```

```text
Answer = PrefixOdd[4] - PrefixOdd[0]

= 6 - 0

= 6
```

---

### Query 3

```text
L = 2
R = 5
```

```text
Answer = PrefixOdd[5] - PrefixOdd[1]

= 12 - 2

= 10
```

Final Output

```text
[12, 6, 10]
```

---

## Why Does This Work?

The prefix sum array stores the cumulative sum of **only the elements at odd indices**.

For any query `[L, R]`:

- `PrefixOdd[R]` contains the sum of all odd-indexed elements from index `0` to `R`.
- `PrefixOdd[L - 1]` contains the sum of all odd-indexed elements before the range.

Subtracting these values leaves only the sum of odd-indexed elements within the required range.

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

    public int[] sumOfOddIndexedElements(int[] A, int[][] B) {

        int n = A.length;

        int[] ps_odd = new int[n];

        ps_odd[0] = 0;

        for (int i = 1; i < n; i++) {

            if (i % 2 != 0) {
                ps_odd[i] = ps_odd[i - 1] + A[i];
            } else {
                ps_odd[i] = ps_odd[i - 1];
            }
        }

        int m = B.length;

        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {

            int l = B[i][0];
            int r = B[i][1];

            if (l == 0) {
                ans[i] = ps_odd[r];
            } else {
                ans[i] = ps_odd[r] - ps_odd[l - 1];
            }
        }

        return ans;
    }
}
```

---

## Key Learning

- Prefix Sum can be customized to maintain cumulative sums for selected indices instead of all elements.
- By storing the cumulative sum of **odd-indexed elements**, each range query can be answered in **O(1)** time.
- Preprocessing the array once greatly improves efficiency when multiple range queries are involved.
- The same technique can be adapted for even indices, positive numbers, negative numbers, prime numbers, or any subset of elements based on a condition.
- The overall solution runs in **O(N + Q)** time with **O(N)** extra space.
