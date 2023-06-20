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
	$sql = "SELECT * FROM books WHERE userID = " . $userID . "";

	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		echo '<table style="width:50%;border-collapse: collapse;"><tr style="border: 1px solid black;"><th>id</th><th>author</th><th>title</th><th>number_of_pages</th><th>genre</rh></tr>';
		while($row = $result->fetch_assoc()) {
			echo '<tr style="border: 1px solid black;"><td style="border: 1px solid black; text-align: center;">' . $row["id"] . '</td><td style="border: 1px solid black;text-align: center;"> ' . $row["author"] . '</td><td style="border: 1px solid black; text-align: center;">' . $row["title"] . '</td><td style="border: 1px solid black; text-align: center;">' . $row["number_of_pages"] . '</td><td style = "text-align: center;"">' . $row["genre"] . "</td></tr>";
		}
		echo "</table>";
	}
	else{
		echo "There are no books for this user!";
	}
}
$conn->close();
?>