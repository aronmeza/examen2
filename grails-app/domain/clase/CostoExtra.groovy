package clase

class CostoExtra {
	String descripcion
	BigDecimal costo
 
	static belongsTo = [automovil:Automovil]

   static constraints = {
	descripcion(blank:false)
	costo(blank:false, scale:2, presicion:8)
    }

	String toString(){
	 return "$descripcion : $costo";
	}
}
