package clase

import static org.junit.Assert.*
import org.junit.*

class CostoExtraControllerIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testSomething() {
        def automovil = new Automovil(
            marca: "test",
            costoCompra: 100,
            costoVenta: 100,
            venta:true ,
            vendido:true 
        ).save(flush:true)
        for(i in 1..20){       	
            def costoExtra = new CostoExtra(
                descripcion: "test$i",
                costo: 100,
                automovil:automovil
            ).save(flush:true)
	}	
	def lista = CostoExtra.list()
	assert 20 == lista.size()
	
	def costoExtra = CostoExtra.findByDescripcion("test1")
	assert costoExtra
	assert "test1" == costoExtra.descripcion

	costoExtra.descripcion = 'prueba'
	costoExtra.save()
	def id = costoExtra.id
	def x = CostoExtra.get(id)
	assert 'prueba' == x.descripcion
	
	x.delete()
	
	lista = CostoExtra.list()
	assert 19== lista.size()
	
	def y = CostoExtra.get(id)
	assert !y
    }
}
