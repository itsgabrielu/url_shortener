# Url_shortener

Stack: BE: Spring Boot, FE: React

To get started:

* You will need to have [SpringBoot & Maven](https://docs.spring.io/spring-boot/docs/1.2.0.M2/reference/html/getting-started-installing-spring-boot.html), as well as [NPM](https://www.npmjs.com/get-npm), set up. Otherwise, follow the links above.

1. Clone this repo into folder of choice.
2. Install Springboot packages via Maven: 
```bash
    mvn install
```
3. Run the maven packages (default port: 8080)
```bash
    ./mvnw spring-boot:run
```
4. In a new terminal window, navigate to the same folder, but step into front-end folder and install the npm packages
```bash
    cd front-end; npm i
```
5. Within this folder, initialize the node project (default port: 3000)
```
    npm start
```

*If you do change any of the ports, you will have to adjust the CORS or frontend API call references.*

