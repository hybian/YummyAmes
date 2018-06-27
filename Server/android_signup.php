<?PHP
 
include("android_connection.php");
 
if(isset($_POST['username']) && isset($_POST['pwd']))
{
	$username=$_POST["username"];
    $password=$_POST["pwd"];
    
 $result = mysqli_query($con, "SELECT c_username FROM Customer WHERE c_username = '".$username."'"); 
 if(mysqli_num_rows($result) > 0)
 { 
 echo "username exist";
 exit;
 } 
 else
 { 
      $query="INSERT INTO Customer VALUES ('20','Default1','Default1','Default1','Default1','{$username}','{$password}',null)";
 
	echo "SQL query to execute: $query";
 
      $data=mysqli_query($con, $query);
 
    if($data)
      {
            echo "Successfully Signed Up";
      }
    else
      {
            echo "Error Sign up";
      }
 
 exit;
 } 
} 
 
?>