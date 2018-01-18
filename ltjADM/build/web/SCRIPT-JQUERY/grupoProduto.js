
//-----------------------------------------------------------------Grupo produto


//Abre janela modal edita grupo prodduto

$(document).ready(function () {
    $(".editarGrpProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalEditGrpProd").fadeIn(200);
    });
    $(".resetGrp").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalEditGrpProd").fadeOut(200);
        $("#editaCaracNomeGrpProd").fadeOut();
    });

//Abre janela modal cadastra grupo prodduto

    $("#novoProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#modalCadasGrpProd").fadeIn(200);
    });
    $(".resetGrp").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#modalCadasGrpProd").fadeOut(200);
        $("#cadasCaracNomeGrpProd").text("Falta 50 caracteres");
    });

//Abre janela modal filtro pesquisa grupo produto

    $(document).ready(function () {
        $("#filtroPesquisa").click(function () {
            $("#janelaModal").fadeIn(200);
            $("#modalFiltroGrpProd").fadeIn(200);
        });
        $(".resetGrp").click(function (e) {
            if (e.target !== this) {
                return;
            }
            $("#janelaModal").fadeOut(200);
            $("#modalFiltroGrpProd").fadeOut(200);
        });
    });

//Chama janela mensagem de bloqueio

    $(".bloquearGrpProduto").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#msnBlocGrpProd").fadeIn(200);
    });
    $(".resetGrp").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#msnBlocGrpProd").fadeOut(200);
    });

//Envia dados para janela mensagem de bloqueio

    $(".bloquearGrpProduto").click(function () {
        var dataIdGrupo = $(this).attr("data-idGrupo");
        var dataNomeGrupo = $(this).attr("data-nomeGrupo");
        $("#codigoBloqueio").attr("value", dataIdGrupo);
        $("#lbBloqueioNomeGrpProduto").text(dataNomeGrupo);
    });

//Envia dados para o janela modal edita grupo produtos

    $(".editarGrpProduto").click(function () {
        var dataNomeGrupo = $(this).attr("data-nomeGrupo");
        var dataIdGrupo = $(this).attr("data-idGrupo");
        $("#txtaEditaNomeGrpProd").text(dataNomeGrupo);
        $("#hidCodigoGrpProduto").attr("value", dataIdGrupo);
    });

//Faz a contagem de caracter restante no campo textarea txtaCadasNomeGrpProd


    var max = $("#txtaCadasNomeGrpProd").attr('maxlength');
    $("#txtaCadasNomeGrpProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#cadasCaracNomeGrpProd").text('Faltam ' + (max - len) + ' caracteres');
    });

//Faz a contagem de caracter restante no campo textarea txtaEditaNomeGrpProd

    var max = $("#txtaEditaNomeGrpProd").attr('maxlength');
    $("#txtaEditaNomeGrpProd").keyup(function () {
        var len = this.value.length;
        if (len > max) {
            this.value = this.value.substring(0, max);
        }
        $("#editaCaracNomeGrpProd").fadeIn();
        $("#editaCaracNomeGrpProd").text('Faltam ' + (max - len) + ' caracteres');
    });
});
