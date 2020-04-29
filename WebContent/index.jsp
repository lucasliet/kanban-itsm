<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

    <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/index.css">

</head>

<body>
	<fmt:setLocale value="pt_BR"/>
    <div class="container">
        <div class="text-center">
            <img class="col-sm-5 col-md-3 img-fluid mt-4" src="img/logoEmpresa.png">
        </div>
        <div class="row justify-content-center">
            <div class="col-md-4 mt-5">
                <div class="card">
                    <div class="card-header">
                        <h5 class="text-center">Fazer login no CTE</h5>
                    </div>
                    <div class="card-body">
                        <form action="usuario.do" method="POST">
                        <div>${msgerror}</div>
                            <div class="input-group form-group mt-4">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="email" class="form-control" placeholder="Email" name="email" required>
                            </div>
                            <div class="input-group form-group ">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control " placeholder="Senha" name="senha" required>
                            </div>

                            <div class="row align-items-center remember ml-1">
                                <input type="checkbox" class="mr-1">Lembra senha
                            </div>
                            <div class="form-group mt-4">
                                <button type="submit" name="acao" value="page-home"
                                    class="btn float-right btn-geral ">Entrar</button>
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Ainda não tem cadastro?<a href="cadastro.html">Clique aqui</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="#">Esqueceu a senha?</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="rodapeleft">
        <img class="img-fluid" src="img/personagens.svg">
    </div>
    <div class="rodaperight">
        <img class="img-fluid" src="img/personagem2.svg">
    </div>

</body>

</html>