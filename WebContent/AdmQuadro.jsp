<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ADM Quadro</title>
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>
	<body>
		<header>
			<a href="index.html" class="btn cbtn-back text-white">
				<i class="fas fa-arrow-circle-left"></i>
			</a>
	        <div class="bg-dark cbg-rolo text-white text-center p-2">
	            <h1 class="text-bold text-uppercase ctext-shadow ">Teste do quadro</h1>
	        </div>
    	</header>
	    <div class="container my-3">
	       	<form action="manter_filmes.do" method="POST" class="card my-3 rounded shadow bg-light">
	            <div class="card-header bg-dark text-white p-3">
	            	<div class="row">
	            		<div class="col-md-9">
			                <h1><i class="fas fa-film"></i> Listar Quadros</h1>
			                <p>Lista de todos os quadros do banco</p>
	            		</div>
	            		<div class="col-md-3 my-auto pr-4">
		                	<button type="submit" name="acao" value="page-todos"
		                                class="btn btn-light float-right">Exibir</button>
	            		</div>
	                </div>                
	            </div>
	        </form>  
	        <div class="row">
	            <div class="col-sm-12 col-md-6">
	                <form action="manter_filmes.do" method="GET" class="card my-3 rounded shadow bg-light">
	                    <div class="card-header bg-dark text-white p-3">
	                        <h1><i class="fas fa-film"></i> Exibir quadro</h1>
	                        <p>Selecione o quadro a ser exibido</p>
	                    </div>
	                    <div class="card-body p-3">
	                        <div class="form-row">
	                            <div class="form-group col-12">
	                                <label for="id_exibir">Quadro</label>
	                                <div class="input-group">
	                            
	                                    <select name="id_exibir" class="form-control" required>
	                                        <option value="" disabled selected>Selecione o quadro</option>
	                                        <c:forEach var="quadro" items="${quadros}" >
	                                        	<option value="${quadro.id}">${quadro.titulo}</option>
	                                        </c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="text-right">
	                            <button type="submit" name="acao" value="page-exibir"
	                                class="btn btn-success">Exibir</button>
	                        </div>
	                    </div>
	                </form>
	            </div>
	
	            <div class="col-sm-12 col-md-6">
	                <form action="manter_filmes.do" method="GET" class="card my-3 rounded shadow bg-light">
	                    <div class="card-header bg-dark text-white p-3">
	                        <h1><i class="fas fa-film"></i> Atualizar Quadro</h1>
	                        <p>Selecione o Quadro para ser alterado</p>
	                    </div>
	                    <div class="card-body p-3">
	                        <div class="form-row">
	                            <div class="form-group col-12">
	                                <label for="id_atualizar">Quadro</label>
	                                <div class="input-group">
	                                    
	                                    <select name="id_atualizar" class="form-control" required>
	                                        <option value="" disabled selected>Selecione o quadro</option>
	                                        <c:forEach var="quadro" items="${quadros}" >
	                                        	<option value="${quadro.id}">${quadro.titulo}</option>
	                                        </c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="text-right">
	                            <button type="submit" name="acao" value="page-atualizar"
	                                class="btn btn-warning">Atualizar</button>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
			
			<div class="row">
			    <div class="col-sm-12 col-md-6"> 
			        <form action="manter_filmes.do" method="POST" class="card my-3 rounded shadow bg-light">
			            <div class="card-header bg-dark text-white p-3">
			                <h1><i class="fas fa-film"></i> Inserir Quadro</h1>
			                <p>Preencha os dados para inserir um novo quadro</p>
			            </div>
			            <div class="card-body p-3">
			                <div class="form-row">
			                    <div class="form-group col-sm-12 col-md-6">
			                        <label for="titulo">Titulo</label>
			                        <div class="input-group">
			   
			                            <input type="text" class="form-control" name="titulo" placeholder="Kanban" required>
			                        </div>
			                    </div>
			                </div> 
			                <div class="text-right">
			                    <button type="submit" name="acao" value="btn-inserir" class="btn btn-primary">Enviar</button>
			                </div>
			            </div>
			        </form>
			     </div>
			     <div class="col-sm-12 col-md-6">
	                <form action="manter_filmes.do" method="GET" class="card my-3 rounded shadow bg-light">
	                    <div class="card-header bg-dark text-white p-3">
	                        <h1><i class="fas fa-film"></i> Excluir Quadro</h1>
	                        <p>Selecione o Quadro a ser deletado</p>
	                    </div>
	                    <div class="card-body p-3">
	                        <div class="form-row">
	                            <div class="form-group col-12">
	                                <label for="id_excluir">Quadro</label>
	                                <div class="input-group">
	                                    <select name="id_excluir" class="form-control" required>
	                                        <option value="" disabled selected>Selecione o quadro</option> 
                                            <c:forEach var="quadro" items="${quadros}" >
	                                        	<option value="${quadro.id}">${quadro.titulo}</option>
	                                        </c:forEach> 
	                                    </select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="text-right">
	                            <button type="submit" name="acao" value="page-excluir"
	                                class="btn btn-danger text-uppercase">excluir</button>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</body>
</html>