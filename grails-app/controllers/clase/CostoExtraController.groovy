package clase

import org.springframework.dao.DataIntegrityViolationException

class CostoExtraController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [costoExtraInstanceList: CostoExtra.list(params), costoExtraInstanceTotal: CostoExtra.count()]
    }

    def create() {
        [costoExtraInstance: new CostoExtra(params)]
    }

    def save() {
        def costoExtraInstance = new CostoExtra(params)
        if (!costoExtraInstance.save(flush: true)) {
            render(view: "create", model: [costoExtraInstance: costoExtraInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), costoExtraInstance.id])
        redirect(action: "show", id: costoExtraInstance.id)
    }

    def show() {
        def costoExtraInstance = CostoExtra.get(params.id)
        if (!costoExtraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "list")
            return
        }

        [costoExtraInstance: costoExtraInstance]
    }

    def edit() {
        def costoExtraInstance = CostoExtra.get(params.id)
        if (!costoExtraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "list")
            return
        }

        [costoExtraInstance: costoExtraInstance]
    }

    def update() {
        def costoExtraInstance = CostoExtra.get(params.id)
        if (!costoExtraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (costoExtraInstance.version > version) {
                costoExtraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'costoExtra.label', default: 'CostoExtra')] as Object[],
                          "Another user has updated this CostoExtra while you were editing")
                render(view: "edit", model: [costoExtraInstance: costoExtraInstance])
                return
            }
        }

        costoExtraInstance.properties = params

        if (!costoExtraInstance.save(flush: true)) {
            render(view: "edit", model: [costoExtraInstance: costoExtraInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), costoExtraInstance.id])
        redirect(action: "show", id: costoExtraInstance.id)
    }

    def delete() {
        def costoExtraInstance = CostoExtra.get(params.id)
        if (!costoExtraInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "list")
            return
        }

        try {
            costoExtraInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'costoExtra.label', default: 'CostoExtra'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
