<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);
$sqlCommand = $connection->prepare("SELECT id, name, price, email, amount FROM "
        . "temporary_place_order INNER JOIN electronic_products ON "
        . "electronic_products.id=temporary_place_order.product_id WHERE email=?");
$sqlCommand->bind_param("s", $_GET["email"]);
$sqlCommand->execute();

$tempOrderArray = array();
$sqlResult = $sqlCommand->get_result();

while($row = $sqlResult->fetch_assoc()) {
    
    array_push($tempOrderArray, $row);
    
}

echo json_encode($tempOrderArray);



