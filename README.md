# individual-project
This is my final year individual project, carried out as part of the KCL BSc Computer Science course for the module 6CCS3PRJ Individual Project.

This project deals with Git commit message analysis through natural language processing. The project report is included in this repository under "Final Report.pdf". This project achieved a classification of First. 

Prerequisites
- Java 8
- Gradle

Installation instructions:
1. Navigate to this folder in Terminal.
2. Type "gradle build" and wait for it to finish building the project. This is a necessary step as it will download the Stanford CoreNLP .jar files and Unirest .jar files, which are too large to include in this archive.
3. Type "gradle run" after the project is built.

For convenience, this source code is bundled with the 201 commit messages used for analysis under the file "messages.txt" in this folder.
