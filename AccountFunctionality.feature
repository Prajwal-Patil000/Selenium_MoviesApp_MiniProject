Feature: Account Page Functionality
  This feature validates the login, account page UI and logout functionality
  of the Movies App.

  # ---------------- LOGIN SCENARIO ----------------
  Scenario: Verify successful login functionality
    Given I am on the movies login page
    When I enter valid login credentials
    And I click the login button
    Then I should be logged in successfully

  # ---------------- ACCOUNT PAGE UI ----------------
  Scenario: Verify Account Page UI elements
    Given I am on the movies login page
    When I enter valid login credentials
    And I click the login button
    Then I should be logged in successfully
    When I navigate to account page
    Then I should be redirected to account page
    And I should see all the account page UI elements

  # ---------------- LOGOUT FUNCTIONALITY ----------------
  Scenario: Verify logout functionality
    Given I am on the movies login page
    When I enter valid login credentials
    And I click the login button
    Then I should be logged in successfully
    When I navigate to account page
    Then I should be redirected to account page
    When I click on logout button
    Then I should be redirected to login page after logout
