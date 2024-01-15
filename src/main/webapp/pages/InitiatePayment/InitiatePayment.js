/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
    Page.Variables.stvSelectedBeneficiary.dataSet = {};
};

Page.buttonSelectBeneficiaryFromListClick = function($event, widget) {
    Page.Variables.stvSelectedBeneficiary.dataSet = Page.Widgets.BeneficiaryList1.selecteditem;
};

Page.anchorChangeBeneficiaryClick = function($event, widget) {
    Page.Variables.stvSelectedBeneficiary.dataSet = {};
};

Page.BeneficiaryLiveForm1Success = function($event, $operation, $data) {
    Page.Variables.stvSelectedBeneficiary.dataSet = $data;
};

Page.wizardPaymentInitiateDone = function(widget, steps) {
    Page.Widgets.TransactionLiveForm1.submit();
};

Page.TransactionLiveForm1Beforeservicecall = function($event, $operation, $data, options) {
    $data.benericiaryId = Page.Variables.stvSelectedBeneficiary.dataSet.id;
    $data.transactionTypeId = 1;
    $data.transactionStatusId = 1;
};

Page.wizardstep2Next = function(widget, currentStep, stepIndex) {
    if (!Page.Variables.stvSelectedBeneficiary.dataSet.id) {
        Page.Actions.nta_selectBeneficiaryWarn.invoke();
        return false;
    }
};
