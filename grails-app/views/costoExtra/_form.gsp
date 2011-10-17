<%@ page import="clase.CostoExtra" %>



<div class="fieldcontain ${hasErrors(bean: costoExtraInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="costoExtra.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${costoExtraInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: costoExtraInstance, field: 'costo', 'error')} required">
	<label for="costo">
		<g:message code="costoExtra.costo.label" default="Costo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="number" name="costo" required="" value="${fieldValue(bean: costoExtraInstance, field: 'costo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: costoExtraInstance, field: 'automovil', 'error')} required">
	<label for="automovil">
		<g:message code="costoExtra.automovil.label" default="Automovil" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="automovil" name="automovil.id" from="${clase.Automovil.list()}" optionKey="id" required="" value="${costoExtraInstance?.automovil?.id}" class="many-to-one"/>
</div>

