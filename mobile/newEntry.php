<?php
if ($_SERVER['REQUEST_METHOD']=='POST') {
    $username = $_POST['vartotojas'];
    $name = $_POST['pavadinimas'];
    $continent = $_POST['zemynas'];
    $duration = $_POST['trukme'];
    $seasons = $_POST['metulaikas'];
    $traveltype = $_POST['kelionestipas'];
    require_once('dbConnect.php');
    $sql = "INSERT INTO ". keliones . " (data, username, name, continent, duration, seasons, traveltype)
VALUES (NOW(), '$username', '$name', '$continent', '$duration', '$seasons', '$traveltype')";
    if (mysqli_query($con,$sql)) {
        echo 'Naujas įrašas buvo sėkmingai įtrauktas.';
    } else {
        echo 'Nepavyko sukurti įrašo';
    }
    mysqli_close($con);
}
?>

// laiko formato nekeisti, nes taip jau buvo sukurta
// is php
function addNewEntry($username, $name, $continent, $duration, $seasons, $traveltype) {
$q = "INSERT INTO " . TBL_TRAVELS . " (data, username, name, continent, duration, seasons, traveltype)
VALUES
(NOW(), '".$username."', '".$name."', '".$continent."', '".$duration."', '".$seasons."','".$traveltype."')";
return mysql_query($q, $this->connection);

/* Entry registration attempt */
//  $retval = $session->newEntry($_POST['vartotojas'], $_POST['pavadinimas'], $_POST['zemynas'], $_POST['trukme'], $seasons, $_POST['kelionestipas']);
$retval = $session->newEntry($_POST['pavadinimas'], $_POST['zemynas'], $_POST['trukme'], $seasons, $_POST['kelionestipas']);
// seasons nes kitaip visa masyva duotu array zodi