<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Details Application</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }
        .container {
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-bottom: 30px;
            width: 100%;
            max-width: 600px;
            margin: 20px auto;
        }
        h2, h4 {
            text-align: center;
            color: #333;
        }
        .form-upload, .form-delete, .form-show, .form-update{
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 12px;
            font-size: 14px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #ffffff;
            border: none;
            padding: 10px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .form-show {
            text-align: center;
        }
        .form-show input[type="submit"] {
            padding: 10px 20px;
        }
    </style>
    <script>
        // Display popup notification if a message is present
        window.onload = function() {
            const message = "${message}";
            if (message) {
                alert(message);
            }
        };
    </script>


</head>
<body>

    <div class="container">
        <h2>Employee Details Upload</h2>
        <form action="/success" method="post" class="form-upload">
            <label for="id">Employee ID</label>
            <input type="text" id="id" name="id" required placeholder="Enter employee ID">

            <label for="name">Employee Name</label>
            <input type="text" id="name" name="name" required placeholder="Enter employee name">

            <label for="age">Age</label>
            <input type="text" id="age" name="age" required placeholder="Enter employee age">

            <label for="salary">Salary</label>
            <input type="text" id="salary" name="salary" required placeholder="Enter salary">

            <input type="submit" value="Submit">
        </form>
    </div>
    <div class= "container">
         <h4>Update Student Details</h4>

        <!-- Form to fetch student details -->
        <form action="/fetch" method="post" class="form-update">
            <label for="id">Student ID:</label>
            <input type="text" id="id" name="id" required placeholder="Enter employee ID">
            <input type="submit" value="Get Details">
        </form>

    </div>
    <div class="container">
        <h4>Delete Employee Record</h4>
        <form action="/delete" method="post" class="form-delete">
            <label for="id1">Enter Employee ID to Delete</label>
            <input type="text" id="id1" name="id1" required placeholder="Enter employee ID">
            <input type="submit" value="Delete">
        </form>
    </div>

    <div class="container">
        <h4>Show Employee Details</h4>
        <form action="/show" method="post" class="form-show">
            <input type="submit" value="Show Details">
        </form>
    </div>
</body>
</html>


<!-- <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <c:if test="${not empty message}">
            <script>
                Swal.fire({
                    icon: 'success',
                    title: 'Notification',
                    text: '${message}',
                    confirmButtonText: 'OK'
                });
            </script>
        </c:if> -->