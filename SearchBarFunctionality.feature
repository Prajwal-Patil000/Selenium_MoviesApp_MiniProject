Feature: Movie Search Functionality

  Scenario: Search movie by name
    Given User is on Home page with search bar visible
    When User searches for a movie named "Iron Man"
    Then Search results should display movies related to "Iron Man"
