# Equilibrium Index

## Problem Statement

You are given an integer array `A` of size `N`.

Your task is to find the **equilibrium index** of the array.

An equilibrium index is an index such that:

- The sum of all elements to its **left** is equal to the sum of all elements to its **right**.

If there are no elements on either side, the corresponding sum is considered to be **0**.

### Note

- Array indexing starts from **0**.
- If there is no equilibrium index, return **-1**.
- If multiple equilibrium indices exist, return the **smallest index**.

---

## Example

### Input

```text
A = [-7, 1, 5, 2, -4, 3, 0]
```

### Output

```text
3
```

### Explanation

At index `3`:

```text
Left Sum  = -7 + 1 + 5 = -1
Right Sum = -4 + 3 + 0 = -1
```

Since both sums are equal, the equilibrium index is `3`.

---

## Another Example

### Input

```text
A = [1, 2, 3]
```

### Output

```text
-1
```

### Explanation

There is no index where the left sum equals the right sum.

---

## Approach

The solution uses a **Prefix Sum Array** to efficiently calculate the sum of elements on the left of every index.

The steps are:

1. Compute the total sum of the array.
2. Build a prefix sum array.
3. For every index:
   - Calculate the left sum.
   - Calculate the right sum using the total sum and prefix sum.
4. If both sums are equal, return the current index.
5. If no equilibrium index is found, return `-1`.

Special care is taken for the first element since there are no elements to its left.

---

## Algorithm

1. Calculate the total sum of the array.
2. Construct a prefix sum array.
3. Traverse the array.
4. For each index:
   - If `i == 0`
     - Left Sum = `0`
     - Right Sum = `Total Sum - A[i]`
   - Otherwise
     - Left Sum = `PrefixSum[i - 1]`
     - Right Sum = `TotalSum - (PrefixSum[i - 1] + A[i])`
5. If `Left Sum == Right Sum`, return the current index.
6. If no such index exists, return `-1`.

---

## Dry Run

### Input

```text
A = [-7, 1, 5, 2, -4, 3, 0]
```

### Step 1: Calculate Total Sum

```text
Total Sum = 0
```

### Step 2: Build Prefix Sum Array

| Index | Element | Prefix Sum |
|------:|--------:|-----------:|
| 0 | -7 | -7 |
| 1 | 1 | -6 |
| 2 | 5 | -1 |
| 3 | 2 | 1 |
| 4 | -4 | -3 |
| 5 | 3 | 0 |
| 6 | 0 | 0 |

---

### Step 3: Check Each Index

| Index | Left Sum | Right Sum | Equilibrium? |
|------:|---------:|----------:|:------------:|
| 0 | 0 | 7 | ❌ |
| 1 | -7 | 6 | ❌ |
| 2 | -6 | 1 | ❌ |
| 3 | -1 | -1 | ✅ |

The first equilibrium index is:

```text
3
```

---

## Another Dry Run

### Input

```text
A = [2, 1, -1]
```

Total Sum:

```text
2
```

Prefix Sum:

```text
[2, 3, 2]
```

| Index | Left Sum | Right Sum |
|------:|---------:|----------:|
| 0 | 0 | 0 |

Since both sums are equal, the equilibrium index is:

```text
0
```

---

## Why Does This Work?

The prefix sum array allows the left sum of any index to be calculated instantly.

The right sum can then be derived using:

```text
Right Sum = Total Sum − Left Sum − Current Element
```

Instead of recalculating sums for every index, which would take **O(N²)** time, this method computes both sums in constant time for each index.

Thus, the entire solution runs in linear time.

---

## Complexity Analysis

### Time Complexity

- Calculate total sum: **O(N)**
- Build prefix sum array: **O(N)**
- Traverse the array: **O(N)**

Overall:

```text
O(N)
```

---

### Space Complexity

```text
O(N)
```

An additional prefix sum array of size `N` is used.

> **Note:** This problem can also be solved in **O(N)** time with **O(1)** extra space by maintaining a running left sum instead of creating a prefix sum array.

---

## Java Solution

```java
public class Solution {

    public int solve(int[] A) {

        int sum = 0;

        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }

        int[] ps = new int[A.length];

        ps[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            ps[i] = ps[i - 1] + A[i];
        }

        int left_sum = 0;
        int right_sum = 0;

        for (int i = 0; i < A.length; i++) {

            if (i == 0) {
                left_sum = 0;
                right_sum = sum - A[i];
            } else {
                left_sum = ps[i - 1];
                right_sum = sum - (ps[i - 1] + A[i]);
            }

            if (left_sum == right_sum) {
                return i;
            }
        }

        return -1;
    }
}
```

---

## Key Learning

- Prefix Sum enables efficient computation of cumulative sums.
- Once the total sum and prefix sum array are available, both the left and right sums for any index can be calculated in **O(1)** time.
- This reduces the overall complexity from **O(N²)** (recomputing sums for every index) to **O(N)**.
- Always handle boundary cases separately, such as the first index where the left sum is `0`.
- This problem introduces the concept of balancing cumulative sums, which is commonly used in array and prefix sum problems.
