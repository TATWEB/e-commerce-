Feature: Sign up feature involving DB layer


  Scenario: New User Sign Up from UI to DB flow
    Given I am on the sign up page and I am connected to the DB
    When I sign up with the following info
         |username| first | last | email | password|
         |bob.evans| Bob | Evans | bobevans@gmail.com| bobevans23|
    Then I should land on the homepage
    And The database should also have the correct info



  Scenario: New User Creation from DB to UI flow
    Given I am connected to the DB
    When I add a new user to the database with the following info
      |username| first | last | email | password|
      |daisyduck| Daisy | Duck | daisyduck@gmail.com| daisyduck99|
    Then I should be able to log in with the "daisyduck" as uasername and "daisyduck99" as password on the UI


  Scenario: Verify Songs Table Column Names
    Given I am connected to the DB
    When I retrieve the column names for the Songs table
    Then It should be the following
      | id         |
      | title      |
      | artist     |
      | album      |
      | genre      |
      | duration   |
      | path       |
      | albumOrder |
      | plays      |




  Scenario: Test if input field leading and trailing spaces are truncated before committing data to the database
    Given I am on the sign up page and I am connected to the DB
    When I sign up with the following info "      bob.evander   " "    Bob   " "   Evans  " "    bobevander@gmail.com     "  "bobevans23"
    Then I should land on the homepage
    And The database should also have the correct info without spaces


  @duotify
  Scenario: Check for duplicate values in the username column
    Given I am connected to the DB
    When I send a query to check for duplicate usernames
    Then The returned result list should be empty