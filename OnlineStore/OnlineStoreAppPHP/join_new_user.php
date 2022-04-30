<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);

$del = $connection->prepare("INSERT INTO electronic_products VALUES(?,?,?,?,?)");
$del->bind_param("isiss", $_GET["id"], $_GET["name"], $_GET["price"], $_GET["brand"], $_GET["picture"]);
$del->execute();

// If an email already exists, do not add the user again
$emailCheckSQLCommand = $connection->prepare("select * from app_users_table where email=?");
$emailCheckSQLCommand->bind_param("s", $_GET["email"]);
$emailCheckSQLCommand->execute();
$emailResult = $emailCheckSQLCommand->get_result();

if ($emailResult->num_rows == 0) {
    
    $sqlCommand = $connection->prepare("insert into app_users_table values (?, ?, ?)");
    $sqlCommand->bind_param("sss", $_GET["email"], $_GET["username"], $_GET["pass"]);
    $sqlCommand->execute();

echo 'Registration Successful!';

} else {
    echo 'Registration Failed. Please Try Again';
}
