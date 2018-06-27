<?php
$host="mysql.cs.iastate.edu";
$port=3306;
$socket="";
$user="dbu309sdb4";
$password="ZvtQfv!x";
$dbname="db309sdb4";

$con = new mysqli($host, $user, $password, $dbname, $port, $socket)
		or die ('Could not connect to the database server' . mysqli_connect_error());
		
$query = "SELECT * FROM Food";

$result = mysqli_query($con, $query) or die("Error in selecting" . mysqli_error($con));
$tmpArr = array();
while($row =mysqli_fetch_assoc($result))
{
	$tmpArr[] = $row;	
}
echo json_encode($tmpArr);
$con->close();
?>
