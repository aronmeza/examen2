
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
			
				<g:if test="${automovilInstance?.costoCompra}">
				<li class="fieldcontain">
					<span id="costoCompra-label" class="property-label"><g:message code="automovil.costoCompra.label" default="Costo Compra" /></span>
					
						<span class="property-value" aria-labelledby="costoCompra-label"><g:fieldValue bean="${automovilInstance}" field="costoCompra"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${automovilInstance?.costoVenta}">
				<li class="fieldcontain">
					<span id="costoVenta-label" class="property-label"><g:message code="automovil.costoVenta.label" default="Costo Venta" /></span>
					
						<span class="property-value" aria-labelledby="costoVenta-label"><g:fieldValue bean="${automovilInstance}" field="costoVenta"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${automovilInstance?.costoExtra}">
				<li class="fieldcontain">
					<span id="costoExtra-label" class="property-label"><g:message code="automovil.costoExtra.label" default="Costo Extra" /></span>
					
						<g:each in="${automovilInstance.costoExtra}" var="c">
						<span class="property-value" aria-labelledby="costoExtra-label"><g:link controller="costoExtra" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${automovilInstance?.marca}">
				<li class="fieldcontain">
					<span id="marca-label" class="property-label"><g:message code="automovil.marca.label" default="Marca" /></span>
					
						<span class="property-value" aria-labelledby="marca-label"><g:fieldValue bean="${automovilInstance}" field="marca"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${automovilInstance?.vendido}">
				<li class="fieldcontain">
					<span id="vendido-label" class="property-label"><g:message code="automovil.vendido.label" default="Vendido" /></span>
					
						<span class="property-value" aria-labelledby="vendido-label"><g:formatBoolean boolean="${automovilInstance?.vendido}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${automovilInstance?.venta}">
				<li class="fieldcontain">
					<span id="venta-label" class="property-label"><g:message code="automovil.venta.label" default="Venta" /></span>
					
						<span class="property-value" aria-labelledby="venta-label"><g:formatBoolean boolean="${automovilInstance?.venta}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${automovilInstance?.id}" />
					<g:link class="edit" action="edit" id="${automovilInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
