$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'order/list',
        datatype: "json",
        colModel: [
            {label: '订单ID', name: 'id', index: 'id', width: 50, key: true},
            {label: '用户ID', name: 'userId', index: 'user_id', width: 80},
            {label: '设备ID', name: 'deviceId', index: 'device_id', width: 80},
            {label: '订单金额', name: 'orderMoney', index: 'order_money', width: 80},
            {
                label: '支付类型', name: 'payType', index: 'pay_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">支付宝</span>';
                    }
                    if (value === 1) {
                        return '<span">微信</span>';
                    }
                    if (value === 2) {
                        return '<span">银联</span>';
                    }
                    if (value === 3) {
                        return '<span">个人账户</span>';
                    }
                    if (value === 4) {
                        return '<span">刷卡支付</span>';
                    } else {
                        return '<span"></span>'
                    }
                }
            },
            {
                label: '订单状态', name: 'payStatus', index: 'pay_status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">待支付</span>';
                    }
                    if (value === 1) {
                        return '<span">支付中</span>';
                    }
                    if (value === 20) {
                        return '<span">支付成功</span>';
                    }
                    if (value === 21) {
                        return '<span">支付失败</span>';
                    } else {
                        return '<span"></span>';
                    }
                }
            },
            {
                label: '订单类型', name: 'orderType', index: 'order_type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">ID卡充值</span>';
                    }
                    if (value === 1) {
                        return '<span">APP消费</span>';
                    }
                    if (value === 2) {
                        return '<span">ID卡消费</span>';
                    }
                }
            },
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
            {label: '完成时间', name: 'payTime', index: 'pay_time', width: 80},
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
            id: null
        },
        showList: true,
        title: null,
        order: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.order = {};
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
            var url = vm.order.id == null ? "order/save" : "order/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.order),
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
                    url: baseURL + "order/delete",
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
            $.get(baseURL + "order/info/" + id, function (r) {
                vm.order = r.order;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'id': vm.q.id,
                    "beginTime": $("#beginTime").val(),
                    "endTime": $("#endTime").val()
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});