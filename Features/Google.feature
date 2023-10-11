Feature: Google Search
  @Rashmika
  Scenario: Search actress image
    Given launch browser name as br
    When user enter url as "https://www.google.com/"
    And enter actress as "Rashmika"
    Then redirected to page title as "Rashmika - Google Search"
    Then close browser

    @Actress
    Scenario Outline: Search actresses
      Given launch browser name as br
      When user enter url as "https://www.google.com/"
      And enter actress_name as "<actress>"
      Then close browser
Examples:
      |actress|
      |Samantha|
      |Nayanthara|
      |Sonam Kapoor|
      |Pooja Hegde|