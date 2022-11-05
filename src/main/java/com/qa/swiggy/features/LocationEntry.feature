Feature: Swiggy Application Location Entry Test

Scenario: Verify if application navigates to homepage when a city or town is entered in the location

	Given user enters <location> in the Enter your Delivery Location field
	Then navigate to home page
	
	Examples:
	
	|location																																						|
	|Mumbai, Maharashtra, India																													|
	|Mumbai Central railway station building, Mumbai Central, Mumbai, Maharashtra, India|
	|Bengaluru, Karnataka, India																												|
	|Chennai, Tamil Nadu, India																													|
	|Chennai Trade Centre, Ramapuram, Tamil Nadu, India																	|
	|Kodaikanal, Tamil Nadu, India																											|
	|Ooty, Tamil Nadu, India																														|
	
Scenario: Verify if application displays no service error when a remote village is entered in the location
	
	Given user enters <location> in the Enter your Delivery Location field
	Then display no service error message
	
	Examples:
	|location															|
	|Thirukattupalli, Tamil Nadu, India		|
	|Mundakayam, Kerala, India						|
	|Vaduvoor, Tamil Nadu, India					|
	