Feature: Swiggy Application Login Test

Scenario: Verify if OTP fields are displayed when valid phone number is entered in the login page

	When user enters valid phone number in login page and checks the OTP fields
	
Scenario: Verify the page navigation to sign up page when invalid phone number is entered 
and validate the corresponding sign up page fields

	When invalid phone number is entered in the login page
	Then validate the navigation to sign up page and verify the respective fields
	
Scenario: Click on Create Account and validate the sign up page fields
	
	When user clicks on Create Account link
	Then Verify the sign up page fields
	