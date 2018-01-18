<%-- 
    Document   : imagensProdut
    Created on : 19/11/2016, 17:12:16
    Author     : laudson
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--Cabeçalho-->

<c:import url="cabecalho.jsp"/>

<!--Topo-->
<c:import url="topo.jsp"/>

<!--Menu-->
<c:import url="menu.jsp"/>

<!--Painel de controle superior-->
<c:import url="painelControleSuperior.jsp"/>
<c:forEach var="listaDadosProduto" items="${listaDadosProduto}">
    <form action="CriaImagensProduto" method="POST" enctype="multipart/form-data">
        <div id="imgPrincipalPainelControle">
            <img src="${ImagemPrincipal}" width="150" height="150" />
            <label title="${listaDadosProduto.nome_produto}">${listaDadosProduto.nome_produto}</label>
        </div>
        <input type="hidden" name="id" id="codigoItem" value="${codProd}" />
        <div id="imgForm">
            <label for="fileUpload">Selecione as imagens</label>
            <input type="file" name="arquivo" id="fileUpload" multiple="" required="required"/>
        </div>
        <div id="bntImgForm">
        <div id="bntImgFormCad">
            <input type="submit" value="Cadastrar" />
        </div>        
    </form>
    <form method="POST" action="OperacaoImagem">
        <div id="bntImgFormExc">
            <input type="submit" value="Excluir album" name="bntExcluir"/>
            <input type="hidden" name="album" value="${codProd}" />
        </div>
    </form> 
</div>
</c:forEach>

<!--Painel de controle inferior-->
<c:import url="painelControleInferior.jsp"/>

<!--Dinamico superior-->
<c:import url="dinamicoSuperior.jsp"/>

<div id="imgGradeImagens">
    <c:if test="${empty listaImagemProduto}">
        <div class="semImagem">
            <h1>Não existe imagens cadastradas para esse produto</h1>
        </div>
    </c:if>
    <c:forEach var="listaImagemProduto" items="${listaImagemProduto}">
        <div class="imagem gradeImg">
            <img src="IMAGENS-PRODUTOS/${listaImagemProduto.nome_album}/${listaImagemProduto.nome_imagem}" 
                 width="150" height="150" alt="sem-imagem" class="bntOperaImagem" 
                 title="Excluir ou tornar essa imagem principal" 
                 data-codImagem="${listaImagemProduto.id_imagem}"
                 data-album="${listaImagemProduto.nome_album}"
                 data-imagem="${listaImagemProduto.nome_imagem}"
                 data-idImagPrinc="${listaImagemProduto.cod_prod}"/>
        </div>
    </c:forEach>
</div>

<!--Dinamico Inferiro-->
<c:import url="dinamicoInferior.jsp"/>

<!--Modal Superiro-->
<c:import url="modalSuperior.jsp"/>

<div id="OperaImagen">
    <input class="fechar resetImg" type="reset" value=""/>
    <h2>O que deseja fazer com essa imagem?</h2>
    <div class="imagem" id="imgConf">
        <img src="" id="confImagem" width="150" height="150" alt="sem-imagem"/>
    </div>
    <form method="POST" action="OperacaoImagem">
        <input type="hidden" name="idImagPrinc" value="" id="idImagPrinc"/>
        <input type="hidden" name="codImagem" value="" id="codImagem"/>
        <input type="hidden" name="imagem" value="" id="imagem"/>
        <input type="hidden" name="album" value="" id="album"/>
        <div id="botoesModalOperaImagen">
            <input type="submit" value="Torna-la Principal" name="operacao"/>
            <input type="submit" value="Excluir" name="operacao"/>
        </div>
    </form>
</div>

<!--Resultado do Upload-->

<div id="infoUploadImagens" data-importUpload="${msnImagensImpostadas}">
    <input class="fechar resetImg" type="reset" value=""/>  
    <h2>Resultado do upload de imagem</h2>
    <div id="resultadoUpload">
        <c:forEach var="resultadoUpload" items="${resultadoUpload}">
            <p>${resultadoUpload}</p>
        </c:forEach>
    </div>
    <input type="submit" value="OK" class="resetImg"/>
</div>

<!--Modal Inferior-->
<c:import url="modalInferior.jsp"/>

