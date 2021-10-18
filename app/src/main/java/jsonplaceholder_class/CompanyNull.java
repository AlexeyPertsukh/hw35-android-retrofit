package jsonplaceholder_class;

public class CompanyNull extends Company {
    private static CompanyNull companyNull;

    private CompanyNull() {
    }

    protected static CompanyNull getInstance() {
        if (companyNull == null) {
            companyNull = new CompanyNull();
        }
        return companyNull;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getName() {
        return EMPTY_STRING;
    }

    @Override
    public String getCatchPhrase() {
        return EMPTY_STRING;
    }

    @Override
    public String getBs() {
        return EMPTY_STRING;
    }
}
