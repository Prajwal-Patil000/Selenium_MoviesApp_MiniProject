
# Selenium_MoviesApp_MiniProject
🎬 Automation testing suite using Java, Selenium WebDriver &amp; TestNG for a Movies Web App. Implements Page Object Model (POM), reusable utilities, and TestNG reporting to ensure reliable UI validation and maintainable test design.


Perfect 👍 — you want a **README.md** written in that same clean, educational, and project-demo style.
Here’s a polished version for your repo **Selenium_MoviesApp_MiniProject**, matching the format you showed:

---

```markdown
## 🎬 Selenium_MoviesApp_MiniProject

### Mini Project — Movies App Automated Testing

This project automates the testing of the **Movies Web Application** using **Selenium WebDriver**, **TestNG**, and **Cucumber (BDD)**.  
It validates all core functionalities — from login to navigation, searching, viewing movie details, and user account management — ensuring a complete end-to-end testing workflow.

---

## 🧩 Prerequisites

1. **Install IntelliJ IDEA Community Edition** (Version 2018.3.6 or later)
2. **Install OpenJDK 11** and configure the environment variables
3. **Download and set up ChromeDriver** compatible with your Chrome version
4. **Create a Selenium Project** named `Selenium_MoviesApp_MiniProject`
5. **Update your `pom.xml`** with dependencies for Selenium, TestNG, and Cucumber
6. **Application URL:**
   👉 [https://qamoviesapp.ccbp.tech](https://qamoviesapp.ccbp.tech)

---

## 🧠 Concepts Used

* **Selenium WebDriver**
* **TestNG Framework**
* **Page Object Model (POM)**
* **Behavior Driven Development (BDD)** using **Cucumber**
* **Parallel Test Execution**
* **Maven Build Management**

---

## ✅ Test Implementation

### **Pages Automated**

* Login Page  
* Home Page  
* Header Section  
* Popular Page  
* Search Page  
* Movie Details Page  
* Account Page  

---

## 🧪 Test Scenarios

### **1. Login Page Tests**

#### UI Verification
* Website logo is displayed  
* Heading text is `"Login"`  
* Username and Password labels are correct  
* Login button is visible  

#### Functional Tests
* Empty field validation  
* Invalid credentials  
* Valid credentials (`Username: rahul`, `Password: rahul@2021`)  
* Browser closes properly  

---

### **2. Home Page Tests**

* Verify section headings  
* Check movie cards in each section  
* Validate play button functionality  
* Verify “Contact Us” section  
* Close browser  

---

### **3. Header Section Tests**

* Verify site logo and navigation links  
* Navigate to Home, Popular, and Account pages  
* Close browser  

---

### **4. Popular Page Tests**

* Verify popular movie cards  
* Click a movie and validate navigation to Movie Details page  
* Close browser  

---

### **5. Search Page Tests**

* Search valid movies (`Avatar`, `Venom`, etc.)  
* Search invalid text — verify error or empty state  
* Close browser  

---

### **6. Movie Details Page Tests**

* From Home Page → click a movie → verify details  
* From Popular Page → click a movie → verify details  
* Validate all UI elements (title, genre, release year, rating, description)  
* Close browser  

---

### **7. Account Page Tests**

* Verify all UI elements  
* Test Logout functionality  
* Close browser  

---

## ⚙️ Running the Tests

* Run using **TestNG.xml** or Cucumber Runner  
* Enable **parallel testing** where supported  

```bash
mvn clean test
````

---

## 🧰 Tools & Frameworks

* **Java 11**
* **Selenium 4**
* **TestNG**
* **Cucumber (BDD)**
* **ChromeDriver**
* **Maven**

---

## 📁 Project Structure

```

Selenium_MoviesApp_MiniProject/
│
├── src/
│ └── test/
│ └── java/
│ ├── pages/
│ │ ├── LoginPage.java
│ │ ├── HomePage.java
│ │ ├── PopularPage.java
│ │ ├── SearchPage.java
│ │ ├── MovieDetailsPage.java
│ │ └── AccountPage.java
│ │
│ └── tests/
│ ├── LoginPageTest.java
│ ├── HomePageTest.java
│ ├── HeaderSectionTest.java
│ ├── PopularPageTest.java
│ ├── SearchPageTest.java
│ ├── MovieDetailsPageTest.java
│ └── AccountPageTest.java
│
└── pom.xml

````


## 🏷️ Tags

`#Selenium` `#TestNG` `#Cucumber` `#BDD` `#AutomationTesting` `#MoviesApp` `#Java` `#Maven` `#PageObjectModel`

---

## 👨‍💻 Author

**Prajwal Patil**
🔗 [GitHub Profile](https://github.com/Prajwal-Patil000)
📧 *[prajwalmpatil000@gmail.com](mailto:prajwalmpatil000@gmail.com)*






---
