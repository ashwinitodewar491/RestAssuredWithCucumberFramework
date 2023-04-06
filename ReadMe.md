Brief on usage of functions
Given: Input details such as path  param , query param, headers, request body
//When: Method we want to use(get,put,post,patch,delete,update), resource url
//Then: All types of assertion and validations can be done here
//Keywords:
//extract(): This function extract api response details once api is triggered
//.assertThat().statusCode(): To assert status code
//response():To fetch response
//asString(): To convert json response as string
//auth(): For adding authentication if it is required field for triggering api
//basic(): need to provide username and password, just like this there are other auths too
// JsonPath: This is inbuilt class available in rest assured jar which is used to extract json response to validate response body