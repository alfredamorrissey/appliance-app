# Appliance Management App

This appliance management app was built with Vue.js for the frontend and Spring Boot for the backend.

## Installation Instructions

### 1. Clone the Repository

Clone the repo to your local machine:

`git clone https://github.com/alfredamorrissey/appliance-app.git`

Enter the root directory

`cd applianceapp`

Run the API tests (these run automatically when you build the artifact)

`./mvnw test `

You can boot up the api using

`./mvnw spring-boot:run`

You can boot up the vue.js front end

```
cd frontend
npm install
npm run dev
```
App will run on: http://localhost:5173
<br>Spring API runs on: http://localhost:8080

A test user has been added with username: testuser password: password

You can see the H2 Database: http://localhost:8080/h2-console/login.jsp?jsessionid=b5dcba6a4cafbeb12a02e7492a95406a<br>
JDBC Url: jdbc:h2:mem:testdb
username: sa<br>
password: password

