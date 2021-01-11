package core.basesyntax;

import core.basesyntax.lib.Injector;
import core.basesyntax.model.Manufacturer;
import core.basesyntax.service.ManufacturerService;

public class Application {
    private static final String PACKAGE_NAME = "core.basesyntax";
    private static Injector injector = Injector.getInstance(PACKAGE_NAME);

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);
        manufacturerService.create(new Manufacturer("Daewoo", "Ukraine"));
        manufacturerService.create(new Manufacturer("Tesla", "USA"));
        System.out.println("Manufacturers list: " + manufacturerService.getAll());
        System.out.println("Manufacturer with id=1: "
                + manufacturerService.get(Long.valueOf(1)));
        Manufacturer newManufacturer = new Manufacturer("Toyota", "Japan");
        newManufacturer.setId(Long.valueOf(1));
        System.out.println("Updated manufacturer with id=1: "
                + manufacturerService.update(newManufacturer));
        System.out.println("New manufacturer list: " + manufacturerService.getAll());
        System.out.println("Deleting manufacturer with id=2: "
                + manufacturerService.delete(Long.valueOf(2)));
        System.out.println("Manufacturer list after deleting id=2: "
                + manufacturerService.getAll());
    }
}
