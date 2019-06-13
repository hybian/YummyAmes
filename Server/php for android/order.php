<?php

require "init.php";


$order_price = $_POST["o_price"];
$customer_name = $_POST["o_cusname"];
$customer_phone = $_POST["o_cusphone"];
$customer_address = $_POST["o_address"];
$restaurant_name = $_POST["r_name"];
$order_status = "0";


$sql = "select r_id from Restaurant where r_name like '".$restaurant_name."';";

$result = mysqli_query($con,$sql);
$response = array();

if(mysqli_num_rows($result)>0)
{
	$row = mysqli_fetch_row($result);
	$restaurant_id = $row[0];
	
	echo $restaurant_id;
	
	$sql = "select c_id from Customer where c_name like '".$customer_name."';";
	$result = mysqli_query($con,$sql);
	$response = array();
	$row = mysqli_fetch_row($result);
	$customer_id = $row[0];
	
	echo $customer_id;
	
	$sql = "insert into Orderlist values(DEFAULT,'".$order_price."','".$customer_name."','".$customer_phone."','".$customer_address."','".$restaurant_id."','".$customer_id."','".$order_status."', '0');";
	$result = mysqli_query($con,$sql);
	$code = "order_success";
	$message = "Thank you for order with us.";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);	
}
else
{
	$code = "order_failed";
	$message = "Something went wrong";
	array_push($response,array("code"=>$code,"message"=>$message));
	echo json_encode($response);	
}

mysqli_close($con);

?>