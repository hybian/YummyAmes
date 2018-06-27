<?PHP
 
include("android_connection.php");
 
if(isset($_POST["foodName"]) && isset($_POST["cusName"]) && isset($_POST["cusPhone"]) && isset($_POST["cusAddr"]))
{
	$foodName=$_POST["foodName"];
	$cusName=$_POST["cusName"];
	$cusPhone=(int)$_POST["cusPhone"];
	$cusAddr=$_POST["cusAddr"];
    
    $query="INSERT INTO Orderlist VALUES ('15','123','{$cusName}','123431','{$cusAddr}','1','2','3')";
 
	echo "SQL query to execute: $query";
 
      $data=mysqli_query($con, $query);
 
    if($data)
      {
            echo "Successfully Ordered";
      }
    else
      {
            echo "Error ordering";
      }
 
 exit;

} 
 
?>