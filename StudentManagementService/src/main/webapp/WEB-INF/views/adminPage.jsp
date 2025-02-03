<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.springlearn.StudentManagementService.model.StudentDetails" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<%
    // Check if the user has the correct role
    String userRole = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("userRole".equals(cookie.getName())) {
                userRole = cookie.getValue();
                break;
            }
        }
    }

    // Redirect to login if not authorized
    if (!"ADMIN".equals(userRole)) {
        response.sendRedirect("api/user/login");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management System</title>
    <style>
        /* Reset default margin and padding */
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        /* Container for the page */
        .container {
            display: flex;
            height: 100%;
        }

        /* Left Menu Bar */
        .left-menu {
            width: 250px; /* Fixed width for the menu */
            background-color: #2c3e50; /* Dark background color */
            color: white;
            overflow-y: auto; /* Make the menu scrollable */
            height: 100vh; /* Full height of the viewport */
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        /* Menu Header */
        .left-menu h2 {
            padding: 20px;
            margin: 0;
            background-color: #34495e; /* Slightly darker background for header */
            text-align: center;
        }

        /* Menu Buttons */
        .left-menu button {
            display: block;
            width: 100%;
            padding: 15px;
            margin: 0;
            border: none;
            background-color: transparent;
            color: white;
            text-align: left;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        /* Hover effect for buttons */
        .left-menu button:hover {
            background-color: #1abc9c; /* Highlight color on hover */
        }

        /* Right Content Area */
        .right-content {
            flex: 1; /* Take remaining space */
            padding: 20px;
            background-color: #ffffff; /* White background for content */
            overflow-y: auto; /* Make content scrollable */
            box-shadow: -2px 0 5px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        /* Form styling */
        .right-content form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f9f9f9;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .right-content form label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        .right-content form input[type="text"],
        .right-content form input[type="number"],
        .right-content form input[type="email"],
        .right-content form input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .right-content form input[type="button"] {
            background-color: #1abc9c;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .right-content form input[type="button"]:hover {
            background-color: #16a085;
        }

        /* Table styling */
        .right-content table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .right-content table th,
        .right-content table td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .right-content table th {
            background-color: #2c3e50;
            color: white;
        }

        .right-content table tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Left Menu Bar -->
        <div class="left-menu">
            <h2>Operations</h2>
            <button onclick="showUpdateForm()">Update Student Details</button>
            <button onclick="showStudentDetails()">Show Student Details</button>
            <button onclick="addStudentDetails()">Add Student Details</button>
            <button onclick="deleteStudentDetails()">Delete Student</button>
            <button onclick="showStudentDetails()">Add Teachers Details</button>
            <button onclick="showStudentDetails()">Update Teachers Details</button>
            <button onclick="showUpdateForm()">Add Course</button>
            <button onclick="showUpdateForm()">Allocate Course to Students</button>
            <button onclick="showUpdateForm()">Allocate course to teachers</button>
            <button onclick="showReports()">Generate Reports</button>
            <button onclick="logout()">Logout</button>
            <!-- Add more buttons as needed -->
        </div>

        <!-- Right Content Area -->
        <div class="right-content" id="right-content">
            <h2>Welcome to Student Operations</h2>
            <p>Please select an operation from the left menu.</p>
        </div>
    </div>

    <script>

// ----------------------------  Function for Show Student Table  --------------------------------- //

        function showStudentDetails() {
            document.getElementById("right-content").innerHTML = `
                <center> <h2> Student List Table </h2> </center>

                <div id= "StudentFilterBox">
                <input type= "text" >
                <input type= "button" name= "submit" value= "Filter">
                </div><br>
                <div id= "studentsTableContainer"></div>
            `;
            $.ajax({
                url: "getAllStudents", // Endpoint to fetch the list of students
                type: "GET", // Use GET to retrieve data
                success: function(response) {
                    console.log(response); // Log the raw response
                    const students = response; // Parse the JSON response
                    console.log(students); // Log the parsed students array

                    // Clear the container
                    const container = document.getElementById("studentsTableContainer");
                    container.innerHTML = "";

                    // Create the table
                    const table = document.createElement("table");
                    table.border = "1";
                    table.id = "studentsTable";

                    // Create the table header
                    const thead = document.createElement("thead");
                    const headerRow = document.createElement("tr");

                    // Define the table headers
                    const headers = [
                        "Register Id", "Name", "Email ID", "Address", "Year Of Study",
                        "Year Of Admission", "Course Duration", "Status", "Designation", "Phone", "Date Of Birth"
                    ];

                    headers.forEach(headerText => {
                        const th = document.createElement("th");
                        th.textContent = headerText;
                        headerRow.appendChild(th);
                    });

                    thead.appendChild(headerRow);
                    table.appendChild(thead);

                    // Create the table body
                    const tbody = document.createElement("tbody");

                    // Populate the table with student data
                    students.forEach(student => {
                        const row = document.createElement("tr");

                        const fields = [
                            student.registerId, student.studentName, student.mailId, student.address,
                            student.yearOfStudy, student.yearOfAdmission, student.courseDuration,
                            student.status, student.designation, student.phone, student.dateOfBirth
                        ];

                        fields.forEach(field => {
                            const td = document.createElement("td");
                            td.textContent = field || ""; // Fallback for null/undefined
                            row.appendChild(td);
                        });

                        tbody.appendChild(row);
                    });

                    table.appendChild(tbody);

                    // Append the table to the container
                    container.appendChild(table);
                },
                error: function(xhr, status, error) {
                    alert("Error fetching students list: " + error);
                }
            });
        }

// ----------------------------  Function for Get the Student Details  --------------------------------- //

        function showUpdateForm() {
            document.getElementById("right-content").innerHTML = `
                <h2>Update Student Details</h2>

                <form id="getDetailsForm">
                    <label>Student ID:</label>
                    <input type="text" id="studentId" name="id" required>
                    <input type="button" value="Get Details" onclick="fetchStudentDetails()">
                </form>
                <br><br>
                <div id="updateFormContainer"></div>
            `;
        }

// ----------------------------  Function for Fetch Student Details in form  --------------------------------- //

        function fetchStudentDetails() {
            const studentId = document.getElementById("studentId").value;
            $.ajax({
                url: "getDetails",
                type: "POST",
                data: { id: studentId },
                success: function(response) {
                    console.log(response); // Log the raw response
                    //const student = JSON.parse(response);
                     const student = response;// Parse the JSON response
                    console.log(student); // Log the parsed student object

                    // Clear the container
                    const container = document.getElementById("updateFormContainer");
                    container.innerHTML = "";

                    // Create the form
                    const form = document.createElement("form");
                    form.id = "updateStudentForm";

                    // Add form fields dynamically
                    const fields = [
                        { label: "Register Id", type: "number", name: "registerId", value: student.registerId },
                        { label: "Name", type: "text", name: "studentName", value: student.studentName },
                        { label: "Email ID", type: "email", name: "mailId", value: student.mailId },
                        { label: "Address", type: "text", name: "address", value: student.address },
                        { label: "Year Of Study", type: "number", name: "yearOfStudy", value: student.yearOfStudy },
                        { label: "Year Of Admission", type: "text", name: "yearOfAdmission", value: student.yearOfAdmission },
                        { label: "Course Duration", type: "number", name: "courseDuration", value: student.courseDuration },
                        { label: "Status", type: "text", name: "status", value: student.status },
                        { label: "Designation", type: "text", name: "designation", value: student.designation },
                        { label: "Phone", type: "text", name: "phone", value: student.phone },
                        { label: "Date Of Birth", type: "date", name: "dateOfBirth", value: student.dateOfBirth }
                    ];

                    fields.forEach(field => {
                        const label = document.createElement("label");
                        label.textContent = field.label + ":";
                        form.appendChild(label);

                        const input = document.createElement("input");
                        input.type = field.type;
                        input.name = field.name;
                        input.value = field.value || ""; // Fallback for null/undefined
                        input.required = true;
                        form.appendChild(input);

                        form.appendChild(document.createElement("br"));
                    });

                    // Add the update button
                    const updateButton = document.createElement("input");
                    updateButton.type = "button";
                    updateButton.value = "Update";
                    updateButton.onclick = updateStudentDetails;
                    form.appendChild(updateButton);

                    // Append the form to the container
                    container.appendChild(form);
                },
                error: function(xhr, status, error) {
                    alert("Error fetching student details: " + error);
                }
            });
        }

// ----------------------------  Function for Update Student Details  --------------------------------- //

        function updateStudentDetails() {
            const formData = $("#updateStudentForm").serialize();
            $.ajax({
                url: "studentUpdate",
                type: "PUT",
                data: formData,
                success: function(response) {
                    alert("Student details updated successfully!");
                    showUpdateForm();
                },
                error: function(xhr, status, error) {
                    alert("Error updating student details: " + error);
                }
            });
        }

// ----------------------------  Function for addStudentDetails  --------------------------------- //

        function addStudentDetails() {
            document.getElementById("right-content").innerHTML = `
                <form id="registeredDetails">
                        <label>Student Name:</label>
                        <input type="text" name="studentName" required><br><br>

                        <label>Email:</label>
                        <input type="email" name="mailId" required><br><br>

                        <label>Address:</label>
                        <input type="text" name="address" required><br><br>

                        <label>Year of Study:</label>
                        <input type="number" name="yearOfStudy" required><br><br>

                        <label>Year of Admission:</label>
                        <input type="text" name="yearOfAdmission" required><br><br>

                        <label>Course Duration:</label>
                        <input type="number" name="courseDuration" required><br><br>

                        <label>Designation:</label>
                        <input type="text" name="designation" required><br><br>

                        <label>Phone:</label>
                        <input type="number" name="phone" required><br><br>

                        <label>Date of Birth:</label>
                        <input type="date" name="dateOfBirth" required><br><br>

                        <input type="button" value="Register" onclick= registerStudentDetails()>
                    </form>
            `;
        }

// ----------------------------  Function for Register Student Details  --------------------------------- //

        function registerStudentDetails() {
            const formData = $("#registeredDetails").serialize();
            $.ajax({
                url: "addStudent",
                type: "POST",
                data: formData,
                success: function(response) {
                console.log(response);
                    alert("Student details registered successfully!");
                    addStudentDetails();
                },
                error: function(xhr, status, error) {
                    alert("Error while register student details: " + error);
                }
            });
        }
// ----------------------------  Function for Delete Student Details  --------------------------------- //

        function deleteStudentDetails() {
            document.getElementById("right-content").innerHTML = `
               <h2>Delete Student Details</h2>
                   <form id="deleteDetailForm">
                       <label>Student ID:</label>
                       <input type="text" id="studentId" name="id" required>
                       <input type="button" value="Delete Details" onclick="deleteStudentById()">
                   </form>
            `;
        }

// ----------------------------  Function for Update Student Details  --------------------------------- //

        function deleteStudentById() {
            const deleteId = document.getElementById("studentId").value;
            $.ajax({
                url: "deleteStudent",
                type: "DELETE",
                data: {id : deleteId},
                success: function(response) {
                    alert("Student details deleted successfully!");
                    deleteStudentDetails();
                },
                error: function(xhr, status, error) {
                    alert("Error while deleting student details: " + error);
                }
            });
        }

// ----------------------------  Function for Logout  --------------------------------- //

        function showReports() {
            document.getElementById("right-content").innerHTML = `
                <h2>Generate Reports</h2>
                <p>Generating reports here...</p>
            `;
        }
// ----------------------------  Function for Logout  --------------------------------- //
        function logout() {
            // Show confirmation dialog
            const confirmLogout = confirm("Are you sure you want to log out?");
            if (confirmLogout) {
                // Call the server-side logout endpoint
                fetch("/api/user/logout", {
                    method: "GET",
                    credentials: "include" // Include cookies in the request
                })
                .then(response => {
                    if (response.status === 302 || response.redirected) {
                        // Redirect to the login page if the server responds with a redirect
                        window.location.href = "/api/user/login";
                    } else {
                        // Update the UI to reflect logout
                        document.getElementById("right-content").innerHTML = `
                            <h2>Logout</h2>
                            <p>You have been logged out.</p>
                        `;
                    }
                })
                .catch(error => {
                    console.error("Logout failed:", error);
                });
            }
        }
    </script>
</body>
</html>