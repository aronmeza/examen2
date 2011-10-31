
<%@ page import="clase.Automovil" %>
<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'automovil.label', default: 'Automovil')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
  <r:require module="jquery-ui"/>
</head>
<body>
  <a href="#show-automovil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div id="show-automovil" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list automovil">

      <g:if test="${automovilInstance?.marca}">
        <li class="fieldcontain">
          <span id="marca-label" class="property-label"><g:message code="automovil.marca.label" default="Marca" /></span>

          <span class="property-value" aria-labelledby="marca-label"><g:fieldValue bean="${automovilInstance}" field="marca"/></span>

        </li>
      </g:if>

      

      <g:if test="${automovilInstance?.costoVenta}">
        <li class="fieldcontain">
          <span id="costoVenta-label" class="property-label"><g:message code="automovil.costoVenta.label" default="Costo Venta" /></span>

          <span class="property-value" aria-labelledby="costoVenta-label"><g:fieldValue bean="${automovilInstance}" field="costoVenta"/></span>

        </li>
      </g:if>




    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${automovilInstance?.id}" />
        <g:link class="comprar" action="comprar" id="${automovilInstance?.id}" onclick="return confirm('${message(code: 'default.button.comprar.confirm.message', default: 'Estas seguro que deseas comprar?')}');"><g:message code="default.button.comprar.label" default="Comprar" /></g:link>
        <g:link class="cancelar" action="listaVenta" value="${message(code: 'default.button.cancelar.label', default: 'Cancelar')}"  ><g:message code="default.button.cancelar.label" default="Cancelar" /></g:link>
      </fieldset>
    </g:form>
  </div>
</body>
</html>
