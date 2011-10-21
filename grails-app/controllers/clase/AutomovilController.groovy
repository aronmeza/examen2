package clase

import org.springframework.dao.DataIntegrityViolationException

class AutomovilController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
	println "----------------------$params"
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [automovilInstanceList: Automovil.list(params), automovilInstanceTotal: Automovil.count()]
    }

    def listaVendidos() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [automovilInstanceList: Automovil.findAllByVendido(true), automovilInstanceTotal: Automovil.count()]
    }

    def listaVenta() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
	[automovilInstanceList: Automovil.findAllByVenta(true), automovilInstanceTotal: Automovil.count()]
    }

    def create() {
	println "----------------------$params"
        [automovilInstance: new Automovil(params)]
    }
    def saveMin(){
println "----------------------$params"
	if(params?.marca){
		if(params?.costoVenta){
			if(params?.costoCompra){
				def automovil = new Automovil(
				marca:params.marca,
				costoCompra: params.costoCompra,
				costoVenta:params.costoVenta
				).save(flush:true)
                                println "------------$automovil.id"
				def automovilInstance = Automovil.get(automovil.id)
				render(view: "edit", model: [automovilInstance: automovilInstance])
			}
		}
	}
	}
        def saveCostoExtraMin(){
println "----------------------$params"
		if(params?.descripcion){
			if(params?.costo){
                            if(params?.idAuto){
                                def automovil = Automovil.get(params.idAuto)
				def costoExtra = new CostoExtra(
				descripcion:params.descripcion,
				costo: params.costo,
				automovil:automovil
				).save(flush:true)
				def automovilInstance = Automovil.get(automovil.id)
                                println "terminado $automovilInstance.id"
				render(view: "edit", controller:"automovil" ,model: [automovilInstance: automovilInstance])
                                }
			}
		}
	}

    def save() {
        def automovilInstance = new Automovil(params)
        if (!automovilInstance.save(flush: true)) {
            render(view: "create", model: [automovilInstance: automovilInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'automovil.label', default: 'Automovil'), automovilInstance.id])
        redirect(action: "show", id: automovilInstance.id)
    }

    def show() {
        def automovilInstance = Automovil.get(params.id)
        if (!automovilInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "list")
            return
        }

        [automovilInstance: automovilInstance]
    }

    def edit() {
        println "-----Entro a edit del controller"
        def automovilInstance = Automovil.get(params.id)
        if (!automovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "list")
            return
        }

        [automovilInstance: automovilInstance]
    }

    def update() {
        def automovilInstance = Automovil.get(params.id)
        if (!automovilInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (automovilInstance.version > version) {
                automovilInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'automovil.label', default: 'Automovil')] as Object[],
                          "Another user has updated this Automovil while you were editing")
                render(view: "edit", model: [automovilInstance: automovilInstance])
                return
            }
        }

        automovilInstance.properties = params

        if (!automovilInstance.save(flush: true)) {
            render(view: "edit", model: [automovilInstance: automovilInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'automovil.label', default: 'Automovil'), automovilInstance.id])
        redirect(action: "show", id: automovilInstance.id)
    }

    def delete() {
        def automovilInstance = Automovil.get(params.id)
        if (!automovilInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "list")
            return
        }

        try {
            automovilInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def buscaMarca() {
		log.debug("Params: $params")
		def filtro = "%$params.term%"
			println filtro +"---------------"
		def autos = Automovil.findAllByMarcaIlike(filtro)

		def lista = []
		for(auto in autos) {
		    lista << [ value:auto.marca]
		}
		render lista as grails.converters.JSON
	    }
}
