<?php

require "init.php";

$deliver_name = $_POST["d_name"];
$deliver_phone = $_POST["d_phone"];
$deliver_email = $_POST["d_email"];
$deliver_username = $_POST["d_username"];
$deliver_password = $_POST["d_pwd"];
$deliver_date = date('Y-m-d H:i:s');

$sql = "select * from Deliver where d_username like '".$deliver_username."';";

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
	$sql = "insert into Deliver values(DEFAULT,'".$deliver_name."','".$deliver_phone."','".$deliver_email."','".$deliver_username."','".$deliver_password."','".$deliver_date."','".$deliver_date."');";
	$result = mysqli_query($con,$sql);
	$code = "reg_success";
	$message = "Thank you for register with us. Now you can login";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);	
}

mysqli_close($con);

?>