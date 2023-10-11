Feature: Login FB with email
Scenario: User login with emailId
  Given launch browser name as br
  When user enter url as "https://www.facebook.com/"
  And enter userid as "abc@gmail.com" and password as "123456"
  Then redirected to homepage
  And close browser
