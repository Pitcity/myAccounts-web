/**
 * Created by IhorTovpinets on 17.12.2016.
 */

var ACCOUNT_NAME_REGEX, NOTE_REGEX, SUM_REGEX;
ACCOUNT_NAME_REGEX = /^[0-9A-Za-z-'\s]+$/;
NOTE_REGEX = /^[a-zA-Zа-яА-ЯЇїІіЄєҐґ0-9\-`´ʼ’'\s]*$/;
SUM_REGEX = /^[0-9]+[.,]?[0-9]*$/;
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
        rules: {
            "accAddName": {
                required: true,
                regexAccountName: ACCOUNT_NAME_REGEX,
                minlength: 5,
                maxlength: 20
            },
            "accAddDeposit": {
                required: true,
                regexAccountDeposit: SUM_REGEX,
                maxlength: 15
            },
            "accAddDescription": {
                regexAccountDescription: NOTE_REGEX,
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

function validateDealInformation() {
    var valid = true;
    var accSellerNameId = $('#addDealSeller').val();
    var accBuyerNameId = $('#addDealBuyer').val();
    $('#inputAccountsError').hide();

    $('#addingDealForm').find('.form-group .error').remove();

    if (accSellerNameId==accBuyerNameId) {
        valid = false;
        $('#inputAccountsError').show();
    }

    var testedValue;
    if (accSellerNameId=='another') {
        testedValue = $('#inputAddDealSeller').val();
        if(!ACCOUNT_NAME_REGEX.test(testedValue)) {
            valid = false;
            $('#inputAddDealSeller').parent('div').addClass('has-error');
            $('#inputAddDealSeller').parent('div').append('<div class="error">Enter valid name for Seller</div>')
        } else
            $('#inputAddDealSeller').parent('div').removeClass('has-error');
    } else
        $('#inputAddDealSeller').parent('div').removeClass('has-error');

    if (accBuyerNameId=='another') {
        testedValue = $('#inputAddDealBuyer').val();
        if(!ACCOUNT_NAME_REGEX.test(testedValue)) {
            valid = false;
            $('#inputAddDealBuyer').parent('div').addClass('has-error');
            $('#inputAddDealBuyer').parent('div').append('<div class="error">Enter valid name for Buyer</div>')
        } else
            $('#inputAddDealBuyer').parent('div').removeClass('has-error');
    } else
        $('#inputAddDealBuyer').parent('div').removeClass('has-error');

    testedValue = $('#addDealSum').val();
    if(!SUM_REGEX.test(testedValue)) {
        valid = false;
        $('#addDealSum').parent('div').addClass('has-error');
        $('#addDealSum').parent('div').append('<div class="error">Enter positive number</div>')
    } else
        $('#addDealSum').parent('div').removeClass('has-error');

    testedValue = $('#addDealNote').val();
    if(!NOTE_REGEX.test(testedValue)) {
        valid = false;
        $('#addDealNote').parent('div').addClass('has-error');
        $('#addDealNote').parent('div').append('<div class="error">Note should not contain special symbols</div>');
    } else if(testedValue.length>50) {
        valid = false;
        $('#addDealNote').parent('div').append('<div class="error">Enter note with max length of 100</div>')
    } else
        $('#addDealNote').parent('div').removeClass('has-error');

    var testedDate = new Date($('#addDealDate').datepicker().val());
    var currentDate = new Date();
    if (testedDate.getTime()>currentDate.getTime()) {
        valid = false;
        $('#addDealDate').parent('div').addClass('has-error');
        $('#addDealDate').parent('div').append('<div class="error">It\'s not possible to create deal in future</div>')
    } else
        $('#addDealDate').parent('div').removeClass('has-error');

    return valid;
}