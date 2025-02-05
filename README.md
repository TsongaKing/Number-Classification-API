# Number Classification API 🔢

[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-brightgreen)](https://spring.io/projects/spring-boot)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A REST API that analyzes numbers and returns mathematical properties (prime, perfect, Armstrong, even/odd) with fun facts from [NumbersAPI](http://numbersapi.com/). Built for DevOps Stage 1.

**Live Demo**: [https://number-classification-api.herokuapp.com](https://number-classification-api.herokuapp.com)  

---

## Table of Contents 📑
- [Features](#features-)
- [Tech Stack](#tech-stack-)
- [API Documentation](#api-documentation-)
- [Getting Started](#getting-started-)
- [Deployment](#deployment-)
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
- **Dependency Management**: Maven
- **HTTP Client**: RestTemplate
- **Documentation**: OpenAPI/Swagger (optional)
- **Testing**: JUnit 5, Mockito
- **Deployment**: Heroku/Azure

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

## Deployment ☁️

### Heroku
1. Create a Heroku app:
   ```bash
   heroku create your-app-name
   ```
2. Deploy:
   ```bash
   git push heroku main
   ```
3. Open in browser:
   ```bash
   heroku open
   ```

### Azure
Follow the [Azure Spring Apps deployment guide](https://learn.microsoft.com/en-us/azure/spring-apps/).

---

## Testing Examples 🧪

### Valid Requests
```bash
# Armstrong + Odd
curl "https://your-api-url/api/classify-number?number=371"

# Perfect + Even
curl "https://your-api-url/api/classify-number?number=28"

# Prime
curl "https://your-api-url/api/classify-number?number=7"
```

### Invalid Requests
```bash
curl "https://your-api-url/api/classify-number?number=3.14"
curl "https://your-api-url/api/classify-number?number=abc"
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

**Happy number crunching!** 🎉  
