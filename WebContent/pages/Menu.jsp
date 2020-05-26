<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<nav class="navbar barraMenu navbar-expand-lg navbar-light bg-light top-fixed">
	<a class="navbar-brand" href="/pages/Home.jsp"> <img src="/img/logo-light.svg"
												  width="80" alt="">
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item  active">
				<a class="nav-link text-white" href="/pages/Home.jsp">
					<i class="fas fa-home mr-1"></i>Home <span class="sr-only">(current)</span>
				</a>
			</li>
			<li class="nav-item ">
				<a class="nav-link text-white" href="/quadro.do?acao=page-meus-quadros">
					<i class="far fa-file-alt mr-1"></i>Meus quadros
				</a>
			</li>
			<li class="nav-item " onclick="QuadroModal()">
				<a class="nav-link text-white">
					<i class="fas fa-plus mr-1"></i>Novo quadro
				</a>
			</li>
		</ul>
		<ul class="navbar-nav mr-2">
			<div class="dropdown  dropleft">
				<img id="img" src="/img/fotoPadrao.png" class="c-imgLogin"
					 alt="avatar" data-toggle="dropdown" aria-haspopup="true"
					 aria-expanded="false">
				<div class="dropdown-menu c-menu m-2" aria-labelledby="dropdownMenuButton">
					<a class="dropdown-item text-white" href="/pages/usuario/EditarPerfil.jsp">
						<i class="fas fa-user mr-1"></i>Editar perfil
					</a> 
					<a class="dropdown-item text-white" href="/usuario.do?acao=logout">
						<i class="fas fa-power-off mr-1"></i>Sair
					</a>
				</div>
			</div>
		</ul>
		<!--Modal -->

		<div id="NewQuadro" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<form action="/quadro.do" method="POST">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">Digite o nome do quadro</h5>
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="input-group mb-3">
								<div class="input-group-prepend">
									<span class="input-group-text" id="basic-addon1"><i class="fas fa-id-card"></i></span>
								</div>
								<input type="text" class="form-control" name="titulo" placeholder="Nome do quadro" aria-label="Username" aria-describedby="basic-addon1" required>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
							<button type="submit" class="btn btn-geral" name="acao" value="btn-inserir">Salvar</button>
						</div>
					</div>
				</div>
			</form>
		</div>


		<script>
			function QuadroModal() {
				$("#NewQuadro").modal({
					show: true
				});
			}
		</script>
	</div>
</nav>