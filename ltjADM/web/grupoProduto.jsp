<%-- 
    Document   : grupoProdut
    Created on : 21/11/2016, 16:44:28
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

<c:if test="${empty listaGrpProduto}">
    <div class="semRegistro">
        <h1>Não foi encontrado nenhum registro na base de dados</h1>
    </div>
</c:if>
<c:forEach var="listaGrpProduto" items="${listaGrpProduto}">
    <div id="conteudoIndividualGrpProduto">
        <div class="infoCodigoGrpProduto">
            <h2>Código</h2>
            <p>${listaGrpProduto.id_grupo}</p>
        </div>
        <div class="infoIndividualGrpProduto" title="${listaGrpProduto.nome_grupo}">
            <h2>Nome Grupo</h2>
            <c:set var="string1" value="${listaGrpProduto.nome_grupo}"/>
            <c:set var="string2" value="${fn:substring(string1, 0, 40)}" />
            <p>${string2}</p>
        </div>
        <div class="infoIndividualGrpProduto">
            <h2>Operação</h2>
            <img class="editarGrpProduto" src="IMAGENS-SISTEMA/edicao.png" width="50" height="49" alt="edicao" 
                 title="Editar esse registro"
                 data-nomeGrupo="${listaGrpProduto.nome_grupo}"
                 data-idGrupo="${listaGrpProduto.id_grupo}"/>
            <img class="bloquearGrpProduto" src="IMAGENS-SISTEMA/bloqueado.png" width="50" height="49" alt="bloqueado" 
                 title="Bloquear esse registro"
                 data-nomeGrupo="${listaGrpProduto.nome_grupo}"
                 data-idGrupo="${listaGrpProduto.id_grupo}"/>
        </div>
    </div>
</c:forEach>

<!--Dinamico Inferiro-->
<c:import url="dinamicoInferior.jsp"/>

<!--Modal Superiro-->
<c:import url="modalSuperior.jsp"/>

<!--Edita grupo produto-->
<div id="modalEditGrpProd">
    <form method="POST" action="EditaGrupoProduto">
        <input class="fechar resetGrp" type="reset" value=""/>
        <div id="editaPainelControleGrpProduto"> 
            <h2>Editar grupo</h2>            
        </div>
        <div id="editaNomeGrpProduto"
             <label for="txtaEditaNomeGrpProd">Nome grupo</label>
            <textarea name="editaNomeGrpProd" rows="4" cols="20" id="txtaEditaNomeGrpProd" maxlength="50" 
                      placeholder="Maximo de 50 caracteres" required=""></textarea>
            <label id="editaCaracNomeGrpProd">Falta 50 caracteres</label>
        </div>
        <div id="editaBntOperacaoGrpProduto">
            <input type="submit" value="Editar" />
            <input type="reset" value="Cancelar" class="resetGrp"/>
        </div>
        <input type="hidden" name="codigoGrpProduto" value="" id="hidCodigoGrpProduto"/>
    </form>
</div>

<!--Cadastra grupo produto-->

<div id="modalCadasGrpProd">
    <form method="POST" action="CriaGrupoProduto">
        <input class="fechar resetGrp" type="reset" value=""/>
        <div id="cadasPainelControleGrpProduto"> 
            <h2>Cadastra grupo</h2>
        </div>
        <div id="cadasNomeGrpProd">
            <label for="txtaCadasNomeGrpProd">Nome grupo</label>
            <textarea name="cadasNomeGrpProd" rows="4" cols="20" id="txtaCadasNomeGrpProd" maxlength="50" 
                      placeholder="Maximo de 50 caracteres" required=""></textarea>
            <label id="cadasCaracNomeGrpProd">Falta 50 caracteres</label>
        </div>
        <div id="cadasBntOperacaoGrpProduto">
            <input type="submit" value="Salvar" />
            <input type="reset" value="Cancelar" class="resetGrp"/>
        </div>
    </form>
</div>

<!--Filtro de pesquisas-->

<div id="modalFiltroGrpProd">
    <form method="POST" action="ConsultaGrupoProduto">
        <input class="fechar resetGrp" type="reset" value=""/>
        <div id="filtroPainelControleGrpProduto"> 
            <h2>Filtro pesquisa</h2>
            <p>A consulta pode ser filtrada pelos parametros código e grupo de produto.
                O resultado a ser apresentado, pode ser ordenado pela campo código, grupo de produto
                na ordem crescente ou descrescente</p>
        </div>
        <div id="filtroCodigoGrpProduto">
            <label>Código grupo</label>
            <input type="number" value="" name="filtroCodigoGrpProduto">
        </div>
        <div id="filtroNomeGrpProduto">
            <label>Nome grupo</label>
            <textarea name="filtroNomeGrpProduto" rows="4" cols="20"></textarea>
        </div>
        <div id="filtroOrdenarGrpProduto">
            <label>Ordenar pelo campo</label>
            <select name="filtroOrdenarGrpProduto">
                <option value="1">Código do grupo</option>
                <option value="2">Nome do grupo</option>
            </select>
        </div>
        <div id="filtroOrdemGrpProduto">
            <label>na ordem:</label>
            <label for="crescente">crescente</label>
            <input type="radio" name="filtroOrdemGrpProduto" value="ASC" id="crescente"/>
            <label for="decrescente">decrescente</label>
            <input type="radio" name="filtroOrdemGrpProduto" value="DESC" checked="" id="decrescente"/>
        </div>
        <div id="filtroBloqueadoGrpProduto">
            <label>Mostra os bloqueados? </label>
            <label for="sim">Sim</label>
            <input type="radio" name="filtroBloqueadoGrpProduto" value="S" id="sim"/>
            <label for="nao">Não</label>
            <input type="radio" name="filtroBloqueadoGrpProduto" value="N" checked="" id="nao"/>
        </div>
        <div id="filtroBntOperacaoGrpProduto">
            <input type="submit" value="Executar" />
            <input type="reset" value="Cancelar" class="resetGrp"/>
        </div>
    </form>
</div>

<!--Mensagem bloqueio-->

<div id="msnBlocGrpProd">
    <input class="fechar resetGrp" type="reset" value=""/>
    <h1>O que deseja fazer com o grupo:</h1>
    <div id="nomeCateProdBloc">
        <label id="lbBloqueioNomeGrpProduto"></label>
    </div>
    <form method="POST" action="BloqueiaGrupoProduto">
        <div id="bntBlocGrpProduto">
            <input type="hidden" name="id" value="" id="codigoBloqueio"/>
            <input type="submit" name="acao" value="Bloquear"/>
            <input type="submit" name="acao" value="Desbloquear"/>
        </div>
    </form>    
</div>

<!--Modal Inferior-->
<c:import url="modalInferior.jsp"/>

