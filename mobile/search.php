<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
    $username = $_POST['username'];
    $id = $_POST['id'];	//tipas
    $DISPLAY_ALL_ENTRIES = -1; //empty search
    $sql = ""; //kad matytu kintamaji
    if (strcasecmp($username, "Administratorius") == 0) {  //admin
        if ($id ==$DISPLAY_ALL_ENTRIES) {
            $sql = "SELECT * FROM keliones ORDER BY name ASC";
        } else {
            $sql = "SELECT * FROM keliones WHERE id='$id'";
        }
    }
    else {  //user
        if ($id ==$DISPLAY_ALL_ENTRIES) {
            $sql = "SELECT * FROM keliones WHERE username LIKE '$username' ORDER BY name ASC";
        } else {
            $sql = "SELECT * FROM keliones WHERE username LIKE '$username' AND id='$id'";
        }
    }
    require_once('dbConnect.php'); //i duombaze einam
    $array = mysqli_fetch_array(mysqli_query($con,$sql)); //connection i sql
    if (isset($array)) { //jeigu masyvas yra tai pavyko
        echo json_encode($array);  //duomenu baze grazina rezultatus masyve(array) kazkokiam sarase. ir paverciam json formatu. su echo grazinam atgal rezultatus
    } else {
        echo 'Nepavyko prisijungti prie duomenų bazės. Bandykite vėliau.'; // užklausos klaida
    }
    mysqli_close($con);
}
?>