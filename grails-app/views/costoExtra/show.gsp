
<%@ page import="clase.CostoExtra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'costoExtra.label', default: 'CostoExtra')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
                <r:require module="jquery-ui"/>
	</head>
	<body>
		<a href="#show-costoExtra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="show-costoExtra" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list costoExtra">
			
				<g:if test="${costoExtraInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="costoExtra.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${costoExtraInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${costoExtraInstance?.costo}">
				<li class="fieldcontain">
					<span id="costo-label" class="property-label"><g:message code="costoExtra.costo.label" default="Costo" /></span>
					
						<span class="property-value" aria-labelledby="costo-label"><g:fieldValue bean="${costoExtraInstance}" field="costo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${costoExtraInstance?.automovil}">
				<li class="fieldcontain">
					<span id="automovil-label" class="property-label"><g:message code="costoExtra.automovil.label" default="Automovil" /></span>
					
						<span class="property-value" aria-labelledby="automovil-label"><g:link controller="automovil" action="show" id="${costoExtraInstance?.automovil?.id}">${costoExtraInstance?.automovil?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${costoExtraInstance?.id}" />
					<g:link class="edit" action="edit" id="${costoExtraInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
