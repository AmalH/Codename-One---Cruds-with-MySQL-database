<?php
require_once('connect.php');

$title=$_GET['title'];
$sql = "DELETE FROM books WHERE title = '$title' ";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>