<?php

require "init.php";
		
$query = "SELECT * FROM Restaurant";

$result = mysqli_query($con, $query);
$tmpArr = array();
while($row = mysqli_fetch_assoc($result))
{
	$tmpArr[] = $row;	
}
echo json_encode($tmpArr);
$con->close();
?>
