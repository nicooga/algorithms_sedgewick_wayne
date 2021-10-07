> 1.4.41 Running times. Estimate the amount of time it would take to run TwoSumFast,
> TwoSum, ThreeSumFast and ThreeSum on your computer to solve the problems for a file
> of 1 million numbers. Use DoublingRatio to do so.

### Generalizing the approach

Let's consider the following test results:

    ...
    1024      0.4     2.0     0.5     1.3
    2048      0.9     2.3     0.3     0.4
    4096      3.9     4.3     0.7     0.2
    8192     15.2     3.9     1.4     0.1
    16384    56.6     3.7     3.7     0.1
    32768   219.6     3.9     7.6     0.0
    65536   875.0     4.0    21.6     0.0
    131072  3599.8    4.1   199.4     0.1

We can assume a doubling ratio of 3.8 for a relatevely accuarate approximation.
It makes sense to choose this value since the CV (coefficient of variation) for these iterations show that the results have stabilized after N = 4096. Taking the average of the ratios after N = 4096 (4.3, 3.9, 3.7, 3.9, 4.0, 4.1) and rounding to 1 decimal place gives 4.

Taking the running time of ~3600ms for 2^17 integers as reference, we can predict the following running times:

N    | T(N)
-----|-----------------
2^15 | 3600 * 1/4 * 1/4
2^16 | 3600 * 1/4
2^17 | 3600 * 4
2^18 | 3600 * 4 * 4
2^19 | 3600 * 4 * 4 * 4

The pattern arises. Given an reference running time `tr` for a number `N = 2^p`, and a doubling ratio `R`, `T(N)` is defined as:

    T(N) = tr * R^(lg(N) - p)

### Predicted running times

In the case of the test shown above, we have `p = 17`, `tr = 3600` and `R = 4`.
`T(N)` is the defined as `T(N) = 3600 * 4^(lg(N) - 17)`.
Using this formula we can predict the running time for `10^6`:

`T(10^6) = 3600 * 4^(lg(10^6) - 17) =~ 209547.5 ms

This is equal to roughly 3.5 minutes.

I will use the same approach to predict the running times for the remaining algoritms.

Alg.         | N = 2^p | tr (ms) | R   | Predicted running time for N = 10^6
-------------|---------|---------|-----|--------------------------------
TwoSum       | 2^17    | 3600    | 4   | 3.5 minutes
TwoSumFast   | 2^19    | 50      | 1.8 | 86.5 ms.
ThreeSum     | 2^13    | 32154   | 7.2 | 326 days
ThreeSumFast | 2^16    | 125558  | 4.4 | 12 hs.

<!-- T(N) = tr * R^(lg(N) - p) -->
<!-- T(N) = 125558 * 4.4^(lg(N) - 16) -->

<details>
    <summary>Based on the following test results:</summary>

    Running experiment "TwoSum"
    Runs per N: 10
    N, avg. time (ms), avg. ratio, time std., time CV
    1         0.0     0.0     0.0     NaN
    2         0.0     0.0     0.0     NaN
    4         0.0     0.0     0.0     NaN
    8         0.0     0.0     0.0     NaN
    16        0.1     0.0     0.3     3.2
    32        0.0     0.0     0.0     NaN
    64        0.0     0.0     0.0     NaN
    128       0.1     0.0     0.3     3.2
    256       0.1     1.0     0.3     3.2
    512       0.2     2.0     0.4     2.1
    1024      0.4     2.0     0.5     1.3
    2048      0.9     2.3     0.3     0.4
    4096      3.9     4.3     0.7     0.2
    8192     15.2     3.9     1.4     0.1
    16384    56.6     3.7     3.7     0.1
    32768   219.6     3.9     7.6     0.0
    65536   875.0     4.0    21.6     0.0
    131072  3599.8    4.1   199.4     0.1

    Running experiment "TwoSumFast"
    Runs per N: 20
    N, avg. time (ms), avg. ratio, time std., time CV
    1         0.0     0.0     0.0     NaN
    2         0.1     0.0     0.2     4.5
    4         0.1     1.0     0.2     4.5
    8         0.0     0.0     0.0     NaN
    16        0.1     0.0     0.2     4.5
    32        0.1     1.0     0.2     4.5
    64        0.1     1.0     0.2     4.5
    128       0.1     2.0     0.3     3.1
    256       0.1     0.5     0.2     4.5
    512       0.1     2.0     0.3     3.1
    1024      0.2     1.5     0.4     2.4
    2048      0.4     2.3     0.5     1.4
    4096      0.8     2.3     0.4     0.5
    8192      1.4     1.8     0.5     0.4
    16384     3.4     2.4     0.8     0.2
    32768     4.7     1.4     0.8     0.2
    65536     7.3     1.6     0.5     0.1
    131072   15.0     2.0     2.5     0.2
    262144   28.7     1.9     5.3     0.2
    524288   50.0     1.7     1.4     0.0

    Running experiment "ThreeSum"
    Runs per N: 10
    N, avg. time (ms), avg. ratio, time std., time CV
    1         0.0     0.0     0.0     NaN
    2         0.1     0.0     0.3     3.2
    4         0.0     0.0     0.0     NaN
    8         0.0     0.0     0.0     NaN
    16        0.0     0.0     0.0     NaN
    32        0.1     0.0     0.3     3.2
    64        0.2     2.0     0.4     2.1
    128       1.1     5.5     1.1     1.0
    256       1.7     1.5     1.3     0.7
    512      10.2     6.0     1.5     0.1
    1024     70.4     6.9     4.2     0.1
    2048    515.1     7.3    13.6     0.0
    4096    3678.8    7.1   145.5     0.0
    8192    32154.7   8.7   603.3     0.0

    Running experiment "ThreeSumFast"
    Runs per N: 4
    N, avg. time (ms), avg. ratio, time std., time CV
    1         0.0     0.0     0.0     NaN
    2         0.3     0.0     0.5     2.0
    4         0.0     0.0     0.0     NaN
    8         0.0     0.0     0.0     NaN
    16        0.0     0.0     0.0     NaN
    32        0.3     0.0     0.5     2.0
    64        0.3     1.0     0.5     2.0
    128       1.0     4.0     0.0     0.0
    256       1.8     1.8     1.0     0.5
    512       4.0     2.3     0.0     0.0
    1024     18.0     4.5     1.8     0.1
    2048     76.0     4.2     2.6     0.0
    4096    366.3     4.8     8.4     0.0
    8192    1561.0    4.3    20.2     0.0
    16384   7051.8    4.5   247.6     0.0
    32768   29055.0   4.1   446.1     0.0
    65536   125558.0  4.3   1487.9    0.0
</details>