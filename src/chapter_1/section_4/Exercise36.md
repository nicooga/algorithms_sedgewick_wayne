> 1.4.36 Space usage for pushdown stacks. Justify the entries in the table below, which
> shows typical space usage for various pushdown stack implementations. Use a static
> nested class for linked-list nodes to avoid the non-static nested class overhead. Assume
> that the Integer objects are not cached (so they must be created for each push).

<table>
    <th>data structure</th>
    <th>item type</th>
    <th>space usage for <em>N</em> int values (bytes)</th>
  </tr>

  <tr>
    <td rowspan="2"><em>linked list</em></td>
    <td><code>int</code></td>
    <td>~32 <em>N</em></td>
  </tr>

  <tr>
    <td><code>Integer</code></td>
    <td>~56 <em>N</em></td>
  </tr>

  <tr>
    <td rowspan="2"><em>resizing array</em></td>
    <td><code>int</code></td>
    <td>between<br/>~4 <em>N</em> and ~16 <em>N</em></td>
  </tr>

  <tr>
    <td><code>Integer</code></td>
    <td>between<br/>~32 <em>N</em> and ~56 <em>N</em></td>
  </tr>
</table>

__Space usage in pushdown stacks (various implementations)__

### _linked list_ / `int`

`Node` objects in a 64-bit architecture machine justfify 32 bytes of memory usage per item (32 _N_):

- object overhead: 16 bytes
- `int` item: 4 bytes
- `Node` next: 8 bytes
- padding: 4 bytes

### _linked list_ / `Integer`

The memory consumption of `Node` objects remains the same, because the difference of `4 + bytes` of comsuption between an `Integer` reference and `int` replaces the 4 bytes of padding.

Then, for each item we need an `Integer` object, which generates an extra 24 bytes of memory consumption, which explains the grand total of `(32 + 24)N bytes = 56N bytes`.


Node:
  - object overhead: 16 bytes
  - `Integer` item reference: 8 bytes
  - `Node` next: 8 bytes

Integer:
  - object overhead: 16 bytes
  - `int` intValue: 4 bytes
  - padding: 4 bytes

### _resizing array_ / `int`

An array of N `int` values consumes `~4N` bytes. Because the internal array can ocasionally be 1/4 full and an empty array still takes space as if it was full, the memory consuption can go up to `4 * 4N = 16N` bytes.

### _resizing array_ / `Integer`

Similar as before, but the array will store references, consuming up to `4 * 8 bytes = 32 bytes`.

A partially full array of references will take space up as if was full, but that does not apply to the `Integer` objects being referenced.  This explains a minimum consumption of `N * 8 bytes` for references, plus `N * 24 bytes` for `Integer` objects, for a minimim consuption of `32 bytes`.

Then, when the array is 1/4 full it will still take `4 * 8 bytes = 32 bytes` of space, but we will need the same `N * 24 bytes` for `Integer` objects. This justifies a maximum consuption of `~57N` bytes.