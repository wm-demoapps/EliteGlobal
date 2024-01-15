/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Partial.onReady = function() {
    /*
     * variables can be accessed through 'Partial.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Partial.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Partial.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Partial.Widgets.username.datavalue'
     */
};

Partial.getFDBalance = function() {
    let fdBalance = 0;
    Partial.Variables.dbGetAccounts.dataSet.forEach((account) => {
        fdBalance = fdBalance + account.fdAmount;
    })
    return fdBalance;
}
Partial.getFDUpperLimit = function() {
    let upperLimit = 0;
    let fdBalance = 0
    Partial.Variables.dbGetAccounts.dataSet.forEach((account) => {
        fdBalance = fdBalance + account.fdAmount;
        upperLimit = upperLimit + account.fdUpperLimit;
    })
    return upperLimit - fdBalance;
}
