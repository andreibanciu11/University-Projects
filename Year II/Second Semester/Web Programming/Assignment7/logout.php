<?php
session_start();

$_SESSION = array();

session_destroy();

header("Location: http://localhost/Assignment7/main.html");
exit();
?>