This application is build for the GCE-IS Digital Challenge.
The follwoing technologies are used:
Server side: Spring boot, Gradle, Rest API, Google Geocoding APIS, H2 in memory database, JUnit
	To get functionality of Google's geocoding PAIS we need to pass API key in application.properties file.

Client Side: angular5, bootstrap
	
To start the server application:
	run com.gcesolutions.shopmanager.ShopManagerApplication class 
	
To start client application:
	 goto shop-manager\shop-manager-client directory and run the following command:
 		ng serve --open  
 	
 	
API documentations
Find all shops:
URL
	/shops
Method:
	GET
Success Response:
	Code: 200
	Contnent: 
		[
			{
				"id": 1,
				"name": "Neev Supplier",
				"category": "Supermarket",
				"address": {
					"address": "1301 N A W Grimes BLVD",
					"city": "Round Rock",
					"state": "Texas",
					"zipCode": 78665,
					"country": "USA"
				},
				"ownerName": "Nand Joshi",
				"longitude": 2.0,
				"latitude": 2.0,
				"createdAt": 1565366117000,
				"updatedAt": 1565366117000
			}
		]
		
To search shops by name
URL:
	/shops/Name/:name
Method:
	GET
URL Params:
	Required:
	name=[String]
Success Response:
	Code: 200
	Contnent: 
		[
			{
				"id": 1,
				"name": "Neev Supplier",
				"category": "Supermarket",
				"address": {
					"address": "1301 N A W Grimes BLVD",
					"city": "Round Rock",
					"state": "Texas",
					"zipCode": 78665,
					"country": "USA"
				},
				"ownerName": "Nand Joshi",
				"longitude": 2.0,
				"latitude": 2.0,
				"createdAt": 1565366117000,
				"updatedAt": 1565366117000
			}
		]

To search shops by near by location
URL:
	/shops/Location/:address
Method:
	GET
URL Params:
	Required:
	address=[String]
Success Response:
	Code: 200
	Contnent: 
		[
			{
				"id": 1,
				"name": "Neev Supplier",
				"category": "Supermarket",
				"address": {
					"address": "1301 N A W Grimes BLVD",
					"city": "Round Rock",
					"state": "Texas",
					"zipCode": 78665,
					"country": "USA"
				},
				"ownerName": "Nand Joshi",
				"longitude": 2.0,
				"latitude": 2.0,
				"createdAt": 1565366117000,
				"updatedAt": 1565366117000
			}
		]	