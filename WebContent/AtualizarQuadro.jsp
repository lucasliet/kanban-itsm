<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Atualizar Quadro</title>
		<link rel="stylesheet"
					href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>
	<body>
	
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-sm-8">
					<form action="quadro.do" method="POST"
						class="card my-3 rounded shadow bg-light">
						<div class="card-header bg-dark text-white p-3">
							<h1>Quadro Atualizar</h1>
						</div>
	
						<div class="my-auto">
							<div class="card-body p-3">
								<div class="row">
									<div class="col-sm-2">
										<div class="input-group ">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1"> 
													id
												</span>
											</div>
											<input type="hidden" value="${quadro.id }">
											<p class="form-control" id="id">${quadro.id}</p>
										</div>
									</div>
									<div class="col-sm-10">
										<div class="input-group ">
											<div class="input-group-prepend">
												<span class="input-group-text" id="basic-addon1"> 
													titulo
												</span>
											</div>
											<input type="text" class="form-control" name="titulo" value="${quadro.titulo}">
										</div>
									</div>
								</div>
							</div>
							<div class="text-right">
								<button type="submit" name="acao" value="btn-atualizar" class="btn btn-warning">Atualizar</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	
		<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
		<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
		<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
	</body>
</html>