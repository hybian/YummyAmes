<?php

require "init.php";

$customer_username = $_POST["c_username"];
$customer_password = $_POST["c_pwd"];

$sql = "select c_name,c_address,c_phone,c_email from Customer where c_username like '".$customer_username."' and c_pwd like '".$customer_password."';";

$result = mysqli_query($con,$sql);
$response = array();

if(mysqli_num_rows($result)>0)
{
	$row = mysqli_fetch_row($result);
	$c_name = $row[0];
	$c_address = $row[1];
	$c_phone = $row[2];
	$c_email = $row[3];
	$code = "login_success";
	array_push($response,array("code"=>$code,"c_name"=>$c_name,"c_address"=>$c_address,"c_phone"=>$c_phone,"c_email"=>$c_email));
	echo json_encode($response);
}
else
{
	$code = "login_failed";
	$message = "User not found, please try again";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);	
}

mysqli_close($con);

?>