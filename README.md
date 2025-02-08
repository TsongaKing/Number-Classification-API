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
  Checks if a number is prime, perfect, Armstrong, even/odd, positive/negative
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
Parameters
Name	Type	Required	Description	Example
number	String	Yes	Numeric value to analyze	371, -5.5
Response Structure
json
Copy
{
    "number": 371,
    "is_prime": false,
    "is_perfect": false,
    "properties": ["armstrong", "odd"],
    "digit_sum": 11,
    "fun_fact": "371 is an Armstrong number because 3^3 + 7^3 + 1^3 = 371"
}
Error Handling
Status Code	Scenario	Example Response
400	Invalid/Missing Parameter	{"number": "abc", "error": true}
500	Server Error	{"number": "server_error", "error": true}
Getting Started 🚀
Prerequisites
Java 17+

Maven 3.9+

Azure Account (Free Trial)

Local Development
Clone repository:

bash
Copy
git clone https://github.com/yourusername/number-classification-api.git
cd number-classification-api
Build and run:

bash
Copy
mvn clean package
mvn spring-boot:run
Test endpoint:

bash
Copy
curl "http://localhost:8080/api/classify-number?number=28"
Azure Deployment ☁️
1. Azure Setup
bash
Copy
az login
az extension add --name spring
az group create --name num-classification-rg --location eastus
az spring create --name num-classification-service --resource-group num-classification-rg
2. Build & Deploy
bash
Copy
mvn clean package -DskipTests
az spring app deploy \
    --name number-classification-api \
    --resource-group num-classification-rg \
    --service num-classification-service \
    --runtime-version Java_17 \
    --artifact-path target/number-classification-api-1.0-SNAPSHOT.jar
3. Get Endpoint URL
bash
Copy
az spring app show --name number-classification-api \
    --resource-group num-classification-rg \
    --service num-classification-service \
    --query properties.url
Testing Examples 🧪
Valid Requests
bash
Copy
# Armstrong Number
curl "https://your-app.azuremicroservices.io/api/classify-number?number=371"

# Perfect Number
curl "https://your-app.azuremicroservices.io/api/classify-number?number=28"

# Negative Number
curl "https://your-app.azuremicroservices.io/api/classify-number?number=-7"
Edge Cases
bash
Copy
# Floating-Point
curl "https://your-app.azuremicroservices.io/api/classify-number?number=3.14"

# Zero Handling
curl "https://your-app.azuremicroservices.io/api/classify-number?number=0"
Contributing 🤝
Fork the repository

Create your feature branch:

bash
Copy
git checkout -b feature/amazing-feature
Commit changes:

bash
Copy
git commit -m 'Add amazing feature'
Push to branch:

bash
Copy
git push origin feature/amazing-feature
Open a Pull Request

License 📄
Distributed under the MIT License. See LICENSE for details.

Happy Number Crunching! 🚀🔢
