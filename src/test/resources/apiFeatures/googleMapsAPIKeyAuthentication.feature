Feature: Places API Features


  Scenario: Verify GET /findplacefromtext

    Given The Base URI is initialized and the following headers are added
      | fields    | formatted_address,name,rating,opening_hours,geometry,photo |
      | input     | Mike's American Grill                                      |
      | inputtype | textquery                                                  |
      | key       | AIzaSyDdNmHK2RgQVbpksSzAFI6A2byAcdm_5l8                    |
    When I send a GET request to the "/findplacefromtext/json" endpoint
    Then The following should be correct
      | status code       | 200                                                    |
      | formatted_address | 6210 Backlick Rd, Springfield, VA 22150, United States |
      | name              | Mike's American                                        |





