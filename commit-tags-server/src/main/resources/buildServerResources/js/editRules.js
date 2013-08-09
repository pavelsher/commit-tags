BS.EditRulesDialog = OO.extend(BS.AbstractModalDialog, OO.extend(BS.AbstractWebForm, {
    getContainer: function() {
        return $('editTagRuleFormDialog');
    },

    formElement: function() {
        return $('editTagRuleForm');
    },

    show: function() {
        this.showCentered();
    }

}));