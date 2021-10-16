> 1.5.6 Repeat Exercise 1.5.5 for weighted quick-union.

We have `N = 10^9` sites and `M = 10^6` pairs.

The inner loop of `union(p, q)` is actually within the calls to `find(p)`, and requires 10 machine instructions.
This inner loop is executed from 2 to `2*lg(N)` times, since we have two calls to `find(p)`.

This is, the total number of required instructions is `10*M*2*lg(N) = 10 * 10^6 * 2 * lg(10^9) =~ 597947057` instructions, or roughly 6 million instructions.

Then, given our computer can process `10^9` instructions per second, this is `10^9 instructions/second`,
the required time to run the program is `~ 6 * 10^6 / 10^9 ins/s =~ 0.6s`, or  roughly `7*10^-6` days.