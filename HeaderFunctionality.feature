Feature: Header Navigation

  Scenario: Navigate to Popular from Header
    Given User is logged in on Home page
    When User clicks on the Popular link in header
    Then User should navigate to Popular page
