$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '设备编码', name: 'code', index: 'code', width: 80},
            {label: 'SIM卡号', name: 'sim', index: 'sim', width: 80},
            {label: '所属商户', name: 'merchantName', index: 'merchant_name', width: 100},
            {
                label: '设备使用状态', name: 'useStatus', index: 'use_status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">未使用</span>';
                    }
                    if (value === 1) {
                        return '<span">使用中</span>';
                    }
                }
            },
            {
                label: '是否故障', name: 'isBreakdown', index: 'is_breakdown', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">正常</span>';
                    }
                    if (value === 1) {
                        return '<span">故障</span>';
                    }
                }
            },
            {
                label: '绑定状态', name: 'bindStatus', index: 'bind_status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">未使用</span>';
                    }
                    if (value === 1) {
                        return '<span">已绑定</span>';
                    }
                    if (value === 2) {
                        return '<span">已解绑</span>';
                    }
                }
            },
            {label: '操作时间', name: 'operateTime', index: 'operate_time', width: 80}
            // {label: '设备使用的总时间', name: 'totalTime', index: 'total_time', width: 80}
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
            page: "page.pageNo",
            total: "page.pages",
            records: "page.totalRecordNum"
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
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        device: {},
        merchantList: [],
        merchantName: "",
        layerIndex: null
    },
    methods: {
        selectMerchant: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-demo',
                title: "绑定设备",
                area: ['400px', '450px'],
                shadeClose: false,
                content: jQuery("#selectPage"),
                btn: ['取消'],
                success: function (layero, index) {
                    vm.layerIndex = index;
                },
                btn1: function (index) {
                    layer.close(index);
                }
            });
        },
        searchMerchant: function () {
            var url = "sys/dept/getMerchantList";
            var data = {"merchantName": vm.merchantName}
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(data),
                success: function (r) {
                    if (r.code === 0) {
                        vm.merchantList = r.data;
                    } else {
                        alert(r.msg);
                    }
                }
            })
        },
        select: function (merchant) {
            vm.device.merchantId = merchant.deptId;
            vm.device.merchantName = merchant.name;
            //不能正常显示....
            $("#merchantName").val(merchant.name);
            layer.close(vm.layerIndex);

        },
        query: function () {
            vm.reload();
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.device = {};
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
            var url = vm.device.id == null ? "device/save" : "device/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.device),
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
                    url: baseURL + "device/delete",
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
            $.get(baseURL + "device/info/" + id, function (r) {
                vm.device = r.device;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page
            }).trigger("reloadGrid");
        }
    }
});