import com.example.vitalisapp.View.Usuario.TipoUsuario

object ObjectPersonal {
    var nome: String? = null
    var nickname: String? = null
    var cpf: String? = null
    var dtNasc: String? = null
    var senha: String? = null
    var sexo: String? = null
    var email: String? = null
    var tipo: TipoUsuario? = null
    var academiaId: Int? = null

    // Método para configurar todos os atributos
    fun inicializar(
        nome: String?,
        nickname: String?,
        cpf: String?,
        dtNasc: String?,
        senha: String?,
        sexo: String?,
        email: String?,
        tipo: TipoUsuario?,
        academiaId: Int?
    ) {
        this.nome = nome
        this.nickname = nickname
        this.cpf = cpf
        this.dtNasc = dtNasc
        this.senha = senha
        this.sexo = sexo
        this.email = email
        this.tipo = tipo
        this.academiaId = academiaId
    }
}
