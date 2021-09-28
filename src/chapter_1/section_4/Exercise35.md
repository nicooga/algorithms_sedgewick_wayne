> 1.4.35 Time costs for pushdown stacks. Justify the entries in the table below, which
> shows typical time costs for various pushdown stack implementations, using a cost
> model that counts both data references (references to data pushed onto the stack, either
> an array reference or a reference to an object’s instance variable) and objects created. As-
> sume that the Integer objects are not cached (so they must be created for each push).

<table>
  <tr>
    <td rowspan="2">data structure</td>
    <td rowspan="2">item type</td>
    <td colspan="2">cost to push <code>N</code> int values</td>
  </tr>

  <tr>
    <th>data references</th>
    <th>objects created</th>
  </tr>

  <tr>
    <td rowspan="2"><em>linked list</em></td>
    <td><code>int</code></td>
    <td><em>2N</em></td>
    <td><em>N</em></td>
  </tr>

  <tr>
    <td><code>Integer</code></td>
    <td><em>3N</em></td>
    <td><em>2N</em></td>
  </tr>

  <tr>
    <td rowspan="2"><em>resizing array</em></td>
    <td><code>int</code></td>
    <td><em>~5N</em></td>
    <td><em>lg N</em></td>
  </tr>

  <tr>
    <td><code>Integer</code></td>
    <td><em>~5N</em></td>
    <td><em>~N</em></td>
  </tr>
</table>

<strong>Time costs for pushdown stacks (various implementations)</strong>

### _linked list_ / `int`

Each node has a reference to the next one, except for the last, summing `N-1` references. Then, the list has `1` reference to the first node. Also, each node has a reference to its value, summing another `N` references. This justifies `2N` data references.

Then, we have to create `N` nodes for each item, which justifies `N` objects created.

### _linked list_ / `Integer`

The same reasoning above applies here, except that we have `N` more references and objects created. We don't reference the `int` values directly but have to create an `Integer` wrapper object, which itself references the primitive values.
This generates the overhead of exactly one object and one reference per item.

### _resizing array_ / `int`

Due to the way resizing works, at times the underlying array will be 1/4 full, meaning that we will have up to `4N` references. A new array created during resizing would justify `N` more references for a total of `5N` references.

The underlying array size is always a power of two, and resizing happens when stack size exceedes the array size. Then, the amount of resizes needed for `N` pushes is relative to `lg N`, which means creating an equal amount of arrays.

### _resizing array_ / `Integer`

Save as above, but since we are storing storing references to wrapper `Integer` objects, we have `N` more references.
Disclaimer: This does not match the values in the table. For this to be correct, the table should either have `~4N` data references for the `int` variant, or `~6N` for the `Integer` variant.

Again, since we store `Integers`, this means having `N` objects created, which quickly offsets the `ln N` arrays created by the resizing operation.