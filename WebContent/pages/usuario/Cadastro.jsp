<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">

        <title>Cadastro</title>

        <link rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
            integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
            crossorigin="anonymous">

        <script src="https://kit.fontawesome.com/da77f520d1.js"
            crossorigin="anonymous"></script>
        <link rel="stylesheet" href="../../css/global.css">
        <link rel="stylesheet" href="../../css/cadastro.css">
    </head>
    <body>
        <!-- Image and text -->
        
        <header>
            <nav class="navbar barraMenu navbar-light bg-light ">
                <a class="navbar-brand" href="../../index.jsp"> <img src="../../img/logo-light.svg"
                                                                     width="80" alt="">
                </a>
            </nav>
        </header>
        
        <div class="container mt-3">
            <div class="row coluna">
                <div class="col-md-5">
                    <div class="imagem d-flex justify-content-center mb-5">
                        <img class="img-fluid mt-4 " src="../../img/logoEmpresa.png">
                    </div>
                </div>
                <div class="col-md-7">
                    <form class="mr-3" action="/usuario.do" method="POST">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input type="text" class="form-control" name="nome" placeholder="Nome" required>
                            </div>
                            <div class="form-group col-md-6">
                                <input type="text" class="form-control" name="sobrenome" placeholder="Último nome" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input type="tel" class="form-control" name="telefone" placeholder="Telefone">
                            </div>
                            <div class="form-group col-md-6">
                                <input type="password" class="form-control" name="senha" placeholder="Senha" required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <input type="text" class="form-control" name="endereco" placeholder="Endereço">
                            </div>
                        </div>
                        
                        <button type="submit" class="btn float-right btn-geral mt-3" name="acao" value="btn-cadastrar">Cadastrar</button>
                    </form>
                </div>
            </div>
            <div class="row mt-3"> 
                <div class="col-md-5 mb-5 ml-5 ">
                    <section class="col-md-10">
                        <h5 class="titulo mt-3"><strong>Cadastro</strong></h5>
                        <p>Faça seu cadastro, entre na plataforma e ajude na melhoria dos projetos da sua empresa.</p>
                        <a href="../../index.jsp"><i class="fas fa-arrow-left"></i> Já tenho cadastro</a>
                    </section> 
                </div>
            </div>
        </div>
    </body>
</html>    