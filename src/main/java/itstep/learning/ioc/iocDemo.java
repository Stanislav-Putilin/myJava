package itstep.learning.ioc;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import itstep.learning.services.hash.HashService;

import java.util.logging.Logger;

public class iocDemo
{
    private final HashService digestHashService;
    private final HashService signatureHashService;
    private  final String appName;
    @Inject private Logger logger; //не рекомендуется

    @Inject
    //рекомендуется
    public iocDemo(
            @Named("digest") HashService digestHashService,
                   @Named("signature")HashService signatureHashService,
                   @Named("appName") String appName) {
        this.digestHashService = digestHashService;
        this.signatureHashService = signatureHashService;
        this.appName = appName;
    }

    public void run()
    {
        System.out.println(appName);
        System.out.println(digestHashService.digest("123"));
        System.out.println(signatureHashService.digest("123"));
        logger.info(appName);
    }
}
