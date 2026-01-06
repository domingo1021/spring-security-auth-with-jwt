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