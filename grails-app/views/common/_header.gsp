
<nav role="navigation">

  <ul id="menu"> 
    <li tabindex="0"><h2><a href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></h2></li>
    <li tabindex="0"><h2><g:link action="index" controller="automovil"><g:message code="default.resumen.automovil.label" default="Autos en Lote"/></g:link></h2>
      <ul class="submenu" tabindex="0"> 
        <li tabindex="0"><g:link action="listaVenta" controller="automovil"><g:message code="default.resumen.automovil" default="Autos en Venta"/></g:link></li>
	<li tabindex="0"><g:link action="listaVendidos" controller="automovil"><g:message code="default.resumen.automovil" default="Autos Vendidos"/></g:link></li>
      </ul>
    </li>
    <li tabindex="0"><h2><g:link action="list" controller="automovil"><g:message code="default.automovil.label" default="Automovil"/></g:link></h2>
      <ul class="submenu" tabindex="0"> 
        <li tabindex="0"><a id="creates-auto" href="#"><g:message code="default.automovil.new" default="Agregar Auto"/></a></li>
      </ul>
    </li> 
    <li tabindex="0"><h2><g:link action="list" controller="usuario"><g:message code="default.usuario.label" default="Usuario"/></g:link></h2>
    </li> 
  </ul>

  </nav>


