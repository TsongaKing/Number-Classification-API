Here's the final **README.md** in GitHub-flavored markdown ready for copy-paste:

```markdown
# Number Classification API 🔢

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Azure](https://img.shields.io/badge/Deployment-Azure%20Spring%20Apps-0089D6)](https://azure.microsoft.com/en-us/products/spring-apps)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A REST API that analyzes numbers and returns mathematical properties with fun facts from [NumbersAPI](http://numbersapi.com/). Deployed on **Azure Spring Apps**.

**Live Demo**: [https://numberclassification.azurewebsites.net](https://numberclassification.azurewebsites.net/api/classify-number?number=371)

---

## Table of Contents 📑
- [Features](#features-)
- [Tech Stack](#tech-stack-)
- [API Documentation](#api-documentation-)
- [Getting Started](#getting-started-)
- [Azure Deployment](#azure-deployment-)
- [Testing Examples](#testing-examples-)
- [Contributing](#contributing-)
- [License](#license-)

---

## Features ✨
- 🧮 **Number Classification**  
  Checks prime, perfect, Armstrong, even/odd, positive/negative status
- 🔍 **Digit Analysis**  
  Calculates sum of digits and special properties
- 🎉 **Fun Facts Integration**  
  Fetches interesting math facts from NumbersAPI
- 🌐 **Full Numeric Support**  
  Handles integers, floats, and negative values
- 🛡️ **Robust Error Handling**  
  JSON error responses with proper HTTP status codes
- 🔄 **CORS Ready**  
  Pre-configured for cross-origin requests

---

## Tech Stack 🛠️
| Component               | Technology                          |
|-------------------------|-------------------------------------|
| **Backend Framework**   | Spring Boot 3.2.2                   |
| **Cloud Platform**      | Azure Spring Apps                   |
| **Build Tool**          | Apache Maven                        |
| **API Documentation**   | Spring Web MVC                      |
| **Validation**          | Spring Validation                   |
| **Testing**             | JUnit 5, Mockito                    |
| **Utilities**           | Lombok, Jackson                     |

---

## API Documentation 📚

### Endpoint
```http
GET /api/classify-number?number={value}
```

### Parameters
| Name     | Type   | Required | Example     |
|----------|--------|----------|-------------|
| `number` | String | Yes      | 371, -5.5   |

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
    "number": "abc",
    "error": true
}
```

---

## Getting Started 🚀

### Prerequisites
- Java 17+
- Maven 3.9+
- [Azure CLI](https://docs.microsoft.com/cli/azure/install-azure-cli)

### Local Development
1. Clone repository:
```bash
git clone https://github.com/yourusername/number-classification-api.git
cd number-classification-api
```

2. Build and run:
```bash
mvn clean package
mvn spring-boot:run
```

3. Test endpoint:
```bash
curl "http://localhost:8080/api/classify-number?number=28"
```

---

## Azure Deployment ☁️

1. Create Azure resources:
```bash
az login
az group create --name num-classification-rg --location eastus
az spring create --name num-classification-service --resource-group num-classification-rg
```

2. Deploy application:
```bash
mvn clean package -DskipTests
az spring app deploy \
    --name number-classification-api \
    --resource-group num-classification-rg \
    --service num-classification-service \
    --runtime-version Java_17 \
    --artifact-path target/number-classification-api-1.0-SNAPSHOT.jar
```

3. Get endpoint URL:
```bash
az spring app show --name number-classification-api \
    --resource-group num-classification-rg \
    --service num-classification-service \
    --query properties.url
```

---

## Testing Examples 🧪

### Valid Requests
```bash
# Armstrong Number
curl "https://your-app.azuremicroservices.io/api/classify-number?number=371"

# Negative Prime
curl "https://your-app.azuremicroservices.io/api/classify-number?number=-7"
```

### Edge Cases
```bash
# Floating-Point
curl "https://your-app.azuremicroservices.io/api/classify-number?number=3.14"

# Zero Handling
curl "https://your-app.azuremicroservices.io/api/classify-number?number=0"
```

---

## Contributing 🤝
1. Fork the repository
2. Create feature branch:
```bash
git checkout -b feature/amazing-feature
```
3. Commit changes:
```bash
git commit -m 'Add amazing feature'
```
4. Push to branch:
```bash
git push origin feature/amazing-feature
```
5. Open a Pull Request

---

## License 📄
Distributed under the MIT License. See [LICENSE](LICENSE) for details.

---

**Happy Number Crunching!** 🚀🔢
```

Just copy this entire content into a new `README.md` file in your repository root. Replace placeholder URLs (`yourusername`, `your-app.azuremicroservices.io`) with your actual values.
