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

The wighted quick union algorithm works so that when we have to merge two trees, we merge the smaller one in to the larger one, if the trees are different sizes.
Otherwise, we will default to merging the right or the left tree into the remaining one.

Merging a smaller tree into a larger tree gives a tree that has the depth of the larger one. **The only way we can grow in depth is when we merge two trees that have the same depth.** This requirement is recursive. For us to have two trees of depth `x`, we need two trees of depth `x-1`, and for each of these we need another two trees of depth `x-2`, and so on.

Then, we can define a function `C(d)` that returns the minimum cost in nodes to form a tree of depth `d`:

```
C(d) = 2 * C(d-1)
C(0) = 1
C(1) = 2
C(2) = 2 * 2
C(3) = 2 * 2 * 2
...
```

then simply:

```
C(d) = 2^d
```

Therefore, for us to have a tree of depth `d`, `N` must be greater than or equal to `2^d`:

```
N >= 2^d
```

From this we can derive:

```
d <= lg(N)
```

.... which finally shows that the depth of any single tree can't go over `lg(N)`.