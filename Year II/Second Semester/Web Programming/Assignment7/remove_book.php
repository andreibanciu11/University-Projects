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

    $sql = "DELETE FROM books WHERE title = (?) AND author = (?) AND userID = (?)";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ssi",$parameter_title,$parameter_author,$userID);
    $stmt->execute();

    if ($stmt->affected_rows > 0) {
        echo '<script>alert("Book deleted!"); window.location.href = "http://localhost/Assignment7/main_menu.html";</script>';
        
        $sql_last_id = "SELECT MAX(id) AS last_id FROM books";
        $result_last_id = $conn->query($sql_last_id);
        $row_last_id = $result_last_id->fetch_assoc();
        $lastID = $row_last_id["last_id"];

    
        $sql_reset_id = "ALTER TABLE books AUTO_INCREMENT = " . ($lastID + 1);
        $conn->query($sql_reset_id);
    }
    else {
        echo '<script>alert("There was an error deleting the book."); window.location.href = "http://localhost/Assignment7/main_menu.html";</script>';
    }

    $stmt->close();
}
$conn->close();
?>