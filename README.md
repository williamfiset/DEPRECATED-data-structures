## The Data Structures

All the data structures provided in this repository (spare a few) have been designed for educational proposes. This is why you will find extensive comments explaining code snippets throughout. Futhermore, these data structures have for the most part been designed for a [data structures video series](https://www.youtube.com/playlist?list=PLDV1Zeh2NRsB6SWUrDFW2RmDotAfPbeHu), which is why you will find a **presentations/** folder containing slides for the associated data structure which we highly recommend you check out.

## Using the Data Structures

Each data structure is found in its own folder with associated tests. The data structures provided do not have any dependencies outside their own files, so just copy the code and have fun! You may also find that sometimes multiple versions of a data structure exist (i.e, A queue implemented as a linked list but also a queue implemented with an array), this is because sometimes one implementation may be more preferable to another.

## Running the tests

We try and test our data structures when possible to ensure the reliability of the code we design. The test framework we use is JUnit, and the jar dependencies can be found at the root directory of the project. Each data structure should contain a **test** shell script which compiles the source files and runs the tests for that data structure. Here is an example:

``` bash
cd Linkedlist
sh test
```

Alternatively, if you wish to run all the tests are each data structure you may with the testall shell script:

``` bash
sh testall
```
