<%-- 
    Document   : categoriaProdut
    Created on : 22/11/2016, 14:39:02
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
    <label id="novaCategoria" class="bntPainelControle">Novo registro</label>
    <label id="filtroPesquisa" class="bntPainelControle">Filtro pesquisa</label>
</fieldset>

<!--Painel de controle inferior-->
<c:import url="painelControleInferior.jsp"/>

<!--Dinamico superior-->
<c:import url="dinamicoSuperior.jsp"/>

<c:if test="${empty listaCategoriaProduto}">
    <div class="semRegistro">
        <h1>Não foi encontrado nenhum registro na base de dados</h1>
    </div>
</c:if>
<c:forEach var="listaCategoriaProduto" items="${listaCategoriaProduto}">
    <div id="conteudoIndividualCateProduto">
        <div class="infoCodigoCateProduto">
            <h2>Código</h2>
            <p>${listaCategoriaProduto.id_categoria}</p>
        </div>
        <div class="infoIndividualCateProduto" title="${listaCategoriaProduto.nome_categoria}">
            <h2>Nome categoria</h2>
            <c:set var="string1" value="${listaCategoriaProduto.nome_categoria}"/>
            <c:set var="string2" value="${fn:substring(string1, 0, 40)}" />
            <p>${string2}</p>
        </div>
        <div class="infoIndividualCateProduto">
            <h2>Operação</h2>
            <img class="editarCateProduto" src="IMAGENS-SISTEMA/edicao.png" width="50" height="50" alt="edicao" 
                 title="Editar esse registro"
                 data-nomeCategoria="${listaCategoriaProduto.nome_categoria}"
                 data-idCategoria="${listaCategoriaProduto.id_categoria}"/>
            <img class="bloquearCateProduto" src="IMAGENS-SISTEMA/bloqueado.png" width="50" height="49" alt="bloqueado" 
                 title="Bloquear esse registro"
                 data-nomeCategoria="${listaCategoriaProduto.nome_categoria}"
                 data-codigoCategoria="${listaCategoriaProduto.id_categoria}"/>
        </div>
    </div>
</c:forEach>

<!--Dinamico Inferiro-->
<c:import url="dinamicoInferior.jsp"/>

<!--Modal Superiro-->
<c:import url="modalSuperior.jsp"/>

<!--Edita categoria produto-->

<div id="modalEditCateProd">
    <form method="POST" action="EditarCategoriaProduto">
        <input class="fechar resetCate" type="reset" value=""/>
        <div id="editaPainelControleCateProduto"> 
            <h2>Editar categoria</h2>            
        </div>
        <div id="editaNomeCateProduto">
            <label for="txtaEditaNomeCateProd">Nome categoria</label>
            <textarea name="editaNomeCateProd" rows="4" cols="20" id="txtaEditaNomeCateProd" maxlength="50" 
                      placeholder="Maximo de 50 caracteres" required=""></textarea>
            <label id="editaCaracNomeCateProd">Falta 50 caracteres</label>
        </div>
        <div id="editaBntOperacaoCateProduto">
            <input type="submit" value="Editar" />
            <input type="reset" value="Cancelar" class="resetCate"/>
        </div>
        <input type="hidden" name="codigoCategoria" value="" id="hidCodigoCateProduto"/>
    </form>
</div>

<!--Cadastra categoria produto-->

<div id="modalCadasCateProd">
    <form method="POST" action="CriaCategoriaProduto">
        <input class="fechar resetCate" type="reset" value=""/>
        <div id="cadasPainelControleCateProduto"> 
            <h2>Cadastra categoria</h2>
        </div>
        <div id="cadasNomeCateProd">
            <label for="txtaCadasNomeCateProd">Nome categoria</label>
            <textarea name="cadasNomeCateProd" rows="4" cols="20" id="txtaCadasNomeCateProd" maxlength="50" 
                      placeholder="Maximo de 50 caracteres" required=""></textarea>
            <label id="cadasCaracNomeCateProd">Falta 50 caracteres</label>
        </div>
        <div id="cadasBntOperacaoCateProduto">
            <input type="submit" value="Salvar" />
            <input type="reset" value="Cancelar" class="resetCate"/>
        </div>
    </form>
</div>

<!--Filtro de pesquisas-->

<div id="modalFiltroCatProd">
    <form method="POST" action="ConsultaCategoriaProduto">
        <input class="fechar resetCate" type="reset" value=""/>
    <div id="filtroCatPainelControle"> 
            <h2>Filtro pesquisa</h2>
            <p>A consulta pode ser filtrada pelos parametros código e grupo de produto.
                O resultado a ser apresentado, pode ser ordenado pela campo código, grupo de produto
                na ordem crescente ou descrescente</p>
        </div>
        <div id="filtroCodigoCatProduto">
            <label>Código categoria</label>
            <input type="number" value="" name="filtroCodigoCatProduto">
        </div>
        <div id="filtroNomeCatProduto">
            <label>Nome categoria</label>
            <textarea name="filtroNomeCatProduto" rows="4" cols="20"></textarea>
        </div>
        <div id="filtroOrdenarCatProduto">
            <label>Ordenar pelo campo:</label>
            <select name="filtroOrdenarCatProduto">
                <option value="1">Código do grupo produto</option>
                <option value="2">Nome do grupo produto</option>
            </select>
        </div>
        <div id="filtroOrdemCatProduto">
            <label>na ordem:</label>
            <label for="crescente">crescente</label>
            <input type="radio" name="filtroOrdemCatProduto" value="ASC" id="crescente"/>
            <label for="decrescente">decrescente</label>
            <input type="radio" name="filtroOrdemCatProduto" value="DESC" checked="" id="decrescente"/>
        </div>
        <div id="filtroBloqueadoCatProduto">
            <label>Mostra os bloqueados? </label>
            <label for="sim">Sim</label>
            <input type="radio" name="filtroBloqueadoCatProduto" value="S" id="sim"/>
            <label for="nao">Não</label>
            <input type="radio" name="filtroBloqueadoCatProduto" value="N" checked="" id="nao"/>
        </div>
        <div id="filtroBntOperacaoCatProduto">
            <input type="submit" value="Executar" />
            <input type="reset" value="Cancelar" class="resetCate"/>
        </div>
    </form>
</div>

<!--Mensagem bloqueio-->

<div id="modalMsnBlocCatProd">
    <input class="fechar resetCate" type="reset" value=""/>
    <h1>O que deseja fazer com a categoria:</h1>
    <div id="nomeCateProdBloc">
        <label id="lbNomeCateProdBloc"></label>
    </div>
    <form method="POST" action="BloqueiaCategoriaProduto">
        <div id="msnBntOperacaoCatBlocProduto">
            <input type="hidden" name="id" value="" id="codigoBloqueio"/>
            <input type="submit" name="acao" value="Bloquear"/>
            <input type="submit" name="acao" value="Desbloquear"/>
        </div>
    </form>    
</div>

<!--Modal Inferior-->
<c:import url="modalInferior.jsp"/>

