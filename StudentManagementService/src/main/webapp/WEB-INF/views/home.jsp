<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Role Selection</title>
    <style>
        /* General Styles */
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #667eea, #764ba2);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            color: #fff;
        }

        .container {
            text-align: center;
        }

        h1 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
        }

        .card-container {
            display: flex;
            gap: 30px;
            justify-content: center;
        }

        .card {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            padding: 30px;
            width: 200px;
            text-align: center;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            transition: transform 0.3s ease, box-shadow 0.3s ease, background 0.3s ease;
            cursor: pointer;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.2);
            background: rgba(255, 255, 255, 0.2);
        }

        .card h2 {
            margin: 0;
            font-size: 1.5rem;
            color: black;
        }

        .card p {
            font-size: 0.9rem;
            color: black;
            margin: 10px 0 0;
        }

        /* Role-Specific Card Colors */
        .card.admin {
            background: linear-gradient(135deg, #ff6b6b, #ffa5a5);
        }

        .card.teacher {
            background: linear-gradient(135deg, #4ecdc4, #88e0d7);
        }

        .card.student {
            background: linear-gradient(135deg, #ffe66d, #ffef9f);
        }

        .card.admin:hover {
            background: linear-gradient(135deg, #ff4d4d, #ff8f8f);
        }

        .card.teacher:hover {
            background: linear-gradient(135deg, #3aa89f, #6fd8cf);
        }

        .card.student:hover {
            background: linear-gradient(135deg, #ffd700, #ffdf4d);
        }

        /* Button Animation */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .card {
            animation: fadeIn 0.5s ease-out;
        }

        .card:nth-child(1) {
            animation-delay: 0.2s;
        }

        .card:nth-child(2) {
            animation-delay: 0.4s;
        }

        .card:nth-child(3) {
            animation-delay: 0.6s;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Portal</h1>
        <div class="card-container">
            <div class="card admin" onclick="window.location.href='/admin/home'">
                <h2>Admin</h2>
                <p>Access administrative functions</p>
            </div>
            <div class="card teacher" onclick="window.location.href='/teacher/home'">
                <h2>Teacher</h2>
                <p>Access teaching resources</p>
            </div>
            <div class="card student" onclick="window.location.href='/student/mainPage'">
                <h2>Student</h2>
                <p>Access student portal</p>
            </div>
        </div>
    </div>
</body>
</html>