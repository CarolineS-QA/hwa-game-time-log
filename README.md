# Hobby Web App: GameTimeLog

My second individual project with QA, started as an Academy Trainee.

Summary: This application aims to be a "Game Time Log" where users can perform CRUD functions on themselves and records of their game sessions. It is currently for demo purposes only, showcasing my knowledge of Spring & connecting a working back-end to a front-end via API calls.

## Table of Contents

1. [About the Project](#about-the-project)
1. [Project Status](#project-status)
1. [Getting Started](#getting-started)
    1. [Dependencies](#dependencies)
    1. [Building](#building)
    1. [Installation](#installation)
    1. [Usage](#usage)
1. [Release Process](#release-process)
    1. [Versioning](#versioning)
    <!--1. [Payload](#payload) -->
<!--1. [How to Get Help](#how-to-get-help) -->
1. [Further Reading](#further-reading)
1. [Contributing](#contributing)
1. [License](#license)
1. [Authors](#authors)
1. [Acknowledgements](#acknowledgements)

## About the Project

MVP: A functional ‘front-end’ web app (and integrated APIs) which connects to a back-end written in Java, and a relational database.
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

Kanban Board for QA Project: [github boards](https://github.com/CarolineS-QA/hwa-game-time-log/projects/1)

Presentation about the project: [on google slides](https://docs.google.com/presentation/d/1wznZu-mg9XHuvzp51Q3ZPYwYu3qjcSRW8Uu_N3bzJ_g/edit?usp=sharing)

Please see the `docs` folder for other documentation.

**[Back to top](#table-of-contents)**

## Project Status 
Current release: v0.1.0 - in development

**Test Coverage:** For src/main/java: 100% // Sonarqube: 0% // Overall: 100%

For test reports please see the `docs` folder.

[![Build Status](http://localhost:8080/hwa-gtl-sonar-nexus/badge/icon)](http://localhost:8080/hwa-gtl-sonar-nexus/http://your-server:12345/job/badge/icon/)

Not currently built successfully on CI Pipeline.

**[Back to top](#table-of-contents)**

## Getting Started

### Dependencies
What things you need to install the software and where to find them.

**To Run**

**To Develop**

**Links for Dependencies**

### Getting the Source

This project is [hosted on GitHub](https://github.com/CarolineS-QA/hwa-game-time-log). You can clone this project directly using this command:

```
git clone git@github.com:CarolineS-QA/hwa-game-time-log.git
```

## Building

How to build my project: 

### Built With

[Maven](https://maven.apache.org/) - Dependency Management

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

## Installation
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

**[Back to top](#table-of-contents)**

## Usage

Instructions for using your project. Ways to run the program, how to include it in another project, etc.

```
Examples should be included
```

If your project provides an API, either provide details for usage in this document or link to the appropriate API reference documents


## Release Process

This project is in development, for demo purposes only and not yet at 'release' stage.

### Versioning

We use [SemVer](http://semver.org/) for versioning. For a list of available versions, see the [repository tag list](https://github.com/CarolineS-QA/hwa-game-time-log/tags).

<!-- ### Payload -->
<!-- ## How to get help -->

## Further Reading

**[Back to top](#table-of-contents)**
## Contributing

Currently not accepting contributions due to the nature of this project being part of my Academy training. However, after June 8th 2020 I will be open to contributions.

Please review [CONTRIBUTING.md](docs/CONTRIBUTING.md) for details on our code of conduct and development process (this is currently in development).

**[Back to top](#table-of-contents)**
## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

**[Back to top](#table-of-contents)**
## Authors

* **Caroline Strasenburgh**

## Acknowledgements

* Felix [[Femarleycode](https://github.com/Femarleycode)]
for his advice regarding the risk assessment.

**[Back to top](#table-of-contents)**