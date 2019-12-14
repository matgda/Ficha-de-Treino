<?php

use Slim\Http\Request;
use Slim\Http\Response;

$app->post('/lista_exercicio', function ($request, $response) {
		$listaEspera = $request->getParsedBody();
	
	$listaEsperaInsert[] = $listaEspera['nome_exercicio'];
	$listaEsperaInsert[] = $listaEspera['nome_lista'];
	$listaEsperaInsert[] = $listaEspera['numero_series'];
	$listaEsperaInsert[] = $listaEspera['numero_repeticoes'];
	$listaEsperaInsert[] = $listaEspera['fk_usuario'];
	$db = $this->db;

$sth = $db->prepare("INSERT INTO lista_exercicio(nome_exercicio,nome_lista,numero_series,numero_repeticoes,fk_usuario) VALUES (?,?,?,?,?)");
	
	try{
		$sth->execute($listaEsperaInsert);
	}catch(PDOException $e){
		return $response->withStatus(404);
	}

	$lastInsertId = $db->lastInsertId();

	$sth = $db->prepare("SELECT * FROM lista_exercicio WHERE id = ?");
	
	try{
		$sth->execute(array($lastInsertId));
		$listaEsperaDB = $sth->fetch(PDO::FETCH_OBJ);
	}catch(PDOException $e){
		return $response->withStatus(404);
	}
	
	return $response->withJson($listaEsperaDB);
});

$app->get('/lista_exercicio', function ($request, $response) {
	$db = $this-> db;
	foreach ($db->query   ('SELECT * FROM lista_exercicio ORDER BY lista_exercicio.nome_Lista ASC') as $linha   )    {
		$retorno[] = $linha;
	}
	return $response->withJson($retorno);
});


$app->delete('/lista_exercicio/{id}', function ($request, $response, $args) {
	$db = $this->db;

	$id[] = $args['id'];

	$sth = $db->prepare('DELETE FROM lista_exercicio WHERe id = ?');

	try{
		$sth->execute($id);
	}catch(PDOException $e){
		return $response->withStatus(404);
	}

	return $response->withStatus(200);
});

$app->post('/usuarios', function ($request, $response) {
		$listaEspera = $request->getParsedBody();

	
	$listaEsperaInsert[] = $listaEspera['usuario'];
	$listaEsperaInsert[] = $listaEspera['senha'];

	$db = $this->db;


$sth = $db->prepare("INSERT INTO login(usuario,senha) VALUES (?,?)");
	
	try{
		$sth->execute($listaEsperaInsert);
	}catch(PDOException $e){
		return $response->withStatus(404);
	}

	$lastInsertId = $db->lastInsertId();

	$sth = $db->prepare("SELECT * FROM login WHERE id = ?");
	
	try{
		$sth->execute(array($lastInsertId));
		$listaEsperaDB = $sth->fetch(PDO::FETCH_OBJ);
	}catch(PDOException $e){
		return $response->withStatus(404);
	}

	return $response->withJson($listaEsperaDB);
});

$app->get('/usuarios', function ($request, $response) {
	
	$db = $this->db;

	
	foreach($db->query('SELECT * FROM login') as $linha){
		
		$retorno[] = $linha;
	};
	
	return $response->withJson($retorno);
});

$app->post('/validar', function ($request, $response) {
		
		$listaEspera = $request->getParsedBody();

	
	$listaEsperaInsert[] = $listaEspera['usuario'];
	$listaEsperaInsert[] = $listaEspera['senha'];

	$db = $this->db;

	$sth = $db->prepare("SELECT * FROM login WHERE usuario = ? and senha =?");
	
	try{
		$sth->execute( $listaEsperaInsert  );
		$listaEsperaDB = $sth->fetch(PDO::FETCH_OBJ);
	}catch(PDOException $e){
		return $response->withStatus(400);
	}

	return $response->withJson($listaEsperaDB);
});


