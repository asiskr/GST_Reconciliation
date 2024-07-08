@order7

Feature: Extract the Gst values of balance sheet from MYOB software 

	Scenario:  User want to extract Gst values of balance sheet
	
		Given User is on the  report page 
		When user click on Balance Sheet report
		Then User enter the to date at balance sheet page
		Then user extract GSTCollected,GSTPaid, GSTActual, GSTLastYear
		When user click on the reporting and reports button