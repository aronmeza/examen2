<%@ page import="clase.Automovil" %>


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

<div class="fieldcontain ${hasErrors(bean: automovilInstance, field: 'costoExtra', 'error')} ">
	<label for="costoExtra">
		<g:message code="automovil.costoExtra.label" default="Costo Extra" />
		
	</label>

<div id="automovil-costo-contain" style="width:30%">
	<table id="costos" >
		<thead>
			<tr>
				<th>Descripcion</th>
				<th>Precio</th>
		</thead>
		<tbody>
                <g:each in="${automovilInstance?.costoExtra?}" var="c">
			<tr>
                               <td><g:link controller="costoExtra" action="show" id="${c.id}">${c?.descripcion}</g:link></td>
                               <td>${c?.costo}</td>
			</tr>
                 </g:each>
                
		</tbody>
	</table>
        <a id="crea-costo">Agregar Costo</a>
</div>
   

  
</div>





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

 <div id="dialog-form-costo" title="Agrear Costo Extra">
<p class="validateTips"></p>

	<div id="formcostosend" >
	<fieldset class="dialog">
		<label for="Descripcion" class="dialog">Descripcion</label>
		<input type="text" name="descripcion" id="descripcionform" class="text ui-widget-content ui-corner-all dialog" />
		<label for="costo" class="dialog">Costo de Compra</label>
		<input type="text" name="costo" id="costoform" value="" class="text ui-widget-content ui-corner-all dialog" />
 	</fieldset>

	</div>
</div>

<r:script>
 

 

  $(document).ready(function() {
		
                $( "#dialog-form-costo" ).dialog( "close" );	
                $( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		var descripcion = $( "#descripcionform" ),
			costo = $( "#costoform" ),
			allFields = $( [] ).add( descripcion ).add( costo ),
			tips = $( ".validateTips" );
		function updateTips( t ) {
			tips
				.text( t )
				.addClass( "ui-state-highlight" );
			setTimeout(function() {
				tips.removeClass( "ui-state-highlight", 1500 );
			}, 500 );
		}


		function checkRegexp( o, regexp, n ) {
			if ( !( regexp.test( o.val() ) ) ) {
				o.addClass( "ui-state-error" );
				updateTips( n );
				return false;
			} else {
				return true;
			}
		}
                        
		$( "#dialog-form-costo" ).dialog({
			autoOpen: false,
			height: 400,
			width: 650,
			modal: true,
			buttons: {
				"Agregar costo": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
					bValid = bValid && checkRegexp( costo, /^([0-9])+$/, "Costo compra solo se puede : 0-9" );
					bValid = bValid && checkRegexp( descripcion, /^([a-zA-Z])+$/, "descripcion solo se puede : a-zA-Z" );

					if ( bValid ) {
						$( "#costoform" ).text( costo.val());
						$( "#descripcionform" ).text(descripcion.val() ); 
						$( "dialog-form-costo" ).dialog( "close" );
						window.location="/clase/automovil/saveCostoExtraMin?descripcion="+$( "#descripcionform" ).text()+"&costo="+$( "#costoform" ).text()+"&idAuto="+$("#idAuto").val()
						
					}
				},
				Cancelar: function() {
					$( "#dialog-form-costo" ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});
                 
		$( "#crea-costo" )	
			.click(function() {
				$( "#dialog-form-costo" ).dialog( "open" );
			});
	});</r:script>