package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.ShaHashService;
import itstep.learning.services.randstring.*;

public class RandModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(RandStringService.class).annotatedWith(Names.named("TemporaryPassword")).to(TemporaryPassword.class);
        bind(RandStringService.class).annotatedWith(Names.named("NameFile")).to(NameFile.class);
        bind(RandStringService.class).annotatedWith(Names.named("Salt")).to(Salt.class);
        bind(RandStringService.class).annotatedWith(Names.named("PermanentPassword")).to(PermanentPassword.class);

        bind(HashService.class).toInstance(new ShaHashService());

        super.configure();
    }
}
