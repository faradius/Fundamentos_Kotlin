package com.pdhn.introduccionkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception

typealias aliasObjeto = SubClasses.Anidada
typealias aliasDato = MutableMap<Int, ArrayList<String>>
typealias aliasFuncion = (a:Int, b:Int) -> Int

class MainActivity : AppCompatActivity() {
    //Objeto anonimo
    object fernanda{
        var apodo ="fer"
        fun saludando(){ println("Hola, me llaman $apodo")}
    }

    companion object{
        const val moneda = "EUR" //const val alamcena una constante
    }

    var saldo : Float = 300.54f
    var sueldo = 764.82f
    var entero : Int = 62


    private fun calculadora(n1: Int, n2: Int, fn: (Int, Int) -> Int) : Int{
        return fn(n1, n2)
    }

    private fun suma(x: Int, y: Int): Int { return x+y }
    private fun resta(x: Int, y: Int): Int { return x-y }
    private fun multiplica(x: Int, y: Int) = x*y
    private fun divide(x: Int, y: Int) = x/y


    private fun String.noSpaces(): String{
        return this.replace(" ", "")
    }

    //fun Clase.Nombrefuncion(.....){......}
    //Es bueno usar este tipo de funciones de extencion cuando son clases del propio android o Kotlin
    //ya que no queremos manipular el codigo que ya esta determinado en el lenguaje
    private fun IntArray.show(){
        print("[")
        for(i in this) print("$i ")
        println("]")
    }

    private fun inColombia(h:Float):Boolean{
        return h>=1.6f
    }

    private fun inSpain(h:Float):Boolean{
        return h>=1.65f
    }

    //No es correcto usar este tipo de funciones de orden superior con extencion cuando nosotros creamos la clase
    //es mejor utilizarlo como es habitual que es declarar la función en la clase person
    private fun Person.checkPolice(fn: (Float)->Boolean): Boolean{
        return fn(height)
    }

    //en fn siempre hay que mantener la estructura de una lambda que es parametro flecha y valor
    //pero en este caso no queremos que devuelva algo y no solo se puede poner de esta forma fn:(Int)
    //ya que marca error por lo que se debe de poner la estructura fn:(Int)->Unit el Unit se pone cuando
    //no hay un tipo de dato a devolver es como si fuera un metodo void
    private fun recorrerArray(array: IntArray, fn: (Int)-> Unit){
        //Reccorre cada uno de los elementos que contenga el array
        for(i in array)
            fn(i) //i es el valor de cada una de las posiciones que contenga el array
    }

    private fun value_try(a: Int, b:Int): Any{
        var res =
            try {
                println("División $a/$b") //este es el cuerpo de la lambda
                a/b //debe llevar un valor como las lambdas
            }catch (e: Exception){
                println("Vamos a manejar este error")
                "División no permitida"
            }
        return res
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var usuario = "   soy     yo     "
        println("${usuario} (${usuario.length}) - ${usuario.noSpaces()} (${usuario.noSpaces().length})")

        var array1 : Array<Int> = arrayOf(5,4,6,7)
        //No se puede en este array por que no es un IntArray, no es lo mismo Array<Int> al IntArray
        //println("array1: "); array1.show();

        var array2 = IntArray(3)
        array2[0] = 10
        array2[1] = 20
        array2[2] = 30
        println("array2: "); array2.show();

        var array3: IntArray = intArrayOf(1,2,3,4,5)
        println("array3: "); array3.show();


        val fecha = "01/01/1990" //val almacena datos que no pueden modificarse - Uso (al obtener un DNI)
        //           0123456789
        var nombre = "Alex"  //var almacena datos que pueden modificarse o actulizarse
        var vip: Boolean = true
        var inicial: Char = 'J'


        //var saludo = "Hola " + saldo.toString()
        //nombre = "adrian"
        var saludo = "Hola " + nombre
        println(saludo)

        if(vip == true){
            saludo+= ", te queremos mucho"
            println("se ejecuta el print del if primero")
            //si hay varias lineas de codigo se utiliza las llaves y si es es una sola linea no es necesario
        }
        else saludo += ", quieres ser vip? paga la cuota"
        mostrar_saldo()
        var dia = fecha.subSequence(0,2).toString().toInt()
        if (dia == 1) ingresar_sueldo()

        var mes = fecha.subSequence(3,5).toString().toInt()
        when(mes){
            1, 2, 3 -> println(" En invierno no hay ofertas de inversiones")
            4, 5, 6 -> println(" En primavera hay ofertas de inversiones con el 1.5% de interes")
            7, 8, 9 -> println(" En verano hay ofertas de inversiones con el 2.5% de interes")
            10, 11, 12 -> println(" En otoño hay ofertas de inversiones con el 3.5% de interes")
            else -> print (" La fecha es erronea \n")
        }

        println(saludo)

        println("La suma de 80 y 20 es ${calculadora(80, 20, ::suma)}")
        println("La resta de 50 y 10 es ${calculadora(50, 10, ::resta)}")
        println("El producto entre 7 y 7 es ${calculadora(7, 7, ::multiplica)}")
        println("La división entre 12 y 3 es ${calculadora(12, 3, ::divide)}")
        println("  ")
        println("  ")

        //LAMBDAS
        var funcion = { x: Int, y: Int -> x+y }
        println("La suma de 80 y 20 con variable es ${calculadora(80, 20, funcion)}")
        funcion = { x: Int, y: Int -> x-y }
        println("La resta de 50 y 10 con variable es ${calculadora(50, 10, funcion)}")

        //el parametro esta dentro de los parentesis y la función es anonima
                                                                            //cabecera - Flecha - Valor, no un return
        println("La suma de 80 y 20 con lambda es ${calculadora(80,20, { x: Int, y: Int -> x+y })}")
        //el parametro esta fuera de los parentesis
        println("La resta de 50 y 10 con lambda es ${calculadora(50,10) { x: Int, y: Int -> x - y }}")

                                                                                    //cabecera - flecha
        println("La potencia de 2 elevado a 5 con lambda es ${calculadora(2,5) { x, y ->
            // - cuerpo
            var valor = 1
            for (i in 1..y) valor *= x
            // - valor
            valor
        }
        }")
        //IntArray es una funcion de orden superior en donde recibe un parametro que indica el tamaño del array
        //y otro parametro que se le puede indicar una funcion
        var array4= IntArray(10){5}
        println("array4: "); array4.show();

        //La palabra reservada it va a colocar en cada posición del array el valor del indice
        var array5= IntArray(10){it}
        println("array5: "); array5.show();

        var array6= IntArray(10){it*2}
        println("array6: "); array6.show();

        var array7= IntArray(10){i -> i*3}
        println("array7: "); array7.show();

        var suma = 0
        recorrerArray(array7){
            suma += it
        }
        println("La suma de todos los elementos del array7 es $suma")

        println("  ")
        println("  ")


        var pin = 1234
        var intentos = 0

        var clave_ingresada: Int = 1232

        do{
            println("Ingrese el PIN: ")
            println("Clave ingresada: ${clave_ingresada++}")
            intentos++
        }while (intentos<3 && clave_ingresada!=pin)

        if(clave_ingresada == pin) {
            println("La clave: $clave_ingresada, es correcta")
        }else if (intentos==3) println("La contraseña es incorrecta la tarjeta se ha bloqueado")

        mostrar_saldo()
        ingresar_dinero(80.5f)
        retirar_dinero(40f)
        retirar_dinero(50f)
        retirar_dinero(2000f)

        var recibos: Array<String> = arrayOf("luz", "agua", "gas")
        recibos.set(2,"internet")
        recorrer_array(recibos)

        var matriz = arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(4,5,6),
            intArrayOf(7,8,9,10,11,12,13)
        )

        for(i in (0 until matriz.size)){ // es similar al for que dice array.size -1 pero no es necesario poner el -1 por que con el until no lo toma en cuenta
            println()
            for(j in (0 until matriz[i].size))
                println("Posición[$i][$j] : ${matriz[i][j]}") //La palabra matriz[i][j] hace que imprima el valor en esa posición
        }

        //Collección Inmutable, es decir que no se puede modificar
        var clientesVIP: Set<Int> = setOf(1234, 5678, 4040)
        val setMezclado = setOf(2,4.56,"casa",'c')

        println("Clientes VIP: \n")
        println(clientesVIP)
        println("Número de clientes VIP: ${clientesVIP.size}")

        if (clientesVIP.contains(1234)) println("1234 es VIP")
        if (clientesVIP.contains(1235)) println("1235 es VIP")

        //Colleción Mutable, es decir se puede modificar
        var clientes: MutableSet<Int> = mutableSetOf(1234,5678,4040,8970)
        println("Clientes:")
        println(clientes)

        clientes.add(3026)
        println(clientes)

        clientes.remove(5678)
        println(clientes)

        clientes.clear()
        println(clientes)

        println("Numero de clientes: ${clientes.size}")

        var divisas: List<String> = listOf("USD","EUR","YEN")
        println(divisas)

        var bolsa: MutableList<String> = mutableListOf("Coca-Cola", "Adidas", "Amazon", "Pfizer")
        println(bolsa)

        bolsa.add("Adobe")
        println(bolsa)

        bolsa.add("Nvidia")
        println(bolsa)

        bolsa.removeAt(2)
        println(bolsa)

        println(bolsa.first())
        println(bolsa.last())
        println(bolsa.elementAt(2))
        println(bolsa.none())//preguntamos si hay algo en la lista

       // bolsa.clear()
       println(bolsa)

       // println(bolsa.none())

        var mapa: Map<Int,String> = mapOf(
            1 to "España",
            2 to "México",
            3 to "Colombia"
        )

        println(mapa)

        var inversiones = mutableMapOf<String,Float>()
        inversiones.put("Coca-Cola", 50f)
        println(inversiones)

        var empresa: String? = null
        mostrar_saldo()

        var cantidad_a_invertir =300f
        var index = 0

        while (saldo >= cantidad_a_invertir){
            empresa = bolsa.elementAtOrNull(index)
            if (empresa != null){
                saldo -= cantidad_a_invertir
                println("Se ha invertido $cantidad_a_invertir $moneda en $empresa")
                inversiones.put(empresa, cantidad_a_invertir)
            }else break

            index++
        }
        mostrar_saldo()
        println(inversiones)

        println("  ")
        println("  ")
        println("Aqui Empieza la Programación Orientada a Objetos")
        println("  ")

        var num: Int = 0
        var jota: Person = Person("Jota", "A8594DF5", 1.62f)
        var anonimo: Person = Person()
        println(jota.alive)
        println(jota.name)
        println(jota.passport)
        println("FUNCION DE ORDEN SUPERIOR EN OBJETOS <--------------")
        if (jota.checkPolice(::inColombia)) println("${jota.name} puede ser Policia en Colombia")
        if (jota.checkPolice(::inSpain)) println("${jota.name} puede ser Policia en España")

        anonimo.Person()
        anonimo.name = "Pablo"
        println(anonimo.alive)
        println(anonimo.name)
        println(anonimo.passport)

        jota.die()
        println(jota.alive)

        var bicho:Pokemon = Pokemon()
        println(bicho.getName())
        println(bicho.getAttackPower())
        bicho.setLife(50f)
        println(bicho.getLife())

        var pele:Athlete = Athlete("Pele","ASD659D6", "Futbol")
        println("Creando Atleta")
        println(pele.alive)
        println(pele.name)
        println(pele.passport)
        println(pele.sport)

        pele.die()
        println(pele.alive)

        //Implementación de Sub Clases no tiene nada que ver con polimorfismo o herencia

        var sc = SubClasses()
        println(sc.presentar())

        var anid = SubClasses.Anidada()
        anid.presentar()

        //Alias
        println("-------->ALIAS<------ ")
        var anidad = aliasObjeto()
        anidad.presentar()

        var saludoss: aliasDato = mutableMapOf()
        saludoss[0] = arrayListOf("Hola", "Adios")
        saludoss[1] = arrayListOf("Hi", "Bye")

        //Recorre el array y lo descompone para extraer cada uno de los datos
        for ((id, palabras) in saludoss)
            println("$id, $palabras")

        //------FIN ALIAS
        var inn = SubClasses().Interna()
        println(inn.presentar("Hola a todos!"))

        fernanda.saludando()
        fernanda.apodo = "SuperFer"
        fernanda.saludando()

        /*Se especifican los tipos de errores que quieren que se tranten o se capturen
        * NullPointerException
        * AritmeticException
        * SecurityException
        * ArrayIndexOutOfBoundException
        *
        * Este es un tipo de error que clasifica a todos los tipos de errores (Atrapa cualquier error)
        * Exception
        * */

        println("TRY CATCH _________")
        try{
            println("División 5/0 = ${5/0}")
        }catch (e:Exception){
            println("Vamos a manejar este error")
        }finally {//el finally siempre se va a ejecutar pase lo que pase
            println("Pase lo que pase vamos hacer cositas")
        }


        //dataclass = Solo almacena datos preferentemente no almacenar funciones
        var sol:Star = Star("Sol",696340f, "Via Láctea")
        println(sol)

        //DESTRUCTURADA
        var (name_star2, radius_star2, galaxy2) = Star("Luna", 65233369f, "Via Láctea2")
        println("Datos Star2 Destructurada: $name_star2, $radius_star2, $galaxy2")

        var (name_star3, radius_star3) = Star("Luna3", 65233369f, "Via Láctea3")
        println("Datos Star3 Destructurada: $name_star3, $radius_star3")

        var (name_star4, _ ,galaxy4) = Star("Luna4", 65233369f, "Via Láctea4")
        println("Datos Star4 Destructurada: $name_star4, $galaxy4")

        var componente = Star("Luna5", 78744f, "Via Láctea5")
        println("Datos Star5 por componentes: ${componente.component1()}, ${componente.component2()}, ${componente.component3()}")

        var betelgeuse: Star = Star("Betelgeuse", 6171000f, "Orion")
        betelgeuse.alive = false
        println(betelgeuse.alive)

        var nueva: Star = Star()
        println(nueva)

        //enumclass = Son constantes y puede tener funciones
        var hoy: Dias = Dias.LUNES
        var semana = Dias.values()
        for (i in semana) println(i)

        println(Dias.valueOf("MIERCOLES")) //muestra el valor del dia Miercoles
        println(hoy.name) //Imprime el valor almacenado
        println(hoy.ordinal) //Imprime la posicion en que se encuentra el valor

        println(hoy.saludo()) //Va a devolver lo que hay en el dia lunes de acuerdo al when
        println(hoy.laboral) //imprime si es laboral el dia lunes
        println(hoy.jornada) //imprime las horas laborales de lunes

        hoy = Dias.DOMINGO
    }

    fun mostrar_saldo(){
        println("Tienes $saldo $moneda")
    }
    fun ingresar_sueldo(){
        saldo += sueldo
        println("Se ha ingresado tu sueldo de $sueldo $moneda")
        mostrar_saldo()
    }

    fun ingresar_dinero(cantidad:Float){
        saldo+=cantidad
        println("Se ha ingresado $cantidad $moneda")
        mostrar_saldo()
    }

    fun retirar_dinero(cantidad:Float){
        if (verificarCantidad(cantidad)){
            saldo-=cantidad
            println("Se ha hecho una retirada de $cantidad $moneda")
            mostrar_saldo()
        }else{
            println("Cantidad superior al saldo. Imposible de realizar la operación")
        }

    }

    fun verificarCantidad(cantidad_a_retirar:Float): Boolean{
        if (cantidad_a_retirar>saldo) return false
        else return true
    }

    /*
    * fun vereficarCantidad(cantidad_a_retirar:Float): Boolean{
    *   return cantidad_a_retirar <= saldo
    * }
    * */

    fun recorrer_array(array: Array<String>){
        for (i in array)//para cada elemento (i) dentro (in) del array (array)
            println(i)

        for (i in array.indices)
            println(array.get(i))

        /*el tamaño del array son 3 elementos y el indice maximo es 2 por lo tanto se pone el -1
        * por que si no se pone sale tamaño=3 e indice = 3 y es un error, ya que con el -1
        * sale tamaño=3 e indice = 2 lo cual de esta forma es correcta*/
        for (i in 0 .. array.size -1)
            println("${i+1}: ${array.get(i)}")

        println("Array con until")
        for(i in 0 until array.size)
            println("Posición $i: ${array[i]}")

        println("Until con parentesis - es indiferente si están o no los parentesis")
        for(i in (0 until array.size))
            println("Elemento ${i+1}: ${array[i]}")
    }

    /*fun verificarCantidad(cantidad_a_retirar:Float): Boolean{
        return cantidad_a_retirar <= saldo
    }*/
}