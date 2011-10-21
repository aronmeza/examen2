package clase

class Automovil {

	String marca
	BigDecimal costoCompra
	BigDecimal costoVenta
	boolean venta = false
	boolean vendido = false
	
	static hasMany = [costoExtra:CostoExtra]

    static constraints = {
	costoCompra(blank:false, scale:2, presicion:8)
	costoVenta(scale:8, presicion:2)
	
    }
	
	String toString(){
	return marca
	}
}
