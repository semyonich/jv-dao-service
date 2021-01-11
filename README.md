"# jv-dao-service" 
Create your own repository - video. Make sure you are creating a new branch before start working.
Create models: Car, Driver and Manufacturer. Use UML diagram (models) for this, see below.
Create DAO and service layer for Manufacturer model. Below you can see the list of required methods.
Add CRUD operations into ManufacturerDao.
Use Storage class as a persistence layer.
Do not forget to use your own annotation Dao.
Use some static variable that will incrementally generate you an id for each model
Return Optional when you can return null in DAO. For example: public Optional<Manufacturer> get(Long id);
Add new injector to your project.
Add class Application with main method where you are invoking all your methods from service
In the main method create instance of manufacturer service and call CRUD methods. It may look like:
public class Main {
  private static final Injector injector = Injector.getInstance("YOUR_PACKAGE");

  public static void main(String[] args) {
      ManufacturerService manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
      Manufacturer manufacturer = new Manufacturer();
      // initialize field values using setters or constructor
      manufacturerService.save(manufacturer);
      // same for all other crud methods and for all models
  }
}
Java classes structure:
Manufacturer
public class Manufacturer {
  private Long id;
  private String name;
  private String country;
}
Driver ```java
import java.util.List;

public class Driver { private Long id; private String name; private String licenseNumber; // keep in mind, one driver can drive several cars per day // but we do not wan’t to store a list of cars in a Driver class }

- Car
```java
import java.util.List;

public class Car {
    private Long id;
    private String model;
    private Manufacturer manufacturer;
    private List<Driver> drivers;
}
ManufacturerDao methods:
- Manufacturer create(Manufacturer manufacturer);
- Optional<Manufacturer> get(Long id);
- List<Manufacturer> getAll();
- Manufacturer update(Manufacturer manufacturer);
- boolean delete(Long id);
ManufacturerService methods:
- Manufacturer create(Manufacturer manufacturer);
- Manufacturer get(Long id);
- List<Manufacturer> getAll();
- Manufacturer update(Manufacturer manufacturer);
- boolean delete(Long id);
