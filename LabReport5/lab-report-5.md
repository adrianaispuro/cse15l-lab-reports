# Lab Report 5 - Grade Server

## What we'll need

* [A clone of this GitHub repository](https://github.com/adrianaispuro/list-examples-grader)

### Part 1: Running grade.sh

Once we clone the GitHub repository from above, we should see a file called "grade.sh" that contains this code:

```bash
# Create your grading script here
CPATH=".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar"
rm -rf student-submission
git clone $1 student-submission
echo 'Finished cloning'
cd student-submission
FILEPATH=`find ./ -name "ListExamples.java"`

if [[ $FILEPATH != "" ]]
then
    echo "File found in $FILEPATH"
    cp $FILEPATH ../
else
    echo "Failed - Missing file / file not found"
    exit 1
fi

cd ..
javac -cp $CPATH *.java 2> compile-err.txt
if [[ $? -ne 0 ]]
then
    echo "Compile error(s)!"
    if [[ `grep "errors" compile-err.txt` -ge 1 ]]
    then
        echo "Failed - multiple compile errors"
    else
        echo "Failed - one compile error"
    fi
    cat compile-err.txt
    exit 1
else
    echo "Compiled successfully"
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-results.txt
if [[ `grep "FAILURES" < test-results.txt` -ne 0 ]]
then
    echo "Failed - Test(s) failed"
    cat test-results.txt
    exit 1
else   
    echo "Passed - all tests run successfully"
    exit 0
fi
```

This script is important as this enables us to "grade" a repository on our server.

Once we run `java GradeServer 4000`, we should see the following page:

![browser with url at localhost:4000](media/gradeserver1.png)

Now, we can pass a GitHub repo URL as an argument in the localhost URL to "grade" a student's submission:

* Grading [Student Submission 1](https://github.com/ucsd-cse15l-f22/list-methods-corrected)

![Grade script run on browser: "Passed - all tests run successfully"](media/gradeserver%20correct.png)

Here, our submission receives a passing grade as it contains the file, compiles correctly, and passes all tests.

* Grading [Student Submission 2](https://github.com/ucsd-cse15l-f22/list-methods-compile-error)

![Grade script run on browser: "Failed - compile error](media/gradeserver%20compile.png)

This student's submission does not receive a passing grade because their file contains a compile error.

* Grading [Student Submission 3](https://github.com/ucsd-cse15l-f22/list-methods-nested)

![Grade script run on browser: "Passed - all tests run successfully](media/gradeserver%20nested.png)

This submission receives a passing grade, although the file we're grading (`ListExamples.java`) is nested in a different directory.

### Part 2: Tracing the grading script

For this report, we'll observe a trace of `grade.sh` grading student submission 3.

**Line 3:** `rm -rf student-submission`

* No stdout, stderr (command deletes directory)
* Return code: 0

**Line 4:** `git clone $1 student-submission`

* No stdout, stderr
* Return code: 0

**Line 7:** ```FILEPATH=`find ./ -name "ListExamples.java"` ```

* Runs `find` command and assigns result to FILEPATH variable
* Result: `./pa1/ListExamples.java`

**Lines 9-16:** `if [[ $FILEPATH != "" ]]`

* If loop checks if `FILEPATH` is not an empty string
* Condition is true, since `FILEPATH` has a path

**Line 12:** `cp $FILEPATH ../`

* No stdout, stderr
* Return code: 0

**Lines 14-15** do not run since the if statement condition is met

**Line 19:** `javac -cp $CPATH *.java 2> compile-err.txt`

* No stdout, stderr
* Return code: 0


**Lines 20-33:** `if [[ $? -ne 0 ]]`

* If loop checks the return code of `javac` does not equal to zero (exit code != 0 means compile errors)
* Condition is false, since `javac` has return code of 0

**Lines 22-30** are not run since if statement condition is not met

**Line 35:** `java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-results.txt`

* stdout:

```bash
JUnit version 4.13.2
..
Time: 0.007

OK (2 tests)
```

* No stderr
* Return code: 0

**Lines 36-44:**

```bash
if [[ `grep "FAILURES" < test-results.txt` -ne 0 ]]
```

* If loop checks if "FAILURES" is present in `test-results.txt`, indicating that tests failed
* Condition is false; since `test-results.txt` does not contain "FAILURES", all tests passed

**Lines 38-40** are not run since if statement condition is not met
