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

        <title>Meus quadros</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../../css/global.css">
        <link rel="stylesheet" href="../../css/meusQuadros.css">
    </head>
	<body>
	    <header>
            <c:import url="../Menu.jsp"/>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <div class="card mt-4 c-cards">
                        <div class="card-header c-header">
                            <p>Titulo</p>
                            <div class="dropleft">
                                <img src="../../img/option.svg" data-toggle="dropdown" aria-haspopup="true"
                                     aria-expanded="false" width="10" alt="icon">
                                <div class="dropdown-menu m-2"
                                    aria-labelledby="dropdownMenuButton">
                                    
                                    <a class="dropdown-item c-item" data-toggle="modal" data-target=".bd-example-modal-sm">
                                        Excluir
                                    </a>
                                </div>
                            </div> 
                        </div>
                        <div class="card-body mb-5">
                            <p class="card-text">Descrição do quadro 1.</p>
                        </div>
                        <hr>
                        <div class="card-link m-2">
                            <a href="#" class="card-link">
                                <i class="far fa-file-alt mr-1"></i>Ir para o quadro
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card mt-4 c-cards">
                        <div class="card-header c-header">
                            <p>Titulo</p>
                            <div class="dropleft">
                                <img src="../../img/option.svg" data-toggle="dropdown" aria-haspopup="true"
                                     aria-expanded="false" width="10" alt="icon">
                                <div class="dropdown-menu m-2"
                                    aria-labelledby="dropdownMenuButton">
                                    
                                    <a class="dropdown-item c-item" data-toggle="modal" data-target=".bd-example-modal-sm">
                                        Excluir
                                    </a>
                                </div>
                            </div> 
                        </div>
                        <div class="card-body mb-5">
                            <p class="card-text">Descrição do quadro 1.</p>
                        </div>
                        <hr>
                        <div class="card-link m-2">
                            <a href="#" class="card-link">
                                <i class="far fa-file-alt mr-1"></i>Ir para o quadro
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card mt-4 c-cards">
                        <div class="card-header c-header">
                            <p>Titulo</p>
                            <div class="dropleft">
                                <img src="../../img/option.svg" data-toggle="dropdown" aria-haspopup="true"
                                     aria-expanded="false" width="10" alt="icon">
                                <div class="dropdown-menu m-2"
                                    aria-labelledby="dropdownMenuButton">
                                    
                                    <a class="dropdown-item c-item" data-toggle="modal" data-target=".bd-example-modal-sm">
                                        Excluir
                                    </a>
                                </div>
                            </div> 
                        </div>
                        <div class="card-body mb-5">
                            <p class="card-text">Descrição do quadro 1.</p>
                        </div>
                        <hr>
                        <div class="card-link m-2">
                            <a href="#" class="card-link">
                                <i class="far fa-file-alt mr-1"></i>Ir para o quadro
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card mt-4 c-cards">
                        <div class="card-header c-header">
                            <p>Titulo</p>
                            <div class="dropleft">
                                <img src="../../img/option.svg" data-toggle="dropdown" aria-haspopup="true"
                                     aria-expanded="false" width="10" alt="icon">
                                <div class="dropdown-menu m-2"
                                    aria-labelledby="dropdownMenuButton">
                                    
                                    <a class="dropdown-item c-item" data-toggle="modal" data-target=".bd-example-modal-sm">
                                        Excluir
                                    </a>
                                </div>
                            </div> 
                        </div>
                        <div class="card-body mb-5">
                            <p class="card-text">Descrição do quadro 1.</p>
                        </div>
                        <hr>
                        <div class="card-link m-2">
                            <a href="#" class="card-link">
                                <i class="far fa-file-alt mr-1"></i>Ir para o quadro
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <!--Modal-->
            <!-- Button trigger modal -->
        
            <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-sm" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalCenterTitle">Deseja excluir o quadro?</h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div class="modal-footer">
                          <button type="button" class="btn btn-secondary" data-dismiss="modal">Não</button>
                          <button type="submit" name="#" value="#" class="btn btn-geral">Sim</button>
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