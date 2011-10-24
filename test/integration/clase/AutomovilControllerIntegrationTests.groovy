package clase

import static org.junit.Assert.*
import org.junit.*

class AutomovilControllerIntegrationTests {

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
      for(i in 1..20){       	
		def automovil = new Automovil(
			marca: "test$i",
			costoCompra: 100,
                        costoVenta: 100,
                        venta:true ,
                        vendido:true 
			).save(flush:true)
	}	
	def lista = Automovil.list()
	assert 20 == lista.size()
	
	def automovil = Automovil.findByMarca("test1")
	assert automovil
	assert "test1" == automovil.marca

	automovil.marca = 'prueba'
	automovil.save()
	def id = automovil.id
	def x = Automovil.get(id)
	assert 'prueba' == x.marca
	
	x.delete()
	
	lista = Automovil.list()
	assert 19== lista.size()
	
	def y = Automovil.get(id)
	assert !y
    }
    
}
