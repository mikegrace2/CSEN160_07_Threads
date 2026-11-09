package Q_Optimizations.accounts;

/** Supplies accounts to a bank manager. */
@FunctionalInterface
public interface AccountProvider {
    Account[] getAll();
}
