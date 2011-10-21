
<%@ page import="clase.Automovil" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'automovil.label', default: 'Automovil')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
                 <r:require module="jquery-ui"/>
	</head>
	<body>
		<a href="#list-automovil" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<div id="list-automovil" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="costoCompra" title="${message(code: 'automovil.costoCompra.label', default: 'Costo Compra')}" />
					
						<g:sortableColumn property="costoVenta" title="${message(code: 'automovil.costoVenta.label', default: 'Costo Venta')}" />
					
						<g:sortableColumn property="marca" title="${message(code: 'automovil.marca.label', default: 'Marca')}" />
					
						<g:sortableColumn property="venta" title="${message(code: 'automovil.venta.label', default: 'Venta')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${automovilInstanceList}" status="i" var="automovilInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${automovilInstance.id}">${fieldValue(bean: automovilInstance, field: "costoCompra")}</g:link></td>
					
						<td>${fieldValue(bean: automovilInstance, field: "costoVenta")}</td>
					
						<td>${fieldValue(bean: automovilInstance, field: "marca")}</td>
					
						<td>
                                                  <g:if test="${automovilInstance.venta != null}">
                                                <img src="${resource(dir: 'images', file: 'for-sale.gif')}" style="heigth:50px; width:70px" alt="ForSale"/>
                                                </g:if>
                                                </td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${automovilInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
