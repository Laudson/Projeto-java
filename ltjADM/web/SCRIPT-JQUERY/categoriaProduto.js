
//----------------------------------------------------------categoria de produto


//Abre janela modal edita categoria prodduto

$(document).ready(function () {
    $(".editarCateProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalEditCateProd").fadeIn(200);
    });
    $(".resetCate").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalEditCateProd").fadeOut(200);
        $("#editaCaracNomeCateProd").fadeOut();
    });

//Abre janela modal cadastra categoria prodduto

    $("#novaCategoria").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalCadasCateProd").fadeIn(200);
    });
    $(".resetCate").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalCadasCateProd").fadeOut(200);
        $("#cadasCaracNomeCateProd").text('Faltam 50 caracteres');
    });

//Abre janela modal filtro pesquisa

    $("#filtroPesquisa").click(function () {
        $("#modalFiltroCatProd").fadeIn(200);
    });
    $(".resetCate").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalFiltroCatProd").fadeOut(200);
    });

//Envia dados para o janela modal edita categoria produtos

    $(".editarCateProduto").click(function () {
        var dataNomeCategoria = $(this).attr("data-nomeCategoria");
        var dataIdCategoria = $(this).attr("data-idCategoria");
        $("#txtaEditaNomeCateProd").text(dataNomeCategoria);
        $("#hidCodigoCateProduto").attr("value", dataIdCategoria);
    });

//Abre janela mensagem de bloqueio

    $(".bloquearCateProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalMsnBlocCatProd").fadeIn(200);
    });
    $(".resetCate").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalMsnBlocCatProd").fadeOut(200);
    });

//Envia dados para janela mensagem de bloqueio

    $(".bloquearCateProduto").click(function () {
        var codigoCategoria = $(this).attr("data-codigoCategoria");
        var nomeCategoria = $(this).attr("data-nomeCategoria");
        $("#codigoBloqueio").attr("value", codigoCategoria);
        $("#lbNomeCateProdBloc").text(nomeCategoria);
    });

//Faz a contagem de caracter restante no campo textarea txtaCadasNomeCateProd

    var max = $("#txtaCadasNomeCateProd").attr('maxlength');
    $("#txtaCadasNomeCateProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#cadasCaracNomeCateProd").text('Faltam ' + (max - len) + ' caracteres');
    });


//Faz a contagem de caracter restante no campo textarea txtaEditaNomeCateProd

    var max = $("#txtaEditaNomeCateProd").attr('maxlength');
    $("#txtaEditaNomeCateProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#editaCaracNomeCateProd").text('Faltam ' + (max - len) + ' caracteres');
        $("#editaCaracNomeCateProd").fadeIn();
    });
});