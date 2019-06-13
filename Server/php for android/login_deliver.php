<?php

require "init.php";

$deliver_username = $_POST["d_username"];
$deliver_password = $_POST["d_pwd"];

$sql = "select d_id,d_name,d_phone,d_email from Deliver where d_username like '".$deliver_username."' and d_pwd like '".$deliver_password."';";

$result = mysqli_query($con,$sql);
$response = array();

if(mysqli_num_rows($result)>0)
{
	$row = mysqli_fetch_row($result);
	$d_id = $row[0];
	$d_name = $row[1];
	$d_phone = $row[2];
	$d_email = $row[3];
	$code = "login_success";
	array_push($response,array("code"=>$code, "d_id"=>$d_id, "d_name"=>$d_name, "d_phone"=>$d_phone,"d_email"=>$d_email));
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
