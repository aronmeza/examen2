package general

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

class UsuarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def  springSecurityService
    
    def index() {
        redirect(action: "list", params: params)
    }
    
    @Secured(['ROLE_VENDEDOR'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
    }

    def create() {
        def usuario = springSecurityService.currentUser
	def rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER'")
        if(usuario!=null){
            if(usuario.getRol() == 'ROLE_ADMIN'){
                rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER' or authority='ROLE_ADMIN' or authority='ROLE_VENDEDOR' ")
                render(view: "create_admin", model: [usuarioInstance: new Usuario(params),rolInstance:rolInstance])
            }else if(usuario.getRol()== 'ROLE_VENDEDOR'){
                rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER' or authority='ROLE_VENDEDOR' ")
                render(view: "create_vendedor", model: [usuarioInstance: new Usuario(params),rolInstance:rolInstance])
            }else{
                render(view: "create_comprador", model: [usuarioInstance: new Usuario(params),rolInstance:rolInstance])
            }
        }else{
            render(view: "create_comprador", model: [usuarioInstance: new Usuario(params),rolInstance:rolInstance])
	}
    }

    def save() {
        def usuarioInstance = new Usuario(params)
        if (!usuarioInstance.save(flush: true)) {
            def usuario = springSecurityService.currentUser
            def rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER'")
            if(usuario!=null){
                if(usuario.getRol() == 'ROLE_ADMIN'){
                    rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER' or authority='ROLE_ADMIN' or authority='ROLE_VENDEDOR' ")
                    render(view: "create_admin", model: [usuarioInstance: usuarioInstance,rolInstance:rolInstance])
                    return
                }else if(usuario.getRol()== 'ROLE_VENDEDOR'){
                    rolInstance = Rol.executeQuery("from Rol where authority='ROLE_USER' or authority='ROLE_VENDEDOR' ")
                    render(view: "create_vendedor", model: [usuarioInstance: usuarioInstance,rolInstance:rolInstance])
                    return
                }else{
                    render(view: "create_comprador", model: [usuarioInstance: usuarioInstance,rolInstance:rolInstance])
                    return
                }
            }else{
                render(view: "create_comprador", model: [usuarioInstance: usuarioInstance,rolInstance:rolInstance])
                return
            }
            
            render(view: "create", model: [usuarioInstance: usuarioInstance])
            
        }
        if(params.authority == null){
            render(view: "create", model: [usuarioInstance: usuarioInstance])
            return
        }
        def rol 
        if(params.authority== 'ROLE_USER'){
            rol = Rol.findByAuthority('ROLE_USER')
        }else if(params.authority== 'ROLE_VENDEDOR'){
            rol = Rol.findByAuthority('ROLE_VENDEDOR')
        }else if(params.authority== 'ROLE_ADMIN'){
            rol = Rol.findByAuthority('ROLE_ADMIN')
        }
        UsuarioRol.create(usuarioInstance, rol , true)
        flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
        redirect(action: "show", id: usuarioInstance.id)
    }

    def show() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    def edit() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    def update() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (usuarioInstance.version > version) {
                usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                    [message(code: 'usuario.label', default: 'Usuario')] as Object[],
                          "Another user has updated this Usuario while you were editing")
                render(view: "edit", model: [usuarioInstance: usuarioInstance])
                return
            }
        }

        usuarioInstance.properties = params

        if (!usuarioInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioInstance: usuarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
        redirect(action: "show", id: usuarioInstance.id)
    }

    def delete() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        try {
            usuarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
