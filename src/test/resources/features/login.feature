Feature: As a admin
  I want to login
  in order to add users

  Background:
    Given I am on <app>
    And I login using valid  Admin credentials

  Scenario: Adding one user
   When I add one user
   Then the user should be created in the <app>

  Scenario: Adding multiple users
    When I add more than one user
    Then the users should be created in the <app>

  Scenario: Adding unauthorised user
    When I add unauthorised user
    Then the user should not be created in the <app>