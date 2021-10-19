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

The base case happens at the start when we only have single node trees.

~~~
0
|
1
~~~

The only way to grow in depth is to union two single nodes.
By definition, we can say that the cost of achieving a tree of depth 1 is then 2 nodes:

```
C(1) = 2
```

Then, to grow up to depth 2 we need another tree that is equally big or bigger than the current.
Unioning more single nodes will not deepen our tree.

The options are either unioning with another pair, or unioning with a tree that looks like this:

```
  0
 /|\
1 2 3
```

It has depth 1 too, but having more children implies that N has exceeded `2^d`, where d is 2, the depth we want to achieve.
Most importantly, this shows that we need at least the same amount of nodes to grow in depth.

~~~
C(2) = 4
~~~

This rule extends recursively for all depths.

Suppose we achieved a depth 2 tree, minimizing the amount of nodes:

```
0
|\
1 2
  |\
  3 4
```

We know the minimum cost for achieving this depth is 4, so we have minimized its size.
Now again, in order to grow higher we need an equallly sized or larger tree.
Its minimum cost is the same as for the current.

The other tree could have had more than 4 nodes, but again this would mean that N exceeded 8.
This is equal to `2^d`, where d is 3, the next depth that we want to achieve.

Hopefully, this illustrates clearly how the minimum cost in nodes is defined:

```
C(1) = 2
C(d) = 2 * C(d-1)
C(d) = 2^d
```

For us to reach a max depth `d` then, `N` must be greater than or equal to `C(d)`.
Or, in other words, N must exceed 2^d:

```
N >= C(d) = 2^d
```

... from which we can derive:

```
d <= lg(N)
```