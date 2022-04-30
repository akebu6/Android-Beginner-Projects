<?php

$host = "localhost";
$user = "root";
$pwd = "";
$dbName = "online_store_db";
        
$connection = new mysqli($host, $user, $pwd, $dbName);

$check_login_info = $connection->prepare("select * from app_users_table where email=? and password=?");
$check_login_info->bind_param("ss", $_GET["email"], $_GET["password"]);
$check_login_info->execute();

$login_result = $check_login_info->get_result();

if($login_result->num_rows == 0) {
    
    echo "Login Failed";
    
} else {
    
    echo "Login Successful!!";
    
}
