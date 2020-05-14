# Hobby Web App: GameTimeLog

My second individual project with QA, started as an Academy Trainee.

Summary: This application aims to be a "Game Time Log" where users can perform CRUD functions on themselves and records of their game sessions.

MVP: A functional ‘front-end’ web app (and integrated APIs) which connect to a back-end written in Java.
<details>
<summary>Technology used in the project...</summary>
- Back-end: Java source code using Spring libraries
- Database: SQL database hosted on Google Cloud Platform
- Front-end: html, css and javascript
- Source Control: Git
- IDE: IntelliJ Ultimate
- Testing: using a combination of Junit, Mockito and Selenium
- Maven to build and integrate with...
- Jenkins as part of my CI Pipeline to send to...
- Sonarqube (hosted on a Google Cloud VM) and...
- Nexus (artifact repository).
</details>
Kanban Board for Project: [github boards](https://github.com/CarolineS-QA/hwa-game-time-log/projects/1)

Presentation about the project: [on google slides](https://docs.google.com/presentation/d/1wznZu-mg9XHuvzp51Q3ZPYwYu3qjcSRW8Uu_N3bzJ_g/edit?usp=sharing)

**Test Coverage:** For src/main/java: 100% // Sonarqube: 0% // Overall: 100%

For test reports and other documentation please see the project_docs folder.

## Getting Started

### Prerequisites
What things you need to install the software and how to install them.

**Links for Prerequisites**

**To Run**

**To Develop**


<details>
<summary>Installing Demo</summary>

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo
</details>

<details>
<summary>Running the tests</summary>

### Unit Tests 
JUnit is used for unit tests. A unit test will test individual methods within a class for functionality.

```
Give an example of why and how to run them
```

### Integration Tests 
Mockito is used for intergration testing. It tests how different classes interact with each other. By 'mocking' the functions that a method/class relies on we can see how the code we are testing works by assuming the parts it relies on work too.

```
Give an example of why and how to run them
```

### Coding style tests (static analysis)
Sonarqube is used for static analysis. I used it to see how well my code conformed to an industry standard, the amount of coverage for my tests, and also highlighting bugs and security warnings.

```
Give an example of why and how to run them
```
</details>


## Deployment

## Built With

[Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Caroline Strasenburgh**

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Felix [[Femarleycode](https://github.com/Femarleycode)]
for his advice regarding the risk assessment.