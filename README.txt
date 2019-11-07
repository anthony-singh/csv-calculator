Steps to build and run
======================

1) Unzip the project and copy it to some location.
  e.g D:\csv-calc
2) Open the command prompt on windowns or terminal on linux and go the dir csv-calc.
3) Execute the build_script.bat on windows or build.sh on linux(On linux file mode should be executable).
4) Run the below command.
java -jar target/spreadsheet-1.jar -i input.csv -o output.csv

*Provide the fully qualified name of input and output files.

** For keeping 26 * 5 million key/value pairs, in real projects some RDBMS or in memory databases like Redis is to be used instead of
keeping records in in memory Map(Map might give out of memory exceptions).