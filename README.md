<h1>Commands and results</h1>
<table>
<thead>
<tr>
<th>Command
</th>
<th>Output
</th>
</tr>
</thead>
<tbody>
<tr>
<th>mvn versions:display-dependency-updates
</th>
<th>[INFO] The following dependencies in Dependencies have newer versions:
    [INFO]   org.seleniumhq.selenium:selenium-java ................. 4.8.0 -> 4.8.1
    [INFO]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time:  7.999 s
    [INFO] Finished at: 2023-02-26T16:10:56+03:00
    [INFO] ------------------------------------------------------------------------
</th>
</tr>
<tr>
<th>
mvn clean test
</th>
<th>
Results :

Tests run: 23, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  49.493 s
[INFO] Finished at: 2023-02-26T16:20:06+03:00
[INFO] ------------------------------------------------------------------------
</th>
</tr>
<tr>
<th>
mvn clean -Dtest=HeaderTest test
</th>
<th>
Results :

Tests run: 2, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  21.017 s
[INFO] Finished at: 2023-02-26T16:29:05+03:00
[INFO] ------------------------------------------------------------------------
</th>
</tr>
<tr>
<th>
mvn clean -Dtest=CartTest#checkAddRemoveProductButton test
</th>
<th>
Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  16.086 s
[INFO] Finished at: 2023-02-26T16:34:21+03:00
[INFO] -----------------------------------------------------------------------
</th>
</tr>
<tr>
<th>
mvn clean -Dtest=ProductsTest#checkAmountOfProducts+checkProductPresentOnProductPage test
</th>
<th>
Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  14.736 s
[INFO] Finished at: 2023-02-26T16:41:08+03:00
[INFO] ------------------------------------------------------------------------
</th>
</tr>
<tr>
<th>
mvn clean -Dtest=CartTest,HeaderTest test
</th>
<th>
Results:
[INFO]
[INFO] Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  32.370 s
[INFO] Finished at: 2023-02-26T16:43:43+03:00
[INFO] ------------------------------------------------------------------------
</th>
</tr>
</tbody>
</table>