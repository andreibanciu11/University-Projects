<?php

session_start();

$servername = "localhost";
$username = "root";
$password = "A10N10D10";
$db = "personal_library";

// Create connection
$conn = new mysqli($servername, $username, $password,$db);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
// Retrieve the userID using the session for username
$parameter_username = $_SESSION["username"];
$userID = null;

// Query to get the userID based on the username
$sql_user = "SELECT id FROM users WHERE username = ?";
$stmt_user = $conn->prepare($sql_user);
$stmt_user->bind_param("s", $parameter_username);
$stmt_user->execute();
$stmt_user->bind_result($userID);
$stmt_user->fetch();
$stmt_user->close();

if ($userID !== null) {
    $parameter_title = $_GET["title"];
    $parameter_author = $_GET["author"];
    $parameter_number_of_pages = intval($_GET["number_of_pages"]);
    $parameter_genre = $_GET["genre"];

    $sql = "INSERT INTO books (author, title, number_of_pages, genre, userID) VALUES (?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ssisi", $parameter_author, $parameter_title, $parameter_number_of_pages, $parameter_genre, $userID);
    $stmt->execute();
    

    if ($stmt->affected_rows > 0) {
        echo '<script>alert("Book added successfully!"); window.location.href = "http://localhost/Assignment7/main_menu.html";</script>';
    } else {
        echo '<script>alert("There was an error adding the book."); window.location.href = "http://localhost/Assignment7/main_menu.html";</script>';
    }

    $stmt->close();
}

$conn->close();
?>