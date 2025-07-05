# 🍽️ FoodFetch

FoodFetch is a RESTful API-based application for managing group food orders. It supports features like creating shared order sessions, submitting personal orders, consolidating items, calculating individual payment summaries with extra fees, and managing restaurant menus.

---

## 🚀 Features

- ✅ Create and manage shared **order sessions**
- ✅ Submit personal orders per session
- ✅ Automatically consolidate duplicate order items across users
- ✅ Split and calculate **extra fees**
- ✅ Generate **payment summaries**
- ✅ Load restaurant menus from JSON
- ✅ Full API versioning (`/api/v1`)
- ✅ Global exception handling with `ProblemDetail`
- ✅ DTO-based architecture
- ✅ Ready for validation and Swagger/OpenAPI integration

---

## 📁 Project Structure

```
src/main/java/com/omarbakr/foodfetch/
│
├── controller/              # REST API controllers
├── dto/                     # DTOs for requests and responses
├── exceptions/              # Custom domain exceptions
├── mapper/                  # DTO <-> Entity mappers
├── model/                   # JPA entities
├── repository/              # Spring Data JPA repositories
├── service/                 # Business logic layer
└── FoodfetchApplication.java
```

---

## 📦 Technologies

- Java 17+
- Spring Boot 3.5
- Spring Web, Spring Data JPA
- H2 / In-memory DB (configurable)
- Jackson for JSON processing

---

## 🧪 Running Locally

### 1. Clone the repository
```bash
git clone https://github.com/your-username/foodfetch.git
cd foodfetch
```

### 2. Build and Run
```bash
./mvnw spring-boot:run
```

By default, the app will start at:  
`http://localhost:8081`

---

## 📬 API Endpoints

### Session Management
- `POST   /api/v1/sessions` – Create a session
- `GET    /api/v1/sessions/{sessionCode}` – Get session details
- `PUT    /api/v1/sessions/{sessionCode}/close` – Close session with extra fees
- `GET    /api/v1/sessions/{sessionCode}/summary` – Payment summary
- `GET    /api/v1/sessions/{sessionCode}/consolidated-items` – Consolidated items

### Person Orders
- `POST   /api/v1/sessions/{sessionCode}/orders` – Submit a personal order

### Restaurant Menus
- `GET    /api/menus/{restaurantName}` – Fetch menu by restaurant

---

## 📝 Sample JSON File

Place your `restaurants.json` under:
```
src/main/resources/restaurants.json
```

Example:
```json
[
  {
    "name": "DOOS",
    "menu": {
      "Main Dishes": {
        "Koshary": 35.0,
        "Spaghetti": 30.0
      },
      "Drinks": {
        "Pepsi": 10.0
      }
    }
  }
]
```

---

## ✅ Next Steps

For a detailed list of what's completed and what remains to be done, refer to the [TODO.md](TODO.md) file.

---

## 📄 License

This project is licensed under the MIT License – see the [LICENSE](LICENSE) file for details.
