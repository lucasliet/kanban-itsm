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
    <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">

    <title>Editar Perfil</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="css/cadastro.css">
</head>

<body>
    <header>
        <nav class="navbar barraMenu navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">
                <img src="img/logoHeader.png" width="150" alt="">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link text-white" href="#">
                            <i class="fas fa-home mr-1"></i>Home <span class="sr-only">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="#">
                            <i class="far fa-file-alt mr-1"></i>Meus quadros
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="#">
                            <i class="fas fa-plus mr-1"></i>Novo processo
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <div class="container mt-3">
        <div class="row coluna">

            <div class="col-md-10 justify-content-center ml-5">
                <form action="usuario.do" method="POST">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <input type="text" class="form-control" name="nome" placeholder="Nome"
                                        value="${usuario.nome}" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <input type="text" class="form-control" name="sobrenome" placeholder="Último nome"
                                        value="${usuario.ultimoNome}" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <input type="password" class="form-control" name="senhaAntiga"
                                        placeholder="Senha antiga" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <input type="password" class="form-control" name="senhaNova" placeholder="Nova senha"
                                        required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-8">
                                    <input type="text" class="form-control" name="endereco" placeholder="Endereço"
                                        value="${usuario.endereco}">
                                </div>
                                <div class="form-group col-md-4">
                                    <input type="tel" class="form-control" name="telefone" placeholder="Telefone"
                                        value="${usuario.telefone}">
                                </div>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="row">
                                <div class="text-center">
                                    <img id="img" src="img/fotoPadrao.png" class="avatar img-circle img-thumbnail" alt="avatar">
                                    <input type="file" id="upload" class="custom-file-input">

                                </div>
                            </div>
                            
                            <button type="submit" class="btn float-right btn-geral " name="acao"
                                value="btn-cadastrar">Salvar</button>
                            
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-5 ml-5 mb-5 ">
                <section class="col-md-10">
                    <h5 class="titulo mt-3"><strong>Alterar dados Cadastrais</strong></h5>
                    <p>Insira os novos dados para atualizar seu cadastro.</p>
                    <a href="#"><i class="fas fa-arrow-left"></i> Voltar à home</a>
                </section>
            </div>
        </div>
    </div>

    <script>
        $(function(){
            $('#upload').change(function(){
                const file = $(this)[0].files[0]
                const fileReader = new FileReader()
                fileReader.onloadend = function(){
                    $('#img').attr('src', fileReader.result)
                }
                fileReader.readAsDataURL(file)
            })
        })
    </script>
</body>

</html>