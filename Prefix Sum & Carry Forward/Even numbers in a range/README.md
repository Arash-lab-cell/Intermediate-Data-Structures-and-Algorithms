# Count Even Numbers in a Range

## Problem Statement

You are given:

- An integer array `A` of length `N`.
- A 2D array `B` of size `Q × 2`, where each query is represented as `[L, R]`.

For every query, find the **number of even elements** in the subarray from index `L` to index `R` (both inclusive).

Return an array containing the answer for each query.

---

## Example

### Input

```text
A = [2, 1, 4, 6, 3]

B = [
    [0, 2],
    [1, 4],
    [2, 3]
]
```

### Output

```text
[2, 2, 2]
```

### Explanation

- Query `[0, 2]` → `[2, 1, 4]` → Even numbers: `2`, `4` → Count = **2**
- Query `[1, 4]` → `[1, 4, 6, 3]` → Even numbers: `4`, `6` → Count = **2**
- Query `[2, 3]` → `[4, 6]` → Even numbers: `4`, `6` → Count = **2**

---

## Approach

A brute-force solution would examine every element within each query range and count the even numbers.

For `Q` queries, this would require **O(N × Q)** time in the worst case.

To optimize the solution, we build a **Prefix Count Array**.

Instead of storing cumulative sums, the prefix array stores the cumulative **count of even numbers**.

For every index:

- If the current element is even, increment the count.
- Store the current count in the prefix array.

Once the prefix array is built, each query can be answered in constant time.

---

## Algorithm

1. Initialize a counter for even numbers.
2. Traverse the array.
3. For every element:
   - If it is even, increment the counter.
   - Store the counter in the prefix array.
4. For every query:
   - If `L == 0`, answer is:

```text
PrefixEven[R]
```

   - Otherwise:

```text
PrefixEven[R] - PrefixEven[L - 1]
```

5. Return the answer array.

---

## Dry Run

### Input

```text
A = [2, 1, 4, 6, 3]
```

### Step 1: Build Prefix Count Array

| Index | Element | Even? | Prefix Count |
|------:|--------:|:-----:|-------------:|
| 0 | 2 | Yes | 1 |
| 1 | 1 | No | 1 |
| 2 | 4 | Yes | 2 |
| 3 | 6 | Yes | 3 |
| 4 | 3 | No | 3 |

Prefix Count Array:

```text
[1, 1, 2, 3, 3]
```

---

### Query 1

```text
L = 0
R = 2
```

```text
Answer = PrefixEven[2]

= 2
```

---

### Query 2

```text
L = 1
R = 4
```

```text
Answer = PrefixEven[4] - PrefixEven[0]

= 3 - 1

= 2
```

---

### Query 3

```text
L = 2
R = 3
```

```text
Answer = PrefixEven[3] - PrefixEven[1]

= 3 - 1

= 2
```

Final Output

```text
[2, 2, 2]
```

---

## Why Does This Work?

The prefix array stores the cumulative count of even numbers from index `0` to every position.

For any range `[L, R]`:

- `PrefixEven[R]` contains the total number of even elements from index `0` to `R`.
- `PrefixEven[L - 1]` contains the total number of even elements before the range.

Subtracting these values gives the count of even numbers only within the required range.

Thus, each query is answered in **O(1)** time.

---

## Complexity Analysis

### Time Complexity

Building the prefix count array:

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

Overall:

```text
O(N + Q)
```

---

### Space Complexity

```text
O(N)
```

An additional prefix count array of size `N` is used.

---

## Java Solution

```java
public class Solution {

    public int[] solve(int[] A, int[][] B) {

        int n = A.length;

        int[] ps_even = new int[n];

        int count = 0;

        for (int i = 0; i < n; i++) {

            if (A[i] % 2 == 0) {
                count++;
            }

            ps_even[i] = count;
        }

        int[] ans = new int[B.length];

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

- Prefix Sum can be extended beyond sums to maintain cumulative **counts**.
- By storing the running count of even numbers, each range query can be answered in **O(1)** time.
- Preprocessing the array once significantly reduces the overall complexity when handling multiple queries.
- This technique can be adapted to count odd numbers, positive numbers, negative numbers, prime numbers, or any elements satisfying a specific condition.
- The overall solution runs in **O(N + Q)** time with **O(N)** extra space.
