Feature: Swiggy Application SignUp Test

Scenario: Verifies if the message 'Mobile Number Already Exists' is displayed when signed up with already existing details
	
		Given user opens the signUp page
		Then  already existing details are entered and proceeded, display appropriate message
		
Scenario: Click on Login link in the sign up page and verify the login page fields
	
		Given user opens the signUp page
		When user clicks on login page link
		Then verify the login page fields
		
Scenario: Enter all mandatory details in sign up page and click continue to verify the login page fields

		Given user opens the signUp page
		When user signs up with new details and click continue
		Then Verify the navigation to login page with given number prefilled in the phone number text box