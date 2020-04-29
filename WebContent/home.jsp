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

        <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        
        <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/home.css">
    </head>
    <body>
        <header>
            <c:import url="menu.jsp"/>
        </header>

        <div class="container mt-5">
            <div class="row">
                <div class="col-md-8">
                    <div class="card c-col-left mb-3">
                        <div class="card-body">
                            <p><strong>Título</strong></p>
                        </div>
                        <div class="card-body c-cards m-2">
                            <img class="card-img-top" src="img/logoHeader.png" alt="">
                        </div>
                        <div class="card-body c-cards m-2">
                          <p><strong>Descrição</strong></p>
                          <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                          <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                        </div>
                        <div class="card-body c-cards m-2">
                            <p>
                                <strong><i class="far fa-comments mr-1"></i>Comentário</strong>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <div class="card c-col-right">
                        <h6 class="text-center mt-3 mb-4">
                            <i class="fas fa-star mr-1" style="color: gold;"></i>Últimos quadros
                        </h6>
                        <div class="card text-white bg-primary m-2" style="max-width: 18rem;">
                            <p class="card-header">Titulo</p>
                            <div class="card-body">
                                <p class="card-text">Descrição do quadro 1.</p>
                            </div>
                        </div>
                        <div class="card text-white bg-secondary m-2" style="max-width: 18rem;">
                            <p class="card-header">Titulo</p>
                            <div class="card-body">
                                <p class="card-text">Descrição do quadro 2.</p>
                            </div>
                        </div>
                        <div class="card text-white bg-secondary m-2" style="max-width: 18rem;">
                            <p class="card-header">Titulo</p>
                            <div class="card-body">
                                <p class="card-text">Descrição do quadro 3.</p>
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