# Read Me First
 
This project requires Java v21, maven and docker

### Build
The following describes the run process for this app
Steps:

    * mvn clean package
    * docker build -t stockexapp .
    * docker run -p 8090:8090 stockexapp

##### Security User Info
Project uses Basic Auth process and includes a pre-user for authentication

    username: myuser
    pass: pass1

##### Test Cases
Test cases can be found under src/test/stock exchange.postman_collection.json

