$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'accountenchashment/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '用户ID', name: 'userId', index: 'user_id', width: 80},
            {label: '金额(分)', name: 'totalFee', index: 'total_fee', width: 80},
            {label: '银行全名', name: 'bankFullName', index: 'bank_full_name', width: 80},
            {
                label: '账户类型', name: 'accountType', index: 'account_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 'P') {
                        return '<span">私户</span>';
                    }
                    if (value === 'C') {
                        return '<span">公户</span>';
                    }
                    else {
                        return '<span">非法</span>';
                    }
                }
            },
            {label: '银行卡号', name: 'accountNo', index: 'account_no', width: 80},
            {label: '姓名或者单位名', name: 'accountName', index: 'account_name', width: 80},
            {
                label: '提现状态', name: 'status', index: 'status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">提现中</span>';
                    }
                    if (value === 1) {
                        return '<span">提现成功</span>';
                    }
                    else {
                        return '<span">非法</span>';
                    }
                }
            },

            {
                label: '卡类型', name: 'cardType', index: 'card_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 'DE') {
                        return '<span">借记卡</span>';
                    }
                    if (value === 'CR') {
                        return '<span">信用卡</span>';
                    }
                    else {
                        return '<span">非法</span>';
                    }
                }
            },
            {label: '支付时间', name: 'payAt', index: 'pay_at', width: 80},
            {label: '创建时间', name: 'createAt', index: 'create_at', width: 80}
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
        }
        ,
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        }
        ,
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
})
;

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            userId: null
        },
        showList: true,
        title: null,
        accountEnchashment: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.accountEnchashment = {};
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
            var url = vm.accountEnchashment.id == null ? "accountenchashment/save" : "accountenchashment/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.accountEnchashment),
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
                    url: baseURL + "accountenchashment/delete",
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
            $.get(baseURL + "accountenchashment/info/" + id, function (r) {
                vm.accountEnchashment = r.accountEnchashment;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'userId': vm.q.userId,
                    "beginTime": $("#beginTime").val(),
                    "endTime": $("#endTime").val()
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});