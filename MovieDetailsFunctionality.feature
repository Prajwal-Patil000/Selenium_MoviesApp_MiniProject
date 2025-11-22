Feature: Movie Details Functionality
  As a user, I want to view movie details from Home and Popular pages
  So that I can see the synopsis, reviews, and similar movies

  Scenario: Verify movie details UI elements when navigating from <Section> Page
    Given I am logged into the QA Movies Application
    When I navigate to the "<Section>" Page
    And I click on a movie in the "<Section>" section
    Then I should be redirected to the movie details page
    And I verify that the Background Image is displayed
    And I verify that the Movie Title and Description are displayed
    And I verify that the Movie Review Container is displayed
    And I verify that the Detailed Categories are displayed
    And I verify that Similar Movies are displayed

