<%@ page import="clase.Automovil" %>



<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'costoCompra', 'error')} required">
	<label for="costoCompra">
		<g:message code="automovil.costoCompra.label" default="Costo Compra" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="costoCompra" required="" value="${fieldValue(bean: automovilInstance, field: 'costoCompra')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'costoVenta', 'error')} required">
	<label for="costoVenta">
		<g:message code="automovil.costoVenta.label" default="Costo Venta" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="costoVenta" required="" value="${fieldValue(bean: automovilInstance, field: 'costoVenta')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'costoExtra', 'error')} ">
	<label for="costoExtra">
		<g:message code="automovil.costoExtra.label" default="Costo Extra" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${automovilInstance?.costoExtra?}" var="c">
    <li><g:link controller="costoExtra" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="costoExtra" action="create" params="['automovil.id': automovilInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'costoExtra.label', default: 'CostoExtra')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'marca', 'error')} ">
	<label for="marca">
		<g:message code="automovil.marca.label" default="Marca" />
		
	</label>
	<g:textField name="marca" value="${automovilInstance?.marca}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'vendido', 'error')} ">
	<label for="vendido">
		<g:message code="automovil.vendido.label" default="Vendido" />
		
	</label>
	<g:checkBox name="vendido" value="${automovilInstance?.vendido}" />
</div>

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'venta', 'error')} ">
	<label for="venta">
		<g:message code="automovil.venta.label" default="Venta" />
		
	</label>
	<g:checkBox name="venta" value="${automovilInstance?.venta}" />
</div>

