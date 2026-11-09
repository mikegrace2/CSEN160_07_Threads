package Q_Optimizations;

import java.io.File;
import java.util.Random;
import Q_Optimizations.accounts.Account;
import Q_Optimizations.accounts.AccountProvider;
import Q_Optimizations.accounts.BankAccount;

public class D_BankManager {
    private static final int NUMBER_OF_ACCOUNTS = 1_000_000;
    private static final int ACCOUNT_LIMIT = (int) Math.sqrt(895732);
    private static final int TAX_YEAR = 2020;
    private static final int TAX_RULE = 4 * 4096 * 17;

    private final AccountProvider accountProvider;

    /** Creates a manager with one million randomly initialized sample accounts. */
    public D_BankManager() {
        this(createRandomAccounts());
    }

    public D_BankManager(AccountProvider accountProvider) {
        this.accountProvider = accountProvider;
    }

    private static AccountProvider createRandomAccounts() {
        Account[] accounts = new Account[NUMBER_OF_ACCOUNTS];
        Random random = new Random();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new BankAccount(
                    "account-" + i,
                    random.nextDouble(1_000.0, 1_000_000.0),
                    random.nextInt(20_000, 250_000));
        }
        return () -> accounts;
    }

    public void processAccounts() {
        Account[] a = accountProvider.getAll();

        for (int i = 0; i < Math.min(ACCOUNT_LIMIT, a.length); i++) {
            a[i].loadTaxData();
            if (a[i].meetsComplexTaxCode(TAX_YEAR)) {
                a[i].fileTaxes(TAX_RULE);
            }
        }

        Account[] a2 = accountProvider.getAll();
        for (int i = 0; i < Math.min(ACCOUNT_LIMIT, a2.length); i++) {
            if (a2[i].meetsComplexTaxCode(TAX_YEAR)) {
                a2[i].setTaxRule(TAX_RULE);
                a2[i].save(new File(a2[i].getName()));
            }
        }
    }

    public static void main(String[] args) {
        D_BankManager manager = new D_BankManager();
        long startTime = System.currentTimeMillis();
        manager.processAccounts();
        long endTime = System.currentTimeMillis();

        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
}
