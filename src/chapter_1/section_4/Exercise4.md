1.4.4 Develop a table like the one on page 181 for TwoSum.

```java
public class TwoSum {
    public static int count(int[] a) {
        int count = 0;
    
        for (int i = 0; i < a.length; i++) // A
            for (int j = i + 1; j < a.length; j++) // B
                if (a[i] + a[j] == 0) // C
                    count++; // D

        return count;
    }
}
```

| Statment block | Time in seconds| Frequency            | Total time         |
|----------------|----------------|----------------------|--------------------|
| D              | t0             | x (depends on input) | x * t0             |
| C              | t1             | (N^2/2 - N/2)        | t1 * (N^2/2 - N/2) |
| B              | t2             | N                    | N * t2             |
| A              | t3             | 1                    | t3                 |

**Given:** x >= 0, x <= n^2/2 - n/2  
**Grand total:** x * t0 + t1 * (N^2/2 - N/2) + N * t2 + t3  
**Tilde aproximation:** t1 * N^2 (assuming x is small)  
**Worst case performance:** (N^2/2 - N/2) * (t0 + t) + n * t2 + t3  
