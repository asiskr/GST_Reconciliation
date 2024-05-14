@order1
Feature: Verify User is able to do login

  Scenario: Verify user should tap on my gov button
    Given User launch website
    When user tap on my gov button
    Then user must redirected to login screen
   # When user provide email id "Vijitha.p@fortunaadvisors.com.au"
    When user do login as per provided in excel
    And click on login button