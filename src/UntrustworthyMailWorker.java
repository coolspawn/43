public class UntrustworthyMailWorker  implements MailService{

    private final MailService[] mails;
    public UntrustworthyMailWorker(MailService[] mails) {

        this.mails = mails;
    }

    public RealMailService  getRealMailService() {
        RealMailService rms = new RealMailService();
        return rms;
    }


    @Override
    public Sendable processMail(Sendable mail) {
        Sendable res = mails[0].processMail(mail);
        for (int i=1; i<mails.length;i++){
            Sendable temres = mails[i].processMail(res);
            res = temres;
        }
        RealMailService rms = getRealMailService();
        return rms.processMail(res);
    }
}
