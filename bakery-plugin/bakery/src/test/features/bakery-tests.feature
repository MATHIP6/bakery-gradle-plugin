#noinspection CucumberUndefinedStep
@cucumber @bakery
Feature: Bakery plugin tests

  Scenario: Canary
    Given a new Gradle project
    When I am executing the task 'tasks'
    Then the build should succeed

#bakery.configPath=
#  given and