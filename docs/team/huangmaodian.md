#  Huang Maodian's Project Portfolio Page  

## Project: MathGenius   
MathGenius is a easy-to-use application that can generate mathematical equations problem sets based on highly customizable user specifications,
and time and record users' performance as they solve the problem sets. It's a simple and effective tool for anyone who wishes to improve their calculation skills or preparing problems for education purposes.

Given below are my contributions to the project.

- **New Features**: Added the Data Persistence File Storage System.
  - What it does: allows the all previous generated and solved problem sets by the user to be saved externally in a text file. Saved information includes time & date, the problem set, the users' the accuracy and the speed.
  - Justification: For users to improve, it's important the application provides a method for the users to review and reflect on their past performances, as well as compare and contextualize their improvements.
  - Highlights: program auto-saves users' attempts after they complete a problem set, and auto-loads any existing save data whenever users start the program. 
- **New Features**: Added the view records function via command "records" 
  - What it does: allows users to view all their past attempted problem sets and their respective performances, in order to better compare and track their progress, as well as an assortment of sorting options for the users to view their past records in the order they desire
- **New Features**: Added a function to retry previously attempted problem sets via command "retry"
  - What it does: allows users to retry a problem set by inputting a 'problem set ID' which is created when a problem set is generated solved for the first time and viewed by using the "records" command.
  - Justification: Most users will appreciate the ability to retry a problem set they previously attempt, since the nature of random generation means it's nearly impossible for two generated problem sets to be exactly the same.


- **Code contributed**: [Reposense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=geinzit&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)  

-  **Project management**:  
Managed releases `v1.0` - `v2.1` (3 releases) on GitHub  

- **Documentation**:  

  - User guide: 
	- Added documentation for the features `records` and `retry` and the save & load function
	- Minor adjustments to overall formatting.

  - Developer Guide:
	- Added documentation for the `records` and `retry` as well as the ID generation logic and external file format.


- **Community**:
  - Reported bugs and suggestions for other teams in the class:
  - Helped improve the test code and text-ui-test for gradle.
  - Fixed numerous incorrect coding styles.

- **Tools**:
  - IntelliJ
  - (Personally) Pandoc Integrated with Emacs for local markdown files editing and previewing
  
[issues](https://github.com/AY2324S2-CS2113-T13-1/tp/issues)
