
//-----------------------------------------------------------------------Produto

//Chama janela de cadastro produto

$(document).ready(function () {
    $("#novoProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalCadasProd").fadeIn(200);
    });
    $(".resetProd").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalCadasProd").fadeOut(200);
        $("#cadasCaracNomeProd").text("Falta 100 caracteres");
    });

//Chama janela de ediçao do produto

    $(".editarProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalEditProd").fadeIn(200);
    });
    $(".resetProd").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalEditProd").fadeOut(200);
        $("#editaCaracNomeProd").fadeOut();
    });

//Chama janela do filtro de pesquisa produto

    $("#filtroPesquisa").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalFiltroProd").fadeIn(200);
    });
    $(".resetProd").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalFiltroProd").fadeOut(200);
    });

//Chama janela mensagem de bloqueio

    $(".bloquearProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalMsnBlocProd").fadeIn(200);
    });
    $(".resetProd").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalMsnBlocProd").fadeOut(200);
    });
    
    //Chama janela mensagem de ajuda

    $("#ajudaCadProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalMsnAjudaCadProd").fadeIn(200);
    });
    $(".resetProd").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalMsnAjudaCadProd").fadeOut(200);
    });


//Envia dados para janela mensagem de bloqueio

    $(".bloquearProduto").click(function () {
        var dataIdProduto = $(this).attr("data-idProduto");
        var dataCaminhoImagem = $(this).attr("data-caminhoImagem");
        var dataNomeProduto = $(this).attr("data-nomeProduto");
        $("#codigoBloqueio").attr("value", dataIdProduto);
        $("#ImagemBloqueio").attr("src", dataCaminhoImagem);
        $("#lbBloqueioNomeProduto").text(dataNomeProduto);
    });

//Envia dados para o Modal edita produto

    $(".editarProduto").click(function () {
        var dataIdProduto = $(this).attr("data-idProduto");
        var dataNomeProduto = $(this).attr("data-nomeProduto");
        var dataNomeGrupo = $(this).attr("data-nomeGrupo");
        var dataIdGrupo = $(this).attr("data-idGrupo");
        var dataNomeCategoria = $(this).attr("data-nomeCategoria");
        var dataIdCategoria = $(this).attr("data-idCategoria");
        var dataCaminhoImagem = $(this).attr("data-caminhoImagem");
        $("#codigoProduto").attr("value", dataIdProduto);
        $("#txtaEditaNomeProd").text(dataNomeProduto);
        $("#editaOptGrupoProd").text(dataNomeGrupo);
        $("#editaOptGrupoProd").attr("value", dataIdGrupo);
        $("#editaOptCateProd").text(dataNomeCategoria);
        $("#editaOptCateProd").attr("value", dataIdCategoria);
        $("#editaCaminhoConsuImagem").attr("href", "ConsultaImagemProduto?id=" + dataIdProduto);
        $("#editaImgProduto").attr("src", dataCaminhoImagem);
    });

//Faz a contagem de caracter restante no campo textarea txtaCadasNomeProd

    var max = $("#txtaCadasNomeProd").attr('maxlength');
    $("#txtaCadasNomeProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#cadasCaracNomeProd").text('Faltam ' + (max - len) + ' caracteres');
    });

//Faz a contagem de caracter restante no campo textarea txtaEditaNomeProd

    var max = $("#txtaEditaNomeProd").attr('maxlength');
    $("#txtaEditaNomeProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#editaCaracNomeProd").fadeIn();
        $("#editaCaracNomeProd").text('Faltam ' + (max - len) + ' caracteres');
    });
});