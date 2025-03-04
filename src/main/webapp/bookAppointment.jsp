<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Appointment Registration</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">

</head>
<body>
<div class="container mt-5">
    <a href="index.jsp">
       <i class="fa-solid fa-house-chimney" style="color: #FFD43B; font-size: 1.5rem"></i>
    </a>
  <div class="row justify-content-center">
    <div class="col-md-6">
      <div class="card">
        <div class="card-header text-center bg-primary text-white">
          <h4>Book an Appointment</h4>
        </div>
        <div class="card-body">

          <!-- Error Message Display -->
          <%
              String errorMessage = (String) request.getAttribute("errorMessage");
              if (errorMessage != null) {
          %>
              <div class="alert alert-danger text-center">
                  <%= errorMessage %>
              </div>
          <% } %>

          <form action="bookappointment" method="POST">
            <div class="mb-3">
              <label for="patient_id" class="form-label">Patient ID</label>
              <input type="number" class="form-control" id="patient_id" name="patient_id" required>
            </div>
            <div class="mb-3">
              <label for="doctor_id" class="form-label">Doctor ID</label>
              <input type="number" class="form-control" id="doctor_id" name="doctor_id" required>
            </div>
            <div class="mb-3">
              <label for="appointment_date" class="form-label">Appointment Date</label>
              <input type="date" class="form-control" id="appointment_date" name="appointment_date" required>
            </div>
            <div class="d-flex justify-content-around">
             <button type="submit" class="btn btn-primary w-50">Submit</button>
             <a href="/HospitalManagement/viewappointment" class="btn btn-warning w-30">All Booked Appointments</a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
