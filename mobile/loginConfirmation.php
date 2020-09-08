<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
    $password = $_POST['password'];
    $username = $_POST['username'];
    require_once('dbConnect.php');
    $HashedPass = md5($password);
    $sql = "SELECT * FROM users WHERE username='$username' AND password='$HashedPass'";
    $check = mysqli_fetch_array(mysqli_query($con,$sql));
    if (isset($check)) {
        echo '202';
    } else {
        echo '203';
    }
    mysqli_close($con);
}
?>