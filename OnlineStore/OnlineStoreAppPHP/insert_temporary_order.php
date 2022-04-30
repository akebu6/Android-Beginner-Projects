<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);
$sqlCommand = $connection->prepare("INSERT INTO temporary_place_order VALUES(?,?,?)");
$sqlCommand->bind_param("sii", $_GET["email"], $_GET["product_id"], $_GET["amount"]);
$sqlCommand->execute();


       

