enum class Nivel(val peso : Int) { 
    BASICO(1), 
    INTERMEDIARIO(2), 
    AVANCADO(3) }

data class Usuario(val nome : String);

data class ConteudoEducacional(val nome: String, val nivel : Nivel, val duracao: Int = 60){
}

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    // De lista para Set pois um mesmo usuário não pode estar duas vezes matriculado no mesmo curso
    val inscritos = mutableSetOf<Usuario>();
    
    //Nivel da formação =  maior nível dos conteúdos
    val nivel : Nivel
    	get() { return conteudos.maxByOrNull { it.nivel.peso }?.nivel ?: Nivel.BASICO }
    
    val duracao : Int
    	get() { return conteudos.map {it.duracao}.sum() }
    
    fun matricular(vararg usuarios: Usuario) {
        usuarios.forEach{
            inscritos.add(it);
        	println("${it.nome} foi matriculado na formação ${nome}")
        }   
    }
}

fun main() {
	val modulo1 = ConteudoEducacional("Criando um projeto em React", Nivel.BASICO)
	val modulo2 = ConteudoEducacional("Criando compentes efetivos com useEffect", Nivel.INTERMEDIARIO, 90)
    val formacaoReact = Formacao("React", listOf(modulo1,modulo2));
    
    val user1 = Usuario("Henrique");
    val user2 = Usuario("Francisco");
    val user3 = Usuario("Wedma");
    
    formacaoReact.matricular(user1);
    formacaoReact.matricular(user2, user3);
    
    println(formacaoReact.inscritos);
    println(formacaoReact.nivel);
    println(formacaoReact.duracao);
}
