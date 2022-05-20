# Selenium-automation
selenium automation
These is selenium automation for front end online store

the project is written using page object model, I used springboot frame work, with cucumber Gerkin for test cases layer

# You need java 11, maven, cucumber Gerkin plugin

# you can run this project in this file src/main/resources/testng.xml or command line type -> maven clean test

# you can make it run in parallel execution thread, by simply changing parallel to true in this file:

# src/test/java/com/automationpractice/Seleniumautomation/runner/TestRunner.java
# thread count is set up inside pom file
             look for this line 
             <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                    <parallel>both</parallel>
                    <threadCount>2</threadCount>
                </configuration>
                
# you can change the browser inside application.properties

application.url=http://automationpractice.com/

browser=chrome

default.timeout=60

# You can also run this automation solution remotely using a maven command
maven clean test -Dspring-boot.run.profiles=remote
# selenium grid url can be configured in this file
application-remote.properties


