package algsex.chapter1.section4;

import java.util.*;
import edu.princeton.cs.algs4.*;
import algsex.chapter1.section4.Exercise38;

// 1.4.39 Improved accuracy for doubling test. Modify DoublingRatio to take a second
// command-line argument that specifies the number of calls to make to timeTrial() for
// each value of N. Run your program for 10, 100, and 1,000 trials and comment on the
// precision of the results.

// Conclusions: none.
// Sometimes for higher number of trials we have lower standard deviation of the running times.
// Sometimes it is the opposite.

// Results:
// Running experiment Fast ThreeSum
// Runs per N: 1
// N, avg. time (ms), avg. ratio, time std.
// 1         1.0     0.0     NaN
// 2         0.0     0.0     NaN
// 4         0.0     0.0     NaN
// 8         0.0     0.0     NaN
// 16        0.0     0.0     NaN
// 32        0.0     0.0     NaN
// 64        0.0     0.0     NaN
// 128       0.0     0.0     NaN
// 256       4.0     0.0     NaN
// 512       4.0     1.0     NaN
// 1024     12.0     3.0     NaN
// 2048     40.0     3.3     NaN
// 4096    167.0     4.2     NaN
// 8192    701.0     4.2     NaN
// 16384   3174.0    4.5     NaN
//
// Running experiment Fast ThreeSum
// Runs per N: 10
// N, avg. time (ms), avg. ratio, time std.
// 1         0.0     0.0     0.0
// 2         0.0     0.0     0.0
// 4         0.1     0.0     0.3
// 8         0.1     1.0     0.3
// 16        0.1     1.0     0.3
// 32        0.0     0.0     0.0
// 64        0.0     0.0     0.0
// 128       0.2     0.0     0.4
// 256       0.9     4.5     0.6
// 512       2.7     3.0     0.8
// 1024     10.2     3.8     1.9
// 2048     35.4     3.5     1.8
// 4096    159.6     4.5     4.5
// 8192    670.3     4.2     9.7
// 16384   3026.6    4.5    91.1
//
// Running experiment Fast ThreeSum
// Runs per N: 1000
// N, avg. time (ms), avg. ratio, time std.
// 1         0.0     0.0     0.1
// 2         0.0     0.5     0.1
// 4         0.0     0.7     0.0
// 8         0.0     0.5     0.0
// 16        0.0     2.0     0.0
// 32        0.0     4.5     0.1
// 64        0.0     4.4     0.2
// 128       0.2     3.8     0.4
// 256       0.6     3.8     0.5
// 512       2.1     3.7     0.5
// 1024      8.8     4.1     1.1
// 2048     36.0     4.1     3.1
// 4096    158.3     4.4     8.9
// 8192    670.7     4.2    21.3
// 16384   2841.5    4.2    39.4
public class Exercise39 {
    public static void main(String[] args) {
        Exercise38.primeTestDataGenerator();

        new Exercise38.FastThreeSumExperiment().run(1);
        new Exercise38.FastThreeSumExperiment().run(10);
        new Exercise38.FastThreeSumExperiment().run(1000);
    }
}