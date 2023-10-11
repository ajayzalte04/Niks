Feature: Myntra Shopping for mens
  Scenario: Shop jacket for men with l size
    Given launch browser name as br
    When user enter url as "https://www.myntra.com/"
    And enter product name in search and click search
    Then redirected to page title as type
    And close browser
