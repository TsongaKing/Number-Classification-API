markdown

# Number Classification API 🌟  
**DevOps Stage 1 - Number Classification API**  

A REST API that classifies numbers and returns their mathematical properties (prime, perfect, Armstrong, even/odd) along with fun facts from [NumbersAPI](http://numbersapi.com/).  

---

## Features ✨  
- **Classify Numbers**: Determine if a number is prime, perfect, Armstrong, even/odd, and its digit sum.  
- **Fun Facts**: Fetch interesting mathematical facts from NumbersAPI.  
- **Error Handling**: Returns `400 Bad Request` for invalid inputs.  
- **CORS Support**: Pre-configured for cross-origin requests.  
- **Deployment Ready**: Optimized for Heroku/Azure deployment.  

---

## Technologies 🛠️  
- **Java 17** | **Spring Boot 3.2.2**  
- **Lombok** | **RestTemplate** | **Maven**  
- **Heroku/Azure** (Deployment)  
- **JUnit 5** | **Mockito** (Testing)  
- **OpenAPI/Swagger** (Optional Documentation)  

---

## API Documentation 📚  

### Endpoint  
`GET /api/classify-number`  

#### Parameters  
| Name   | Type   | Required | Description          |  
|--------|--------|----------|----------------------|  
| `number` | String | Yes      | Integer to classify. |  

#### Responses  
**200 OK**  
```json
{
    "number": 371,
    "is_prime": false,
    "is_perfect": false,
    "properties": ["armstrong", "odd"],
    "digit_sum": 11,
    "fun_fact": "371 is an Armstrong number."
}
400 Bad Request

json
Copy
{
    "number": "alphabet",
    "error": true
}
Setup & Run 🚀
Prerequisites
Java 17

Maven 3.9+

Steps
Clone the Repository

bash
Copy
git clone https://github.com/your-username/number-classification-api.git
Build & Run

bash
Copy
cd number-classification-api
mvn clean install
mvn spring-boot:run
Test Locally

bash
Copy
curl "http://localhost:8080/api/classify-number?number=371"
Deployment ☁️
Heroku
Create a Procfile in the root directory:

plaintext
Copy
web: java -jar target/number-classification-api-1.0-SNAPSHOT.jar
Push to Heroku:

bash
Copy
heroku create
git push heroku main
Azure
Follow Azure Spring Apps deployment guide.

Testing Examples 🧪
Valid Requests
bash
Copy
# Armstrong + Odd
curl "https://your-api-url/api/classify-number?number=371"  

# Even
curl "https://your-api-url/api/classify-number?number=28"  

# Prime
curl "https://your-api-url/api/classify-number?number=7"  
Invalid Requests
bash
Copy
curl "https://your-api-url/api/classify-number?number=abc"  
Contribution 🤝
Contributions are welcome!

Fork the repository.

Create a feature branch (git checkout -b feature/your-feature).

Commit changes (git commit -m 'Add your feature').

Push to the branch (git push origin feature/your-feature).

Open a Pull Request.

License 📄
MIT License. See LICENSE for details.

Happy Classifying! 🎉


This README includes:  
- Clear usage examples  
- Deployment guides  
- API response formats  
- Contribution guidelines  
- Links to external tools/APIs  
