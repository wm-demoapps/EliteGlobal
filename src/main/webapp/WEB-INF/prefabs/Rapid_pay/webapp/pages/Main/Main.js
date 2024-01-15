    /*
     * Use App.getDependency for Dependency Injection
     * eg: var DialogService = App.getDependency('DialogService');
     */

    /*
     * This function will be invoked when any of this prefab's property is changed
     * @key: property name
     * @newVal: new value of the property
     * @oldVal: old value of the property
     */
    Prefab.onPropertyChange = function(key, newVal, oldVal) {
        /*
        switch (key) {
            case "prop1":
                // do something with newVal for property 'prop1'
                break;
            case "prop2":
                // do something with newVal for property 'prop2'
                break;
        }
        */
    };

    Prefab.onReady = function() {
        // this method will be triggered post initialization of the prefab.
    };

    Prefab.currentDate = function() {
        return new Date().toJSON();
    };

    Prefab.swgPatchAccountDetailsonSuccess = function(variable, data) {
        Prefab.Widgets.mvRapidPayFormForm1.reset()
    };
