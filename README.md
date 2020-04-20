Coverage: 5%

# Stock Database Management Project

A java project that allows an end user to use a telephone menu system via a console command window to manage a database for a shops stock, as well as its orders and customers. The database will use the CRUD database methods (CREATE, READ, UPDATE, DELETE) for its interactions via MySQL.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

A java runtime environment is required on your PC to run this along with a version of Maven to build the project. Having Eclipse would also be useful if you wish to edit or test the code, though other Java IDE's will work fine.

### Installing

Fork this repository to your own GitHub and then clone it to your PC. From there, the .jar file can be created using the command line.
Open your command line in the main directory of the project and run the following commands to get the program working, using Maven and Java. **Run each command one line at a time!**
'''
    mvn clean package
    cd target
    java -jar LukeConway-SoftwareMarch16-jar-with-dependencies.jar
'''

## Running the tests

Running the tests can be done by opening the source code in an IDE and running the test codes as a JUnit application. Alternatively, opening your command line in the main directory and running the tests via maven will also work, with the command given below.
'''
    mvn clean test
'''

### Coding style tests

Building this application and pushing it forward to a computer or virtual machine hosting Sonarqube will allow the user to anylise the code for coding style issues, bugs and security issues.


## Deployment

The project has been deployed via multiple stages of a CI pipeline. It is first pushed to GitHub, before being retrieved by Jenkins. Jenkins utilises maven to run the unit tests and build the project before pushing it further ahead to both Sonarqube (for more testing) and Nexus, for artifact repositing and hosting the fully built application.


## Built With

* [GitHub](https://github.com) - Version Management and Source Code Backup
* [Maven](https://maven.apache.org/) - Dependency Management
* [Jenkins](https://jenkins.io) - Pipeline Automation and Code Building and Deploying
* [Sonarqube](https://www.sonarqube.org) - Java Code Testing
* [Nexus](https://www.sonatype.com/product-nexus-repository) - Build Artifact Repository

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Luke Conway** - *Main author of the source code.*[ConwayQA](https://github.com/ConwayQA)
* **Chris Perrins** - *Huge Coding inspiration and author of Hashcode methods* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Chris Perrins** - *Huge Coding inspiration and author of Hashcode methods* - [christophperrins](https://github.com/christophperrins)
* **Nick Johnson** - *Massive influence from teaching all aspects of the development for this project.* - [nickrstewarttds](https://github.com/nickrstewarttds)

## Project Links

* [Porject presentation](https://docs.google.com/presentation/d/1TrNQplVnAPpAdIzv6l3Q7L1uG5-7R4nVaf4mELwHiz8/edit?usp=sharing)
* [GitHub Project KanBan Board](https://github.com/ConwayQA/Stock-Database-Project/projects/1)
* [Trello Project Road Map](https://trello.com/b/kjbP2J0J/qa-stock-database-project)
