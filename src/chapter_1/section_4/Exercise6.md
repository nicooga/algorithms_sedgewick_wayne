1.4.6 Give the order of growth (as a function of N ) of the running times of each of the following code fragments:  

a.
~~~java
int sum = 0;

for (int n = N; n > 0; n /= 2)
    for(int i = 0; i < n; i++)
        sum++;
~~~

b.
~~~java
int sum = 0;

for (int i = 1; i < N; i *= 2)
    for (int j = 0; j < i; j++)
        sum++;
~~~

c.
~~~java
int sum = 0;

for (int i = 1; i < N; i *= 2)
    for (int j = 0; j < N; j++)
         sum++;
~~~

Answers:

a.

Short answer: O(N)  
Long answer:

Given the frequency of execution of the main loop can be expressed by:


![expression1](./Exercise6/expression1.png)

b.  
c.  