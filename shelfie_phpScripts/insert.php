<?php
require_once('connect.php');

$title=$_GET['title'];
$author=$_GET['author'];
$category=$_GET['category'];
$isbn=$_GET['isbn'];
$sql = "INSERT INTO books (title,author,category,isbn)
VALUES ( '$title','$author','$category','$isbn')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}

mysqli_close($conn);
?>