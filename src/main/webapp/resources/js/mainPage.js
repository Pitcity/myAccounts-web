/**
 * Created by IhorTovpinets on 16.12.2016.
 */

var allInnerAccounts = [];

$(document).ready(function () {
    showAccountList();

    $('#addAccBtn').click(function () {

        $('#editNewAcc').hide();
        $('#addNewAcc').show();
        $('#dialogForAditingAccount').show();
    });

    $('#hideDialog').click(function () {
        $('#dialogForAditingAccount').hide();
    });
});


function deleteAcc(id) {
    var path = "deleteAcc_" + id;
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: path,
        dataType: 'json',
        success: function (data) {
            allInnerAccounts = jsonToAccArray(data);
            populateAccountList(allInnerAccounts);
        },
        error: function (xhr) {
            alert(xhr.body);//todo:dialog with error message cause acc exists
        }
    });
}

function getDataForEditingAcc(id) {
    var path = "getEditAcc_" + id;
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: path,
        dataType: 'json',
        success: function (data) {
            $('#accAddName').val(data.name);
            $('#accEditId').val(data.id);
            $('#accAddDeposit').val(data.deposit);
            $('#accAddDescription').val(data.description);
            $('#dialogForAditingAccount').show();

            $('#editNewAcc').show();
            $('#addNewAcc').hide();
            $('#editNewAcc').click(function () {
                if ($("#addingAccountForm").valid()) {
                    updateAccount();
                }
            });
        },
        error: function (xhr) {
            alert(xhr.body);
        }
    });
}

function updateAccount() {
    var acc = new AccountForUpdate($('#accEditId').val(), $('#accAddName').val(), $('#accAddDeposit').val(), $('#accAddDescription').val(), true);
    sendAccountToServerForUpdate(acc);
}

function createAccount() {
    var acc = new Account($('#accAddName').val(), $('#accAddDeposit').val(), $('#accAddDescription').val(), true);
    sendAccountToServerForCreate(acc);
}

function sendAccountToServerForUpdate(acc) {
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        url: 'updateAcc',
        dataType: 'json',
        data: JSON.stringify(acc),
        success: function (data) {
            allInnerAccounts = jsonToAccArray(data);
            populateAccountList(allInnerAccounts);
            $('#dialogForAditingAccount').hide();
        },
        error: function (xhr) {
            showErrorDialog(xhr.responseText);
            $('#dialogForAditingAccount').hide();
        }
    });
}
function jsonToAccArray(data) {
    var innerAccList = [];
    data.forEach(function (item, i) {
        if (!item.isOuter) {
            var newAccount = {
                id: item.id,
                name: item.name,
                deposit: item.deposit,
                description: item.description,
                isOuter: item.isOuter
            };
            innerAccList[i] = newAccount;
        }
    });
    return innerAccList;
}

function showAccountList() {
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        url: 'getAccList',
        dataType: 'json',
        success: function (data) {
            allInnerAccounts = jsonToAccArray(data);
            populateAccountList(allInnerAccounts);
            $('#dialogForAditingAccount').hide();
        },
        error: function (xhrv) {
            alert(xhr.body);//todo:dialog with error message cause acc exists
        }
    });
}

$("#addNewAcc").click(function () {
    if ($("#addingAccountForm").valid()) {
        createAccount();
    }
});

function Account(name, deposit, description, isOuter) {
    this.name = name;
    this.deposit = deposit;
    this.description = description;
    this.isOuter = isOuter;
}

function AccountForUpdate(id, name, deposit, description, isOuter) {
    this.id = id;
    this.name = name;
    this.deposit = deposit;
    this.description = description;
    this.isOuter = isOuter;
}
function sendAccountToServerForCreate(acc) {
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        url: 'addAcc',
        dataType: 'json',
        data: JSON.stringify(acc),
        success: function (data) {
            allInnerAccounts = jsonToAccArray(data);
            populateAccountList(allInnerAccounts);
            $('#dialogForAditingAccount').hide();

        },
        error: function (xhr) {
            showErrorDialog(xhr.responseText);
            $('#dialogForAditingAccount').hide();
        }
    });
}
function showErrorDialog(text) {
    $('#errorMessage').html('We\'re sorry, but' + text);
    $('#closeErrorDialog').click(function () {
        $('#dialogForServerErrors').hide();
    });
    $('#dialogForServerErrors').show();
}
function populateAccountList(allInnerAccounts) {
    $('#tableWithAccs_wrapper').remove();

    var contentForTable = '';
    allInnerAccounts.forEach(function (item, i) {
        contentForTable += '<tr id=\'accountId_' + item.id + '\'><td>' + (i + 1) + '</td><td hidden>' + item.id + '</td><td>' + item.name + '</td><td>' + item.deposit +
            '</td><td>' + item.description + '</td><td id="tatata">';
        contentForTable += ' <button id =\'editAcc_' + item.id + '\' class="btn btn-primary btn-xs editAcc" ' +
            'data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button>';
        contentForTable += ' <button id =\'deleteAcc_' + item.id + '\' class="btn btn-danger btn-xs deleteAcc" ' +
            'data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button>';
    });

    var table = '<table id="tableWithAccs" class="table table-striped"><thead><th>id</th><th hidden>id</th>' +
        '<th>Name</th><th>Deposit</th><th>Description</th><th>Options</th></thead><tbody>' + contentForTable + '</tbody></table>'
    $('#divForTableWithAccs').append(table);


    $('#tableWithAccs').DataTable();
    $('.deleteAcc').click(function () {
        var res = this.id.substring(10, 20);
        deleteAcc(res);
    });

    $('.editAcc').click(function () {
        var res = this.id.substring(8, 20);
        getDataForEditingAcc(res);
    });
}



