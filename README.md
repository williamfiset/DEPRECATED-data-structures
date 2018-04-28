[![Travis](https://img.shields.io/travis/williamfiset/data-structures.svg)](https://travis-ci.org/williamfiset/data-structures) [![License: MIT](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# The data structures project

Data structures are amongst the most fundamental ingredients in the recipe for efficient algorithms and good software design. Knowledge of how to create and design excellent data structures is an essential skill required in becoming an exemplary programmer. The goal of this repository is to provide a complete and thorough explanation of the most common data structures.

# Contributing

This repository is contribution friendly :smiley:. If you're a data structures enthusiast (like me!) and want to add or improve a data structure your contribution is welcome! Please be sure to include tests :kissing_heart:.

# For developers

This project uses [Gradle](https://gradle.org/) as a build system and for testing. To get started install the gradle command-line tool and run the build command to make sure you don't get any errors:

```bash
data-structures$ gradle build
```

### Adding a new data structure

The procedure to add a new data structure named **Foo** is the following:

1) Create a new folder called Foo in **com/williamfiset/datastrutures/Foo**
2) Add the data structure implementation for Foo in **com/williamfiset/datastrutures/Foo/Foo.java**
3) Add tests for Foo in **javatests/com/williamfiset/datastrutures/Foo/FooTest.java**
4) Edit the **build.gradle** file and add Foo to the project.
5) Add tests for your data structure :kissing_heart:
6) Send pull request for review :open_mouth:

### Testing

This repository places a large emphasis on good testing practice to ensure that published data structures are bug free and high quality. Testing is done using a combinations of frameworks including: [JUnit](http://junit.org/junit4/), [Mockito](http://site.mockito.org/) and the [Google Truth](http://google.github.io/truth) framework, but mostly JUnit.

When developing you likely do not want to run all tests but only a subset of them. For example, if you want to run the LinkedListTest.java file under [javatests/com/williamfiset/datastructures/linkedlist/LinkedListTest.java](https://github.com/williamfiset/data-structures/tree/master/javatests/com/williamfiset/datastructures/linkedlist/LinkedListTest.java) you can execute:
```bash
data-structures$ gradle test --tests "javatests.com.williamfiset.datastructures.linkedlist.LinkedListTest"
```
Sometimes there are many test files for one data structure. One example is the :evergreen_tree:FenwickTree:evergreen_tree: which currently has two test files: [javatests.com.williamfiset.datastructures.fenwicktree.FenwickTreeRangeQueryPointUpdateTest.java](https://github.com/williamfiset/data-structures/tree/master/javatests/com/williamfiset/datastructures/fenwicktree/FenwickTreeRangeQueryPointUpdateTest.java) and [javatests.com.williamfiset.datastructures.fenwicktree.FenwickTreeRangeUpdatePointQueryTest.java](https://github.com/williamfiset/data-structures/tree/master/javatests/com/williamfiset/datastructures/fenwicktree/FenwickTreeRangeUpdatePointQueryTest.java), in which case you can specify a glob expression to capture all the appropriate test files:
```bash
# Using globbing:
gradle test --tests "javatests.com.williamfiset.datastructures.fenwicktree.FenwickTree*Test"
```

# License

This repository is released under the [MIT license](https://opensource.org/licenses/MIT). In short, this means you are free to use this software in any personal, open-source or commercial projects. Attribution is optional but appreciated.

