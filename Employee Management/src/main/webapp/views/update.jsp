<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: #ffffff;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 20px 30px;
            width: 400px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .container h1 {
            font-size: 20px;
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        p {
            text-align: center;
            margin: 10px 0;
        }

        p[style="color: red;"] {
            font-weight: bold;
        }

        p[style="color: green;"] {
            font-weight: bold;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }

        input[type="text"],
        input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            font-weight: bold;
        }

        .success {
            color: green;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class='container'>
        <!-- Display error or success messages -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <c:if test="${not empty success}">
            <p class="success">${success}</p>
        </c:if>

        <!-- Form to update student details -->
        <c:if test="${not empty student}">
            <form action="/index" method="post">
                <h1>Update Student</h1>
                <input type="hidden" name="id" value="${emp.id}">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${emp.name}" required>
                <label for="age">Age:</label>
                <input type="number" id="age" name="age" value="${emp.age}" required>
                <label for="salary">Salary:</label>
                <input type="text" id="salary" name="salary" value="${emp.salary}" required>
                <button type="submit">Save</button>
            </form>
        </c:if>
    </div>
</body>
</html>
