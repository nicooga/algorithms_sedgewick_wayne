> 1.4.43 Resizing arrays versus linked lists. Run experiments to validate the hypothesis
> that resizing arrays are faster than linked lists for stacks (see Exercise 1.4.35 and Exer-
> cise 1.4.36). Do so by developing a version of DoublingRatio that computes the ratio

Experiments show that resizing array running times are consistently shorter for all values of N.

<details>
    <summary>Based on the following test results:</summary>

~~~
Picked up JAVA_TOOL_OPTIONS: -Xmx22g
runs per N: 10
max N: 2147483647
forced garbage collection: false
Tests passed
Running experiment "Resizing array"
Runs per N: 10
N, avg. time (ms), avg. ratio, time std., time CV
1                 0.0     0.0    0.0     NaN
2                 0.0     0.0    0.0     NaN
4                 0.0     0.0    0.0     NaN
8                 0.1     0.0    0.3     3.2
16                0.1     1.0    0.3     3.2
32                0.0     0.0    0.0     NaN
64                0.1     0.0    0.3     3.2
128               0.1     1.0    0.3     3.2
256               0.1     1.0    0.3     3.2
512               0.1     1.0    0.3     3.2
1024              0.0     0.0    0.0     NaN
2048              0.1     0.0    0.3     3.2
4096              0.2     2.0    0.4     2.1
8192              0.3     1.5    0.5     1.6
16384             0.4     1.3    0.5     1.3
32768             0.6     1.5    1.3     2.1
65536             0.8     1.3    1.2     1.5
131072            2.5     3.1    3.5     1.4
262144            3.0     1.2    1.1     0.4
524288            6.7     2.2    6.2     0.9
1048576          20.0     3.0   29.3     1.5
2097152          57.0     2.9   34.6     0.6
4194304         164.4     2.9  123.1     0.7
8388608         343.0     2.1  246.7     0.7
16777216        673.3     2.0  344.9     0.5
33554432        1454.7    2.2  441.7     0.3
67108864        2759.1    1.9  492.0     0.2
134217728       6451.1    2.3  2759.0    0.4

Picked up JAVA_TOOL_OPTIONS: -Xmx22g
runs per N: 10
max N: 2147483647
forced garbage collection: false
Tests passed
Running experiment "Linked list"
Runs per N: 10
N, avg. time (ms), avg. ratio, time std., time CV
1                 0.0     0.0     0.0     NaN
2                 0.0     0.0     0.0     NaN
4                 0.0     0.0     0.0     NaN
8                 0.0     0.0     0.0     NaN
16                0.0     0.0     0.0     NaN
32                0.0     0.0     0.0     NaN
64                0.0     0.0     0.0     NaN
128               0.1     0.0     0.3     3.2
256               0.0     0.0     0.0     NaN
512               0.1     0.0     0.3     3.2
1024              0.1     1.0     0.3     3.2
2048              0.1     1.0     0.3     3.2
4096              0.2     2.0     0.4     2.1
8192              0.2     1.0     0.4     2.1
16384             0.8     4.0     1.2     1.5
32768             0.5     0.6     1.3     2.5
65536             0.9     1.8     0.6     0.6
131072            2.1     2.3     3.5     1.7
262144            4.9     2.3     4.7     0.9
524288           16.0     3.3    32.0     2.0
1048576          82.8     5.2    80.9     1.0
2097152          94.8     1.1   112.8     1.2
4194304         282.4     3.0   309.1     1.1
8388608         727.5     2.6   739.6     1.0
16777216        2236.1    3.1   1392.3    0.6
33554432        5888.0    2.6   2416.1    0.4
67108864        16329.8   2.8   5650.7    0.3
134217728       36317.9   2.2   9829.8    0.3
~~~
</details>