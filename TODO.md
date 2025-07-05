# ✅ FoodFetch Project – TODO Tracker

This document tracks the progress of project features, design, and improvements.

---

## ✅ Completed

- [x] Use of DTOs (Request/Response separation)
- [x] Custom exceptions for domain-specific error handling
- [x] Global exception handling using `@ControllerAdvice` and `ProblemDetail`
- [x] Clean separation between Controller, Service, and Repository layers
- [x] Menu loading from JSON file
- [x] Consolidated order calculation
- [x] Payment summary calculation
- [x] API versioning (`/api/v1`)
- [x] Tested endpoints using Postman

---

## 🛠️ In Progress / TODO

### 🔍 Validation
- [ ] Add `javax.validation` annotations (e.g., `@NotBlank`, `@Positive`) to DTOs
- [ ] Add `@Valid` to controller methods
- [ ] Add exception handling for `MethodArgumentNotValidException`

---

### 📘 API Documentation (Swagger/OpenAPI)
- [ ] Add SpringDoc OpenAPI dependency
- [ ] Generate Swagger UI (`http://localhost:8081/swagger-ui.html`)
- [ ] Annotate DTOs and controllers with `@Operation`, `@Schema` (optional)

---

### ✅ Testing
- [ ] Add unit tests for services using JUnit and Mockito
- [ ] Add integration tests for controllers using `@WebMvcTest` or `@SpringBootTest`
- [ ] Test edge cases (invalid input, duplicate orders, etc.)

---

### 📦 Logging
- [ ] Add SLF4J logging to services and controllers
- [ ] Log incoming requests and key processing steps
- [ ] Log exceptions in service layer before they bubble up

---

### 🧪 Database Integration
- [ ] Use PostgreSQL or MySQL (currently using in-memory or static JSON)
- [ ] Configure `application.yml` for DB connection
- [ ] Migrate entities and repositories to use real DB

---

### 🔐 Security (optional)
- [ ] Integrate Spring Security
- [ ] Protect endpoints (basic auth or JWT)
- [ ] Add user roles if needed

---

### 🐳 Dockerization (optional)
- [ ] Create Dockerfile for application
- [ ] Create `docker-compose.yml` to run app + DB together
- [ ] Add `.dockerignore` file

---

### 🔄 CI/CD Setup (optional)
- [ ] Set up GitHub Actions or GitLab CI
- [ ] Add steps to run tests, build app, and push Docker image

---

## 🔚 Final Notes

Focus first on **Validation** and **Swagger**, then move on to **Testing** and **Database Integration**. Other steps like Security, Docker, and CI/CD can follow when you're ready for deployment or scaling.

