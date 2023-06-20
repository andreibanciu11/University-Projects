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

// Get from url the users' input
$parameter_username = $_GET["username"];
$parameter_password = $_GET["password"];

$logged = false;

$sql2 = "SELECT * FROM users";
$result = $conn->query($sql2);
$available_username = true;

if ($result->num_rows > 0) {
    
    while($row = $result->fetch_assoc()) {
    	
        if($row["username"] == $parameter_username){
        	$available_username = false;
            break;
        }
    }
}

if( $available_username == true ){
    $sql = "INSERT INTO users(username, password) VALUES (?,?)";
	$stmt = $conn->prepare($sql);
	$stmt->bind_param("ss", $parameter_username, $parameter_password);
	$stmt->execute();
	$_SESSION["username"] = $row["username"];
    $_SESSION["password"] = $row["password"];

    header("Location: http://localhost/Assignment7/main_menu.html");
}
else{
    echo "The username is already used, go back and try again!";
}

?>