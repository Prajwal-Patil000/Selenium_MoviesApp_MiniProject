## 🎬 Selenium_MoviesApp_MiniProject

### Mini Project — Movies App Automated Testing

In this exercise, you will automate the testing of the **Movies App** using **Selenium**, **TestNG**, and **Cucumber (BDD)**.

---

## 🧩 Prerequisites

1. **Install IntelliJ IDEA Community Edition** (Version 2018.3.6 or later)
2. **Install OpenJDK 11** and complete environment configuration
3. **Download and set up ChromeDriver**
4. **Create a Selenium Project** named `MoviesAppTest`
5. **Update your `pom.xml`** with the necessary Selenium, TestNG, and Cucumber dependencies
6. **Application URL:**
   👉 [https://qamoviesapp.ccbp.tech](https://qamoviesapp.ccbp.tech)

---

## 📁 Project Structure

```
MoviesAppTest/
├── src/
│   └── test/
│       └── java/
│           ├── pages/
│           │   ├── LoginPage.java
│           │   ├── HomePage.java
│           │   ├── PopularPage.java
│           │   ├── SearchPage.java
│           │   ├── MovieDetailsPage.java
│           │   └── AccountPage.java
│           └── tests/
│               ├── LoginPageTest.java
│               ├── HomePageTest.java
│               ├── HeaderSectionTest.java
│               ├── PopularPageTest.java
│               ├── SearchPageTest.java
│               ├── MovieDetailsPageTest.java
│               └── AccountPageTest.java
└── pom.xml
```

---

## 🧠 Concepts Used

* **Selenium WebDriver**
* **TestNG Framework**
* **Page Object Model (POM)**
* **Behavior Driven Development (BDD)** using Cucumber
* **Parallel Testing**

---

## ✅ Test Instructions

### **Pages to Implement**

* Login Page
* Home Page
* Account Page
* Popular Page
* Search Page
* Movie Details Page

> You can create additional helper classes as needed.

---

## 🧪 Test Scenarios

### **1. Login Page Tests**

#### UI Verification

* Website logo is displayed
* Heading text is `"Login"`
* Username label text is `"USERNAME"`
* Password label text is `"PASSWORD"`
* "Login" button is visible
* Close the browser

#### Functional Tests

* Empty input fields
* Empty username
* Empty password
* Invalid credentials (correct username + wrong password)
* Valid credentials (`Username: rahul`, `Password: rahul@2021`)
* Close the browser

---

### **2. Home Page Tests**

* Verify section headings
* Check if play button is displayed
* Validate movie cards are visible in each section
* Verify “Contact Us” section
* Close the browser

---

### **3. Header Section Tests**

#### UI Verification

* Website logo is displayed
* Navbar elements are visible
* Close the browser

#### Functional Tests

* Navigation to Home and Popular pages
* Navigation to Account page
* Close the browser

---

### **4. Popular Page Tests**

* Verify popular movies are displayed
* Click on a movie and verify navigation to its details page
* Close the browser

---

### **5. Search Page Tests**

* Search for valid movies (`Avatar`, `Venom`, etc.) and verify results
* Search for invalid text and check error message or empty state image
* Close the browser

---

### **6. Movie Details Page Tests**

* From Home Page, click on a movie and verify all UI elements
* From Popular Page, click on a movie and verify all UI elements
* Close the browser

---

### **7. Account Page Tests**

* Verify all UI elements on the account page
* Test the Logout functionality
* Close the browser

---

## ⚙️ Running the Tests

* Use **TestNG.xml** or Cucumber runner for execution
* Enable **parallel testing** where applicable
* Example Maven command:

  ```bash
  mvn clean test
  ```

---

## 🧰 Tools & Frameworks

* **Java 11**
* **Selenium 4**
* **TestNG**
* **Cucumber**
* **ChromeDriver**
* **Maven**

---

## 🏷️ Tags

`#Selenium` `#TestNG` `#AutomationTesting` `#MoviesApp
---

Would you like me to generate a **matching `pom.xml`** (with Selenium + TestNG + Cucumber dependencies) that 
