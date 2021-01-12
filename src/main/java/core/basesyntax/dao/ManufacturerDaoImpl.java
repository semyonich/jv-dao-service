package core.basesyntax.dao;

import core.basesyntax.db.Storage;
import core.basesyntax.lib.Dao;
import core.basesyntax.model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (Storage.manufacturers.get(i).getId().equals(manufacturer.getId())) {
                Storage.manufacturers.set(i, manufacturer);
                return Storage.manufacturers.get(i);
            }
        }
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(i -> i.getId().equals(id));
    }
}
