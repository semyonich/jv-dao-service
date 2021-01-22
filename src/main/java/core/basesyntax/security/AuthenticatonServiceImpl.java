package core.basesyntax.security;

import core.basesyntax.exception.AuthenticationException;
import core.basesyntax.lib.Inject;
import core.basesyntax.lib.Service;
import core.basesyntax.model.Driver;
import core.basesyntax.service.DriverService;

@Service
public class AuthenticatonServiceImpl implements AuthenticationService {
    @Inject
    private DriverService driverService;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Driver driver = driverService.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException("Login incorrect"));
        if (driver.getPassword().equals(password)) {
            return driver;
        }
        throw new AuthenticationException("Password incorrect");
    }
}
