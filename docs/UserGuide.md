# User Guide of MathGenius

## Table of Contents
1. [Introduction](#introduction)
2. [Get Started](#get-started)
3. [Features and Commands](#features-and-commands)
   1. [Command Summary](#command-summary)
4. [Interaction guide](#interaction-guide)

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

For a comprehensive list of all possible commands and their usage, run the `help` command: It lists out the **command you can use** and the **standard input format** (e.g. input should be in fraction or in decimal form).

**Format:** `help COMMAND_NAME`
- The `COMMAND_NAME` can be commands like `generate`, `records`, `exit`
  
*Example of usage:*
```
	help // to list instructions for all command
	help generate
	help exit
```
### Generate problem set: `generate`
Generate the problem based on series of parameters. For example, what operators you wish to include in the set **`+ - * /`**, the number of problems in the set etc. And as you enter the answer in order, the program will auto check your answers for correction.

**Format:** `generate -t OPERATOR -n NUMBER_OF_PROBLEMS -d MAXIMUM_DIGITS`

- `OPERATOR` can be **`+ - * /`** or any combinations of these 4 operators
- `NUMBER_OF_PROBLEMS` can be any **positive integers** 
- `MAXIMUM_DIGITS` can be any **positive integers**

*Example of usage:*

```
	generate -t + -n 1 -d 1
	generate -t */ -n 5 -d 3
```

### Autosaving/Loading past answers

Each time you finish a problem set, you results will be automatically stored in an external file `recordList.txt`. And each time you restart the application, the data from the file will be automatically loaded. 

Please do not manually change the file unless you're sure of what you're doing(Improper editing of the file could lead to data corruption/loss). 

### View past records: `records`

View the records of your past problem solving sessions. Each record information including the date the problem set was solved, the time spent(in seconds) to solve the problem set, and the accuracy(measured in percentage), as well as the details of the problem set(including the problem set's unique ID, and each individual problem if you desire so).

**Format:** `records -sortByDate -sortBySpeed -sortByAccuracy -sortByProblemID -showProblemDetails`

- all parameters are optional
- `sortByDATE` should be replaced with `d`(increasing order) or `dr`(decreasing order).
- `sortBySpeed` should be replaced with `s`(increasing order) or `sr`(decreasing order).
- `sortByAccuracy` should be replaced with `a`(increasing order) or `ar`(decreasing order).
- `sortByProblemID` should be replaced with `p`(increasing order) or `pr`(decreasing order).
- `showProblemDetails` should be replaced with `details`. Which shows each individual problem's description in the problem set.

### Submit Answer: `ENTER/RETURN`

type the answer in the terminal and press ENTER/RETURN

### Judge Answer: *NA*

After finishing all the problem sets, the program will automate judged the correctness and output the accuracy and speed.

### DIY: `DIY`
Add user DIY problem sets into our problem set datasets. The DIY problem set can also be saved and retried.

### Exit: `exit`

User can use this to exit the program.

**Format:** `exit`

### Example Commands Summary
Here are some example commands you can use in **MathGenius**:

Here are some example commands you can try:
1. `generate`: generate problem set (with specific parameters )
2. Pressing `Enter/Return`: Submits your answer to a problem.
3. `exit`: Exits the program.
4. `records`: Displays your past problem-solving sessions, including the date you finished the set, your speed, accuracy, and the details of the problems.
5. `DIY`: Add user DIY problem sets into our problem set datasets. The DIY problem set can also be saved and retried.

Remember, practice makes perfect. Happy learning with **MathGenius**!

### Command summary:

| Command          | Description | Format                                                                                  | Example                        |
|------------------|-------------|-----------------------------------------------------------------------------------------|--------------------------------|
| `help`           | Show the message about the command you can use and the standard input format | `help COMMAND_NAME`                                                                     | `help generate`                |
| `gen / generate` | Generate the problem based on series of parameters | `generate -t OPERATOR -n NUMBER_OF_PROBLEMS -d MAXIMUM_DIGITS`                          | `generate -t + -n 1 -d 1`      |
| `records`        | View the records of your past problem solving sessions | `records -sortByDate -sortBySpeed -sortByAccuracy -sortByProblemID -showProblemDetails` | `records -d -s -a -p -details` |
| `ENTER/RETURN`   | Submit the answer in the terminal | NA | NA                             |
| `DIY`            | Add user DIY problem sets into our problem set datasets. | `DIY` | `DIY`                          |
| `exit`           | Exit the program | `exit`| `exit`                         |

## Interaction Guide

You may wonder what is the input format for every questions, e.g. + - * /, or even decimals, here we provided a detailed guide for you!

### *Infinite Decimal Answers*

When a problem generates an answer with an infinite decimal, you should **round your answer in 2 decimal places**. 

For example, if the exact answer is 1.666666..., you should enter **`1.67`**.

Our program is designed to check answers with a tolerance up to the hundredth place. 

Remember, the goal of **MathGenius** is to help you improve your math skills. Don't worry too much about the precision of your answers. Focus on understanding the concepts and improving your problem-solving speed and accuracy.