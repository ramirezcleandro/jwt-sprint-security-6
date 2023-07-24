# Role Based Authentication JWT - Spring boot

Role-based access control allows you to set granular access to your site, or to specific pages. We use JSON Web Tokens (JWT), roles, and redirect rules to grant access to those sections.

## Description
In the project are two roles created(ROLE_ADMIN, ROLE_CLIENT), and two users created, the user "admin" with ROLE_ADMIN role with "123" password, and the user "client" with ROLE_CLIENT role with "123" password.

The project has two endpoints :
1. localhost:8282/api/v1/auth/**
2. localhost:8282/roles/**

The ADMIN_ROLE has access to "roles" end point.

## Usage
### Auth API
The auth API has two endpoint.
1. Sigin
    1. [POST] http://localhost:8282/api/v1/auth/signin
    2. ```javascript
       {"email" : "admin@example.com","password" : "123" }
2. Sigup
      1. [POST] http://localhost:8282/api/v1/auth/signup
    2. ```javascript
        {"firstName" : "Admin","lastName" : "","email" :"admin@gmail.com","password" : "123","enabled" : "true"} 
### Role API
The Role API has five endpoints, you must to include the Bearer Token in the header generated in the "SignIn" end point, the user must be ROLE_ADMIN.
1. Create
    1. [POST] localhost:8282/roles
    2. ```javascript
       {"name" : "ROLE_READ"}
2. Read
      1. [GET] localhost:8282/roles/[id]
3. Update
    1. [PUT] localhost:8282/roles/[id]
    2. ```javascript
       {"name" : "ROLE_READ"}    
4. Update
    1. [DELETE] localhost:8282/roles/[id]
5. List
    1. [GET] localhost:8282/roles/




## License

[MIT](https://choosealicense.com/licenses/mit/)
