<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);
$sqlCommand = $connection->prepare("DELETE FROM temporary_place_order where email=?");
$sqlCommand->bind_param("s", $_GET["email"]);
$sqlCommand->execute();
