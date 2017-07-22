$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'accountenchashment/list',
        datatype: "json",
        colModel: [			
			{ label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: 'accout_info表中用户id对应', name: 'userId', index: 'user_id', width: 80 }, 			
			{ label: '支付时间', name: 'payAt', index: 'pay_at', width: 80 }, 			
			{ label: 'int类型金额以分为单位的金额', name: 'totalFee', index: 'total_fee', width: 80 }, 			
			{ label: '银行全名', name: 'bankFullName', index: 'bank_full_name', width: 80 }, 			
			{ label: '账户类型 区分对公和对私 P代表私户，C代表公户', name: 'accountType', index: 'account_type', width: 80 }, 			
			{ label: '收款方的银行卡号', name: 'accountNo', index: 'account_no', width: 80 }, 			
			{ label: '收款方的姓名或者单位名', name: 'accountName', index: 'account_name', width: 80 }, 			
			{ label: '0提现中，1提现成功', name: 'status', index: 'status', width: 80 }, 			
			{ label: '创建时间', name: 'createAt', index: 'create_at', width: 80 }, 			
			{ label: '卡类型 DE代表借记卡，CR代表信用卡，其他值为非法', name: 'cardType', index: 'card_type', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		accountEnchashment: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.accountEnchashment = {};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
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
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "accountenchashment/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get(baseURL + "accountenchashment/info/"+id, function(r){
                vm.accountEnchashment = r.accountEnchashment;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});