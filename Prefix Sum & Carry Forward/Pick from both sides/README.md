# Pick from Both Sides

## Problem Statement

You are given an integer array `A` of size `N`.

You must perform exactly **B** operations.

In each operation, you can remove **either**:

- The leftmost element of the array, or
- The rightmost element of the array.

Your task is to find the **maximum possible sum** of the `B` removed elements.

### Note

If `B = 3`, the possible choices are:

- Pick `3` elements from the front and `0` from the back.
- Pick `2` elements from the front and `1` from the back.
- Pick `1` element from the front and `2` from the back.
- Pick `0` elements from the front and `3` from the back.

Return the maximum possible sum.

---

## Example

### Input

```text
A = [5, -2, 3, 1, 2]

B = 3
```

### Output

```text
8
```

### Explanation

Possible selections are:

| Front Picks | Back Picks | Selected Elements | Sum |
|-------------|------------|-------------------|----:|
| 3 | 0 | 5, -2, 3 | 6 |
| 2 | 1 | 5, -2, 2 | 5 |
| 1 | 2 | 5, 1, 2 | 8 |
| 0 | 3 | 3, 1, 2 | 6 |

Maximum sum:

```text
8
```

---

## Approach

Instead of simulating every possible sequence of removals, observe that after `B` operations, the removed elements must consist of:

- Some elements from the **front**.
- The remaining elements from the **back**.

There are only:

```text
B + 1
```

possible combinations.

To evaluate each combination efficiently, the solution constructs:

- A **Prefix Sum Array** for elements taken from the front.
- A **Suffix Sum Array** for elements taken from the back.

Using these two arrays, every possible split between front and back picks can be evaluated in constant time.

---

## Algorithm

1. Build the prefix sum array.
2. Build the suffix sum array.
3. Initialize the answer as the better of:
   - Taking all `B` elements from the front.
   - Taking all `B` elements from the back.
4. For every possible split:
   - Take `i` elements from the front.
   - Take `B - i` elements from the back.
   - Compute the total sum.
   - Update the maximum answer.
5. Return the maximum sum.

---

## Dry Run

### Input

```text
A = [5, -2, 3, 1, 2]

B = 3
```

---

### Step 1: Build Prefix Sum

| Index | Prefix Sum |
|------:|-----------:|
| 0 | 5 |
| 1 | 3 |
| 2 | 6 |
| 3 | 7 |
| 4 | 9 |

```text
Prefix = [5, 3, 6, 7, 9]
```

---

### Step 2: Build Suffix Sum

| Elements Taken from Back | Sum |
|---------------------------|----:|
| 2 | 2 |
| 1, 2 | 3 |
| 3, 1, 2 | 6 |
| -2, 3, 1, 2 | 4 |
| 5, -2, 3, 1, 2 | 9 |

```text
Suffix = [2, 3, 6, 4, 9]
```

---

### Step 3: Check Every Combination

| Front Picks | Back Picks | Sum |
|-------------|------------|----:|
| 3 | 0 | 6 |
| 2 | 1 | 5 |
| 1 | 2 | 8 |
| 0 | 3 | 6 |

Maximum:

```text
8
```

---

## Why Does This Work?

Every valid solution consists of selecting:

- Some elements from the beginning of the array.
- The remaining elements from the end.

There are exactly `B + 1` such combinations.

The prefix sum array provides the sum of the first `k` elements in **O(1)** time.

The suffix sum array provides the sum of the last `k` elements in **O(1)** time.

By trying every possible split between front and back picks, the algorithm guarantees that the maximum possible sum is found.

---

## Complexity Analysis

### Time Complexity

Building the prefix sum array:

```text
O(N)
```

Building the suffix sum array:

```text
O(N)
```

Checking all combinations:

```text
O(B)
```

Overall:

```text
O(N + B)
```

---

### Space Complexity

Two additional arrays are used.

```text
O(N)
```

---

## Java Solution

```java
public class Solution {

    public int solve(int[] A, int B) {

        int n = A.length;

        int[] ps = new int[n];
        ps[0] = A[0];

        for (int i = 1; i < n; i++) {
            ps[i] = ps[i - 1] + A[i];
        }

        int[] ss = new int[n];
        ss[0] = A[n - 1];

        for (int i = n - 2, m = 1; i >= 0; i--, m++) {
            ss[m] = ss[m - 1] + A[i];
        }

        int ans = Math.max(ps[B - 1], ss[B - 1]);

        for (int i = 1; i <= B - 1; i++) {

            int sum = ps[i - 1] + ss[B - i - 1];

            if (sum > ans) {
                ans = sum;
            }
        }

        return ans;
    }
}
```

---

## Key Learning

- A sequence of removals from both ends can be represented as choosing some elements from the **front** and the remaining elements from the **back**.
- Instead of simulating every removal order, evaluate the `B + 1` possible front/back combinations.
- Prefix sums efficiently compute sums of elements taken from the front, while suffix sums do the same for elements taken from the back.
- This reduces the problem from a potentially exponential exploration of choices to an efficient **O(N + B)** solution.
- Prefix and suffix sum techniques are widely used in optimization problems involving ranges and selections from array boundaries.
