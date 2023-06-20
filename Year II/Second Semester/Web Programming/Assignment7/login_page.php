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
echo "Connected successfully to db!<br>";

$sql = "SELECT * FROM users";
$result = $conn->query($sql);

// Get from url the users' input
$parameter_username = $_GET["username"];
$parameter_password = $_GET["password"];

$logged = false;

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
    	
        if($row["username"] == $parameter_username && $row["password"] == $parameter_password ){
        	$logged = true;

            $_SESSION["username"] = $row["username"];
            $_SESSION["password"] = $row["password"];

        	header("Location: http://localhost/Assignment7/main_menu.html");
        	break;
        }
    }
    
    if( $logged == false )
    	header("Location: http://localhost/Assignment7/main.html");
}

$conn->close();
?>