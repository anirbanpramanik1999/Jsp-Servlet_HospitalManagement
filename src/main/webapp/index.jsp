<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hospital Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
</head>
<body class="container mt-4">

    <!-- Header with Marquee -->
    <header class="mb-4">
        <marquee behavior="scroll" direction="left" scrollamount="9">
            <h2 class="text-center">Hospital Management</h2>
        </marquee>
    </header>



    <!-- Main Layout -->
    <div class="d-flex align-items-center justify-content-center flex-wrap">

        <!-- Left Side Buttons -->
        <div class="d-flex flex-column gap-3 me-4">
            <a href="/HospitalManagement/viewdoctor" class="btn btn-primary w-100">All Doctors</a>
            <a href="/HospitalManagement/viewpatient" class="btn btn-secondary w-100">All Patients</a>
            <a href="/HospitalManagement/viewappointment" class="btn btn-success w-100">All Booked Appointments</a>
        </div>

        <!-- Centered Image -->
        <div class="d-flex justify-content-center">
            <img src="https://img.freepik.com/free-vector/people-walking-sitting-hospital-building-city-clinic-glass-exterior-flat-vector-illustration-medical-help-emergency-architecture-healthcare-concept_74855-10130.jpg?t=st=1740664128~exp=1740667728~hmac=a95cc3855008ae53ed9edbd410d012891c04ef1c6267c49c12057f9547380d80&w=1480"
                 alt="Hospital Image" class="img-fluid rounded" style="width: 600px; height: 450px;">
        </div>

        <!-- Right Side Buttons -->
        <div class="d-flex flex-column gap-3 ms-4">
            <a href="/HospitalManagement/adddoctor" class="btn btn-danger w-100">Add Doctor</a>
            <a href="/HospitalManagement/addpatient" class="btn btn-warning w-100">Add Patient</a>
            <a href="/HospitalManagement/bookappointment" class="btn btn-info w-100">Book Appointment</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
