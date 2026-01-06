# Spring Security POC
> Proof of concept using modern Spring Security and JWT (tested with Spring Security 7.0.2).

## Summary
Spring Security changes over time, I create my POC of Spring Security authentication.

This is a lightweight demo showing registration, authentication, JWT-based RBAC and a stateless security filter chain with `DaoAuthenticationProvider`.

## Main features
- Registration and login (`/api/auth/*`)
- JWT authentication
- Role-Based Access Control (RBAC) for `/api/users` and `/api/admins`
- Stateless JWT filter chain and `DaoAuthenticationProvider`

## Endpoints
- `POST /api/auth/register-user` — register a new user (public)
- `POST /api/auth/register-admin` — register a new admin (public)
- `POST /api/auth/login` — authenticate and receive JWT (public)
- `GET /api/users` — protected; requires a user role
- `GET /api/admins` — protected; requires a admin role

## Quick start

Prerequisites:
- JDK 21
- Maven

Build and run:
```bash
mvn -DskipTests package
java -jar target/spring-security-jwt-0.0.1-SNAPSHOT.jar
```

Try the APIs with curl:
The jwt token can be obtained in the `Authorization` header of the login response.
```sh
# Register user
curl -X POST http://localhost:8080/api/auth/register-user \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"<your_password>"}'

# Register admin
curl -X POST http://localhost:8080/api/auth/register-admin \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"<your_password>"}'

# Login
curl -i -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"alice","password":"<your_password>"}'
  
# Get /api/users with Bearer token
curl -i -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer <YOUR_JWT_TOKEN>"

# Get /api/admins with Bearer token
curl -i -X GET http://localhost:8080/api/admins \
  -H "Authorization: Bearer <YOUR_JWT_TOKEN>"
```