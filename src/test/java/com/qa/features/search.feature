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
    | tops           |
   
   
Scenario Outline: Search with invalid Text
   
Given I am  on Product page to do a single keyword search
When  I do search for "<invalidtext>"
Then correct validation message must be shown

 Examples:
    | invalidtext     | 
    | bdfggh@         |
    | gh%$            |


Scenario Outline: Search with Empty Text
   
Given I am  on Product page to do a single keyword search
When  I do search for ""
Then  All products must be shown


Scenario Outline: Verify whether search text box and search button are shown in UI
Given  I am  on Product page to do a single keyword search
When I click on search text box 
And  I click on search button
Then Search text must have placeholder and Search button must be clickable


   
    
  