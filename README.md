[![Travis](https://img.shields.io/travis/williamfiset/data-structures.svg)](https://travis-ci.org/williamfiset/data-structures) [![License: MIT](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# The data structures project

Data structures are amongst the most fundamental ingredients in the recipe for efficient algorithms and good software design. Knowledge of how to create and design excellent data structures is an essential skill required in becoming an exemplary programmer. The goal of this repository is to provide a complete and thorough explanation of the most common data structures.

# Contributing!

If you want to add/edit/improve a data structure in this repository contributions are welcome! Please be sure to include tests :)

# Development

This project uses [Gradle](https://gradle.org/) as a build system. To get started install the gradle command-line tool. 

### Running all tests

To run all tests type:
```bash
data-structures$ gradle test
```

### Running specific test file
When developing you likely do not want to run all tests for every data structure. Instead specify the name of the test class you wish to run:
```bash
data-structures$ gradle test --tests "LinkedListTest"
```

Sometimes there are many test files for one data structure. One example is the FenwickTree which currently has two test files: [FenwickTreeRangeQueryPointUpdateTest.java](FenwickTree/FenwickTreeRangeQueryPointUpdateTest.java) and [FenwickTreeRangeUpdatePointQueryTest.java](FenwickTree/FenwickTreeRangeUpdatePointQueryTest.java), in which case you can specify a glob expression to capture all the appropriate test files:
```bash
gradle test --tests "FenwickTree*Test"

# Equivalently for the explicit:
gradle test --tests "FenwickTreeRangeQueryPointUpdateTest" --tests "FenwickTreeRangeUpdatePointQueryTest"
```

# License

This repository is released under the [MIT license](https://opensource.org/licenses/MIT). In short, this means you are free to use this software in any personal, open-source or commercial projects. Attribution is optional but appreciated.

