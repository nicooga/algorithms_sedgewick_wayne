> 1.5.14 Weighted quick-union by height. Develop a UF implementation that uses the
> same basic strategy as weighted quick-union but keeps track of tree height and always
> links the shorter tree to the taller one. Prove a logarithmic upper bound on the height
> of the trees for N sites with your algorithm.

This proof derives from the facts in [Ex. 1.5.9](./Exercise9.md). We need only make one change to the _*depth formula*_, and it is to define that instead of `size(t1) <= (t2)`, we just need `s <= l`:

   ~~~
   d = max(s + 1, l)

   ... given

   s = depth(t1)
   l = depth(t2)
   s <= l
   ~~~

`C(1) = 2` is still true. Then, for any other value of `d`, we need to merge 2 trees with depth `d-1`. Merging trees that are any shallower will not result in a greater depth.

This happens because the algorithm merges the shallower tree into the deeper one.
Because the new depth is going to be `max(s + 1, l)`, and both `s` and `l` are smaller than `d` (fact 3), the only way to get a greater new depth is for `s` and `l` to be equal to `d-1`.

Then again, the cost in nodes for reaching depth `d` is the same as for building two trees of depth `d-1`:

~~~
C(d) = 2 * C(d-1)
~~~

The same conclusion follows as for the union-by-size algorithm: `d <= lg(N)`.