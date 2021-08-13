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

Long answer: The frequency of execution of the main loop can be expressed by:

![expression1](./Exercise6/expression1.png)

... which we can simplify by removing flooring, ceiling and superfluous constants:

![expression2](./Exercise6/expression2.png)

Also:

![expression3](./Exercise6/expression3.png)  
![expression4](./Exercise6/expression4.png)  
![expression5](./Exercise6/expression5.png)  
![expression6](./Exercise6/expression6.png)  

So we can further simplify the first expression to:

![expression7](./Exercise6/expression7.png)  
![expression8](./Exercise6/expression8.png)  
![expression9](./Exercise6/expression9.png)  
![expression10](./Exercise6/expression10.png)  


... which finally means that the order of growth of this code is just `O(N)`.

b.  
c.  