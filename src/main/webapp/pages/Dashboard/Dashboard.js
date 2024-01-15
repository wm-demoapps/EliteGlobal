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
};

Page.switchCardsLayoutChange = function($event, widget, newVal, oldVal) {
    $(".app-container.col-md-12").resize();

};

Page.checkboxTransactionCalendarChange = function($event, widget, newVal, oldVal) {
    Page.Variables.stvWidgetConfig.dataSet.transactionCalendar = newVal;
};

Page.checkboxCreditChartChange = function($event, widget, newVal, oldVal) {
    Page.Variables.stvWidgetConfig.dataSet.creditLineChart = newVal;
};

Page.checkboxRapidPayChange = function($event, widget, newVal, oldVal) {
    Page.Variables.stvWidgetConfig.dataSet.rapidPay = newVal;
};
