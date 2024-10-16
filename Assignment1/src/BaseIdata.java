public class BaseIdata implements IIdata{

    protected MainWork mainWorkEnum;
    protected String mainWorkString;

    @Override
    public MainWork getMainWork() {
        return mainWorkEnum;
    }

    @Override
    public String getDepartment() {
        return mainWorkString;
    }
}
