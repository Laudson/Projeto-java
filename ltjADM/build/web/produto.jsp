<%-- 
    Document   : produt
    Created on : 19/11/2016, 12:12:44
    Author     : laudson
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--Cabeçalho-->

<c:import url="cabecalho.jsp"/>

<!--Topo-->
<c:import url="topo.jsp"/>

<!--Menu-->
<c:import url="menu.jsp"/>

<!--Painel de controle superior-->
<c:import url="painelControleSuperior.jsp"/>

<fieldset>
    <legend>Operações</legend>
    <label id="novoProduto" class="bntPainelControle">Novo registro</label>
    <label id="filtroPesquisa" class="bntPainelControle">Filtro pesquisa</label>
</fieldset>

<!--Painel de controle inferior-->
<c:import url="painelControleInferior.jsp"/>

<!--Dinamico superior-->
<c:import url="dinamicoSuperior.jsp"/>

<c:if test="${empty listaProduto}">
    <div class="semRegistro">
        <h1>Não foi encontrado nenhum registro na base de dados</h1>
    </div>
</c:if>
<c:forEach var="listaProduto" items="${listaProduto}">
    <div id="conteudoIndividualProduto">
        <div class="infoImagem">
            <img src="${listaProduto.caminho_imagem}" width="100" height="100" alt="sem-imagem"/>
        </div>
        <div class="infoCodigoProduto" title="${listaProduto.id_produto}">
            <h2>Código</h2>
            <p>${listaProduto.id_produto}</p>
        </div>
        <div class="infoIndividualProduto" title="${listaProduto.nome_produto}">
            <h2>Nome</h2>
            <c:set var="string1" value="${listaProduto.nome_produto}"/>
            <c:choose>
                <c:when test="${fn:length(string1)>70}">
                    <c:set var="string2" value="${fn:substring(string1, 0, 70)}....."/>
                </c:when>
                <c:otherwise>
                    <c:set var="string2" value="${fn:substring(string1, 0, 70)}"/>
                </c:otherwise>
            </c:choose>
            <p>${string2}</p>
        </div>
        <div class="infoIndividualProduto" title="${listaProduto.nome_grupo}">
            <h2>Grupo</h2>
            <c:set var="string1" value="${listaProduto.nome_grupo}"/>
            <c:choose>
                <c:when test="${fn:length(string1)>40}">
                    <c:set var="string2" value="${fn:substring(string1, 0, 40)}....."/>
                </c:when>
                <c:otherwise>
                    <c:set var="string2" value="${fn:substring(string1, 0, 40)}"/>
                </c:otherwise>
            </c:choose>
            <p>${string2}</p>
        </div>
        <div class="infoIndividualProduto" title="${listaProduto.nome_categoria}">
            <h2>Categoria</h2>
            <c:set var="string1" value="${listaProduto.nome_categoria}"/>
            <c:choose>
                <c:when test="${fn:length(string1)>40}">
                    <c:set var="string2" value="${fn:substring(string1, 0, 40)}....."/>
                </c:when>
                <c:otherwise>
                    <c:set var="string2" value="${fn:substring(string1, 0, 40)}"/>
                </c:otherwise>
            </c:choose>
            <p>${string2}</p>
        </div>
        <div class="infoIndividualProduto">
            <h2>Operação</h2>
            <img class="editarProduto" src="IMAGENS-SISTEMA/edicao.png" width="50" height="50" alt="edicao" 
                 title="Editar esse registro"
                 data-idProduto="${listaProduto.id_produto}"
                 data-nomeProduto="${listaProduto.nome_produto}"
                 data-nomeGrupo="${listaProduto.nome_grupo}"
                 data-idGrupo="${listaProduto.id_grupo}"
                 data-nomeCategoria="${listaProduto.nome_categoria}"
                 data-idCategoria="${listaProduto.id_categoria}"
                 data-caminhoImagem="${listaProduto.caminho_imagem}"/>
            <img class="bloquearProduto" src="IMAGENS-SISTEMA/bloqueado.png" width="50" height="49" alt="bloqueado" 
                 title="Bloquear/Desbloquear esse registro"
                 data-idProduto="${listaProduto.id_produto}"
                 data-nomeProduto="${listaProduto.nome_produto}"
                 data-caminhoImagem="${listaProduto.caminho_imagem}"/>
        </div>
    </div>
</c:forEach>

<!--Dinamico Inferiro-->
<c:import url="dinamicoInferior.jsp"/>

<!--Modal Superiro-->
<c:import url="modalSuperior.jsp"/>

<!--Edita produto-->
<div id="modalEditProd">
    <form method="POST" action="EditaProduto">
        <input class="fechar resetProd" type="reset" value=""/>
        <div id="editaPainelControle"> 
            <h2>Editar produto</h2>            
            <fieldset>
                <legend>Operações</legend>
                <a href="" id="editaCaminhoConsuImagem"><div>Cadastrar imagem</div></a>
            </fieldset>
        </div>
        <div id="editaImagemProduto">
            <img src="" width="100" height="100" alt="sem-imagem" id="editaImgProduto"/>
        </div>
        <div id="editaNomeProduto">
            <label for="txtaEditaNomeProd">Nome</label>
            <textarea name="editaNomeProd" rows="4" cols="20" id="txtaEditaNomeProd" maxlength="100" 
                      placeholder="Maximo de 100 caracteres" required=""></textarea>
            <label id="editaCaracNomeProd">Falta 100 caracteres</label>
        </div>
        <div id="editaGrupoProd">
            <label for="ddrEditaGrupoProd">Grupo</label>
            <select name="editaGrupoProd" id="ddrEditaGrupoProd" required="">
                <option hidden="" value="" id="editaOptGrupoProd"></option>
                <c:forEach var="listaGrpProduto" items="${listaGrpProduto}">
                    <option value="${listaGrpProduto.id_grupo}">${listaGrpProduto.nome_grupo}</option>
                </c:forEach>
            </select>        
        </div>
        <div id="editaCateProd">
            <label for="ddrEditaCateProd">Categoria</label>
            <select name="editaCateProd" id="ddrEditaCateProd" required="">
                <option hidden="" value="" id="editaOptCateProd"></option>
                <c:forEach var="listaCateProduto" items="${listaCateProduto}">
                    <option value="${listaCateProduto.id_categoria}">${listaCateProduto.nome_categoria}</option>
                </c:forEach>
            </select>  
        </div>
        <div id="editaBntOperacaoProduto">
            <input type="submit" value="Editar" />
            <input type="reset" value="Cancelar" class="resetProd"/>
        </div>
        <input type="hidden" name="codigoProduto" value="" id="codigoProduto"/>
    </form>
</div>

<!--Cadastra produto-->

<div id="modalCadasProd">
    <form method="POST" action="CriaProduto">
        <input class="fechar resetProd" type="reset" value=""/>
        <div id="cadasPainelControle"> 
            <h2>Cadastra produto</h2>
        </div>
        <div id="cadasNomeProd">
            <label for="txtaCadasNomeProd">Nome</label>
            <textarea name="cadasNomeProd" rows="4" cols="20" id="txtaCadasNomeProd" maxlength="100" 
                      placeholder="Maximo de 100 caracteres" required=""></textarea>
            <label id="cadasCaracNomeProd">Falta 100 caracteres</label>
        </div>
        <div id="cadasGrupoProduto">
            <label for="ddlCadasGrupoProd">Grupo</label>
            <select name="cadasGrupoProd" id="ddlCadasGrupoProd" required="">
                <option value="">Selecione um grupo para o produto</option>
                <c:forEach var="listaGrpProduto" items="${listaGrpProduto}">
                    <option value="${listaGrpProduto.id_grupo}">${listaGrpProduto.nome_grupo}</option>
                </c:forEach>
            </select>
        </div>
        <div id="cadasCateProduto">
            <label for="ddlCadasCateProd">Categoria</label>
            <select name="cadasCateProd" id="ddlCadasCateProd" required="">
                <option value="">Selecione uma categoria para o produto</option>
                <c:forEach var="listaCateProduto" items="${listaCateProduto}">
                    <option value="${listaCateProduto.id_categoria}">${listaCateProduto.nome_categoria}</option>
                </c:forEach>
            </select>        
        </div>
        <div id="cadasBntOperacaoProduto">
            <input type="submit" value="Salvar" />
            <input type="reset" value="Cancelar" class="resetProd"/>
        </div>
    </form>
</div>

<!--Filtro de pesquisas-->

<div id="modalFiltroProd">
    <form method="POST" action="ConsultaProduto">
        <input class="fechar resetProd" type="reset" value=""/>
        <div id="filtroProdPainelControle"> 
            <h2>Filtro pesquisa</h2>
            <p>A consulta pode ser filtrada pelos parametros código, 
                grupo, categoria de produto, e pode ser ordenado pelos campos código, 
                grupo e categoria de produto, na ordem crescente ou descrescente.
                Para apresentar os produtos bloqueados basta clicar na opção sim da pergunta "Mostrar os bloqueados?"</p>
        </div>
        <div id="filtroCodigoProduto">
            <label>Código</label>
            <input type="number" value="" name="filtroCodigoProduto">
        </div>
        <div id="filtroNomeProduto">
            <label>Nome</label>
            <textarea name="filtroNomeProduto" rows="4" cols="20"></textarea>
        </div>
        <div id="filtroGrupoProduto">
            <label>Grupo</label>
            <select name="filtroGrupoProduto">
                <option value="">Filtro por grupo de produto</option>
                <c:forEach var="listaGrpProduto" items="${listaGrpProduto}">
                    <option value="${listaGrpProduto.id_grupo}">${listaGrpProduto.nome_grupo}</option>
                </c:forEach>
            </select>
        </div>
        <div id="filtroCategoriaProduto">
            <label>Categoria</label>
            <select name="filtroCategoriaProduto">
                <option value="">Filtro por categoria de produto</option>
                <c:forEach var="listaCateProduto" items="${listaCateProduto}">
                    <option value="${listaCateProduto.id_categoria}">${listaCateProduto.nome_categoria}</option>
                </c:forEach>
            </select>
        </div>
        <div id="filtroOrdenarProduto">
            <label>Ordenar pelo campo:</label>
            <select name="filtroOrdenarProduto">
                <option value="1">Código do produto</option>
                <option value="2">Nome do produto</option>
                <option value="3">Grupo do produto</option>
                <option value="4">Categoria do produto</option>
            </select>
        </div>
        <div id="filtroOrdemProduto">
            <label>na ordem:</label>
            <label for="crescente">crescente</label>
            <input type="radio" name="filtroOrdemProduto" value="ASC" id="crescente"/>
            <label for="decrescente">decrescente</label>
            <input type="radio" name="filtroOrdemProduto" value="DESC" checked="" id="decrescente"/>
        </div>
        <div id="filtroBloqueadoProduto">
            <label>Mostra os bloqueados? </label>
            <label for="sim">Sim</label>
            <input type="radio" name="filtroBloqueadoProduto" value="S" id="sim"/>
            <label for="nao">Não</label>
            <input type="radio" name="filtroBloqueadoProduto" value="N" checked="" id="nao"/>
        </div>
        <div id="filtroBntOperacaoProduto">
            <input type="submit" value="Executar" />
            <input type="reset" value="Cancelar" class="resetProd"/>
        </div>
    </form>
</div>

<!--Mensagem do bloqueio-->

<div id="modalMsnBlocProd">
    <input class="fechar resetProd" type="reset" value=""/>
    <h1>O que deseja fazer com o produto:</h1>
    <div id="imagemBloc">
        <img src="" id="ImagemBloqueio" width="150" height="150" alt="sem-imagem"/>
    </div>
    <div id="nomeProdBloc">
        <label id="lbBloqueioNomeProduto"></label>
    </div>
    <form method="POST" action="BloqueiaProduto">
        <div id="bntBlocProduto">
            <input type="hidden" name="id" value="" id="codigoBloqueio"/>
            <input type="submit" name="acao" value="Bloquear"/>
            <input type="submit" name="acao" value="Desbloquear"/>
        </div>
    </form>    
</div>

<!--Modal Inferior-->
<c:import url="modalInferior.jsp"/>
