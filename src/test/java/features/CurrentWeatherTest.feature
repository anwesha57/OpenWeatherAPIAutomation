Feature: Current Weather test 

@Regression @Smoke
Scenario Outline: Verify city name is as expected in current weather API response body
	Given <Latitude> and <Longitude> of the city for which wether report has to fetch
	When user calls currentweather api with get http request
	Then the API call returns status code 200
	And <id> <timezone> and <cityname> in response body are fetched accordingly
	
Examples:
|Latitude 	 | Longitude | id			|cityname				|timezone|
|44.34			 |10.99			 |3163858 |"Zocca"				|7200    |
|51.50			 |0.1276		 |7302135 |"Abbey Wood" 	|3600    |

@Regression 
Scenario Outline: Verify the API call get fail with status code 400 when user passes invalid latitude
	Given <Latitude> and <Longitude> of the city for which wether report has to fetch
	When user calls currentweather api with get http request
	Then the API call returns status code 400	
	And "message" in response body is "wrong latitude"
Examples:
|Latitude 	 | Longitude |
|467.34		   |10.99			 |

@Regression
Scenario Outline: Verify the API call get fail with status code 400 when user passes invalid longitude
	Given <Latitude> and <Longitude> of the city for which wether report has to fetch
	When user calls currentweather api with get http request
	Then the API call returns status code 400
	And "message" in response body is "wrong longitude"
Examples:
|Latitude 	 | Longitude |
|44.34		 	 |190.99	 	 |	
	
@Regression
Scenario Outline: Verify the API call get fail with status code 401 when user passes invalid app id
	Given <Latitude> <Longitude> and <InvalidAppid> of the city for which weather report api has to be tested 
	When user calls currentweather api with get http request
	Then the API call returns status code 401
	And "message" in response body is "Invalid API key. Please see https://openweathermap.org/faq#error401 for more info."
Examples:
|Latitude 	 | Longitude |InvalidAppid										 |
|44.34		 	 |10.99	 		 |"7ff603a9af53be5e9303c470f762a59"|
 
