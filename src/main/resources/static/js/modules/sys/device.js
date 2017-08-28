$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'device/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '设备编码', name: 'code', index: 'code', width: 80},
            {label: '所属商户', name: 'merchantName', index: 'merchant_name', width: 100},
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
                    } else {
                        return '<span>未知</span>'
                    }
                }
            },
            {
                label: '设备使用状态', name: 'useStatus', index: 'use_status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span">未使用</span>';
                    }
                    if (value === 1) {
                        return '<span">使用中</span>';
                    } else {
                        return '<span>未知</span>'
                    }
                }
            },
            {
                label: '是否故障', name: 'isBreakdown', index: 'is_breakdown', width: 80,
                formatter: function (value, options, row) {
                    if (value === 0) {
                        return '<span class="label label-success">正常</span>';
                    }
                    if (value === 1) {
                        return '<span class="label label-danger">故障</span>';
                    } else {
                        return '<span class="label label-info">未知</span>'
                    }
                }
            },
            {label: '操作时间', name: 'operateTime', index: 'operate_time', width: 80}
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


});

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url: "nourl"
        }
    }
};
var ztree;

var vm = new Vue({
    el: '#rrapp',

    data: {
        showList: true,
        title: null,
        device: {},
        merchantList: [],
        merchantName: "",
        layerIndex: null,

        q: {
            code: null,
            merchantName: null,
            merchantId: null
        },
        dept: {
            parentName: null,
            parentId: 0,
            orderNum: 0
        }
    },
    methods: {
        selectMerchant: function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-lan',
                title: "绑定设备",
                area: ['600px', '450px'],
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
                postData: vm.q,
                page: page
            }).trigger("reloadGrid");
        },
        deptTree: function () {
            this.getDept();
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-lan',
                title: "选择部门",
                area: ['360px', '400px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.q.merchantId = node[0].deptId;
                    vm.q.merchantName = node[0].name;

                    layer.close(index);
                }
            });
        },
        getDept: function () {
            //加载部门树
            $.get(baseURL + "sys/dept/select", function (r) {
                ztree = $.fn.zTree.init($("#deptTree"), setting, r.deptList);
                var node = ztree.getNodeByParam("deptId", vm.dept.parentId);
                ztree.selectNode(node);

                vm.dept.parentName = node.name;
            })
        }
    }
});




