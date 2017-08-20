$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'card/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '商户ID', name: 'merchantId', index: 'user_id', width: 80},
            {label: 'ID卡号', name: 'code', index: 'code', width: 80},
            {label: '持卡人姓名', name: 'cardHoldName', index: 'cardHoldName', width: 80},
            {label: '持卡人电话', name: 'cardHoldPhone', index: 'cardHoldPhone', width: 80},
            {label: '剩余使用次数', name: 'count', index: 'count', width: 80},
            {
                label: '是否激活', name: 'isActive', index: 'is_active', width: 80,
                formatter: function (value, options, row) {
                    if (value === 1) {
                        return '<span">是</span>';
                    }
                    if (value === 0) {
                        return '<span">否</span>';
                    }
                }
            },
            {
                label: '是否绑定', name: 'isBind', index: 'is_bind', width: 80,
                formatter: function (value, options, row) {
                    if (value === 1) {
                        return '<span">是</span>';
                    }
                    if (value === 0) {
                        return '<span">否</span>';
                    }
                }
            },
            {label: '创建时间', name: 'createTime', index: 'create_time', width: 80},
            {label: '上次使用时间', name: 'lastUseTime', index: ' ', width: 80}
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
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        q: {
            code: null
        },
        showList: true,
        title: null,
        card: {},
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
            vm.card.merchantId = merchant.deptId;
            vm.card.merchantName = merchant.name;
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
            vm.card = {};
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
            var url = vm.card.id == null ? "card/save" : "card/update";
            $.ajax({
                type: "POST",
                url: baseURL + url,
                contentType: "application/json",
                data: JSON.stringify(vm.card),
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
                    url: baseURL + "card/delete",
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
            $.get(baseURL + "card/info/" + id, function (r) {
                vm.card = r.card;
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'code': vm.q.code
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});