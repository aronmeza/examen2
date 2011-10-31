package general

class Usuario {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
        
        static hasMany = [autosComprados: clase.Automovil]

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}
        
        String getRol() {
		def usuarioRol = UsuarioRol.findByUsuario(this)
                return usuarioRol.rol.authority
                
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
