$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'applycard/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '联系人姓名', name: 'contactName', index: 'contact_name', width: 80},
            {label: '联系人电话', name: 'contactPhone', index: 'contact_phone', width: 80},
            {label: '联系人地址', name: 'contactAddress', index: 'contact_address', width: 80},
            {label: '身份证号码', name: 'idCard', index: 'id_card', width: 80},
            {
                label: '申请状态', name: 'status', index: 'status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">提交申请</span>';
                    }
                    if (value === 1) {
                        return '<span">审批失败</span>';
                    }
                    if (value === 2) {
                        return '<span">审批成功</span>';
                    }
                }
            },
            {label: '申请时间', name: 'applyTime', index: 'apply_time', width: 80},
            {label: '更新时间', name: 'updateTime', index: 'update_time', width: 80},
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
            userName: null
        },
        showList: true,
        title: null,
        applyCard: {}
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.applyCard = {};
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
            var url = vm.applyCard.id == null ? "applycard/save" : "applycard/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.applyCard),
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
                    url: baseURL + "applycard/delete",
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
            $.get(baseURL + "applycard/info/" + id, function (r) {
                vm.applyCard = r.applyCard;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'contactName': vm.q.contactName,
                    "beginTime": $("#beginTime").val(),
                    "endTime": $("#endTime").val()
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});