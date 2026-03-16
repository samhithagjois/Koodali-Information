**Hello, and welcome to the information repository of Koodali, Namma Kannada Shaale's student administration tool!**

Namma Kannada Shaale is a voluntary organisation based in Munich, Germany, 
which teaches the Indian language **Kannada** (spoken by ca. 110 million native speakers in the state of Karnataka, India) to children of Kannada heritage in Europe over online as wel as offline classes.

The administration team of the school has been struggling with the growing number of registrations of both students and teachers. 
The old way of administration over chatting platforms like Whatsapp and multiple data spreadsheets on Google sheets was deemed not sufficient, 
and the need for a platform to cover all the different administration needs.

Koodali is a simple application that provides teachers and administrators a stable database for the following administration needs : 

1. Structural Overview of the school
   - List of students registered, attending, and former students
   - List of teachers registered, teaching, and former teachers
   - List of sections (classes) with location and schedule as well as students/teachers in section
2. Trackers
   - Student attendance tracker
   - Textbook distribution tracker
   - Teacher availability tracker
   - Student homework submission tracker
3. Miscellaneous
   - Homework score leaderboard

Please note that this project is not completed and is still being developed by one person. 

# Information on the tech stack used in this project :
Client-side
- Java
    - Spring Boot
    - REST API (HTTP calls)
    - Spring MVC (pattern)
    - JpaRepository (database)
    - gradle (building)

Server-side :
- Angular
    - HTML/CSS (templates)
    - TypeScript (Angular)

# Model

## UML Class Diagram of the Model (Entities)

![UMLClassDiagram (3).png](UMLDiagrams%2FUMLClassDiagram%20%283%29.png)

## UML Class Diagram of the DTOs used 
![Bridge.png](UMLDiagrams%2FBridge.png)

## Example Communication Diagram of the Repository/Service/Controller interactions
![Communication Diagram.png](UMLDiagrams%2FCommunication%20Diagram.png)

# How to run the project (for now)

- run the Angular CLI server with 'ng serve'
- run the Spring Boot Application with 'gradle run'



