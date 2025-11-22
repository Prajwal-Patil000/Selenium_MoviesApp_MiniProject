Feature: Search Bar Functionality
  As a user, I want to perform comprehensive testing of the search bar
  So that I can verify both successful and unsuccessful search flows, including navigation.

  Background:
    Given I am logged in to the Movies App

  Scenario: Comprehensive Search Validation
    # 1. Successful Search
    When I open the search bar
    And I search for "Venom"
    Then I should see at least 1 movie displayed

    # 2. Navigation Test - STEP TEXT CHANGED
    When I click the first movie in the results
    Then I see the corresponding movie details page

    # 3. State Reset (Navigate back to search results page)
    When I navigate back to the search results page

    # 4. Invalid Search Test
    And I search for "xyz123"
    Then I should see a no results found message
