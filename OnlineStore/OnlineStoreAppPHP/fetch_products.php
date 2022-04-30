<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);
$fetchProductsCommand = $connection->prepare("SELECT * FROM electronic_products WHERE brand=?");
$fetchProductsCommand->bind_param("s", $_GET["brand"]);
$fetchProductsCommand->execute();

$epResults = $fetchProductsCommand->get_result();
$epArray = array();

while ($row = $epResults->fetch_assoc()) {
    
    array_push($epArray, $row);
    
}

echo json_encode($epArray);

