> 1.4.33 Memory requirements on a 32-bit machine. Give the memory requirements
> for Integer, Date, Counter, int[], double[], double[][], String, Node, and Stack
> (linked-list representation) for a 32-bit machine. Assume that references are 4 bytes,
> object overhead is 8 bytes, and padding is to a multiple of 4 bytes.

### Integer

> 12 bytes

- object overhead: 8 bytes
- `int` value: 4 bytes

No padding needed.


### Date

> 20 bytes

- object overhead: 8 bytes
- `int` month: 4 bytes
- `int` day: 4 bytes
- `int` year: 4 bytes

No padding needed.

### Counter

> `32 + pad(12 + 2N)` bytes

For a counter with an `N` length name.

- Counter
    - object overhead: 8 bytes
    - `String` name reference: 4 bytes
    - `int` count: 4 bytes
- `String` name: 16 + `pad(12+2N)`

### `int[]`

> `12+4N` bytes

For an `N` length integer array. Padding not needed since integers occupy 4 bytes, a multiply of 4.

### `double[]`

> `12+8N` bytes

For an `N` length double array.

### double[][]

> `16M + 8MN + 12` bytes

For an `M` by `N` double matrix.

- Root array:
    - array overhead: 12 bytes
    - nested array refs: `4M` bytes
- Nested arrays: `M*(12+8N)` bytes (padding uneeded).

Padding is not needed since all types involved are multiples of 4.

### Node

> `20 + T` bytes

For an item type that occupies `T` bytes.

- object overhead: 8 bytes
- inner class overhead: 4 bytes
- `4+T` for object item types, since we need to hold a reference to it (we can't use a primitive here)
- Node next reference: 4 bytes

### Stack

> `16 + 20N + TN` bytes

For an `N` length stack of items of a type that occupy `T` bytes.

- object overhead: 8 bytes
- `Node` first reference: 4 bytes
- `int` N (used for storing size): 4 bytes

### Array

> `pad(12+NI)` bytes, for primitive item types  
> ... or   
> `pad(12+N*(4+I))`, bytes for object item types  

For an `N` length array, and an item type that consumes that occupies `I` bytes.

- array overhead: 12 bytes
    - object overhead: 8 bytes
    - int length: 4 bytes
- either:
    - `N*I` bytes for primitive types,
    - or `N*(I+4)` bytes for object types (extra 4 bytes are for references)

The total memory consumption is padded up to the closest multiple of four.

#### Examples

- `int[10]` consumes 52 bytes

    12 bytes for array overhead, plus 40 (`10*4`) bytes for the elements.
    Both the array overhead and the memory comsuption of `int` values are multiples of 4, so we will never need padding, no matter the length of the array.

- `SomeObject[12]` consumes 252 bytes:

    Assuming `SomeObject` consumes 16 bytes, we have 12 bytes for array overhead, plus 240 (`12*(4+16)`) bytes for references to `SomeObject` objects and the objects themselves. Again, array overhead, object overhead and object memory consumption already happen to be a multiple of 4, so padding will never be needed.

### String

> `16 + pad(12 + 2N)` bytes

For a string of length N.

- String:
    - object overhead: 8 bytes
    - `int` hash: 4 bytes
    - `char[]` value reference: 4 bytes
- `char[]` value:
    - array overhead: 12 bytes
    - `2*N` bytes, since `char` is a primitive type and references are not needed