
//---------------------------------------------------------------Imagens produto

//Envia dados para o janela modalOperaImagen

$(document).ready(function () {
    $(".bntOperaImagem").click(function () {
        var album = $(this).attr("data-album");
        var imagem = $(this).attr("data-imagem");
        var idImagPrinc = $(this).attr("data-idImagPrinc");
        var codImagem = $(this).attr("data-codImagem");
        $("#confImagem").attr("src", "IMAGENS-PRODUTOS/" + album + "/" + imagem + "");
        $("#idImagPrinc").attr("value", idImagPrinc);
        $("#codImagem").attr("value", codImagem);
        $("#album").attr("value", album);
        $("#imagem").attr("value", imagem);
    });


//Abre janela modalOperaImagen

    $(".bntOperaImagem").click(function () {
        $("#janelaModal").fadeIn(200);
        $("#OperaImagen").fadeIn(200);
    });
    $(".resetImg").click(function (e) {
        if (e.target !== this) {
            return;
        }
        $("#janelaModal").fadeOut(200);
        $("#OperaImagen").fadeOut(200);
    });

    var infoUploadImagens = $("#infoUploadImagens").attr("data-importUpload");
    if (infoUploadImagens === "sim") {
        $("#janelaModal").fadeIn(200);
        $("#infoUploadImagens").fadeIn(200);
    }

    $(".resetImg").click(function (e) {
//        if (e.target !== this) {
//            return;
//        }
        $("#janelaModal").fadeOut(200);
        $("#infoUploadImagens").fadeOut(200);
    });
});

