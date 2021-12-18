# Clinic Manager

This project delivers a REST API for a Clinic CRM.
It provides the following features ...

- Create an appointment for a specific patient with a specific doctor
- Get all appointments for a doctor 
- Get all appointment for a patient

It has a classic three layered architecture.
The repository classes are implemented as in-memory repositories, however, 
the code level architecture is such that it enables us to quickly switch to using
repository classes, which connect to a real database.

The project comes with a set of test cases, which cover
all the principle use cases of the system. It contains a combination of unit and integartion 
tests. All layers view-service-repository are covered by test cases.

## How to run the project
To run the project use
`./gradlew bootrun`

To run the test suite use 
`./gradlew test`

## Domain model
The app contains three domain classes ...

Doctor -> represents a doctor
Patient -> represents a patient
Appointment -> represents an appointment 

## View layer
A single controller with 3 endpoints is created.

## Service layer
There is a single service layer class AppointmentService.
This class does input validation and coordinates CRUD actions via repository classes

## Repository layer
The project does not connect to a real DB, but rather 
uses in-memory repositories. It contains some mock data for doctors and patients.

## Notes 
For convenience the system comes with some mock data already saved in the repositories.

