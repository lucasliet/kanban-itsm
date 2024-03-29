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
    <link rel="shortcut icon" href="../../img/favicon.ico" type="image/x-icon">

    <title>Editar Perfil</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <script src="https://kit.fontawesome.com/da77f520d1.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../../css/global.css">
    <link rel="stylesheet" href="../../css/cadastro.css">
</head>

<body>
<header>
    <c:import url="../Menu.jsp"/>
</header>
<div class="container mt-4 mb-4">
    <div class="row my-3 justify-content-center">
        <div class="bg-grey">
            <div class="row coluna justify-content-center">
                <div class="col-md-11">
                    <form action="/usuario.do" method="POST">
                        <div id="auth_feedback">${authFeedback}</div>
                        <div class="row px-3">
                            <div class="col">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" name="nome" placeholder="Nome"
                                               value="${usuario.nome}" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="text" class="form-control" name="sobrenome"
                                               placeholder="Último nome"
                                               value="${usuario.ultimoNome}" required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <input type="password" class="form-control" name="senha_antiga"
                                               placeholder="Senha antiga" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <input type="password" class="form-control" name="senha_nova"
                                               placeholder="Nova senha"
                                               required>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-8">
                                        <input type="text" class="form-control" name="endereco"
                                               placeholder="Endereço"
                                               value="${usuario.endereco}">
                                    </div>
                                    <div class="form-group col-md-4">
                                        <input type="tel" class="form-control" name="telefone"
                                               placeholder="Telefone"
                                               value="${usuario.telefone}">
                                    </div>
                                </div>
                            </div>

                            <div class="col-3">
                                <div class="row">
                                    <div class="text-center">
                                        <img id="img" src="${usuario.foto}"
                                             class="avatar img-circle col"
                                             alt="avatar"/>
                                        <input type="file" id="upload" class="custom-file-input">
                                    </div>
                                </div>

                                <button type="submit" class="btn float-right btn-geral " name="acao"
                                        value="btn-atualizar">Salvar
                                </button>

                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5 ml-5 mb-5 ">
                    <section class="col-md-10">
                        <h5 class="titulo mt-3"><strong>Alterar dados Cadastrais</strong></h5>
                        <p>Insira os novos dados para atualizar seu cadastro.</p>
                        <a href="/ticket.do?acao=page-home"><i class="fas fa-arrow-left"></i> Voltar à home</a>
                    </section>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        $('#upload').change(function () {
            const file = $(this)[0].files[0]
            const fileReader = new FileReader()
            fileReader.onloadend = function () {
                $('#img').attr('src', fileReader.result)
            }
            fileReader.readAsDataURL(file)
        })
    })
</script>

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