import java.util.ArrayList

fun main() {
    Ejecutable()
}

class Ejecutable {
    private var listaIngredientes : ArrayList<Ingredientes> = ArrayList<Ingredientes>()
    private var listaBases : ArrayList<BasePizza> = ArrayList<BasePizza>()
    private var listaPedidos : ArrayList<Pedido> = ArrayList<Pedido>()

    init {
        generaBase()
        generaIngredientes()
        menu()
    }

    private fun menu() {
        println("Pulse la opcion indicada de lo que desea hacer")
        var opcion: Int
        do{
            println("0.- Salir")
            println("1.- Nuevo Pedido")
            println("2.- Mostrar Pedido")
            println("3.- Listar pedidos")
            println("4.- Mostrar un pedido en concreto")
            println("5.- Total de pedidos")
            opcion= readln().toInt()
        when(opcion){
            1->creaPedido()
            2-> println(listaPedidos.last())
            3-> for((index,elemento) in listaPedidos.withIndex()) println("Pedido #${index+1} \n $elemento \n")
            4-> {println("Indique el numero de pedido que desea consultar")
                println(listaPedidos.get(readln().toInt()-1))}
            5-> totalPeddos()
        }
        }while (opcion!=0)
    }

    private fun totalPeddos() {
        for((index,elemento) in listaPedidos.withIndex()) println("Pedido #${index+1} \n $elemento \n")
        var total=0.0
        listaPedidos.forEach { total+=it.costePedido() }
        println("El total de pedidos del dia es: $total")
    }

    private fun creaPedido() {
        var masPizzas:Int
        val unPedido=ArrayList<Pizzas>()
        do{
            unPedido.add(creaPizza())
            println("Si desea mas pizzas pulse 0 \n En caso contrario cualquier otra tecla")
            masPizzas= readln().toInt()
        }while(masPizzas==0)
        var valorPedido=0.0
        unPedido.forEach{valorPedido+=it.costePizza()}
        unPedido.forEach { println("$it \n") }
        println("El total del pedido es: $valorPedido")
        println("Si desea cancelar el pedido pulse n en caso contrario cualquier tecla")
        if(readln()!="n")listaPedidos.add(Pedido(unPedido))
    }


    private fun creaPizza() :Pizzas{
       val listaParaPizza=ArrayList<Ingredientes>()
        println("Indique el numero de la base de pizza que desea")
        for((index,elemento) in listaBases.withIndex()) println("$index para la base $elemento")
        var baseP=0
        var c =true
        while(c){
            baseP=readln().toInt()
            if(baseP>listaBases.size || baseP<0) println("Un numero que coincida con el de la base por favor")  else c=false
        }
        val base=listaBases.get(baseP)
        var deseaMas=""
        do {
            for ((index, elemento) in listaIngredientes.withIndex()) println("$index para la base $elemento")
            var ingre = 0
             c = true
             while (c) {
               ingre = readln().toInt()
                if (ingre > listaIngredientes.size || ingre < 0) println("Un numero que coincida con el de la base por favor")
                    else c =false
             }
            listaParaPizza.add(listaIngredientes.get(ingre))
            println("Si desea añadir otro ingrediente 0 sino cualquier tecla")
            deseaMas= readln()
         }while(deseaMas.equals("0"))
        return Pizzas(base,listaParaPizza)
    }

    private fun generaIngredientes() {
        listaIngredientes.add(Ingredientes("Sin ingredientes",0.0))
        listaIngredientes.add(Ingredientes("Queso",0.5))
        listaIngredientes.add(Ingredientes("Mozzarella",0.5))
        listaIngredientes.add(Ingredientes("Gorgonzola",0.5))
        listaIngredientes.add(Ingredientes("Chedar",0.5))
        listaIngredientes.add(Ingredientes("4 Quesos",1.75))
        listaIngredientes.add(Ingredientes("Extra tomate",0.5))
        listaIngredientes.add(Ingredientes("Barbacoa",5.0))
        listaIngredientes.add(Ingredientes("Atun",1.5))
        listaIngredientes.add(Ingredientes("Carne",1.5))
        listaIngredientes.add(Ingredientes("Salchicha",1.5))
        listaIngredientes.add(Ingredientes("Pollo",1.0))
        listaIngredientes.add(Ingredientes("Chorizo",0.75))
    }

    fun generaBase() {
        listaBases.add(BasePizza("Basica", 6.0))
        listaBases.add(BasePizza("Esponjosa", 7.0))
        listaBases.add(BasePizza("Rellena", 8.0))
        listaBases.add(BasePizza("Esponjosa y Rellena", 10.0))
    }
}
class Ingredientes (val nombreIngredientes: String, var precio :Double){
    override fun toString(): String {
        return " Ingrediente $nombreIngredientes precio: $precio "
    }
}

class BasePizza (val nombreBase: String,  var precio :Double) {
    override fun toString(): String {
        return "Base $nombreBase precio: $precio "
    }
}

class Pizzas(val base: BasePizza, val ingrediente: ArrayList<Ingredientes>) {
    fun costePizza() :Double{
        var valor = 0.0
        ingrediente.forEach { valor+=it.precio }
        valor+= base.precio
        return valor
    }

    override fun toString(): String {
        var ingr=""
        ingrediente.forEach { ingr+=" $ingrediente \n" }
          return "Pizza $base  \n" + ingr +"El coste de la pizza es: ${costePizza()}"
    }
}

class Pedido (val pizza:ArrayList<Pizzas>){

    fun costePedido():Double{
        var valorPedido=0.0
        pizza.forEach{valorPedido+=it.costePizza()}
        return valorPedido
    }

    override fun toString(): String {
        var imprPedido=""
            pizza.forEach{ imprPedido+="${it.base} \n ${it.ingrediente} \n${it.costePizza()}\n" }
        return imprPedido+"\nEl coste total es: ${costePedido()}"
    }
}
