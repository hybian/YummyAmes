<?php

require "init.php";

$customer_name = $_POST["c_name"];
$customer_address = $_POST["c_address"];
$customer_phone = $_POST["c_phone"];
$customer_email = $_POST["c_email"];
$customer_username = $_POST["c_username"];
$customer_password = $_POST["c_pwd"];
$customer_date = date('Y-m-d H:i:s');

$sql = "select * from Customer where c_username like '".$customer_username."';";

$result = mysqli_query($con,$sql);
$response = array();

if(mysqli_num_rows($result)>0)
{
	$code = "reg_failed";
	$message = "User already exist";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);
}
else
{
	$sql = "insert into Customer values(DEFAULT,'".$customer_name."','".$customer_address."','".$customer_phone."','".$customer_email."','".$customer_username."','".$customer_password."','".$customer_date."');";
	$result = mysqli_query($con,$sql);
	$code = "reg_success";
	$message = "Thank you for register with us. Now you can login";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);	
}

mysqli_close($con);

?>