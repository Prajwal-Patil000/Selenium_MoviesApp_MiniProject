Feature: Account Page Testing
  As a user of the movies website
  When I click on the Account page
  I should see all UI elements visible
  And be able to log out and return to the login page

  Scenario: Successful Login and Account Verification
    Given I am on Login page for Account
    When I enter valid username and password for Account
    And I click on login button
    Given I navigate to the Account Page for Account
    Then All UI elements should be present and visible for Account
    When User clicks on the logout button for Account
    Then I should be redirected to login page
