package clase



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(AutomovilController)
@Mock(Automovil)
class AutomovilControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/automovil/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.automovilInstanceList.size() == 0
        assert model.automovilInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.automovilInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.automovilInstance != null
        assert view == '/automovil/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/automovil/show/1'
        assert controller.flash.message != null
        assert Automovil.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/automovil/list'


        populateValidParams(params)
        def automovil = new Automovil(params)

        assert automovil.save() != null

        params.id = automovil.id

        def model = controller.show()

        assert model.automovilInstance == automovil
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/automovil/list'


        populateValidParams(params)
        def automovil = new Automovil(params)

        assert automovil.save() != null

        params.id = automovil.id

        def model = controller.edit()

        assert model.automovilInstance == automovil
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/automovil/list'

        response.reset()


        populateValidParams(params)
        def automovil = new Automovil(params)

        assert automovil.save() != null

        // test invalid parameters in update
        params.id = automovil.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/automovil/edit"
        assert model.automovilInstance != null

        automovil.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/automovil/show/$automovil.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        automovil.clearErrors()

        populateValidParams(params)
        params.id = automovil.id
        params.version = -1
        controller.update()

        assert view == "/automovil/edit"
        assert model.automovilInstance != null
        assert model.automovilInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/automovil/list'

        response.reset()

        populateValidParams(params)
        def automovil = new Automovil(params)

        assert automovil.save() != null
        assert Automovil.count() == 1

        params.id = automovil.id

        controller.delete()

        assert Automovil.count() == 0
        assert Automovil.get(automovil.id) == null
        assert response.redirectedUrl == '/automovil/list'
    }
}
