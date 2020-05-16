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
        <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">

        <title>Novo quadro</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../../css/global.css">
        <link rel="stylesheet" href="../../css/novoQuadro.css">
    </head>
    <body>
        <header>
            <c:import url="../Menu.jsp"/>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    
                    <button type="button" class="btn btn-collapse mt-3 col-md-10" data-toggle="collapse" href="#collapseTitulo" 
                        role="button" aria-expanded="false" aria-controls="collapseTitulo">
                        <i class="fas fa-plus mr-2"></i>Adionar nova lista
                    </button>
                    <form action="">
                        <div class="form-group">
                            <div class="collapse" id="collapseTitulo">
                                <div class="card card-body c-card-body">
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <input type="text" class="form-control" name="3" placeholder="Insira o título da lista" required>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <button type="submit" class="btn btn-geral bt-sm"  data-toggle="collapse" href="#coluna" 
                                            role="button" aria-expanded="false" aria-controls="coluna">Adicionar</button>
                                        <div>
                                            <i class="fas fa-times mt-2 ml-4" data-toggle="collapse" href="#collapseTitulo" 
                                                role="button" aria-expanded="false" aria-controls="collapseTitulo">
                                            </i>
                                        </div>
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="collapse" id="coluna">
                        <div class="card card-body c-card-body">
                            <div class="header c-header">
                                <p>Titulo</p>
                                <div class="dropleft">
                                    <img src="../../img/option.svg" data-toggle="dropdown" aria-haspopup="true"
                                         aria-expanded="false" width="10" alt="icon">
                                    <div class="dropdown-menu m-2" aria-labelledby="dropdownMenuButton">
                                        <div class="header c-header">
                                            <span class="dropdown-item-text">Ações da lista</span>
                                            <i class="fas fa-times mt-2 mr-2" data-toggle="collapse" href="#coluna" 
                                                role="button" aria-expanded="false" aria-controls="coluna">
                                            </i>
                                        </div>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item c-item"><i class="fas fa-plus mr-2"></i>Adicionar Ticket</a>
                                        <a class="dropdown-item c-item" data-toggle="modal" data-target="#modalTicket">
                                            <i class="fas fa-edit mr-1"></i>Editar Lista
                                        </a>
                                        <a class="dropdown-item c-item"><i class="fas fa-arrow-right mr-2"></i>Mover Lista</a>
                                        <a class="dropdown-item c-item"><i class="far fa-trash-alt mr-2"></i>Deletar Lista</a>
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div> 
                <!-- Modal de editar ticket-->
                <!-- Button trigger modal -->
                
                
                <!-- Modal -->
                <div class="modal fade" id="modalTicket" tabindex="-1" role="dialog" aria-labelledby="modalTicketTitle" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                        <h5 class="modal-title" id="modalTicketTitle">Titulo do Ticket</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-10">
                                   
                                </div>
                                <div class="col-md-1">
                                    <button type="button" class="btn btn-primary btn-sm mb-2">Salvar</button>
                                    <button type="button" class="btn btn-primary btn-sm mb-2">Salvar</button>
                                    <button type="button" class="btn btn-primary btn-sm mb-2">Salvar</button>
                                    <button type="button" class="btn btn-primary btn-sm mb-2">Salvar</button>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-geral btn-sm">Salvar</button>
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