/**
 * Created by IhorTovpinets on 16.12.2016.
 */

$(document).ready(function () {
    showAccountList();

    $('#addDealDate').datepicker().val($.datepicker.formatDate('mm/dd/yy', new Date()));
    $('#addAccBtn').click(function () {
        $('#editNewAcc').hide();
        $('#addNewAcc').show();
        $('#accAddName').val('');
        $('#accAddDeposit').val('');
        $('#accAddDescription').val('');
        $('#accAddDeposit').prop('disabled', false);
        $('#dialogForAditingAccount').show();
    });
});

$('#addNewDeal').click(function () {
    if (validateDealInformation()) {
        createDeal();
    }
});

$('#hideDealDialog').click(function () {
    $('#dialogForAdditingDeal').hide();
});

$('#closeDealsForAcc').click(function () {
    $('#dialogForDealsFroAcc').hide();
});

$('#hideDialog').click(function () {
    $('#dialogForAditingAccount').hide();
});

$('#addingDealForm').click(function () {
    validateDealInformation();
});

$('#addDealBtn').click(function () {
    $('#editNewDeal').hide();
    $('#addDealSum').val('');
    $('#addDealNote').val('');
    $('#dialogForAdditingDeal').show();
});

function checkSelected() {
    var accSellerNameId = $('#addDealSeller').val();
    var accBuyerNameId = $('#addDealBuyer').val();

    $('#addDealSeller option').prop('disabled', false);
    $('#addDealBuyer option').prop('disabled', false);
    $('#addDealBuyer option[value=' + accSellerNameId + ']').prop('disabled', true);
    $('#addDealSeller option[value=' + accBuyerNameId + ']').prop('disabled', true);

    if (accSellerNameId == 'another')
        ($('#inputAddDealSeller').show());
    else
        ($('#inputAddDealSeller').hide());
    if (accBuyerNameId == 'another')
        ($('#inputAddDealBuyer').show());
    else
        ($('#inputAddDealBuyer').hide());
}

function populateAccountOptions(allInnerAccounts) {
    var content = '';
    allInnerAccounts.forEach(function (item, i) {
        content += '<option class="list-group-item-success" value="' + item.id + '">' + item.name + '</option>';
    });
    content += '<option class="list-group-item-danger" value="another">another</option>';
    $('#addDealSeller').html('');
    $('#addDealSeller').append(content);
    $('#addDealBuyer').html('');
    $('#addDealBuyer').append(content);
    $('#addDealBuyer option[value="another"]').prop('selected', true);
    checkSelected();
}

function deleteAcc(id) {
    var allInnerAccounts = [];
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
            showErrorDialog(xhr.responseText);
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
            $('#accAddDeposit').prop('disabled', true);

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
            showErrorDialog(xhr.body);
        }
    });
}

function getDealsForAcc(id) {
    var path = "dealsFroAcc_" + id;
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: path,
        dataType: 'json',
        success: function (data) {
            var dealsForAcc = jsonToDealList(data);
            populateDealList(dealsForAcc);
            $('#dialogForDealsFroAcc').show();
        },
        error: function (xhr) {
            showErrorDialog(xhr.body);
        }
    });
}

function updateAccount() {
    var acc = new AccountForUpdate($('#accEditId').val(), $('#accAddName').val(), $('#accAddDeposit').val(), $('#accAddDescription').val(), false);
    sendAccountToServerForUpdate(acc);
}

function createAccount() {
    var acc = new Account($('#accAddName').val(), $('#accAddDeposit').val(), $('#accAddDescription').val(), false);
    sendAccountToServerForCreate(acc);
}

function guid() {
    function s4() {
        return Math.floor((1 + Math.random()) * 0x10000)
            .toString(16)
            .substring(1);
    }
    return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
}

function createDeal() {
    var accSellerNameId = $('#addDealSeller').val();
    var accBuyerNameId = $('#addDealBuyer').val();
    $('#inputAccountsError').hide();

    var seller, buyer, note, sum, date;

    seller = accSellerNameId == 'another' ? $('#inputAddDealSeller').val() :
        $('#addDealSeller').find('option[value=' + accSellerNameId + ']').html();
    buyer = accBuyerNameId == 'another' ? $('#inputAddDealBuyer').val() :
        $('#addDealBuyer').find('option[value=' + accBuyerNameId + ']').html();

    sum = $('#addDealSum').val();
    note = $('#addDealNote').val();
    date = $('#addDealDate').val();
    if (accBuyerNameId === 'another') {
        accBuyerNameId = guid();
    }
    if (accSellerNameId === 'another') {
        accSellerNameId = guid();
    }
    var deal = new Deal(buyer, accBuyerNameId, seller, accSellerNameId, new Date(date).getTime(), note, sum);
    sendDealToServerForCreate(deal);
}

function sendDealToServerForCreate(deal) {
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        url: 'addDeal',
        dataType: 'json',
        data: JSON.stringify(deal),
        success: function (data) {
            showAccountList();
            $('#dialogForAdditingDeal').hide();
        },
        error: function (xhr) {
            showErrorDialog(xhr.responseText);
            $('#dialogForAdditingDeal').hide();
        }
    });
}

function sendAccountToServerForUpdate(acc) {
    var allInnerAccounts = [];
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

function jsonToDealList(data) {
    var innerDealList = [];
    data.forEach(function (item, i) {
        var newDeal = {
            id: item.id,
            seller: item.seller,
            buyer: item.buyer,
            date: item.date,
            note: item.note,
            sum: item.sum
        };
        innerDealList[i] = newDeal;
    });
    return innerDealList;
}

function generateColor(i, max) {
    collor = 255*((i+1)/(max+1));
    return ("rgba("+ (collor/5)+","+(collor/5)+","+(collor + Math.random()* 255*i/max)+", 0.8");
}

function updateDeals(AccId) {
    var path = "dealsFroAcc_" + AccId;
    $.ajax({
        type: 'get',
        contentType: 'application/json',
        url: path,
        dataType: 'json',
        success: function (data) {
            var dealsForAcc = jsonToDealList(data);

            var ctx = document.getElementById("myChart").getContext('2d');

            var arrDealDate = new Array(31);
            var arrDeposit = new Array(31);
            var arrColors = new Array(31);
            for (var i=0;i<31;i++) {
                arrDealDate[i] = i+1;
                arrDeposit[i] = 0;
                arrColors[i] = 'rgba(255, 206, 86, 0)';
            }
            dealsForAcc.forEach(function (value) {
                arrDealDate[new Date(value.date).getDate()] = (new Date(value.date).getDate());
                arrDeposit[new Date(value.date).getDate()] += (value.sum);
                arrColors[new Date(value.date).getDate()] = generateColor(0, dealsForAcc.length);
            });
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: arrDealDate,
                    datasets: [{
                        data: arrDeposit,
                        backgroundColor: arrColors,
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            }
                        }]
                    }
                }
            });
        },
        error: function (xhr) {
            showErrorDialog(xhr.body);
        }
    });
}
function updateDonChart (allInnerAccounts) {
    var ctx = document.getElementById("myChart1").getContext('2d');
    var arrDepo = [];
    var arrAccNames = [];
    var arrColors = [];
    allInnerAccounts.forEach(function (value, i) {
        arrDepo[i] = (value.deposit);
        arrAccNames[i] = (value.name);
        arrColors[i] = generateColor(i, allInnerAccounts.length);
    });
    data = {
        datasets: [{
            data: arrDepo,
            backgroundColor : arrColors
        }],

        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: arrAccNames
    };
    var myDoughnutChart = new Chart(ctx, {
        type: 'doughnut',
        data: data,
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}

function showAccountList() {
    var allInnerAccounts = [];
    $.ajax({
        type: 'post',
        contentType: 'application/json',
        url: 'getAccList',
        dataType: 'json',
        success: function (data) {
            allInnerAccounts = jsonToAccArray(data);
            populateAccountList(allInnerAccounts);
            $('#dialogForAditingAccount').hide();

            updateDonChart(allInnerAccounts);
            updateDeals(allInnerAccounts[0].id);
        },
        error: function (xhr) {
            showErrorDialog(xhr.responseText);
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

function Deal(buyer, buyerId, seller, sellerId, date, note, sum) {
    this.buyer = buyer;
    this.buyerId = buyerId;
    this.seller = seller;
    this.sellerId = sellerId;
    this.date = date;
    this.note = note;
    this.sum = sum;
}

function AccountForUpdate(id, name, deposit, description, isOuter) {
    this.id = id;
    this.name = name;
    this.deposit = deposit;
    this.description = description;
    this.isOuter = isOuter;
}
function sendAccountToServerForCreate(acc) {
    var operationResult;
    var allInnerAccounts = [];
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
            operationResult = true;
        },
        error: function (xhr) {
            showErrorDialog(xhr.responseText);
            $('#dialogForAditingAccount').hide();
            operationResult = false;
        }
    });
    return operationResult;
}
function showErrorDialog(text) {
    $('#errorMessage').html('We\'re sorry, but ' + text);
    $('#closeErrorDialog').click(function () {
        $('#dialogForServerErrors').hide();
    });
    $('#dialogForServerErrors').show();
}

function populateDealList(dealsForAccount, account) {

    //$('#dealsForAccountName').append(account);
    $('#tableWithDealsForAccount_wrapper').remove();

    var contentForTable = '';
    dealsForAccount.forEach(function (item, i) {
        contentForTable += '<tr id=\'dealId_' + item.id + '\'><td>' + (i + 1) + '</td><td hidden>' + item.id + '</td><td>'
            + item.seller + '</td><td>' + item.buyer + '</td><td>' + item.sum +
            '</td><td>' + (new Date(item.date)).toLocaleDateString() + '</td><td>' + item.note + '</td></tr>';//todo:check this
    });
    var table = '<table id="tableWithDealsForAccount" class="table table-striped"><thead><th>id</th><th hidden>id</th>' +
        '<th>Seller</th><th>Buyer</th><th>Sum</th><th>Date</th><th>Note</th></thead><tbody>' + contentForTable + '</tbody></table>';
    $('#divForTableWithDeals').append(table);
    $('#tableWithDealsForAccount').DataTable();

}
function populateAccountList(allInnerAccounts) {
    $('#tableWithAccs_wrapper').remove();

    var contentForTable = '';
    allInnerAccounts.forEach(function (item, i) {
        contentForTable += '<tr id=\'accountId_' + item.id + '\'><td>' + (i + 1) + '</td><td hidden>' + item.id + '</td><td>' + item.name + '</td><td>' + item.deposit +
            '</td><td>' + item.description + '</td><td>';
        contentForTable += '<button id =\'editAcc_' + item.id + '\' class="btn btn-primary btn-xs editAcc" ' +
            'data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button>';
        contentForTable += '<button id =\'deleteAcc_' + item.id + '\' class="btn btn-danger btn-xs deleteAcc" ' +
            'data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button>';
        contentForTable += '<button id =\'dealsForAcc_' + item.id + '\' class="btn btn-info btn-xs dealsForAcc" ' +
            'data-title="Additional" data-toggle="modal" data-target="#info" ><span class="glyphicon glyphicon-duplicate"></span></button>';
    });
    var table = '<table id="tableWithAccs" class="table table-striped"><thead><th>id</th><th hidden>id</th>' +
        '<th>Name</th><th>Deposit</th><th>Description</th><th>Options</th></thead><tbody>' + contentForTable + '</tbody></table>';

    $('#divForTableWithAccs').append(table);

    $('.deleteAcc').click(function () {
        var res = this.id.substring(10, 50);
        deleteAcc(res);
    });

    $('.editAcc').click(function () {
        var res = this.id.substring(8, 50);
        getDataForEditingAcc(res);
    });

    $('.dealsForAcc').click(function () {
        var res = this.id.substring(12, 50);
        getDealsForAcc(res);
    });

    $('#tableWithAccs').DataTable();
    populateAccountOptions(allInnerAccounts);
}



