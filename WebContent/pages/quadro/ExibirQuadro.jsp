<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="/img/favicon.ico" type="image/x-icon">

        <title>Quadro</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="/css/global.css">
        <link rel="stylesheet" href="/css/exibirQuadro.css">
    </head>
    <body>
        <header>
            <c:import url="/pages/Menu.jsp"/>
        </header>
        <div class="d-flex bg-info c-bg-info mb-2 py-3 col-12 text-white">
            <svg class="bi bi-grid-1x2 mx-2" width="1.4em" height="1.3em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg" >
                <path fill-rule="evenodd" d="M6 1H1v14h5V1zm9 0h-5v5h5V1zm0 9h-5v5h5v-5zM0 1a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm9 0a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1V1zm1 8a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1v-5a1 1 0 0 0-1-1h-5z"/>
            </svg>
            <p style="margin: 0;">${quadro.titulo}</p>
        </div>
        <div class="container-fluid">
            <div class="scrolling-wrapper">
                <c:forEach var="coluna" items="${colunas}">
                    <div class="col-md-3 col-12">
                        <div id="#coluna-scroll" class="c-coluna c-rolagem">
                            <div class="card card-body c-card-body fixed">
                                <div class="header c-header">
                                    <p>${coluna.titulo}</p>
                                    <div class="dropleft">
                                        <svg class="bi bi-filter-right" width="1.5em" height="1.5em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg"
                                             id="dropdown${coluna.id}" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <path fill-rule="evenodd" d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z"/>
                                        </svg>
                                        <div class="dropdown-menu m-2" aria-labelledby="dropdown${coluna.id}">
                                            <div class="header c-header">
                                                <span class="dropdown-item-text">Ações da coluna</span>
                                                <i class="fas fa-times mt-2 mr-2" data-toggle="collapse" href="#coluna"
                                                   role="button" aria-expanded="false" aria-controls="coluna">
                                                </i>
                                            </div>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" data-toggle="modal" data-target="#ModalAdicionarTicket${coluna.id}">
                                                <i class="fas fa-plus mr-2"></i>Adicionar Ticket
                                            </a>
                                            <a class="dropdown-item ">
                                                <i class="fas fa-edit mr-1"></i>Editar Coluna
                                            </a>
                                            <a class="dropdown-item ">
                                                <i class="fas fa-arrow-right mr-2"></i>Mover Coluna
                                            </a>
                                            <a class="dropdown-item" href="coluna.do?id_excluir=${coluna.id}&acao=btn-excluir">
                                                <i class="far fa-trash-alt mr-2"></i>Deletar Coluna
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:forEach var="ticket" items="${coluna.tickets}">
                                <div class="card m-2">
                                    <div class="card-body" data-toggle="modal" data-target="#modalTicket">
                                        <h5 class="card-title">${ticket.titulo}</h5>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <!-- modal adicionar ticket -->
                    <div id="ModalAdicionarTicket${coluna.id}" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                        <form action="/ticket.do" method="POST">
                            <div class="modal-dialog modal-sm">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="titleTicket">Digite o nome do ticket</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="input-group mb-3">
                                            <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputTicket">
                                                <i class="fas fa-id-card"></i>
                                            </span>
                                            </div>
                                            <input type="hidden" name="id_quadro" value="${quadro.id}">
                                            <input type="hidden" name="id_coluna" value="${coluna.id}">
                                            <input type="text" class="form-control" name="titulo" placeholder="Nome do ticket"
                                                   aria-label="Username" aria-describedby="inputTicket" required
                                            />
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
                </c:forEach>
                <div class="col-md-3 col-12">
                    <button id="btn-coluna" class="btn btn-outline-geral col-md-12" data-toggle="modal" data-target="#ModalColuna">
                        <i class="fas fa-plus mr-1"></i>Adicionar outra coluna
                    </button>
                </div>


                <!-- Modal adicionar coluna-->
                <div id="ModalColuna" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                    <form action="/coluna.do" method="POST">
                        <div class="modal-dialog modal-sm">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="titleColuna">Digite o nome da coluna</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputColuna"><i class="fas fa-id-card"></i></span>
                                        </div>
                                        <input type="hidden" name="id_quadro" value="${quadro.id}">
                                        <input type="text" class="form-control" name="titulo" placeholder="Nome da Coluna"
                                               aria-label="Username" aria-describedby="inputColuna" required
                                        />
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

                <!-- Modal de editar ticket-->
                <div id="modalTicket" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="titleEditarTicket">Titulo do Ticket</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-md-10">
                                        <form action="">
                                            <div class="input-group">
                                                <label><i class="fas fa-align-left mr-2"></i>Descrição</label>
                                            </div>
                                            <div class="input-group">
                                                <textarea class="form-control" name="" value=""
                                                      aria-label="With textarea" placeholder="Digite a descrição do ticket">
                                                </textarea>
                                            </div>
                                            <div class="input-group mt-5">
                                                <label><i class="fas fa-paperclip mr-2"></i>Anexo</label>
                                            </div>
                                            <div class="input-group">
                                                <div class="col-md-8">
                                                    <div class="card">
                                                        <div class="card-body">
                                                            Aqui vai os anexos.
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="input-group mt-5">
                                                <label><i class="far fa-comment-alt mr-2"></i></i>Comentário</label>
                                            </div>
                                            <div class="input-group">
                                                <input class="form-control" value="" placeholder="Faça um comentário">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <button type="submit" class="btn btn-modal text-white mt-2" name="" value="">
                                            <i class="fas fa-paperclip mr-2"></i>Anexo
                                        </button>
                                        <button type="submit" class="btn btn-modal text-white mt-2" name="" value="">
                                            <i class="fas fa-arrow-right mr-2"></i>Mover
                                        </button>
                                        <button type="submit" class="btn btn-modal text-white mt-2" name="" value="">
                                            <i class="far fa-edit mr-2"></i>Editar
                                        </button>
                                        <button type="submit" class="btn btn-modal text-white mt-2" name="" value="">
                                            <i class="fas fa-trash mr-2"></i>Excluir
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-geral" name="" value="">Salvar</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" 
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" 
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    </body>
</html>