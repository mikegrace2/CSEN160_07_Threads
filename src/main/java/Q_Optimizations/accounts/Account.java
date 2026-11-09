package Q_Optimizations.accounts;

import java.io.File;

/** Account operations used by the bank manager. */
public interface Account {
    void loadTaxData();

    boolean meetsComplexTaxCode(int year);

    void fileTaxes(int taxRule);

    void setTaxRule(int taxRule);

    String getName();

    void save(File file);
}
