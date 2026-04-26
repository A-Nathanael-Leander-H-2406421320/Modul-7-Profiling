# Module 7: Profiling

## Test 1: `/all-student` endpoint

![Request of test 1](assets/test1/request.png) Request of test 1

|                                                                               |                                                                                  |
| ----------------------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| ![View result tree test 1 sampler 1](<assets/test1/tree%20(1).png>) Sampler 1 | ![View result tree test 1 sampler 2](<assets/test1/tree%20(2).png>) Sampler 2    |
| ![View result tree test 1 sampler 3](<assets/test1/tree%20(3).png>) Sampler 3 | ![View result tree test 1 sampler 4](<assets/test1/tree%20(4).png>) Sampler 4    |
| ![View result tree test 1 sampler 5](<assets/test1/tree%20(5).png>) Sampler 5 | ![View result tree test 1 sampler 6](<assets/test1/tree%20(6).png>) Sampler 6    |
| ![View result tree test 1 sampler 7](<assets/test1/tree%20(7).png>) Sampler 7 | ![View result tree test 1 sampler 8](<assets/test1/tree%20(8).png>) Sampler 8    |
| ![View result tree test 1 sampler 9](<assets/test1/tree%20(9).png>) Sampler 9 | ![View result tree test 1 sampler 10](<assets/test1/tree%20(10).png>) Sampler 10 |

![Summary result of test 1](assets/test1/summary.png) Summary result of test 1

![Graph result of test 1](assets/test1/graph.png) Graph result of test 1

![CLI run result of test 1](assets/test1/cli.png) CLI result of test 1
JTL file of test 1: [test1.jtl](profiling-test/test_result_1.jtl)

**Refactoring changes**: The changes is in querying the database. Instead of doing N+1 queries to get the students and their courses, we can use `JOIN` to get all the data in one query. This will reduce the number of queries and improve the performance of the endpoint.

![CLI run result of test 1 after optimization](assets/test1/opt-cli.png) CLI result of test 1 after optimization. Note the latency reduction from about 150000ms to about 500ms (about 99% increased performance).
JTL file of test 1 run after optimization: [test1.jtl](profiling-test/test_result_opt_1.jtl)

## Test 2: `/all-student-name` endpoint

![Request of test 2](assets/test2/request.png) Request of test 2

|                                                                               |                                                                                  |
| ----------------------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| ![View result tree test 2 sampler 1](<assets/test2/tree%20(1).png>) Sampler 1 | ![View result tree test 2 sampler 2](<assets/test2/tree%20(2).png>) Sampler 2    |
| ![View result tree test 2 sampler 3](<assets/test2/tree%20(3).png>) Sampler 3 | ![View result tree test 2 sampler 4](<assets/test2/tree%20(4).png>) Sampler 4    |
| ![View result tree test 2 sampler 5](<assets/test2/tree%20(5).png>) Sampler 5 | ![View result tree test 2 sampler 6](<assets/test2/tree%20(6).png>) Sampler 6    |
| ![View result tree test 2 sampler 7](<assets/test2/tree%20(7).png>) Sampler 7 | ![View result tree test 2 sampler 8](<assets/test2/tree%20(8).png>) Sampler 8    |
| ![View result tree test 2 sampler 9](<assets/test2/tree%20(9).png>) Sampler 9 | ![View result tree test 2 sampler 10](<assets/test2/tree%20(10).png>) Sampler 10 |

![Summary result of test 2](assets/test2/summary.png) Summary result of test 2

![Graph result of test 2](assets/test2/graph.png) Graph result of test 2

![CLI run result of test 2](assets/test2/cli.png) CLI result of test 2
JTL file of test 2: [test2.jtl](profiling-test/test_result_2.jtl)

**Refactoring changes**: The changes focus on reducing string-processing and data-loading overhead. Instead of concatenating immutable strings in a loop and loading full `Student` entities, the service now fetches only student names and combines them using `String.join(", ", names)`. This reduces memory churn and improves response time for the endpoint.

![CLI run result of test 2 after optimization](assets/test2/opt-cli.png) CLI result of test 2 after optimization. Note the latency reduction from about 3000ms to about 60ms (about 98% increased performance).
JTL file of test 2 run after optimization: [test2.jtl](profiling-test/test_result_opt_2.jtl)

## Test 3: `/highest-gpa` endpoint

![Request of test 3](assets/test3/request.png) Request of test 3

|                                                                               |                                                                                  |
| ----------------------------------------------------------------------------- | -------------------------------------------------------------------------------- |
| ![View result tree test 3 sampler 1](<assets/test3/tree%20(1).png>) Sampler 1 | ![View result tree test 3 sampler 2](<assets/test3/tree%20(2).png>) Sampler 2    |
| ![View result tree test 3 sampler 3](<assets/test3/tree%20(3).png>) Sampler 3 | ![View result tree test 3 sampler 4](<assets/test3/tree%20(4).png>) Sampler 4    |
| ![View result tree test 3 sampler 5](<assets/test3/tree%20(5).png>) Sampler 5 | ![View result tree test 3 sampler 6](<assets/test3/tree%20(6).png>) Sampler 6    |
| ![View result tree test 3 sampler 7](<assets/test3/tree%20(7).png>) Sampler 7 | ![View result tree test 3 sampler 8](<assets/test3/tree%20(8).png>) Sampler 8    |
| ![View result tree test 3 sampler 9](<assets/test3/tree%20(9).png>) Sampler 9 | ![View result tree test 3 sampler 10](<assets/test3/tree%20(10).png>) Sampler 10 |

![Summary result of test 3](assets/test3/summary.png) Summary result of test 3

![Graph result of test 3](assets/test3/graph.png) Graph result of test 3

![CLI run result of test 3](assets/test3/cli.png) CLI result of test 3
JTL file of test 3: [test3.jtl](profiling-test/test_result_3.jtl)

**Refactoring changes**: The changes move highest GPA selection from in-memory iteration to the database query layer. Instead of loading all students and scanning in Java, the service now requests only the top record ordered by GPA (`findFirstByOrderByGpaDescIdAsc`). This reduces data transfer and processing time for the endpoint.

![CLI run result of test 3 after optimization](assets/test3/opt-cli.png) CLI result of test 3 after optimization. Note the latency reduction from about 150ms to about 55ms (about 63% increased performance).
