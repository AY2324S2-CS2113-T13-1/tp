# User Guide of MathGenius

## Introduction

**`MathGenius`** is a user-friendly application that provides a platform to enhance your equation-solving abilities. Whether you're a student learning calculation, a math enthusiast looking to sharpen your skills, or a teacher preparing for exams/lessons, this application is here to support you.

## Get Started:

Run `java mathGenius.java` to get started   

Type the command in the command box and press Enter to execute it.   

e.g. typing help and pressing Enter will get the help message.   

Some example commands you can try:
1. `generate`: generate problem set
2. `pressing Enter/Return`: submit your answer
3. `exit`: exit the main program
4. `records`: view the past problem solving sessions you did, including the date you finished the set, your speed, accuracy, and the problems details etc.

## Feature:

### Show the Help: `help`

Show the message about the command you can use and the standard input format (e.g. input should be in fraction or in decimal form).

**Format:** `help COMMAND_NAME`
- The `COMMAND_NAME` can be commands like `generate`, `records`, `exit`
  
*Example of usage:*
```
	help generate

	help exit
```
### generate problem set: `generate`
Generate the problem based on series of parameters. For example, what operators you wish to include in the set (+-*/), the number of problems in the set etc. And as you enter the answer in order, the program will auto check your answers for correction.

**Format:** `generate -t OPERATOR -n NUMBER_OF_PROBLEMS -d MAXIMUM_DIGITS`

- `OPERATOR` can be `+-*/` or any combinations of these 4 operators
- `NUMBER_OF_PROBLEMS` can be any integers
- `MAXIMUM_DIGITS` can be any integers

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
- `sortByDATE` can be d(increasing order) or dr(decreasing order).
- `sortBySpeed` can be s(increasing order) or sr(decreasing order).
- `sortByAccuracy` can be a(increasing order) or ar(decreasing order).
- `sortByProblemID` can be p(increasing order) or pr(decreasing order).
- `showProblemDetails` can be details. Which shows each individual problem's description in the problem set.

### Submit Answer: `ENTER/RETURN`

type the answer in the terminal and press ENTER/RETURN

### Judge Answer: *NA*

After finishing all the problem sets, the program will automate judged the correctness and output the accuracy and speed.

### Exit: `exit`

User can use this to exit the program.

**Format:** `exit`
