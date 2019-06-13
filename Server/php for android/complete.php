<?php

require "init.php";

$order_id = $_POST["o_id"];

echo $order_id;

$sql = "UPDATE Orderlist SET situation = '1' WHERE o_id like '".(int)$order_id."';";

if (mysqli_query($con, $sql)) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . mysqli_error($con);
}

mysqli_close($con);

?>