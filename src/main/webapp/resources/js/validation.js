/**
 * Created by IhorTovpinets on 17.12.2016.
 */

$(document).ready(function () {

    $.validator.addMethod("regexAccountName", function (value, element, regexpr) {
        return regexpr.test(value);
    });
    $.validator.addMethod("regexAccountDeposit", function (value, element, regexpr) {
        return regexpr.test(value);
    });
    $.validator.addMethod("regexAccountDescription", function (value, element, regexpr) {
        return regexpr.test(value);
    });

    $("#addingAccountForm").validate({
        errorElement: "div",
        rules: {
            "accAddName": {
                required: true,
                regexAccountName: /^[A-Za-z0-9-'\s]+$/,
                minlength: 5,
                maxlength: 20
            },
            "accAddDeposit": {
                required: true,
                regexAccountDeposit: /^[0-9]+[.,]?[0-9]+$/,
                maxlength: 15
            },
            "accAddDescription": {
                regexAccountDescription: /^[a-zA-Zа-яА-ЯЇїІіЄєҐґ0-9\-`´ʼ’'\s]*$/,
                maxlength: 50
            }
        },
        messages: {
            "accAddName": {
                required: messages.account.requiredName,
                regexAccountName: messages.account.regexAccName,
                minlength: messages.account.nameMinlength,
                maxlength: messages.account.nameMaxlength
            },
            "accAddDeposit": {
                required: messages.account.requiredDeposit,
                regexAccountDeposit: messages.account.regexDeposit,
                maxlength: messages.account.depositMaxlength
            },
            "accAddDescription": {
                regexAccountDescription: messages.account.regexDescription,
                maxlength: messages.account.maxDescription
            }
        }
    });
});