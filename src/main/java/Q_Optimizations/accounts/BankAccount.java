package Q_Optimizations.accounts;

import java.io.File;

/** In-memory sample account used by the bank manager demonstration. */
public class BankAccount implements Account {
    private final String name;
    private final double balance;
    private final int annualIncome;
    private boolean taxDataLoaded;
    private boolean taxesFiled;
    private int taxRule;

    public BankAccount(String name, double balance, int annualIncome) {
        this.name = name;
        this.balance = balance;
        this.annualIncome = annualIncome;
    }

    @Override
    public void loadTaxData() {
        taxDataLoaded = true;
    }

    @Override
    public boolean meetsComplexTaxCode(int year) {
        return taxDataLoaded && year >= 2020 && annualIncome > 100_000 && balance > 50_000;
    }

    @Override
    public void fileTaxes(int taxRule) {
        this.taxRule = taxRule;
        taxesFiled = true;
    }

    @Override
    public void setTaxRule(int taxRule) {
        this.taxRule = taxRule;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void save(File file) {
        // The demonstration keeps account state in memory and does not write files.
    }

    public boolean isTaxesFiled() {
        return taxesFiled;
    }

    public int getTaxRule() {
        return taxRule;
    }
}
