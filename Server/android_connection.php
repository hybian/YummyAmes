<?php
$host="mysql.cs.iastate.edu";
$port=3306;
$socket="";
$user="dbu309sdb4";
$password="ZvtQfv!x";
$dbname="db309sdb4";

$con = new mysqli($host, $user, $password, $dbname, $port, $socket)
		or die ('Could not connect to the database server' . mysqli_connect_error());

?>