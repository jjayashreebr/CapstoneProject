Feature: Search
 	I want to search for products
	So that I can quickly find what I am looking for

 Scenario Outline: Search with valid Search Text
      
    Given I am  on Product page to do a single keyword search
    When I do search for "<searchtext>"
    Then I see search result page with more than zero results
    
 Examples:
    | searchtext     | 
    | blue           | 
    | top            | 
    | frozen         | 