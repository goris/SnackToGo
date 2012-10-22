
<?php
global $enlace;
$enlace =  mysql_connect('localhost', 'root', '');
$bd_seleccionada = mysql_select_db('snack-to-go', $enlace);

$query  =  	'SELECT u.nombre, u.apellido, p.estado, p.entrega, a.nombre, lp.cantidad, p.usuario
				FROM usuarios u, pedidos p, articulos a, lineaspedido lp
					WHERE u.email = p.usuario AND a.id = lp.articulo AND p.id = lp.pedido';
$result = mysql_query($query) or die("MySQL ERROR: ".mysql_error());

?>
<html>
		<head>
			<script>
				function click(){
					if (window.XMLHttpRequest){// code for IE7+, Firefox, Chrome, Opera, Safari
					  xmlhttp=new XMLHttpRequest();
					} else {// code for IE6, IE5
					  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function(){
						if (xmlhttp.readyState==4 && xmlhttp.status==200){}
					  }
					xmlhttp.open("GET","update.php?q="+str,true);
					xmlhttp.send();
				}
			</script>
			<style>
			td{
				margin-right: 15px;
			}
			</style>
		</head>
			<body>
				<table>
				<tr>
				<td style= "padding-bottom: 20px;"><center><b>Nombre</b></center></td>&nbsp;<td style= "padding-bottom: 20px;"><center><b>Apellido</b></center></td>&nbsp;<td style= "padding-bottom: 20px;"><center><b>Estado</b></center></td>&nbsp;<td style= "padding-bottom: 20px;"><center><b>Entrega</center></td>&nbsp;
				<td style= "padding-bottom: 20px;"><center><b>Pedido</b></center></td>&nbsp;<td style= "padding-bottom: 20px;"><center><b>Cantidad</b></center></td>&nbsp;<td style= "padding-bottom: 20px;"><center><center><b>Confirmar termino de pedido</b></center></td>
			</tr>
<?php
while( $fila = mysql_fetch_array($result) ){
	echo '
			<tr>
				<td><center>'.$fila[0].'</center></td>&nbsp;<td><center>'.$fila[1].'</center></td>&nbsp;<td><center>'.$fila[2].'</center></td>&nbsp;<td><center>'.date("Y-m-d",strtotime($fila[3])).'</center></td>&nbsp;
				<td style="padding-left:30px">'.$fila[4].'</td>&nbsp;<td><center>'.$fila[5].'</center></td>&nbsp;<td><center><input type="button" value="Terminar Orden" onclick=click('.$fila[6].')/></center></td>
			</tr>';
}
?>
		</table>
	</body>
</html>