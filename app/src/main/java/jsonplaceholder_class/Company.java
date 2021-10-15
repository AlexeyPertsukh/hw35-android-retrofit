package jsonplaceholder_class;

import java.io.Serializable;

public class Company implements Serializable, INull {
    private String name;
    private String catchPhrase;
    private String bs;

    protected Company() {
    }

    @SuppressWarnings("unused")
    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public static Company getInstanceNull() {
        return CompanyNull.getInstance();
    }

    public String getName() {
        return getValueOrEmptyStringIfNull(name);
    }

    public String getCatchPhrase() {
        return getValueOrEmptyStringIfNull(catchPhrase);
    }

    public String getBs() {
        return getValueOrEmptyStringIfNull(bs);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}

