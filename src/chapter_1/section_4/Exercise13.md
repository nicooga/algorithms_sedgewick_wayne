1.4.13 Using the assumptions developed in the text, give the amount of memory need-
ed to represent an object of each of the following types:

a. Accumulator: `32 bytes`
  - object overhead (16 bytes)
  - double total (8 bytes)
  - int N (4 bytes)
  - padding (4 bytes)

b. Transaction: `(104 + padded(24 + 2W)) bytes `, where `W` is the length of `who` string. 40 bytes + String memory usage + Date memory usage
  - object overhead (16 bytes)
  - ref String who (8 bytes)
  - ref Date when (8 bytes)
  - double amount (8 bytes)

c. FixedCapacityStackOfStrings with capacity C and N entries:
`32 + padded(24 + 32C + sum(i=1, C, pad(24 + 2Si))) bytes`, where `Si` is the length of the string a index `i`. `N` does not affect the final memory usage, as Java reserves memory for arrays even if they are not filled. 
- object overhead (16 bytes)
- ref String[] a (8 bytes)
- int N (4 bytes)
- padding (4 bytes)

d. Point2D: `32 bytes`
- object overhead (16 bytes)
- double x (8 bytes)
- double y (8 bytes)
  
e. Interval1D: `32 bytes`
- object overhead (16 bytes)
- double left (8 bytes)
- double right (8 bytes)

f. Interval2D: `96 bytes`. 32 bytes + 2 times Interval1D memory usage
- object overhead (16 bytes)
- ref Interval1D x (8 bytes)
- ref Interval1D y (8 bytes)

g. Double: `24 bytes`
  - object overhead (16 bytes)
  - double value (8 bytes)

### Other types

Array\<T>: for primitives: `padded(24 + T*N) bytes`; for objects: `padded(24 + N(8 + T)) bytes`
- array overhead (24 bytes)
- values
  - when T is an primitive: N*T. N times T memory usage.
  - when T is an object: (8 + T)N. 8N (for references) plus T memory usage times N.

String: `32 + padded(24 + 2N) bytes`: 32 bytes + char[] memory usage
- object overhead (16 bytes)
- ref char[] values (8 bytes)
- int hash (4 bytes)
- padding (4 bytes)

Date: 32 bytes
- object overhead (16 bytes)
- int day (4 bytes)
- int month (4 bytes)
- int year (4 bytes)
- padding (4 bytes)