<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);
$selectBrandCommands = $connection->prepare("select distinct brand from electronic_products");
$selectBrandCommands->execute();

$brandsResult = $selectBrandCommands->get_result();

$brands = array();

while($row = $brandsResult->fetch_assoc()) {
    
    array_push($brands, $row);
    
}

echo json_encode($brands);