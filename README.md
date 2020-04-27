# TP-Ahorcado
(Diferencia entre Runnable y Thread)
-El punto principal es evadir el extends, ya que java no permite herencia multiple.
(Ciclo de vida de un Thread)
-New Thread, este se crea pero no se inicializa. -> Luego llamando al start comienza a ejecutarse, pero puede verse bloqueado en la ejecucion, el cual lleva a que no se le asigne tiempo de cpu.
 En cambio si este no entra a bloqueado, se veria en un futuro muerto debido a la finalizacion del metodo run(). 
(Diferencia entre los metodos)
Start() = Cambia la instacia a Running del hilo.
Sleep() = Pausa la ejecucion del hilo en milisegundos.
Yield() = Pausa la ejecucion del hilo y deja que otros hilos se ejecuten
Join() = Hace que no se cierre el programa hasta que el hilo muera. 