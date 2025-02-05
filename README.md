# URL Shortener

A Java-based URL shortener that generates short URLs and redirects users to the original URLs. It provides basic functionality to shorten URLs and handle redirection.

## Features

- **URL Shortening**: Generates a short URL for any given original URL.
- **Redirection**: Redirects users from the short URL to the original URL.
- **In-Memory Storage**: Uses a simple in-memory map to store URL mappings.

## Requirements

- Java Development Kit (JDK)

## Usage

1. Clone the repository or download the source code.
2. Compile the Java files:
   
    ```bash
    javac URLShortener.java
    javac URLShortenerServer.java
    ```

3. Run the server:

    ```bash
    java URLShortenerServer
    ```

4. Use a tool like Postman or `curl` to interact with the server:
   - Send a POST request to `http://localhost:8080/shorten` with the original URL in the body to receive a shortened URL.
   - Access the shortened URL in a browser to be redirected to the original URL.

## Example

### Shortening a URL:
```bash
curl -X POST -d "https://www.example.com" http://localhost:8080/shorten
```

## Screenshots

![image](https://github.com/user-attachments/assets/014c1211-deda-4c34-91e7-037476c7b4d5)
![image](https://github.com/user-attachments/assets/4834ee34-6573-446d-84b2-aabec86677e4)
![image](https://github.com/user-attachments/assets/4fb2f7bc-f693-464b-b0cd-b5fd8c051127)
