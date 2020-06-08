<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <svg class="bi bi-grid-1x2 mx-2 my-auto" width="1.4em" height="1.3em" viewBox="0 0 16 16" fill="currentColor"
         xmlns="http://www.w3.org/2000/svg">
        <path fill-rule="evenodd"
              d="M6 1H1v14h5V1zm9 0h-5v5h5V1zm0 9h-5v5h5v-5zM0 1a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm9 0a1 1 0 0 1 1-1h5a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1h-5a1 1 0 0 1-1-1V1zm1 8a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h5a1 1 0 0 0 1-1v-5a1 1 0 0 0-1-1h-5z"/>
    </svg>
    <p class="my-auto">${quadro.titulo}</p>
    <button type="button" class="ml-auto my-auto btn btn-outline-branco" data-toggle="modal"
            data-target="#ModalQuadroTitulo">
        Editar Titulo
    </button>
    <button type="button" class="mx-3 my-auto btn btn-outline-branco" data-toggle="modal"
            data-target="#ModalQuadroUsuarios">
        Gerenciar Equipe
    </button>
</div>
<div class="container-fluid">
    <div class="scrolling-wrapper">
        <c:forEach var="coluna" items="${colunas}">
            <div class="col-md-3 col-12">
                <div id="#coluna-scroll" class="c-coluna c-rolagem">
                    <div class="card card-body header-coluna fixed">
                        <div class="header c-header">
                            <p>${coluna.titulo}</p>
                            <div class="dropleft">
                                <svg style="cursor: pointer" class="bi bi-filter-right" width="1.5em" height="1.5em" viewBox="0 0 16 16"
                                     fill="currentColor" xmlns="http://www.w3.org/2000/svg"
                                     id="dropdown${coluna.id}" data-toggle="dropdown" aria-haspopup="true"
                                     aria-expanded="false">
                                    <path fill-rule="evenodd"
                                          d="M14 10.5a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0 0 1h3a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-7a.5.5 0 0 0 0 1h7a.5.5 0 0 0 .5-.5zm0-3a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0 0 1h11a.5.5 0 0 0 .5-.5z"/>
                                </svg>
                                <div class="dropdown-menu m-2" aria-labelledby="dropdown${coluna.id}">
                                    <div class="header c-header">
                                        <span class="dropdown-item-text">Ações da coluna</span>
                                        <i style="cursor: pointer" class="fas fa-times mt-2 mr-2" data-toggle="collapse" href="#coluna"
                                           role="button" aria-expanded="false" aria-controls="coluna">
                                        </i>
                                    </div>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item c-item" data-toggle="modal"
                                       data-target="#ModalAdicionarTicket${coluna.id}">
                                        <i class="fas fa-plus mr-2"></i>Adicionar Ticket
                                    </a>
                                    <a class="dropdown-item c-item" data-toggle="modal"
                                       data-target="#ModalEditarColuna${coluna.id}">
                                        <i class="fas fa-edit mr-1"></i>Editar Coluna
                                    </a>
                                    <a class="dropdown-item c-item">
                                        <i class="fas fa-arrow-right mr-2"></i>Mover Coluna
                                    </a>
                                    <a class="dropdown-item c-item" data-toggle="modal"
                                       data-target="#ModalExcluiColuna${coluna.id}">
                                        <i class="far fa-trash-alt mr-2"></i>Deletar Coluna
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="ticket" items="${coluna.tickets}">
                        <div class="card c-ticket m-2">
                            <div class="card-body d-flex" style="justify-content: space-between;" data-toggle="modal"
                                 data-target="#modalTicket${ticket.id}">
                                <p class="card-title">${ticket.titulo}</p>
                                <i class="fas fa-pen fa-sm mt-1"></i>
                            </div>
                        </div>

                        <!-- Modal de editar ticket-->
                        <div id="modalTicket${ticket.id}" class="modal fade bd-example-modal-lg" tabindex="-1"
                             role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header bg-info">
                                        <h5 class="modal-title text-white" id="titleEditarTicket">${ticket.titulo}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span class="text-white" aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="\ticket.do" method="POST">
                                            <div class="row">
                                                <input type="hidden" name="id_ticket" value="${ticket.id}">
                                                <div class="col-md-10">
                                                    <div class="input-group">
                                                        <label><i class="fas fa-id-card mr-2"></i>Insira o novo
                                                            título</label>
                                                    </div>
                                                    <div class="input-group">
                                                        <input class="form-control" name="titulo"
                                                               value="${ticket.titulo}">
                                                    </div>
                                                    <div class="input-group mt-4">
                                                        <label><i class="fas fa-align-left mr-2"></i>Descrição</label>
                                                    </div>
                                                    <div class="input-group">
                                                        <textarea class="form-control" name="descricao" aria-label="With textarea" placeholder="Digite a descrição do ticket">${ticket.descricao}</textarea>
                                                    </div>
                                                    <div class="input-group mt-4">
                                                        <label><i class="fas fa-paperclip mr-2"></i>Anexo</label>
                                                    </div>
                                                    <div class="input-group">
                                                        <div class="col-md-8">
                                                            <div class="card">
                                                                <div class="card-body">
                                                                    Aqui vão os anexos.
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <select name="id_coluna" class="form-control select-col mt-2">
                                                        <option value="" disabled selected>Colunas</option>
                                                        ${options_colunas}
                                                    </select>
                                                    <button type="submit" class="btn btn-outline-geral mt-2 px-3"
                                                            name="acao" value="btn-mover">
                                                        <i class="fas fa-arrow-right mr-2"></i>Mover
                                                    </button>
                                                    <button type="button" class="btn btn-outline-geral mt-2 px-3"
                                                            data-toggle="modal"
                                                            data-target="#ModalExcluirTicket${ticket.id}">
                                                        <i class="fas fa-trash mr-2"></i>Excluir
                                                    </button>
                                                    <button type="submit" class="btn btn-outline-geral mt-2 px-3" disabled>
                                                        <i class="fas fa-paperclip mr-2"></i>Anexo
                                                    </button>
                                                    <button type="submit" class="btn btn-geral mt-2 px-3" name="acao" value="btn-atualizar">
                                                        <i class="fas fa-save mr-2"></i>Salvar
                                                    </button>
                                                </div>
                                            </div> <!--Row -->
                                        </form>

                                        <c:forEach var="comentario" items="${ticket.comentarios}">
                                            <form action="/comentario.do" method="POST">
                                                <input type="hidden" name="id_comentario" value="${comentario.id}">
                                                <input type="hidden" name="id_ticket" value="${ticket.id}">
                                                <input type="hidden" name="page" value="exibir-quadro">
                                                <div class="input-group d-flex my-3">
                                                    <div class="col-4 col-md-2 d-flex">
                                                        <img class="img-fluid rounded my-auto" src="${comentario.usuario.foto}">
                                                    </div>
                                                    <div class="col">
                                                        <strong class="text-secondary">${comentario.usuario.nome}&nbsp${comentario.usuario.ultimoNome}</strong>
                                                        <div class="card">
                                                            <div class="card-body py-2">
                                                                    ${comentario.corpo}
                                                                <span
                                                                        class="d-flex float-right text-bold text-white badge badge-primary mt-2"
                                                                >${comentario.curtidas}</span>
                                                                <button type="submit" class="btn-like float-right" name="acao"
                                                                        value="curtir">
                                                                    <i class="far fa-thumbs-up"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </c:forEach>

                                        <form action="/comentario.do" method="POST" class="input-group">
                                            <label><i class="far fa-comment-alt mr-2 mt-4"></i></i>Comentário</label>
                                            <div class="input-group">
                                                <input type="hidden" name="id_ticket" value="${ticket.id}">
                                                <input type="hidden" name="page" value="exibir-quadro">
                                                <input class="form-control col-10" name="corpo" placeholder="Faça um comentário" required>
                                                <button type="submit" class="ml-auto btn btn-outline-geral px-3" name="acao" value="btn-postar">
                                                    <i class="far fa-paper-plane mr-2"></i>Postar
                                                </button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal deletar ticket-->
                        <div class="modal fade" id="ModalExcluirTicket${ticket.id}" tabindex="-1" role="dialog"
                             aria-labelledby="ModalCenterTitle" aria-hidden="true">
                            <form action="/ticket.do" method="POST">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header bg-info">
                                            <h5 class="modal-title text-white" id="titleDeleteTicket">Tem certeza que
                                                deseja excluir o ticket?</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span class="text-white" aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                Fechar
                                            </button>
                                            <input type="hidden" name="id_excluir" value="${ticket.id}">
                                            <button type="submit" class="btn btn-geral" name="acao" value="btn-excluir">
                                                Excluir
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- modal adicionar ticket -->
            <div id="ModalAdicionarTicket${coluna.id}" class="modal fade bd-example-modal-sm" tabindex="-1"
                 role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <form action="/ticket.do" method="POST">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header bg-info">
                                <h5 class="modal-title text-white" id="titleTicket">Digite o nome do ticket</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span class="text-white" aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputTicket">
                                                <i class="fas fa-id-card"></i>
                                            </span>
                                    </div>
                                    <input type="hidden" name="id_coluna" value="${coluna.id}">
                                    <input type="text" class="form-control" name="titulo" placeholder="Nome do ticket"
                                           aria-label="Username" aria-describedby="inputTicket" required
                                    />
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                <button type="submit" class="btn btn-geral" name="acao" value="btn-inserir">Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <!--modal editar coluna -->
            <div id="ModalEditarColuna${coluna.id}" class="modal" tabindex="-1" role="dialog">
                <form action="/coluna.do" method="POST">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header bg-info">
                                <h5 class="modal-title text-white" id="titleEditarColuna">Insira o novo título da
                                    coluna</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span class="text-white" aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1"><i class="fas fa-id-card"></i></span>
                                    </div>
                                    <input type="text" class="form-control" name="titulo_coluna"
                                           value="${coluna.titulo}" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                <input type="hidden" name="id_editar" value="${coluna.id}">
                                <button type="submit" class="btn btn-geral" name="acao" value="btn-editar">Salvar
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Modal deletar coluna-->
            <div id="ModalExcluiColuna${coluna.id}" class="modal" tabindex="-1" role="dialog">
                <form action="/coluna.do" method="GET">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header bg-info">
                                <h5 class="modal-title text-white" id="titleDeleteColuna">Tem certeza que deseja excluir
                                    a coluna?</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span class="text-white" aria-hidden="true">&times;</span>
                                </button>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                <input type="hidden" name="id_excluir" value="${coluna.id}">
                                <button type="submit" class="btn btn-geral" name="acao" value="btn-excluir">Excluir
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>

        <div class="col-md-3 col-12">
            <button id="btn-coluna" class="btn btn-outline-geral col-md-12 my-3" data-toggle="modal"
                    data-target="#ModalColuna">
                <i class="fas fa-plus mr-1"></i>Adicionar outra coluna
            </button>
        </div>
    </div>
</div>
    <!-- Modal adicionar coluna-->
    <div id="ModalColuna" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <form action="/coluna.do" method="POST">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header bg-info">
                        <h5 class="modal-title text-white" id="titleColuna">Digite o nome da coluna</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span class="text-white" aria-hidden="true">&times;</span>
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

    <div id="ModalQuadroUsuarios" class="modal fade" tabindex="-1" role="dialog"
    aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <form action="/quadro.do" method="POST">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-info">
                    <h5 class="modal-title text-white" >Gerenciar membros da equipe no quadro</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span class="text-white" aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="d-flex">
                        <div class="input-group mb-3 col-8">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-id-card"></i></span>
                            </div>
                            <input type="hidden" name="id_quadro" value="${quadro.id}">
                            <select name="id_usuario">
                                <option value="" disabled selected>Selecione um usuário</option>
                                ${options_usuarios}
                            </select>
                        </div>
                        <div class="col-4">
                            <button type="submit" class="btn btn-outline-geral mb-2" name="acao" value="+usuario-quadro">
                                <i class="fas fa-user-plus mr-2 "></i>Adicionar
                            </button>
                            <button type="submit" class="btn btn-outline-geral pr-3" name="acao" value="-usuario-quadro">
                                <i class="fas fa-user-times mr-2"></i>Remover
                            </button>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </form>
    </div>

    <div id="ModalQuadroTitulo" class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" aria-hidden="true">
        <form action="/quadro.do" method="POST">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header bg-info">
                        <h5 class="modal-title text-white" id="titleQuadro">Insira o novo titulo do quadro</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span class="text-white" aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="inputQuadro"><i class="fas fa-id-card"></i></span>
                            </div>
                            <input type="hidden" name="id_quadro" value="${quadro.id}">
                            <input type="text" class="form-control" name="titulo" value="${quadro.titulo}"
                                   aria-label="Username" aria-describedby="inputColuna" required
                            />
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                        <button type="submit" class="btn btn-geral" name="acao" value="btn-atualizar">Salvar</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>

    </body>
</html>