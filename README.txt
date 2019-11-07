Steps to build and run
======================

1) Clone or download the project.
2) Import the project in Eclipse/Intellij
3) Open the command prompt on windows or terminal on linux and go the project directory.
4) Execute the maven_build.bat on windows or maven_build.sh on linux(On linux file mode should be executable).
5) Run the below command.
java -jar target/spreadsheet-1.jar -i input.csv -o output.csv

*Provide the fully qualified name of input and output files.

** For keeping 26 * 5 million key/value pairs, in real projects some RDBMS or in memory databases like Redis is to be used instead of
keeping records in in memory Map(Map might give out of memory exceptions).
