> 1.4.42 Problem sizes. Estimate the size of the largest value of P for which you can run
> TwoSumFast, TwoSum, ThreeSumFast, and ThreeSum on your computer to solve the
> problems for a file of 2P thousand numbers. Use DoublingRatio to do so.

There are two main resources that we need to thing about: time and memory.

### Memory

All these algorithms have a memory consumption relative to the cost of holding an N length `int` array in memory. This is, `~4N` bytes.

The following inequality gives an upper and very rough limit on the size of `N = 2^p` for the execution of these algorithms, assuming 20GB of free memory:

`4(2^p) <= 20 * 10^9`

Solving for `p` gives us `p <= lg(5*10^9) -> p <~ 32`.

Depending on the algorithm and the available memory and the maximum time that we allow the program to run, the upper limit may be given by time or memory.

### Time

We can use the formula `T(N) = tr * R^(lg(N) - rp)` to derive another inequation for the time. For this pupose we need to set a maximum time `M` that we allow the program to ru for.

Then, let's rewrite `T(2^p)` in terms of `p` to get an inequation that makes the running time less than or equal to `M`:

    T(2^p) = rt * R^(lg(2^p) - rp) = rt * R^(p - rp)
    rt * R^(p - rp) <= M
    R^(p - rp) <= M/rt
    log(r, R^(p-rp)) <= log(R, M/rt)
    p - rp <= log(R, M/rt)
    p <= log(R, M/rt) + rp

So we finally get the general formula `p <= log(R, M/rt) + rp`.
We can later replace here the values for doubling ratio (`R`), reference time (`rt`) and reference power `rp` to get final answers:

Algorithm    | N = 2^rp | rt (ms) | R   | Upper bound for P
-------------|----------|---------|-----|-------------------
TwoSum       | 2^17     | 3600    | 4   | ~23
TwoSumFast   | 2^19     | 50      | 1.8 | ~40
ThreeSum     | 2^13     | 32154   | 7.2 | ~16
ThreeSumFast | 2^16     | 125558  | 4.4 | ~19

... given `M = 4 * 60 * 60 * 1000 = 14.4 * 10^6` (4 hours to milliseconds).

In my particular case, having chosen 4 hours for `M` and 20GB as my available memory, the upper limit for `p` is always going to be given by `M` and not the memory.

<details>
    <summary>Based on the following test results from previous exercise:</summary>

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