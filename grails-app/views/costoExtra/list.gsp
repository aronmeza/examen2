
<%@ page import="clase.CostoExtra" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'costoExtra.label', default: 'CostoExtra')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-costoExtra" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-costoExtra" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'costoExtra.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="costo" title="${message(code: 'costoExtra.costo.label', default: 'Costo')}" />
					
						<th><g:message code="costoExtra.automovil.label" default="Automovil" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${costoExtraInstanceList}" status="i" var="costoExtraInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${costoExtraInstance.id}">${fieldValue(bean: costoExtraInstance, field: "descripcion")}</g:link></td>
					
						<td>${fieldValue(bean: costoExtraInstance, field: "costo")}</td>
					
						<td>${fieldValue(bean: costoExtraInstance, field: "automovil")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${costoExtraInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
