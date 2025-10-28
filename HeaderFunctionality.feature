Feature: Testing Header Section
  As a user of the movies website
  I should see the website logo,
  All the navbar elements,
  And be able to navigate to all corresponding pages successfully.

  Scenario: Verify Header Navigation Functionality
    Given I am on Login page for Header
    When I enter valid username and password for Account for Header
    And I click on login button
    Then I should be redirected to home page
    And I should see the Navbar elements
    Then I should be navigated to homepage
    Then I should be navigate to popular page
    When I should be navigate to search page
    Then I should be Navigate to Account Page Through elements Successfully in header section
