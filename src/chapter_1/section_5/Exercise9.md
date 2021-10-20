> 1.5.9 Draw the tree corresponding to the id[] array depicted at
> right. Can this be the result of running weighted quick-union?
> Explain why this is impossible or give a sequence of operations
> that results in this array.
> 
> i     | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
> ------|---|---|---|---|---|---|---|---|---|---|
> id[i] | 1 | 1 | 3 | 1 | 5 | 6 | 1 | 3 | 4 | 5 |

~~~
1 ----- 0
 \\
  \\--- 3 --- 7
   \     \--- 2
    \
     \- 6 --- 5 --- 4 --- 8 
                     \--- 9
~~~

Here the maximum tree depth is `4`. This exeedes `lg(N)` which cannot happen as explained before by Sedgewick.

Here's another way to explain why the max depth of trees cannot go over `lg(N)`.

### Important facts

1. We start with `N` zero depth trees.
2. New trees can only appear by merging.
3. The first tree with some depth `d` must form from trees that are less deep than `d`.
4. The depth of a new tree can be expressed by this _*depth formula*_:
   ~~~
   d = max(s + 1, l)

   ... given

   size(t1) <= size(t2)
   s = depth(t1)
   l = depth(t2)
   ~~~

### Defining the minimum cost for achieving depth `d`

**Fact 3** does not imply we can't get a tree of depth `d` by merging deeper trees.
But, the minimum cost in nodes for achieving a tree of depth `d` is the same as for the *first* of such trees, so I'll focus on that.
 
Now, lets start defining a function `C` that returns the **minimum cost** in nodes required to achieve some depth d:
  ~~~
  C(d) = ?
  ~~~

We can declare `C(1) = 2` by definition: the only way to reach depth 1 is by merging two single nodes (in other words, two zero-depth trees).

Then, for every other depth `d` we will need to derive the cost from the _**depth formula**_:

~~~
l <= r
d = max(l + 1, r)
~~~

We know that `l` and `r` must be smaller than `d` (fact 3).
Because of this, the only way for us to reach depth `d`, is for the smaller or equal tree `l` to at least have depth `d - 1`. The cost in nodes for such a tree is `C(d-1)`. We have this so far:

```
C(d) = C(d-1) + ?
```

Then, the other tree must be equally big or greater as the one with depth `l`.
So no matter what `C(d-1)` is, we need at least two of these to grow. Therefore:

```
C(d) = C(d-1) + C(d-1)
     = 2 * C(d-1)
```

Since `C(1) = 2`, we can get rid of this recursive definition and just define `C` as follows:

~~~
C(d) = 2^d
~~~

### Conclusion

For there to be a tree of depth `d`, `N` must be greater than or equal to `C(d)`:

~~~
N >= C(d) = 2^d
~~~

From here we can derive the our final conclusion:

~~~
d <= lg(N)
~~~

... where `d` is the maximum depth achievable given `N` sites.