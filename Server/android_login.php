<?php
 
include("android_connection.php");
 
if(isset($_POST["c_username"]) && isset($_POST["c_pwd"]))
{
    
   $username=$_POST["c_username"];
    
   $password=$_POST["c_pwd"];
  
   $result = mysqli_query($con, "select * from Customer where c_username='$username' && c_pwd='$password'");
 
 if(mysqli_num_rows($result) > 0)
 { 
 echo "Login";
 exit;
 } 
 else
 { 
 echo "invalid";
 exit;
 }
}
 
 
?>