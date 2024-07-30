# Spring Boot School

This repository contains a school management system developed using Spring Boot. The application provides
functionalities for managing students, courses, and enrollments.

## Features

- **Student Management**: Add, update, and delete student information.
- **Course Management**: Create, update, and delete courses.
- **Enrollment Management**: Enroll students in courses and track their progress.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6.3 or higher
- MySQL or any other relational database

## Quick Setup

### Run

```bash
docker compose up -d
```

### Stop

> This will remove all volumes and images. Remove the flag --volumes to keep the db data

```bash
docker compose down --volumes --rmi local
```

### Must Read

- Don't forget to change the password in `db/password.txt` to something else and move it to a secure location
- Check application.properties and change the settings accordingly, db is on create-drop mode by default
- Default Spring Boot port is `8080` and the db port is `5432` (remove the ports setting on the yaml when not needed) )

### Installation

1. **Clone the Repository**

    ```bash
    git clone https://github.com/rui-tx/springboot-school.git
    cd springboot-school
    ```

2. **Configure the Database**

   Update the `application.properties` file located in `src/main/resources` with your database configuration:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/school_db
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the Project**

    ```bash
    mvn clean install
    ```

4. **Run the Application**

    ```bash
    mvn spring-boot:run
    ```

### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

### Endpoints

#### Student Endpoints

- GET /students - List all students
- GET /students/{id} - Get student by ID
- POST /students - Add a new student
- PUT /students/{id} - Update an existing student
- DELETE /students/{id} - Delete a student

#### Course Endpoints

- GET /courses - List all courses
- GET /courses/{id} - Get course by ID
- POST /courses - Add a new course
- PUT /courses/{id} - Update an existing course
- DELETE /courses/{id} - Delete a course

#### Enrollment Endpoints

- POST /enrollments - Enroll a student in a course

### License

This project is licensed under the MIT License - see the LICENSE file for details.

> This README was auto-generated.
