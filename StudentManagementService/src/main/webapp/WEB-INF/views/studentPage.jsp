<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    if (!"STUDENT".equals(userRole)) {
        response.sendRedirect("/api/user/login");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>STUDENT PORT</title>
    <style>
        /* Reset default margin and padding */
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
        }

        /* Container for the page */
        .container {
            display: flex;
            height: 100%;
        }

        /* Left Menu Bar */
        .left-menu {
            width: 250px; /* Fixed width for the menu */
            background-color: #343a40; /* Dark background color */
            color: white;
            overflow-y: auto; /* Make the menu scrollable */
            height: 100vh; /* Full height of the viewport */
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        /* Menu Header */
        .left-menu h2 {
            padding: 20px;
            margin: 0;
            background-color: #2c3e50; /* Slightly darker background for header */
            text-align: center;
            font-size: 1.5rem;
            font-weight: 600;
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
            transition: background-color 0.3s, padding-left 0.3s;
            font-size: 1rem;
        }

        /* Hover effect for buttons */
        .left-menu button:hover {
            background-color: #495057; /* Highlight color on hover */
            padding-left: 25px;
        }

        /* Right Content Area */
        .right-content {
            flex: 1; /* Take remaining space */
            padding: 30px;
            background-color: #ffffff; /* White background for content */
            overflow-y: auto; /* Make content scrollable */
            box-shadow: -2px 0 5px rgba(0, 0, 0, 0.1); /* Add shadow for depth */
        }

        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #f8f9fa;
            font-weight: 600;
        }

        /* Form Styling */
        form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 20px auto;
        }

        form input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        form button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s;
        }

        form button:hover {
            background-color: #0056b3;
        }

        /* Heading Styling */
        .right-content h2 {
            color: #343a40;
            font-size: 2rem;
            margin-bottom: 20px;
        }

        /* Paragraph Styling */
        .right-content p {
            color: #6c757d;
            font-size: 1rem;
            line-height: 1.5;
        }

        /* Logout Button Styling */
        .logout-btn {
            background-color: #dc3545;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1rem;
            transition: background-color 0.3s;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">

        <!-- Left Menu Bar -->
        <div class="left-menu">
            <h2>Operations</h2>
            <button onclick="showStudentDetails()">Student Details</button>
            <button onclick="showUpdateForm()">Update Student Details</button>
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
        //-------------------------------- Show Student Details ----------------------------------------------
        function showStudentDetails() {
            document.getElementById("right-content").innerHTML = `
                <form onsubmit="return showStudent()">
                    <label><h4>Enter Student Id</h4></label>
                    <input type="text" id="id" name="id" required>
                    <button type="submit">Submit</button>
                </form>
                <div id="studentsTableContainer"></div>
            `;
        }

        function showStudent() {
            const studentId = document.getElementById("id").value;

            $.ajax({
                url: "getDetails",
                type: "POST",
                data: { id: studentId },
                success: function(response) {
                    const student = response;
                    console.log(student);
                    const container = document.getElementById("studentsTableContainer");
                    container.innerHTML = ""; // Clear previous data

                    const table = document.createElement("table");
                    table.border = "1";

                    // Table Body for Vertical Layout
                    const tbody = document.createElement("tbody");

                    const fields = {
                        "Register Id": student.registerId,
                        "Name": student.studentName,
                        "Email ID": student.mailId,
                        "Address": student.address,
                        "Current Year": student.yearOfStudy,
                        "Year Of Admission": student.yearOfAdmission,
                        "Course Duration": student.courseDuration,
                        "Designation": student.designation,
                        "Date Of Birth": student.dateOfBirth,
                        "Phone": student.phone
                    };

                    Object.entries(fields).forEach(([key, value]) => {
                        const row = document.createElement("tr");

                        const th = document.createElement("th");
                        th.textContent = key;
                        row.appendChild(th);

                        const td = document.createElement("td");
                        td.textContent = value || "N/A";
                        row.appendChild(td);

                        tbody.appendChild(row);
                    });

                    table.appendChild(tbody);
                    container.appendChild(table);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching students list:", error);
                    alert("Error fetching students list");
                }
            });

            return false; // Prevent form submission
        }

        function showUpdateForm() {
            document.getElementById("right-content").innerHTML = `
                <h2>Update Student Details</h2>
                <form>
                    <input type="text" placeholder="Student ID" required>
                    <input type="text" placeholder="Name" required>
                    <input type="email" placeholder="Email" required>
                    <input type="text" placeholder="Course" required>
                    <button type="submit">Update</button>
                </form>
            `;
        }

        function showReports() {
            document.getElementById("right-content").innerHTML = `
                <h2>Generate Reports</h2>
                <p>Generating reports here...</p>
            `;
        }

        function logout() {
            document.getElementById("right-content").innerHTML = `
                <h2>Logout</h2>
                <p>You have been logged out.</p>
                <button class="logout-btn" onclick="window.location.href='/api/user/login'">Login Again</button>
            `;
        }
    </script>
</body>
</html>