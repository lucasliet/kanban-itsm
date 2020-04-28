<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<nav class="navbar barraMenu navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#"> <img src="img/logoHeader.png"
		width="150" alt="">
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link text-white"
				href="#"> <i class="fas fa-home mr-1"></i>Home <span
					class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item"><a class="nav-link text-white" href="#">
					<i class="far fa-file-alt mr-1"></i>Meus quadros
			</a></li>
			<li class="nav-item"><a class="nav-link text-white" href="#">
					<i class="fas fa-plus mr-1"></i>Novo processo
			</a></li>
		</ul>
		<ul class="navbar-nav mr-2">
			<div class="dropdown  dropleft">
				<img id="img" src="img/fotoPadrao.png" class="c-imgLogin"
					alt="avatar" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
				<div class="dropdown-menu c-menu m-2"
					aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item text-white" href="#"> <i
						class="fas fa-user mr-1"></i>Editar perfil
					</a> <a class="dropdown-item text-white" href="index.jsp"> <i
						class="fas fa-power-off mr-1"></i>Sair
					</a>
				</div>
			</div>
		</ul>
	</div>
</nav>