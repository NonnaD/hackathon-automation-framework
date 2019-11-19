# hackathon-automation-framework

How to set up this framework on your local machine

1. Clone this repository
2. Import new project from "Version control" using your favorite IDEA
2. Go to pom.xml and make sure all dependencies and plugins were successfully imported
3. Navigate to config.properties and set your "operating.system" property. "mac" - for MacOS and "windows" for Windows OS
4. Running tests
        4.1 Run using maven: run the next command  - mvn clean test 
        4.2 Run individually - go to TC file and click "Run test" button or click (Shift + f12 Intllij IDEA)
5. Generating report - this framework use Allure report plugin. To generate report, enter command: allure serve 
        
        


