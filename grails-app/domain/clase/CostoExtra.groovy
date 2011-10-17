package clase

class CostoExtra {
	String descripcion
	BigDecimal costo
 
	static belongsTo = [automovil:Automovil]

   static constraints = {
	descripcion(blank:false)
	costo(blank:false, scale:8, presicion:2)
    }

	String toString(){
	 return descripcion;
	}
}
