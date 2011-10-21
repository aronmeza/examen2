<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'style.css')}" type="text/css">

		<g:layoutHead/>
		<r:layoutResources/>
		<style>
		
		label.dialog, input.dialog { display:block; }
		input.text.dialog { margin-bottom:12px; width:95%; padding: .4em; }
		fieldset.dialog { padding:0; border:0; margin-top:25px; }
		h1.dialog { font-size: 1.2em; margin: .6em 0; }
		.ui-dialog .ui-state-error { padding: .3em; }
	</style>
       
	</head>
	<body>
		<div id="grailsLogo" role="banner"><a href="http://grails.org"><img src="${resource(dir: 'images', file: 'grails_logo.png')}" alt="Grails"/></a></div>
		 <g:render template="/common/header"/>			
		
		<g:layoutBody/>

		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		





<div id="dialog-form" title="Agrear Automovil">
<p class="validateTips"></p>

	<form id="formautosend" action="/clase/automovil/save"  method="POST">
	<fieldset class="dialog">
		<label for="marca" class="dialog">Marca</label>
		<input type="text" name="marca" id="marca" class="text ui-widget-content ui-corner-all dialog" />
		<label for="costoCompra" class="dialog">Costo de Compra</label>
		<input type="text" name="costoCompra" id="costoCompra" value="" class="text ui-widget-content ui-corner-all dialog" />
		<label for="costoCompra" class="dialog">Costo de Venta</label>
		<input type="text" name="costoVenta" id="costoVenta" value="" class="text ui-widget-content ui-corner-all dialog" />
 	</fieldset>

	</form>
</div>



<r:script>
 $(document).ready(function(){
 $("input#marca").autocomplete({
       		   source: '${createLink(action:'buscaMarca', controller:'automovil')}'
     		   });



  

		
                $( "#dialog-form" ).dialog( "close" );	
                $( "#dialog:ui-dialog" ).dialog( "destroy" );
		
		var marca = $( "#marca" ),
			costoVenta = $( "#costoVenta" ),
			costoCompra = $( "#costoCompra" ),
			allFields = $( [] ).add( marca ).add( costoCompra ).add(costoVenta),
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
		
		$( "#dialog-form" ).dialog({
			autoOpen: false,
			height: 400,
			width: 650,
			modal: true,
			buttons: {
				"Agregar carro": function() {
					var bValid = true;
					allFields.removeClass( "ui-state-error" );
					bValid = bValid && checkRegexp( costoCompra, /^([0-9])+$/, "Costo compra solo se puede : 0-9" );
					bValid = bValid && checkRegexp( costoVenta, /^([0-9])+$/, "Costo venta solo se puede : 0-9" );
					bValid = bValid && checkRegexp( marca, /^([a-zA-Z])+$/, "Marca solo se puede : a-zA-Z" );

					if ( bValid ) {
						$( "#costoCompra" ).text( costoCompra.val());
						$( "#costoVenta" ).text( costoVenta.val());  
						$( "#marca" ).text(marca.val() ); 
						$( "#dialog-form" ).dialog( "close" );
						window.location="/clase/automovil/saveMin?marca="+$( "#marca" ).text()+"&costoCompra="+$( "#costoCompra" ).text()+"&costoVenta="+$( "#costoVenta" ).text()
						
					}
				},
				Cancelar: function() {
					$( "#dialog-form" ).dialog( "close" );
				}
			},
			close: function() {
				allFields.val( "" ).removeClass( "ui-state-error" );
			}
		});

		$( "#creates-auto" )	
			.click(function() {
				$( "#dialog-form" ).dialog( "open" );
			});
 });
</r:script>
		  <r:layoutResources/>
		<g:javascript library="application"/>

	</body>
</html>


