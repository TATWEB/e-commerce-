Feature: Unit tests that involve DB constraints





@
  Scenario: Verify the column length for name column of the playlists table
    Given I am connected to the DB
    When I update the name column with a String with an invalid length of 100 , the update should truncate the length to 50





  Scenario: Verify the unicode support for name column of the playlists table
    Given I am connected to the DB
    When I update the name column with a unicode chars, the update should be successful
    Then The update should be also successful on the UI


  Scenario: Verify the unicode support for name
    Given I am connected to the DB
    When I update the name column with a unicode chars, the update should be successful
    Then The update should be also successful on the UI
















