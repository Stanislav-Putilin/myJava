package itstep.learning.ioc;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import itstep.learning.services.randstring.RandStringService;

public class RandDemo
{
    private final RandStringService temporaryPassword;
    private final RandStringService nameFile;
    private final RandStringService salt;
    private final RandStringService permanentPassword;

    @Inject
    public RandDemo(@Named("TemporaryPassword") RandStringService temporaryPassword,
                    @Named("NameFile") RandStringService nameFile,
                    @Named("Salt") RandStringService salt,
                    @Named("PermanentPassword") RandStringService permanentPassword) {
        this.temporaryPassword = temporaryPassword;
        this.nameFile = nameFile;
        this.salt = salt;
        this.permanentPassword = permanentPassword;
    }

    public void run()
    {
        System.out.println("Name File: " + nameFile.getRandString());
        System.out.println("Salt: " + salt.getRandString());
        System.out.println("Temporary Password: " + temporaryPassword.getRandString());
        System.out.println("Permanent Password: " + permanentPassword.getRandString());
    }
}