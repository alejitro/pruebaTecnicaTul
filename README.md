# Prueba tecnica Tül

Se solicita desarrollar prueba tecnica con los siguientes requerimientos

La prueba consiste en un Ecommerce sencillo, su trabajo consiste en realizar el módulo correspondiente al carrito de compras, este módulo cuenta con los siguientes requerimientos
1.	Un producto debe contener nombre, sku, descripción y precio.
2.	Existen dos tipos de productos, el producto simple y el producto con descuento. El precio del producto con descuento es el ingresado divido por 2
3.	Un carrito debe contener la lista de los productos asociada a él, cada producto en el carrito debe tener su cantidad correspondiente (ej: Pintura 6 unidades, cemento 3 unidades, etc) y un estado, pendiente o completado según sea el caso.
4.	Los identificadores de las entidades deben ser un UUID
5.	El manejo de los estados debe hacerse con enumerables

Las funcionalidades esperadas son las siguientes:
*     Lista de todos los productos
*     Agregar, eliminar y modificar productos
*     Lista de todos los productos de un carrito
*     Agregar, eliminar y modificar productos al carrito
*     Checkout, debe devolver el costo final de los productos del carrito y cambiar su estado a completado.
 
Tecnología requerida
*     Spring 2.3.4 Kotlin

# Desarrollo

La prueba técnica se desarrolla de acuerdo a los requerimientos usando un repositorio relacional JPA y una base de
datos H2 para correr en memoria.

Las credenciales de acceso a la base se encuentran en el archivo application.yml

Se accede a la base levantando el proyecto y accediendo a http://localhost:8080/marketplace-console

El proyecto tiene implementado swagger para prueba de los respectivos endpoints
se accede a este a traves de: http://localhost:8080

Asi mismo se hacen test unitarios de las funcionalidades.