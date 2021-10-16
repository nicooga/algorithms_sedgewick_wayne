> 1.5.5 Estimate the minimum amount of time (in days) that would be required for
> quick-find to solve a dynamic connectivity problem with 10^9 sites and 10^6 input pairs,
> on a computer capable of executing 10^9 instructions per second. Assume that each itera-
> tion of the inner for loop requires 10 machine instructions.

We have `N = 10^9` sites and `M = 10^6` pairs.

The inner loop of `union(p, q)` requires 10 machine instructions, and is executed once for each pair and for each site.
This is, the total number of required instructions is `10*M*N = 10 * 10^6 * 10^9 = 10^16` instructions.

Then, given our computer can process `10^9` instructions per second, this is `10^9 instructions/second`,
the total time required to run the program is `10^16 ins. / (10^9 ins./second)`, which equals to `10^7 seconds`.
This is equal to a little more than 115 days.