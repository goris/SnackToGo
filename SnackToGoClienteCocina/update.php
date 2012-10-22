<?php
global $enlace;
$enlace =  mysql_connect('localhost', 'root', '');
$bd_seleccionada = mysql_select_db('snack-to-go', $enlace);
$q=$_GET["q"];
$update  = 	'UPDATE `pedidos` SET `estado`= "entregado" WHERE `usuario` = "'.$q.'"';
?>
