package clase



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(CostoExtraController)
@Mock(CostoExtra)
class CostoExtraControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/costoExtra/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.costoExtraInstanceList.size() == 0
        assert model.costoExtraInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.costoExtraInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.costoExtraInstance != null
        assert view == '/costoExtra/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/costoExtra/show/1'
        assert controller.flash.message != null
        assert CostoExtra.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/costoExtra/list'


        populateValidParams(params)
        def costoExtra = new CostoExtra(params)

        assert costoExtra.save() != null

        params.id = costoExtra.id

        def model = controller.show()

        assert model.costoExtraInstance == costoExtra
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/costoExtra/list'


        populateValidParams(params)
        def costoExtra = new CostoExtra(params)

        assert costoExtra.save() != null

        params.id = costoExtra.id

        def model = controller.edit()

        assert model.costoExtraInstance == costoExtra
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/costoExtra/list'

        response.reset()


        populateValidParams(params)
        def costoExtra = new CostoExtra(params)

        assert costoExtra.save() != null

        // test invalid parameters in update
        params.id = costoExtra.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/costoExtra/edit"
        assert model.costoExtraInstance != null

        costoExtra.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/costoExtra/show/$costoExtra.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        costoExtra.clearErrors()

        populateValidParams(params)
        params.id = costoExtra.id
        params.version = -1
        controller.update()

        assert view == "/costoExtra/edit"
        assert model.costoExtraInstance != null
        assert model.costoExtraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/costoExtra/list'

        response.reset()

        populateValidParams(params)
        def costoExtra = new CostoExtra(params)

        assert costoExtra.save() != null
        assert CostoExtra.count() == 1

        params.id = costoExtra.id

        controller.delete()

        assert CostoExtra.count() == 0
        assert CostoExtra.get(costoExtra.id) == null
        assert response.redirectedUrl == '/costoExtra/list'
    }
}
