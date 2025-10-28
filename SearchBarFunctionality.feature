Feature: SearchBar Test
    As a user of the movies website
    I want to be able to login to my account
    then i want to click on search bar
    so that i can redirect to search bar page
    then i can search any movie

    Scenario: Successful Search
      Given I am on Login page for search bar
      When I enter valid username and password
      And i click on login button
      Then I should be redirect to home page
      Then I should search the searchbar and click
      And I should redirect to searchbar and search movies
      Then I should be able to search for Avatar and Venom movies
