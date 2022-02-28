Feature: Search
Verify whether the search result reflect the search keyword

 Scenario: Login with valid credentials
      
    Given User is on Product page
    When User enters search as "<searchtext>"
    Then User should be able to login sucessfully

  Examples:
    | searchtext     | 
    | Friday         | 
    | Sunday         | 
    | anything else  | 