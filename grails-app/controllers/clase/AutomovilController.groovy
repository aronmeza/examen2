package clase

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class AutomovilController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def  springSecurityService
     
    def index() {
        redirect(action: "list", params: params)
    }
    @Secured(['ROLE_ADMIN'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [automovilInstanceList: Automovil.list(params), automovilInstanceTotal: Automovil.count()]
    }
    
    def comprarShow(){
        def automovilInstance = Automovil.get(params?.id)
        if(automovilInstance!=null){
            [automovilInstance: automovilInstance]
            
        }else{
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automovil.label', default: 'Automovil'), params.id])
            redirect(action: "listaVenta")
        }
        
    }
   
    def saveUsuario(){
        println "-----saveUsuario-------$params"
        def usuarioInstance = new general.Usuario(params)
        if (!usuarioInstance.save(flush: true)) {
            flash.message = "El usuario no se pudo guardar"
            redirect(action:"listaVenta")
        }else{
            def rol = general.Rol.findByAuthority('ROLE_USER')
            general.UsuarioRol.create(usuarioInstance, rol , true)
            springSecurityService.reauthenticate usuarioInstance.username
            flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
            redirect (action:"comprar", id:params.idAuto)
        }
    }
    @Secured(['ROLE_USER'])
    def showUser(){
        println"------------usuario---------$params"
        def usuario = springSecurityService.currentUser
        if(usuario!=null){
            def usuarioInstance = general.Usuario.getByUsername(usuario.username)
            [usuarioInstance:usuarioInstance]
        }
    }
    
    def comprar(){
        println "comprar auto--------$params"
        def usuario = springSecurityService.currentUser
        if(usuario!=null){
            //el usuario ya esta registrado
            println"===== $usuario.id"
            general.Usuario usuarioInstance = general.Usuario.get(usuario.id)
            println"usaurio = $usuarioInstance"
            Automovil autoComprado = Automovil.get(params.id)
            autoComprado.vendido= true
            autoComprado.save(flush:true)
            usuarioInstance.autosComprados.add(autoComprado)
            redirect(action: "listaVenta")
            
        }else{
            //el usuario no esta registrado
            def rolInstance = general.Rol.executeQuery("from Rol where authority='ROLE_USER'")
            render(view:"create_comprador", model:[usuarioInstance: new general.Usuario(),rolInstance:rolInstance,idAuto:params.id])
        }
        
    }
    
    @Secured(['ROLE_VENDEDOR'])
    def listaVendidos() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [automovilInstanceList: Automovil.findAllByVendido(true), automovilInstanceTotal: Automovil.count()]
    }
   
    def listaVenta() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
	[automovilInstanceList: Automovil.executeQuery("from Automovil where venta=true and vendido=false"), automovilInstanceTotal: Automovil.executeQuery("from Automovil where venta=true and vendido=false").size()]
    }

    def create() {
        [automovilInstance: new Automovil(params)]
    }
    
    @Secured(['ROLE_VENDEDOR'])
    def saveMin(){
        def usuario = springSecurityService.currentUser
        if(usuario!=null){
            if(params?.marca){
		if(params?.costoVenta){
                    if(params?.costoCompra){
                        def automovil = new Automovil(
                            marca:params.marca,
                            costoCompra: params.costoCompra,
                            costoVenta:params.costoVenta,
                            usuario:usuario
                        ).save(flush:true)
                        println "------------$automovil.id"
                        def automovilInstance = Automovil.get(automovil.id)
                        render(view: "edit", model: [automovilInstance: automovilInstance])
                    }
		}
            }
	}
    }
    
    @Secured(['ROLE_VENDEDOR'])
    def saveCostoExtraMin(){
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
    
    @Secured(['ROLE_VENDEDOR'])
    def save() {
        def usuario = springSecurityService.currentUser
        if(usuario!=null){
            def automovilInstance = new Automovil(params)
            automovilInstance.usuario = usuario
            if (!automovilInstance.save(flush: true)) {
                render(view: "create", model: [automovilInstance: automovilInstance])
                return
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'automovil.label', default: 'Automovil'), automovilInstance.id])
            redirect(action: "show", id: automovilInstance.id)
        }
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
    
    @Secured(['ROLE_VENDEDOR'])
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
    
    @Secured(['ROLE_VENDEDOR'])
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
