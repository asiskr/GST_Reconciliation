@order4
Feature: Xero Selecting Quarter Date

  Scenario: User selects quarter date on Xero and handles "Needs Attention" if present

    Given user is on client home page
    When user clicks on the Accounting button
    And user clicks on Activity Statement
    Then user performs actions based on Needs Attention
    Then user is on client Activity statement page