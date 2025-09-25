# Labseq

This project implements a solution for the Labseq sequence problem, consisting of a Quarkus-based Java backend and a simple Angular frontend GUI. The backend provides a RESTful API to calculate the sequence's values, while the frontend offers a user-friendly interface to query the API.

The Labseq sequence is defined as follows:
* `I(0) = 0`
* `I(1) = 1`
* `I(2) = 0`
* `I(3) = 1`
* `I(n) = I(n-4) + I(n-3)` for `n > 3`




## 🚀 Execution Instructions

To run the full application, you must start both the backend and the frontend services.

#### Option 1: Using Docker Compose (Recommended)

    docker-compose down
    docker-compose up --build

The frontend will be available at `http://localhost:4200` and the backend API at `http://localhost:8080`.

#### Option 2: Running Individually

### 1. Backend


    cd backend
    ./mvnw quarkus:dev


 The API will be available at `http://localhost:8080/labseq/{n}` where *n* represents the index of the sequence.

### 2. Frontend

    npm install
    ng serve --open

Open the app at `http://localhost:4200`.


### 📖 REST API Documentation

The backend includes auto-generated OpenAPI documentation. Once the Quarkus application is running, you can access the Swagger UI to interact with the API endpoints.

* **Swagger UI**: `http://localhost:8080/q/swagger-ui/`


## 🛠️ Technologies Used

### Backend
* **Quarkus Framework**: A Kubernetes-native Java framework for building high-performance applications.
* **Maven**: Dependency management and build automation.
* **Java**: The core programming language.
* **`java.math.BigInteger`**: Used to handle potentially large numbers in the sequence.
* **OpenAPI**: For generating REST API documentation (Swagger UI).
* **Quarkus Cache**: Implements the caching mechanism for the sequence's values.

### Frontend
* **Angular**: A platform for building web applications.
* **TypeScript**: A typed superset of JavaScript.
* **Tailwind CSS**: A utility-first CSS framework for styling.

### Containerization
* **Docker Compose**: Used to manage and run both the frontend and backend services in isolated containers.
