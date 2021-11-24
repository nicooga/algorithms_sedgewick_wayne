[![CI](https://github.com/nicooga/algorithms_sedgewick_wayne/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/nicooga/algorithms_sedgewick_wayne/actions/workflows/main.yml)

# Solved exercises for "Algorithms" by R. Sedgewick

This is a collection of solved exercises for the book "Algorithms" by Robert Sedgewick.
Most of them come in the form of executable Java code, and some as Markdown docs.

I wrote and tested the exercises using the following Java version:

> $ java -version  
> openjdk version "16.0.2" 2021-07-20  
> OpenJDK Runtime Environment (build 16.0.2+7-67)  
> OpenJDK 64-Bit Server VM (build 16.0.2+7-67, mixed mode, sharing)  

In order to aid me in working with the book, I created a series of scripts.

You can run a single exercise by running `./script/run-ex <chapter> <section> <exercise> [arg1] [arg2] [...]`. For instance:

![Running an exercise](./README/run_exercise_output.png)

**Disclaimer:** I learnt Java as I advanced through the exercises. I'm not a seasoned Java developer, and that reflects in my code style and the way I setup this project.

## (WIP) This project is tested

In order to test that these exercise solutions compile and work, I've set up some primitive automated tests using Java assertions, Docker and Github actions.

These tests were implemented by having a class called `algsex.MainTest` whose main method calls the main method of all the exercises, sometimes simulating the needed input for these to work. 

You can run them locally like this:

```bash
$ run algsex.MainTest
```

This testing is not the best, but should give us some confidence that the code in this project works.