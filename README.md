# Number Classification API 🔢

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Azure](https://img.shields.io/badge/Deployment-Azure%20Spring%20Apps-0089D6)](https://azure.microsoft.com/en-us/products/spring-apps)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A REST API that analyzes numbers and returns mathematical properties (prime, perfect, Armstrong, even/odd) with fun facts from [NumbersAPI](http://numbersapi.com/). Deployed on **Azure Spring Apps**.

**Live Demo**: [https://your-app-name.azuremicroservices.io](https://your-app-name.azuremicroservices.io)  

---

## Table of Contents 📑
- [Features](#features-)
- [Tech Stack](#tech-stack-)
- [API Documentation](#api-documentation-)
- [Getting Started](#getting-started-)
- [Azure Deployment](#azure-deployment-)
- [Testing Examples](#testing-examples-)
- [Contributing](#contributing-)

---

## Features ✨
- 🧮 **Number Classification**: Checks if a number is prime, perfect, Armstrong, even/odd.
- 🔍 **Digit Sum Calculation**: Computes the sum of a number's digits.
- 🎉 **Fun Facts**: Fetches interesting math facts from NumbersAPI.
- 🛡️ **Error Handling**: Returns structured JSON errors for invalid inputs.
- 🌍 **CORS Support**: Pre-configured for cross-origin requests.

---

## Tech Stack 🛠️
- **Backend**: Java 17, Spring Boot 3.2.2
- **Cloud Deployment**: Azure Spring Apps
- **Dependency Management**: Maven
- **HTTP Client**: RestTemplate
- **Testing**: JUnit 5, Mockito

---

## API Documentation 📚

### Endpoint
```http
GET /api/classify-number?number={number}
```

### Parameters
| Name     | Type   | Required | Example  |
|----------|--------|----------|----------|
| `number` | String | Yes      | `371`    |

### Responses

**200 OK**  
```json
{
    "number": 371,
    "is_prime": false,
    "is_perfect": false,
    "properties": ["armstrong", "odd"],
    "digit_sum": 11,
    "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}
```

**400 Bad Request**  
```json
{
    "number": "alphabet",
    "error": true
}
```

---

## Getting Started 🚀

### Prerequisites
- Java 17+
- Maven 3.9+
- [Azure CLI](https://docs.microsoft.com/en-us/cli/azure/install-azure-cli)
- Azure Subscription ([Free Trial](https://azure.microsoft.com/en-us/free/))

### Local Setup
1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/number-classification-api.git
   cd number-classification-api
   ```
2. Build and run:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. Test locally:
   ```bash
   curl "http://localhost:8080/api/classify-number?number=371"
   ```

---

## Azure Deployment ☁️

### Step 1: Prepare Azure Environment
1. **Login to Azure CLI**:
   ```bash
   az login
   ```
2. **Install Azure Spring Apps Extension**:
   ```bash
   az extension add --name spring
   ```

### Step 2: Create Azure Resources
1. **Create a Resource Group**:
   ```bash
   az group create --name your-resource-group --location eastus
   ```
2. **Create an Azure Spring Apps Instance**:
   ```bash
   az spring create --name your-spring-apps-service --resource-group your-resource-group --location eastus
   ```

### Step 3: Deploy the Application
1. **Build the JAR**:
   ```bash
   mvn clean package -DskipTests
   ```
2. **Deploy to Azure Spring Apps**:
   ```bash
   az spring app deploy \
       --name number-classification-api \
       --resource-group your-resource-group \
       --service your-spring-apps-service \
       --runtime-version Java_17 \
       --artifact-path target/number-classification-api-1.0-SNAPSHOT.jar
   ```

### Step 4: Access the Deployed API
1. **Get the Public URL**:
   ```bash
   az spring app show --name number-classification-api --resource-group your-resource-group --service your-spring-apps-service --query properties.url
   ```
2. **Test the Live API**:
   ```bash
   curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=371"
   ```

---

## Testing Examples 🧪

### Valid Requests
```bash
# Armstrong + Odd
curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=371"

# Perfect + Even
curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=28"

# Prime
curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=7"
```

### Invalid Requests
```bash
curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=3.14"
curl "https://your-app-name.azuremicroservices.io/api/classify-number?number=abc"
```

---

## Contributing 🤝
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit changes:
   ```bash
   git commit -m "Add your feature"
   ```
4. Push to branch:
   ```bash
   git push origin feature/your-feature
   ```
5. Open a Pull Request.

---

## License 📄
Distributed under the MIT License. See [LICENSE](LICENSE) for details.

---

**Happy number crunching on Azure!** 🚀  
