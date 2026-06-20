# Biometric Attendance Ingestion Service

A Spring Boot REST API that processes biometric attendance punches for students and staff. The system determines whether a punch belongs to a student or staff member, updates attendance records, logs punches, and prevents duplicate punch processing within a short time window.

## Features

* Student and staff identification using Punch ID
* Automatic attendance creation for new student punches
* Biometric override of manually marked "Absent" students
* Duplicate punch detection (within 60 seconds)
* Staff punch logging
* In-memory data storage (no database required)
* RESTful API design
* Proper error handling and HTTP status codes

## Tech Stack

* Java 21
* Spring Boot 3.x/4.x
* Maven
* REST API
* In-Memory Collections (HashMap, ArrayList)

## Project Structure

```text
src/main/java/com/example/attendance
│
├── controller
│   └── AttendanceController.java
│
├── service
│   └── AttendanceService.java
│
├── model
│   ├── PunchRequest.java
│   ├── Student.java
│   ├── Staff.java
│   ├── AttendanceRecord.java
│   └── PunchLog.java
│
└── AttendanceApplication.java
```

## API Endpoint

### Process Punch

**POST**

```http
/api/ams/punch
```

### Request Body

```json
{
  "punchId": "RFID1001",
  "deviceSerial": "DEV001",
  "timestamp": "2026-06-20T09:15:00"
}
```

## Sample Responses

### Student Absent → Present Override

```json
{
  "action": "overridden_to_present",
  "student": "Aarav Shetty"
}
```

### New Attendance Record

```json
{
  "action": "new_record_created",
  "student": "Aarav Shetty"
}
```

### Duplicate Punch

```json
{
  "action": "duplicate_ignored",
  "message": "Punch ignored within 60 seconds"
}
```

### Staff Punch Logged

```json
{
  "action": "staff_punch_logged",
  "staff": "Priya Nair",
  "time": "2026-06-20T10:00:00"
}
```

### Unknown Punch ID

```json
{
  "error": "Punch ID not found"
}
```

Status Code:

```http
404 NOT FOUND
```

## Mock Data

### Students

```java
RFID1001 -> Aarav Shetty (Class 8-B)
```

### Staff

```java
RFID2001 -> Priya Nair (Teacher)
```

### Existing Attendance Record

```java
RFID1001 -> Absent
```

## How to Run

### Clone Repository

```bash
git clone <repository-url>
cd attendance
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

## Testing with Postman

### URL

```http
POST http://localhost:8080/api/ams/punch
```

### Headers

```text
Content-Type: application/json
```

### Body

```json
{
  "punchId": "RFID1001",
  "deviceSerial": "DEV001",
  "timestamp": "2026-06-20T09:15:00"
}
```

## Bonus Feature Implemented

Duplicate punch protection:

* Ignores repeated punches from the same Punch ID within 60 seconds.
* Simulates protection against faulty biometric devices sending duplicate signals.

## Assumptions

* No database is used.
* Data is stored in memory and resets when the application restarts.
* Attendance records are maintained only for the current application session.
* Staff punches are logged but do not affect attendance status.

## Author

**Amrita Pandey**

Backend Developer Internship Assignment

Flione Innovation and Technologies
