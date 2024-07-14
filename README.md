# 21vek UI & API tests in Java
This is a diploma project.

It consists of:
* UI tests in Selenium
* API tests in REST-assured
* Allure plugin for reports generation

## Frameworks and tools

* **Selenium** for UI tests
* **Rest-assured** for API tests
* **TestNG** for writing and running tests
* **Maven** for building and running suites
* **Log4j** for logs output
* **Allure** for reports generation
* **AspectJ** for Allure screenshots attachements

## How to run

Requires Java 16 to run.

Run test suits with the following commands:

For API tests `mvn test -Dsuite=ApiSuite`

For UI tests `mvn test -Dsuite=UiSuite`

To generate and observe Allure reports, in a Terminal run 

`allure serve`

on Windows

`allure.bat serve`
