$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'accounttransactionhistory/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '账户id', name: 'accountId', index: 'account_id', width: 80},
            {label: '订单ID', name: 'orderId', index: 'order_id', width: 80},
            {label: '交易前金额', name: 'beforeAdjustMoney', index: 'before_adjust_money', width: 80},
            {label: '交易金额', name: 'adjustMoney', index: 'adjust_money', width: 80},
            {label: '账户余额', name: 'afterAdjustMoney', index: 'after_adjust_money', width: 80},
            {
                label: '调整类型', name: 'adjustType', index: 'adjust_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">用户充值</span>';
                    }
                    if (value === 1) {
                        return '<span">用户消费</span>';
                    }
                    if (value === 2) {
                        return '<span">分成结算</span>';
                    }
                    if (value === 3) {
                        return '<span">提现</span>';
                    }
                }
            },
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80}

        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    //初始化日期控件
    $('.form_date').datetimepicker({
        // language: 'zh',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            accountId: null
        },
        showList: true,
        title: null,
        accountTransactionHistory: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.accountTransactionHistory = {};
        },
        update: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
        },
        saveOrUpdate: function (event) {
            var url = vm.accountTransactionHistory.id == null ? "accounttransactionhistory/save" : "accounttransactionhistory/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.accountTransactionHistory),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }

            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: baseURL + "accounttransactionhistory/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (id) {
            $.get(baseURL + "accounttransactionhistory/info/" + id, function (r) {
                vm.accountTransactionHistory = r.accountTransactionHistory;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'accountId': vm.q.accountId,
                    "beginTime": $("#beginTime").val(),
                    "endTime": $("#endTime").val()
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});