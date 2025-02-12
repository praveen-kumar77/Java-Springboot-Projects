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
            response.sendRedirect("/api/user/login");
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
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f8f9fa;
                color: #333;
            }

            /* Container for the page */
            .container {
                display: flex;
                height: 100%;
            }

            /* Left Menu Bar */
            .left-menu {
                width: 250px;
                background-color: #343a40;
                color: white;
                height: 100vh;
                box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }

            /* Menu Header */
            .left-menu h2 {
                margin: 0 0 20px 0;
                font-size: 24px;
                font-weight: 600;
                text-align: center;
                color: #fff;
            }

            /* Menu Buttons */
            .left-menu button {
                display: block;
                width: 100%;
                padding: 12px;
                margin: 10px 0;
                border: none;
                background-color: #495057;
                color: white;
                text-align: left;
                cursor: pointer;
                border-radius: 5px;
                transition: background-color 0.3s ease, transform 0.2s ease;
            }

            /* Hover effect for buttons */
            .left-menu button:hover {
                background-color: #1abc9c;
                transform: translateX(5px);
            }

            /* Right Content Area */
            .right-content {
                flex: 1;
                padding: 30px;
                background-color: #ffffff;
                overflow-y: auto;
                box-shadow: -2px 0 5px rgba(0, 0, 0, 0.1);
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
                font-weight: 600;
                color: #555;
            }

            .right-content form input[type="text"],
            .right-content form input[type="number"],
            .right-content form input[type="email"],
            .right-content form input[type="date"],
            .right-content form textarea {
                width: 100%;
                padding: 10px;
                margin-bottom: 15px;
                border: 1px solid #ddd;
                border-radius: 4px;
                font-size: 14px;
                transition: border-color 0.3s ease;
            }

            .right-content form input[type="text"]:focus,
            .right-content form input[type="number"]:focus,
            .right-content form input[type="email"]:focus,
            .right-content form input[type="date"]:focus,
            .right-content form textarea:focus {
                border-color: #1abc9c;
                outline: none;
            }

            .right-content form input[type="button"] {
                background-color: #1abc9c;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
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
                background-color: #343a40;
                color: white;
                font-weight: 600;
            }

            .right-content table tr:hover {
                background-color: #f1f1f1;
            }

            /* Center align text */
            .center {
                text-align: center;
            }

            /* Add some spacing */
            .mb-20 {
                margin-bottom: 20px;
            }

            /* Add hover effect to table rows */
            .right-content table tr {
                transition: background-color 0.3s ease;
            }

            .right-content table tr:hover {
                background-color: #e9ecef;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <!-- Left Menu Bar -->
            <div class="left-menu">
                <h2>Operations</h2>
                <button onclick="showUpdateForm()">Update Student Details</button>
                <button onclick="getStudentDetails()">Show Student Details</button>
                <button onclick="addStudentDetails()">Add Student Details</button>
                <button onclick="deleteStudentDetails()">Delete Student</button>
                <button onclick="addTeacherDetails()">Add Teachers Details</button>
                <button onclick="getTeachersDetailsById()">Update Teachers Details</button>
                <button onclick="addCourse()">Add Course</button>
                <button onclick="getStudentDetailsForEnroll()">Allocate Course to Students</button>
                <button onclick="addCourseTeacher()">Allocate course to teachers</button>
                <button onclick="showReports()">Generate Reports</button>
                <button onclick="logout()">Logout</button>
            </div>

            <!-- Right Content Area -->
            <div class="right-content" id="right-content">
                <h2>Welcome to Student Operations</h2>
                <p>Please select an operation from the left menu.</p>
            </div>
        </div>

    <script>

// ----------------------------  Function for Show Student Table  --------------------------------- //

        function getStudentDetails() {
            document.getElementById("right-content").innerHTML = `
                <center> <h2> Student List Table </h2> </center>

                <div id= "StudentFilterBox">
                <form id= "filerForm">
                <label> Designation : </label>
                <select name="designation" id="designation">
                  <option value="All" selected>All Department</option>
                  <option value="B.Sc Mathematics" >B.Sc Mathematics</option>
                  <option value="B.A English">B.A English</option>
                  <option value="B.Sc Chemistry">B.Sc Chemistry</option>
                  <option value="B.Com">B.Com</option>
                  <option value="B.E Computer Science">B.E Computer Science</option>
                  <option value="B.Sc IT">B.Sc IT</option>
                  <option value="B.Sc Botany">B.Sc Botany</option>
                  <option value="B.Sc Forensic Science">B.Sc Forensic Science</option>
                  <option value="B.A Environmental Science">B.A Environmental Science</option>
                  <option value="B.A Political Science">B.A Political Science</option>
                </select>
                <label> Semester : </label>
                <input type= "text" id= "semester" name= "yearOfStudy" value= "0" placeHolder= "search by semester..." >
                <input type= "button" value= "Filter" onclick= "showStudentDetails()">
                </form>
                </div><br>
                <div id= "studentsTableContainer">
                </div>
            `;
             showStudentDetails();
        }
// ----------------------------  Function for Show Student Table  --------------------------------- //

        function showStudentDetails() {
            const designationId = document.getElementById("designation").value;
            const semesterId = document.getElementById("semester").value;

            console.log(designationId);
            console.log(semesterId);
            $.ajax({

                url: "getAllStudents", // Endpoint to fetch the list of students
                type: "GET", // Use GET to retrieve data
                data: { designation : designationId,
                        semester : semesterId },
                success: function(response) {

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
                        "Register Id", "Name", "Email ID", "Address", "Semester",
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
                    console.error("Error fetching students list:", error);
                    alert("Error fetching students list");
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
                        { label: "Register Id", type: "text", name: "registerId", value: student.registerId },
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
            <center>
                <h2> Add Student Details </h2>
            </center>
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

// ----------------------------  Function for Add Teachers Details  --------------------------------- //

        function addTeacherDetails() {
            document.getElementById("right-content").innerHTML = `
            <center>
        <h2>Teacher Registration Form</h2>
        </center>
        <br>
        <form id="teacherForm" method="POST" action="/submitTeacher">
            <!-- Teacher Details -->
            <div class="form-group">

                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>

                <label for="contactNo">Contact No:</label>
                <input type="number" id="contactNo" name="contactNo" required>

                <label for="emailId">Email ID:</label>
                <input type="email" id="emailId" name="emailId" required>

                <label for="dateOfBirth">Date Of Birth:</label>
                <input type="date" id="dateOfBirth" name="dateOfBirth" required>

                <label for="qualification">Qualification:</label>
                <input type="text" id="qualification" name="qualification" required>

                <label for="joinedAt">Joined At:</label>
                <input type="date" id="joinedAt" name="joinedAt" required>
            </div>
            <div><center>
                <input type= "button" value= "Register" onclick= "addTeachers()">
                </center>
            </div>
            </form>

            `;

        }

// ----------------------------  Function for post addTeacherDetails   --------------------------------- //

        function addTeachers() {
                    const formData = $("#teacherForm").serialize();
                    $.ajax({
                        url: "addTeachersDetails",
                        type: "POST",
                        data: formData,
                        success: function(response) {
                        console.log(response);
                            alert("teacher details registered successfully!");
                            addTeacherDetails();
                        },
                        error: function(xhr, status, error) {
                            alert("Error while register teacher details: " + error);
                        }
                    });
                }

// ----------------------------  Function for Update Teachers Details 1 --------------------------------- //

        function getTeachersDetailsById() {
             document.getElementById("right-content").innerHTML = `
                    <h2>Update Teacher Details</h2>

                    <form id="getTeacherDetailsForm">
                        <label>Teachers ID:</label>
                        <input type="text" id="teacherId" name="id" required>
                        <input type="button" value="Get Details" onclick="fetchTeachersDetails()">
                    </form>
                    <br><br>
                    <div id="updateTeacherFormContainer"></div>
                `;

        }


// ----------------------------  Function for Fetch Teachers Details in form  --------------------------------- //

        function fetchTeachersDetails() {
            const teacherId = document.getElementById("teacherId").value;
            $.ajax({
                url: "getTeachersDetails",
                type: "POST",
                data: { id: teacherId },
                success: function(response) {
                    console.log(response); // Log the raw response
                    //const teacher = JSON.parse(response);
                     const teacher = response;// Parse the JSON response
                    console.log(teacher); // Log the parsed student object

                    // Clear the container
                    const container = document.getElementById("updateTeacherFormContainer");
                    container.innerHTML = "";

                    // Create the form
                    const form = document.createElement("form");
                    form.id = "updateTeacherForm";

                    // Add form fields dynamically
                    const fields = [
                        { label: "Teacher Id", type: "number", name: "teacherId", value: teacher.teacherId },
                        { label: "Name", type: "text", name: "name", value: teacher.name },
                        { label: "Email ID", type: "email", name: "emailId", value: teacher.emailId },
                        { label: "Address", type: "text", name: "address", value: teacher.address },
                        { label: "Contact No", type: "number", name: "contactNo", value: teacher.contactNo },
                        { label: "Qualification", type: "text", name: "qualification", value: teacher.qualification },
                        { label: "Joined At", type: "date", name: "joinedAt", value: teacher.joinedAt }
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
                    updateButton.onclick = updateTeachersDetails;
                    form.appendChild(updateButton);

                    // Append the form to the container
                    container.appendChild(form);
                },
                error: function(xhr, status, error) {
                    alert("Error fetching Teachers details: " + error);
                }
            });
        }

// ----------------------------  Function for Update Teachers Details 2 --------------------------------- //

        function updateTeachersDetails() {
            const formData = $("#updateTeacherForm").serialize();
            $.ajax({
                url: "updateTeachersDetails",
                type: "PUT",
                data: formData,
                success: function(response) {
                    alert("Teachers details updated successfully!");
                    getTeachersDetailsById();
                },
                error: function(xhr, status, error) {
                    alert("Error updating Teacher details: " + error);
                }
            });
        }

// ----------------------------  Function for addCourseDetails Form --------------------------------- //

        function addCourse() {
            document.getElementById("right-content").innerHTML = `
            <center>
                <h2> Add Student Details </h2>
            </center>
            <form id= "addCourseForm">

                <label for="courseName">Course Name:</label>
                <input type="text" id="courseName" name="courseName" required>

                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="4" cols="50" required></textarea>

                <label for="duration">Duration (in hours):</label>
                <input type="number" id="duration" name="duration" required>

                <center><input type="button" value= "Add Course" onclick= "addCourseDetails()"></center>
            </form>

            `;

            }


// ----------------------------  Function for Add Course to DataBase --------------------------------- //

        function addCourseDetails() {
            const formData = $("#addCourseForm").serialize();
            $.ajax({
                url: "addCourse",
                type: "POST",
                data: formData,
                success: function(response) {
                    alert("Course Added successfully!");
                    addCourse();
                },
                error: function(xhr, status, error) {
                    alert("Error Adding Course details: " + error);
                }
            });
        }


// ----------------------------  Function for Fetch Details For Enroll  --------------------------------- //

        function getStudentDetailsForEnroll() {
            document.getElementById("right-content").innerHTML = `
                <center> <h2> Student Enrollment Table </h2> </center>

                <div id= "StudentFilter">
                <form id= "filterStudentForm">
                <label> Designation : </label>
                <select name="designation" id="designation">
                  <option value="All" selected>All Department</option>
                  <option value="B.Sc Mathematics" >B.Sc Mathematics</option>
                  <option value="B.A English">B.A English</option>
                  <option value="B.Sc Chemistry">B.Sc Chemistry</option>
                  <option value="B.Com">B.Com</option>
                  <option value="B.E Computer Science">B.E Computer Science</option>
                  <option value="B.Sc IT">B.Sc IT</option>
                  <option value="B.Sc Botany">B.Sc Botany</option>
                  <option value="B.Sc Forensic Science">B.Sc Forensic Science</option>
                  <option value="B.A Environmental Science">B.A Environmental Science</option>
                  <option value="B.A Political Science">B.A Political Science</option>
                </select>
                <label> Semester : </label>
                <input type= "text" id= "semester" name= "yearOfStudy"  placeHolder= "search by semester..." >
                <input type= "button" value= "Filter" onclick= "getStudentList()">
                </form>
                </div><br>
                <div id= "studentsTableContainer">
                </div>
            `;
        }

// ----------------------------  Function for Fetch Details For Enroll  --------------------------------- //
        let selectedStudents = [];
        function getStudentList() {
                const designationId = document.getElementById("designation").value;
                const semesterId = document.getElementById("semester").value;

                console.log(designationId);
                console.log(semesterId);
                $.ajax({
                    url: "getAllStudents", // Endpoint to fetch the list of students
                    type: "GET", // Use GET to retrieve data
                    data: { designation : designationId,
                            semester : semesterId },
                    success: function(response) {
                        selectedStudents = response; // Parse the JSON response
                        console.log(selectedStudents);
                        setCourseToStudents();
                    },
                    error: function(xhr, status, error) {
                        alert("Error Fetching details: " + error);
                    }
                });

                }


// ----------------------------  Function for set course to Students --------------------------------- //

        function setCourseToStudents() {
             document.getElementById("studentsTableContainer").innerHTML = `
                   <center> <h2>Set Course To Students</h2> </center>

                    <form id="setCourseForm">
                        <label>Course Id:</label>
                        <input type="text" id="courseId" name="courseId" required>
                        <input type="button" value="Enroll Course" onclick="enrollDetails()">
                    </form>
                    <br><br>
                    <div id="updateTeacherFormContainer"></div>
                `;

        }


// ----------------------------  Function for Enroll course to students --------------------------------- //

        function enrollDetails() {
            const courseId = document.getElementById("courseId").value;

            console.log(courseId);
            console.log(selectedStudents);

            $.ajax({
                url: "setEnrollments?course=" + courseId,  // Send courseId as a query parameter
                type: "POST",
                contentType: "application/json",  // Set content type to JSON
                data: JSON.stringify(selectedStudents),  // Serialize selectedStudents to JSON
                success: function(response) {
                    alert("Enrolled successfully!");
                    getStudentDetailsForEnroll();
                },
                error: function(xhr, status, error) {
                    console.error("The error: " + error);
                    alert("Error during enrollment: " + error);
                }
            });
        }


// ----------------------------  Function for set course to teachers --------------------------------- //

        let courseIds = []; // Global array to store course IDs

        function addCourseTeacher() {
            document.getElementById("right-content").innerHTML = `
            <h2>Assign Courses to Teacher</h2>
            <form>
                <label>Teacher ID:</label>
                <input type="text" id="teacherId" placeHolder= "Teacher Id..."> <!-- Replace with actual teacher ID -->
                <br><br>

                <label>Enter Course ID:</label>
                <input type="text" id="courseId">
                <button type="button" onclick="addCourses()">Add Course</button>

                <h3>Selected Courses:</h3>
                <select id="courseList" multiple></select>

                <br><br>
                <input type="button" onclick="submitForm(event)" value="Submit">
            </form>`;
        }

        function addCourses() {
            let courseId = document.getElementById("courseId").value.trim();
            if (!courseId) {
                alert("Please enter a valid Course ID.");
                return;
            }

            if (!courseIds.includes(courseId)) {
                courseIds.push(courseId);

                // Update the displayed course list
                let courseList = document.getElementById("courseList");
                let option = document.createElement("option");
                option.value = courseId;
                option.text = "Course ID: " + courseId;
                courseList.appendChild(option);

                document.getElementById("courseId").value = ""; // Clear input
            } else {
                alert("This course has already been added.");
            }
        }

        function submitForm(event) {
            event.preventDefault();

            const teacherId = document.getElementById("teacherId").value.trim();
            if (!teacherId) {
                alert("Please enter a valid Teacher ID.");
                return;
            }

            if (courseIds.length === 0) {
                alert("Please add at least one course.");
                return;
            }
            console.log(courseIds);
            console.log(teacherId);

            $.ajax({
                url: "assignCourses?teacherId=" + teacherId,  // Send courseId as a query parameter
                type: "POST",
                contentType: "application/json",  // Set content type to JSON
                data: JSON.stringify(courseIds),

                success: function(response) {
                    alert("Courses assigned successfully!");
                    resetForm(); // Reset the form after successful submission
                },
                error: function(xhr, status, error) {
                    alert("Error assigning courses: " + xhr.responseText);
                }
            });
        }

        function resetForm() {
            document.getElementById("teacherId").value = "";
            document.getElementById("courseId").value = "";
            document.getElementById("courseList").innerHTML = "";
            courseIds = [];
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