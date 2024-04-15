# User Guide of MathGenius

## Table of Contents
1. [Introduction](#introduction)
2. [Get Started](#get-started)
3. [Features and Commands](#features-and-commands)
   1. [Show Help Instructions: `help`](#show-help-instructions-help)
   2. [Generate Problem Set: `generate`](#generate-problem-set-generate)
   3. [Autosaving/Loading Past Attempts](#autosavingloading-past-attempts)
   4. [View Past Records: `records`](#view-past-records-records)
   5. [Submit Answer](#submit-answer-enterreturn)
   6. [Judge Answer](#judge-answer)
   7. [Create DIY Problem Sets: `DIY`](#create-diy-problem-sets-diy)
   8. [Retry Previous Problem Sets: `retry`](#retry-previous-problem-sets-retry)
   9. [Exit: `exit`](#exit-exit)
   10. [Command Summary](#command-summary)
4. [FAQ](#faq)
	1. [Interaction Guide](#interaction-guide)
5. [Final Words](#final-words)

## Introduction

**MathGenius** is a user-friendly application that provides a platform to enhance your Math equation-solving abilities. 

Whether you're a student learning calculation, a math enthusiast looking to sharpen your skills, or a teacher preparing for exams/lessons, this application is here to support you.

## Get Started:

To start using **MathGenius**, run the following command: 
```
java mathGenius.java
```

## Features and Commands:

### Show help instructions: `help`

For a comprehensive list of all possible commands and their usage, run the `help` command: It lists out the **command you can use** and the **standard input format** (e.g., input should be in fraction or in decimal form).

**Format:** `help COMMAND_NAME`
- The `COMMAND_NAME` can be commands like `generate`, `records`, `exit`
  
*Example of usage:*
```
	help // to list instructions for all command
	help generate
	help exit
```
### Generate problem set: `generate`
Generate the problem based on a series of parameters. For example, what operators do you wish to include in the set **`+ - * /`**, the number of problems in the set, etc. As you enter the answers in order, the program will auto-check your answers for correction.

**Format:** `generate -t OPERATOR -n NUMBER_OF_PROBLEMS -d MAXIMUM_DIGITS -l LENGTH_OF_EQUATION`

- `OPERATOR` can be **`+ - * /`** or any combinations of these 4 operators
- `NUMBER_OF_PROBLEMS` can be any **positive integers** 
- `MAXIMUM_DIGITS` can be any **positive integers**
- `LENGTH_OF_EQUATION` can be any **positive integers**

*Example of usage:*

```
	generate -t + -n 1 -d 1
	generate -t */ -n 5 -d 3 -l 3
```

### Autosaving/Loading past attempts

Each time you finish a problem set, your results will be automatically stored in an external file `recordList.txt`. Each time you restart the application, the data from the file will be automatically loaded. 

Please **do not** manually change the file unless you're sure of what you're doing(Improper editing of the file could lead to data corruption/loss). 

### View past records: `records`

View the records of your past problem-solving sessions. Each record information including the date the problem set was solved, the time spent(in seconds) to solve the problem set, and the accuracy(measured in percentage), as well as the details of the problem set(including the problem set's unique ID, and each individual problem if you desire so).

**Format:** `records -SORT_BY_DATE -SORT_BY_SPEED -SORT_BY_ACCURACY -SORT_BY_PROBLEM_SET_ID -SHOW_DETAILS`

- all parameters are optional
- `SORT_BY_DATE` should be replaced with `d`(increasing order) or `dr`(decreasing order).
- `SORT_BY_SPEED` should be replaced with `s`(increasing order) or `sr`(decreasing order).
- `SORT_BY_ACCURACY` should be replaced with `a`(increasing order) or `ar`(decreasing order).
- `SORT_BY_PROBLEM_SET_ID` should be replaced with `p`(increasing order) or `pr`(decreasing order).
- `SHOW_DETAILS` should be replaced with `details`. Which shows each individual problem's description in the problem set.

### Submit Answer: `ENTER/RETURN`

type the answer in the terminal and press ENTER/RETURN

### Judge Answer:

After finishing all the problem sets, the program will automatically judge the correctness and output the accuracy and speed.

### Create DIY Problem Sets: `DIY`

Add user DIY problem sets into our problem set datasets. The DIY problem set can also be saved and retried.

**Format:** `DIY`

- after inputting `DIY`:
  1. The system will output 
   `Please input your DIY problemSet:
    input the description of the problem (e.g. 1+2*3): `
    user can input a user-defined problem into the system.
  2. Then the System will ask for input:
    `input the correct answer of the problem (e.g. 7): `
    user should input the correct answer to the user-defined problem.
  3. Then the System will ask:
    `Have you finished adding problems? y/n: `
    if the user inputs `y`, which means no more problems will be added.
    if the user inputs `n`, the system will repeat steps 1 to 3 until the user inputs `y`.
- The user can see the type of the problem set, i.e., user-DIY or auto-generated, in the records.

### Retry previous problem sets: `retry`

Allows users to try a problem set that they have attempted before in the past, meaning problem sets within the list of `records` can be attempted by copying the *problem set ID*, generated when users first attempt the problem set. For how to locate the problem set ID you wish to retry, check out the **View past records** section of the UserGuide. 

All retry attempts will also be saved in the records through the auto-saving feature, and will use the same Problem set ID in the new record as well. This means users can easily filter through all their attempts at one particular problem set through the `records` feature

**Format:** `retry PROBLEM_SET_ID`

- PROBLEM_SET_ID is a signed integer number, which can be found by using the `records` command

### Exit: `exit`

Users can use this to exit the program.

**Format:** `exit`

### Example Commands Summary
Here are some example commands you can use in **MathGenius**:

Here are some example commands you can try:
1. `generate`: generate problem set (with specific parameters )
2. Pressing `Enter/Return`: Submit your answer to a problem.
3. `exit`: Exits the program.
4. `records`: Displays your past problem-solving sessions, including the date you finished the set, your speed, accuracy, and the details of the problems.
5. `DIY`: Add user DIY problem sets into our problem set datasets. The DIY problem set can also be saved and retried.
6. `retry`: Retry a problem set that you've attempted in the past(through ID in records)

Remember, practice makes perfect. Happy learning with **MathGenius**!

### Command summary:

| Command          | Description                                                                  | Format                                                                                         | Example                        |
|------------------|------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|--------------------------------|
| `help`           | Show the message about the command you can use and the standard input format | `help COMMAND_NAME`                                                                            | `help generate`                |
| `gen / generate` | Generate the problem based on series of parameters                           | `generate -t OPERATOR -n NUMBER_OF_PROBLEMS -d MAXIMUM_DIGITS`                                 | `generate -t + -n 1 -d 1 -l 3` |
| `records`        | View the records of your past problem solving sessions                       | `records -SORT_BY_DATE -SORT_BY_SPEED -SORT_BY_ACCURACY -SORT_BY_PROBLEM_SET_ID -SHOW_DETAILS` | `records -d -details`          |
| `ENTER/RETURN`   | Submit the answer in the terminal                                            | NA                                                                                             | NA                             |
| `DIY`            | Add user DIY problem sets into our problem set datasets.                     | `DIY`                                                                                          | `DIY`                          |
| `retry`          | Retry a problem set that you've attempted in the past(through ID in records) | `retry -PROBLEM_SET_ID`                                                                        | `retry 17462645`               |
| `exit`           | Exit the program                                                             | `exit`                                                                                         | `exit`                         |

## FAQ

### Interaction Guide

You may wonder about the input format for every question, e.g., + - * /, or even decimals. Here, we provide a detailed guide for you!

### *Infinite Decimal Answers*

When a problem generates an answer with an infinite decimal, you should **round your answer in 2 decimal places**. 

For example, if the exact answer is 1.666666..., you should enter **`1.67`**.

Our program is designed to check answers with a tolerance up to the hundredth place. 

## Final Words

Remember, the goal of **MathGenius** is to help you improve your math skills. Please don't worry too much about the accuracy of your answers. Focus on understanding the concepts and improving your problem-solving speed and accuracy.
