# Best Time to Buy and Sell Stock

## Problem Statement

You are given an integer array `A`, where `A[i]` represents the price of a stock on the `iᵗʰ` day.

You are allowed to complete **at most one transaction**, which means:

- Buy one share of the stock.
- Sell that share once.
- You must buy **before** you sell.

Your task is to determine the **maximum profit** that can be earned.

If no profit can be made, return **0**.

---

## Example

### Input

```text
A = [1, 4, 5, 2, 4]
```

### Output

```text
4
```

### Explanation

- Buy on Day 0 at price **1**
- Sell on Day 2 at price **5**

Profit:

```text
5 - 1 = 4
```

This is the maximum possible profit.

---

## Another Example

### Input

```text
A = [7, 6, 4, 3, 1]
```

### Output

```text
0
```

### Explanation

The stock price keeps decreasing.

No profitable transaction is possible.

Therefore, the answer is:

```text
0
```

---

## Approach

To maximize profit, we need to:

- Buy at the **lowest price** encountered so far.
- Sell at the **highest possible price after buying**.

Instead of checking every possible pair of buy and sell days (which would take **O(N²)** time), the solution scans the array once.

During traversal, maintain:

- `min_price` → Lowest stock price seen so far.
- `max_profit` → Maximum profit obtained so far.

For every day's price:

1. Calculate the profit if the stock were sold today.

```text
Current Profit = Current Price - Minimum Price
```

2. Update the minimum price if a lower price is found.
3. Update the maximum profit whenever a larger profit is obtained.

This guarantees the optimal answer in a single pass.

---

## Algorithm

1. If the array is empty, return `0`.
2. Initialize:
   - `min_price = A[0]`
   - `max_profit = 0`
3. Traverse the array.
4. For each price:
   - Calculate the profit using the minimum price seen so far.
   - Update the minimum price if the current price is lower.
   - Update the maximum profit if the current profit is greater.
5. Return the maximum profit.

---

## Dry Run

### Input

```text
A = [1, 4, 5, 2, 4]
```

| Day | Price | Minimum Price | Current Profit | Maximum Profit |
|----:|------:|--------------:|---------------:|---------------:|
| 0 | 1 | 1 | 0 | 0 |
| 1 | 4 | 1 | 3 | 3 |
| 2 | 5 | 1 | 4 | 4 |
| 3 | 2 | 1 | 1 | 4 |
| 4 | 4 | 1 | 3 | 4 |

Final Answer

```text
4
```

---

## Another Dry Run

### Input

```text
A = [7, 6, 4, 3, 1]
```

| Day | Price | Minimum Price | Current Profit | Maximum Profit |
|----:|------:|--------------:|---------------:|---------------:|
| 0 | 7 | 7 | 0 | 0 |
| 1 | 6 | 6 | -1 | 0 |
| 2 | 4 | 4 | -2 | 0 |
| 3 | 3 | 3 | -1 | 0 |
| 4 | 1 | 1 | -2 | 0 |

No positive profit is obtained.

Final Answer

```text
0
```

---

## Why Does This Work?

The algorithm always keeps track of the **lowest stock price encountered so far**.

For every day:

- Assume the stock is sold on that day.
- The best buying opportunity before that day is the minimum price already seen.

Thus,

```text
Profit = Current Price - Minimum Price
```

By evaluating this for every day, the algorithm considers every valid buy-sell combination without explicitly checking all pairs.

This reduces the time complexity from **O(N²)** to **O(N)**.

---

## Complexity Analysis

### Time Complexity

The array is traversed only once.

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

    public int maxProfit(final int[] A) {

        if (A.length == 0) {
            return 0;
        }

        int min_price = A[0];
        int max_profit = 0;
        int diff = 0;

        for (int i = 0; i < A.length; i++) {

            diff = A[i] - min_price;

            if (A[i] < min_price) {
                min_price = A[i];
            }

            if (diff > max_profit) {
                max_profit = diff;
            }
        }

        return max_profit;
    }
}
```

---

## Key Learning

- To maximize profit, always track the **minimum stock price** seen so far while traversing the array.
- For every day, calculate the profit if the stock is sold on that day.
- Updating the minimum price and maximum profit during a single traversal eliminates the need for nested loops.
- This reduces the time complexity from **O(N²)** to **O(N)** while using only **O(1)** extra space.
- This pattern of maintaining a running minimum or maximum is a common technique in array optimization problems.
