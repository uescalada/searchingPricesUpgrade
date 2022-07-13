SearchingPrices
###############

This was really a challenging excercise.

I have decided, in addition owas orginaly requested in the test,
add Swagger plugin to enhance visualziation of the Rest microservices
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/price-controller



Microservices REST.
###################

PriceController Class:
###################################################################################
# This one return the String of the Price requeste

@Operation(summary = "Obtain String Data Price by productId & brandId & dateValue")
PriceController.searchByProductIdAndBrandIdAndDateString(
	@PathVariable("productId") int productId,
	@PathVariable("brandId") int brandId, 
	@PathVariable("dateValue") String dateValue)

###############################
# This one return a Price Object

@Operation(summary = "Obtain Object Data Price by productId & brandId & dateValue")
public ResponseEntity<List<Price>> searchByProductIdAndBrandIdAndDate(
	@PathVariable("productId") int productId,
	@PathVariable("brandId") int brandId, 
	@PathVariable("dateValue") String dateValue)

###################################################################################

PriceRepository Class:
###################################################################################
@Operation(summary = "Obtain String Data Price by productId & brandId & dateValue")
/**
* Find top 1 prices by product id and brand id and start date less than and end
* date greater than order by priority asc.
* 
* @param productId the product id
* @param brandId   the brand id
* @param startDate the start date
* @param endDate   the end date
* @return the list
*
*/
PriceRepository.findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanOrderByPriorityAsc(
	int productId, 
	int brandId, 
	Date startDate, 
	Date endDate);

############################################################################################
# JUnit  - PriceRepositoryTest
##############################

-Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

/**
* Find top 1 prices by product id and brand id and start date less than and end
* date greater than test 5.
* 
* Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1
* 
* @throws ParseException the parse exception
*/
void findTop1PricesByProductIdAndBrandIdAndStartDateLessThanAndEndDateGreaterThanTest5()

############################################################################################
# JUnit  - PriceControllerTest
##############################

-Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

/**
* Search by product id and brand id and date string test 5.
* 
* Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1
* The result expected is 35.50 -> True
* 
* @throws ParseException the parse exception
*/
void searchByProductIdAndBrandIdAndDateStringTest5()

#####################################################################################

I have added another methods:
-list Prices
-Search by Id
-delete  price by id
-create new price

############################################################################################

En cuanto al diseño y construcción de este ejercicio:
Además del modelo MVC como se vio en el ejemplo codificado, utilizando
Spring Boot + arquitectura Cloud ( Spring Cloud)
Donde se crearía un API Gateway como una principal punto de acceso
De esta manera se tiene un control de las entradas, y en base a los llamados recibidos 
se dirige al servicio REST correspondiente/necesario.


Eso sería como primer punto, luego estaría la parte del servidor Eureka, el cual va a proporcionar 
el manntenimiento de un registro de todos los microservicios disponibles en el sistema.
Ya que es una gran aplicacion web que va trabajar con diferentes componenentes que colaboran entre sí, deben
poder saber que microservicios están disponibles.
Además, se puede conocer si hay instancias disponibles, levantar si hace falta o bien, rechazarla 
por super la quota de acceso.

