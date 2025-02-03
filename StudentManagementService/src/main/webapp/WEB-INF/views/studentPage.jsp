<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.springlearn.StudentManagementService.model.StudentDetails" %>
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
        response.sendRedirect("api/user/login");
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
            font-family: Arial, sans-serif;
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
            background-color: #f4f4f4; /* Light background for content */
            overflow-y: auto; /* Make content scrollable */
        }

         table {
                width: 50%;
                border-collapse: collapse;
                margin-top: 20px;
                }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            column-span: 2;
        }

        /* Example content styling */
        .right-content h2 {
            color: #2c3e50;
        }

        .right-content p {
            color: #333;
        }
    </style>
</head>
<body>
    <div class="container">


    <%
        StudentDetails stud = (StudentDetails) request.getAttribute("student");
    %>

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
            <form action= "getDetails" method = "POST">
                <label><h4> Enter Student Id </h4></label>
                <input type="text" id= "id" name= "id">
                <input type= "submit" value= "submit">
            </form>
        </div>
    </div>

    <script>
        // JavaScript functions to update the right content
        function showStudentDetails() {
            document.getElementById("right-content").innerHTML = `
            <% if(stud == null) { %>
                   <p> Student not found </p>
            <% } else { %>
                <table>
                <th> Personal info </th>
                <tr>
                    <td> Name </td>
                    <td> <%= stud.getStudentName() %>
                </tr>
                <tr>
                    <td> Register Id </td>
                    <td> <%= stud.getRegisterId() %>
                </tr>
                <tr>
                    <td> Email Id </td>
                    <td> <%= stud.getMailId() %>
                </tr>
                <tr>
                      <td> Address </td>
                      <td> <%= stud.getAddress() %>
                </tr>
                <tr>
                    <td> Current Year </td>
                    <td> <%= stud.getYearOfStudy() %>
                </tr>
                <tr>
                    <td> Year of Admission </td>
                    <td> <%= stud.getYearOfAdmission() %>
                </tr>
                <tr>
                    <td> Designation </td>
                    <td> <%= stud.getDesignation() %>
                </tr>
                <tr>
                    <td> Course Duration </td>
                    <td> <%= stud.getCourseDuration() %>
                </tr>
                <tr>
                    <td> Date Of Birth </td>
                    <td> <%= stud.getDateOfBirth() %>
                </tr>
                <tr>
                      <td> Phone no </td>
                      <td> <%= stud.getPhone() %>
                </tr>

                </table>

               <% } %>
            `;
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
            `;
        }
    </script>
</body>
</html>