> 1.4.32 Amortized analysis. Prove that, starting from an empty stack, the number of array
> accesses used by any sequence of M operations in the resizing array implementation
> of Stack is proportional to M.

### Upsizing cost

The cost of upsizing the array to a new size `k` is `2k`:

- new array initialization: `k` array accesses
- copying:
    - `k/2` array accesses to get the values of the old array
    - `k/2` array accesses to copy the values to the new array

This happens because whenever we upsize to `k` there are `k/2` items to copy.

### Amortized upsizing cost

Resizing occurs at any push that takes the stack size to a number `2^p + 1`, for some power `p`.
E.g.: 3, 5, 9, 17, 33, etc.  
At this point, the array is resized to `2^(p+1)`.  
__Therefore, the cost of upsizing is `2*2^(p+1) = 2^(p+2)`__.

Only two events can force us to resize the array again:
- the stack size going over `2^(p+1)`, which would require a minimum of `2^p` pushes.
- the stack size going under `2^(p-1)`, which would require a minimum of `2^(p-1) + 1` pops.

So, the cost of upsizing will be amortized across a minimum of `2^(p-1) + 1` operations.   
__The maximum amortized cost for upsizing is given by `lim x -> infinity 2^(p+2)/(2^(p-1) + 1) = 8`.  
Therefore, the maximum amortized cost for upsizing is constant__.

### Downsizing cost

The cost of downsizing the array to a new size `k` is also `2k`, for the same reasons as for upsizing.

### Amortized downsizing cost

The underlying array size is always a power of two, which we can describe as `2^p`, for some integer `p`.  
Downsizing happens when the stack size becomes `1/4` of the underlying array size.  
__Therefore, we know that downsizing happens when stack size becomes `2^p/4 = 2^(p-2)`__.

Then, the array is downsized to a half of its previous size, this is, `2^p/2 = 2^(p-1)`.  
The cost of this operation is `2 * 2*(p-1) = 2^p`.

At this point, there are only two events that can force us to resize the array:
- the stack size going over the new array size, `2^p/2 = 2^(p-1)`, which would require a minimum of `2^(p-1) + 1` pushes
- the stack size going under or becoming equal to `1/4` of the new array size, this is, `1/4 * 2^(p-1) = 2^(p-3)`, which would require a minimum of `2^(p-3)` pops.

So, the cost of downsizing will be amortized among a minimum of `2^(p-3)` operations.  
__The amortized downsizing cost is given by the expression `2^p/2^(p-3)`, which simplifies down to just `8`, showing that the maximum amortized cost is constant__.

### Maximum amortized cost

I have focused on the maximum amortized cost in order to show that it is constant. But the number of operations that we can amortize over is potentially infinite, also making the amortized cost potentially approach `0`.