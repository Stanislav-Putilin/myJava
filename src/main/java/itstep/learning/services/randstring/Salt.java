package itstep.learning.services.randstring;

import com.google.inject.Inject;
import itstep.learning.services.hash.HashService;

import java.util.UUID;

public class Salt implements RandStringService
{
    private final HashService hashService;

    @Inject
    public Salt(HashService hashService) {
        this.hashService = hashService;
    }

    @Override
    public String getRandString()
    {
        return hashService.digest(UUID.randomUUID().toString()).substring(0,20);
    }
}
