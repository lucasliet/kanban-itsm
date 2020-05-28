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
        <title>Home</title>

        <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        
        <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../css/global.css">
        <link rel="stylesheet" href="../css/home.css">
    </head>
    <body>
        <header>
            <c:import url="Menu.jsp"/>
        </header>

        <div class="container mt-5">
            <div class="row">
                <div class="col-md-7">
                    <div class="card c-coluna mb-3">
                        <div class="card-header">
                            <p>Título</p>
                        </div>
                        <div class="card-body">
                            <img class="card-img-top" src="../img/logoHeader.png" alt="">
                        </div>
                        <div class="card-footer">
                            <label><i class="far fa-comment-alt mr-2"></i>Comente</label>
                            <div class="row">
                                <div class="col-md-10">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control text-input col-12" name="" value="" placeholder="Faça um comentário">
                                </div>
                                </div>
                                <div class="2">
                                    <button type="submit" class="btn btn-geral ml-3" name="" value="">Enviar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2"></div>
                    <div class="col-md-3">
                        <div class="card c-coluna">
                            <h6 class="text-center mt-3 mb-4">
                                <i class="fas fa-star mr-2" style="color: gold"></i>Últimos quadros
                            </h6>
                            <c:forEach var="quadro" items="${quadros}">
                                <div class="card c-cards m-3">
                                    <div class="card-header c-header-blue">
                                        <p class="text-white">${quadro.titulo}</p>
                                    </div>
                                    <div>
                                        <form action="/quadro.do" method="post" class="m-2">
                                            <input type="hidden" name="id_exibir" value="${quadro.id}">
                                            <button type="submit" class="btn btn-geral btn-sm float-right m-2"
                                                    name="acao" value="page-exibir">
                                                <i class="far fa-file-alt mr-1"></i>Ir para o quadro
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
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