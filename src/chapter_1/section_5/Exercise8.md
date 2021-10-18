> 1.5.8 Give a counterexample that shows why this intuitive implementation of union()
> for quick-find is not correct:
>
> ~~~java
>     public void union(int p, int q)
>     {
>         if (connected(p, q)) return;
>         // Rename p’s component to q’s name.
>         for (int i = 0; i < id.length; i++)
>             if (id[i] == id[p]) id[i] = id[q];
>         count--
>     }
> ~~~

Given we have `N = 3`, the initial state of id is going to be: `[0, 1, 2]`.
Just two calls to `union(p, q)` show why this code is not correct:

- `union(0, 2)`:
    We have `p = 0` and `q = 2`, and all sites are withing their own components.
    The only id where `id[i] == id[p]` is `id[p]`, so we change it.
    Now the array is: `[2, 1, 2]`

- `union(0, 1)`:
    We have `p = 0` and `q = 1`, and `id[p] = 2` and `id[q] = 1`.
    The first element where `id[i] == id[p]` is `id[0]`, so we change it. The id array is now: `[1, 1, 2]`.
    But here is the problem. `id[p]` has now changed, so `id[p] = 1`.
    So the next element is going to match and we are going to change it to `1`, which has no effect since it already is set to that value.

After processing these 2 pairs the 3 sites should be within the same component but they are not.
Using a temporary variable to remember the value of `id[p]` before iterating fixes this code.