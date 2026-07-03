# Given an integer A, you need to find the count of it's factors.

Factor of a number is the number which divides it perfectly leaving no remainder.

Example : 1, 2, 3, 6 are factors of 6

# Approach:
Instead of checking every number from 1 to A, we only iterate up to √A.
Every divisor appears in a pair:
If i divides A, then A / i is also a divisor.
For example:
A = 36

1  ↔ 36
2  ↔ 18
3  ↔ 12
4  ↔ 9
6  ↔ 6
Most divisors contribute two factors.
The only exception occurs when i == A / i, which happens when A is a perfect square. In that case, the divisor is counted only once.

# Algorithm
Initialize fact_count = 0.
Iterate from i = 1 while i * i <= A.
If A % i == 0:
If i == A / i, increment the count by 1.
Otherwise, increment the count by 2.
Return fact_count.

# Dry Run
Input
A = 12
i	Divides?	Factors Found	Count
1	Yes	(1, 12)	2
2	Yes	(2, 6)	4
3	Yes	(3, 4)	6
Final Answer:
6
Perfect Square Example
A = 36
i	Factors	Count
1	(1, 36)	2
2	(2, 18)	4
3	(3, 12)	6
4	(4, 9)	8
6	(6)	9
Notice that 6 × 6 = 36, so 6 is counted only once.
Complexity Analysis
Time Complexity
O(√A)
We only iterate up to the square root of the number.
Space Complexity
O(1)
No extra space is used apart from a few variables.

# Key Learning
Factors always occur in pairs.
It is sufficient to iterate only up to √N.
Perfect squares require special handling to avoid counting the square root twice.
This optimization reduces the time complexity from O(N) to O(√N), making it efficient even for large values of A.
