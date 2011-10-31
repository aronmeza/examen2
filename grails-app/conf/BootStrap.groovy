class BootStrap {

//    def springSecurityService

    def init = { servletContext ->
//        log.info("Inicializando aplicacion")
//
//        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        def rolVendedor = general.Rol.findByAuthority('ROLE_VENDEDOR')
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (general.Rol.count() != 3 ){
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            
            if (!rolVendedor) {
                rolVendedor = new general.Rol(authority: 'ROLE_VENDEDOR').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        }
//
//        log.info "Validando usuarios"
        
        if (!admin) {
            admin = new general.Usuario(
                username:'admin',
                password:'admin',
                enabled: true
            )
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }
//
//        log.info("Aplicacion inicializada")
    }

    def destroy = {
    }
}
