/**
 * @author vudoanthang (http://codecanyon.net/user/vudoanthang)
 * @version 1.1
 */
(function($) {
	var _config = {};
	
	var _condition = {};
	
	var _data = {};
	
	var xTable = function() {
		if (typeof xTable.instance === 'object') {
			return xTable.instance;
		}
		xTable.instance = this;
	}

	xTable.prototype.init = function(id,config) {
		_condition[id] = {};
		_config[id] = $.extend(true, {}, config);
		
		var object = $('<div></div>');
		object
		.attr({id:'x-table-'+id})
		.addClass('x-table');
		
		if (typeof config.theme == 'string'){
			object.addClass(config.theme);
		}
		
		$('#'+id).append(object);
		
		_condition[id] = {};
		if (config.order != undefined && 
			config.order.column != undefined 
			&& config.order.type != undefined){
			_condition[id].order_column = config.order.column;
			_condition[id].order_type = config.order.type;
			if (config.pagination != undefined){
				_condition[id].record_per_page = (config.pagination.record_per_page != undefined)?config.pagination.record_per_page:20;
			}
		}
		
		_condition[id].page_index = 1;
		
		return object;
	}
	xTable.prototype.createTitle = function(id,object,config) {
		if (config.title != undefined){
			object
			.append(
					$('<div></div>')
					.attr({id:'x-table-header-title-'+id})
					.addClass('title')
					.append(config.title)
			);
		}else{
			object.css({border:'0px',padding:'0px'});
		}
		return object;
	}
	xTable.prototype.createToolbar = function(id,object, config) {
		var config = _config[id];
		if (config.toolbar != undefined){
			var toolbar = $('<div></div>')
							.addClass('toolbar')
							.attr({id:'#x-table-toolbar-'+id});
			if (typeof config.toolbar.align == 'string'){
				toolbar.css({textAlign:config.toolbar.align});
			}
			if (config.toolbar.buttons != undefined){
				for(var k in config.toolbar.buttons){
					var button = $('<button></button>');
					if (typeof config.toolbar.buttons[k].icon == 'string'){
						button.append('<i class="'+config.toolbar.buttons[k].icon+'">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>');
					}
					button.append(config.toolbar.buttons[k].text);
					if (typeof config.toolbar.buttons[k].script == 'function'){
						button.each(function(){
							config.toolbar.buttons[k].script(this,id);
						});
					}
					toolbar.append(button);
				}
			}
			object.append(toolbar);
			
		}
		return object;
	}
	xTable.prototype.createHeader = function(id,object, config) {
		var _this = this;
		object
		.append(
				$('<div></div>')
				.attr({id:'x-table-header-'+id})
				.addClass('header')
				.append(
						$('<div></div>')
						.attr({id:'x-table-header-box-'+id})
						.addClass('box')
						.css({paddingRight:'17px'})
						.append(
								$('<table></table>')
								.attr({
									id:'x-table-header-table-'+id,
									cellpadding:0,
									cellspacing:0,
									border:0
								})
								.width(50)
								.append(
										$('<thead></thead>')
										.append($('<tr></tr>'))
								)
						)
				)
		);
		
		for(var column in config.columns){
			var header = (config.columns[column].header != undefined)?config.columns[column].header:'';
			var th = $('<th></th>');
			if (config.columns[column].width != undefined){
				th.width(config.columns[column].width);
			}
			if (config.columns[column].resize == false){
				th.addClass('fixed-width');
			}
			var dth = $('<div></div>');
			if (config.order != undefined &&
				config.order.column != undefined && 
				config.order.type != undefined){
				if (config.columns[column].name == config.order.column){
					switch (config.order.type.toLowerCase()){
						case 'asc':
							dth.addClass('up');
							break;
						case 'desc':
							dth.addClass('down');
							break;
					}
					
				}
			}
			if (config.columns[column].name != undefined && 
				config.columns[column].order == true){
				th
				.css({cursor:'pointer'})
				.click(function(){
					var type = 'DESC';
					var column = $(this).attr('name');
					if ($(this).children().hasClass('up')){
						$(this).children().removeClass('up');
						$(this).children().addClass('down');
					}else{
						$('div[name="x-table-header-title-'+id+'"]').removeClass('up').removeClass('down');
						$(this).children().addClass('up');
						type = 'ASC';
					}
					
					
					_condition[id].order_column = column;
					
					_condition[id].order_column = column;
					_condition[id].order_type = type;
					_condition[id].page_index = parseInt($('#x-table-pagination-page-index-'+id).val());
					
					_this.loadData(id,object,config);
				});
			}
			
			th
			.attr({name:config.columns[column].name})
			.append(
					dth
					.attr({name:'x-table-header-title-'+id})
					.append(
							$('<div></div>')
							.css({overflow: 'hidden'})
							.append(header)
					)
			);
			$('#x-table-header-table-'+id+' > thead > tr')
			.append(th);
		}
		return object;
	}
	
	xTable.prototype.createSlider = function(id,object, config){
		var _this = this;
		$('#x-table-header-table-'+id+' > thead > tr >th').each(function(index){
			var _index = index;
			var position = $(this).offset().left+$(this).outerWidth() ;
			var slider = $('<div class="draghandle"><span /></div>')
			.attr({id:'x-table-drag-'+id})
			.css({left:position-3,height: 22})
			.insertBefore('#x-table-header-table-'+id);
			
			if ($(this).hasClass('fixed-width') == false){
				slider.mousedown(function(){
					_this.startDrag($(this),_index,id);
				});
				$(document).mousemove(function (e) {
					_this.dragMove(e.clientX);
				});
				$(document).mouseup(function (e) {
					_this.stopDrag();
				});
			}else{
				slider.hide();
			}
		});
		
		return object;
	}
	
	xTable.prototype.createBody = function(id,object, config) {
		var _this = this;
		object.append(
				$('<div></div>')
				.attr({id:'x-table-body-'+id})
				.addClass('body')
				.append(
						$('<div></div>')
						.attr({id:'x-table-body-box-'+id})
						.addClass('box')
						.append(
								$('<table></table>')
								.attr({
									id:'x-table-body-table-'+id,
									cellpadding:0,
									cellspacing:0,
									border:0
								})
								.width(50)
								.append($('<tbody></tbody>'))
						)
				)
		);
		$('#x-table-body-'+id).scroll(function(){
			$('#x-table-header-'+id).scrollLeft($(this).scrollLeft());
			_this.setSliderPosition(id);
		});
		return object
	}
	xTable.prototype.createPagination = function(id,object, config) {
		if (config.pagination == undefined){
			return object;
		}
		var config = _config[id];
		var _this = this;
		var btnFirst = $('<div/>').attr({id:'x-table-pagination-button-first-'+id}).addClass('first');
		var btnPrev = $('<div/>').attr({id:'x-table-pagination-button-prev-'+id}).addClass('prev');
		var btnNext = $('<div/>').attr({id:'x-table-pagination-button-next-'+id}).addClass('next');
		var btnLast = $('<div/>').attr({id:'x-table-pagination-button-last-'+id}).addClass('last');
		
		
		btnFirst.click(function(){
			if (parseInt($('#x-table-pagination-page-index-'+id).val()) > 1){
				_condition[id].page_index = 1;
				_this.loadData(id,object,config);
			}
		});
		
		btnPrev.click(function(){
			if (parseInt($('#x-table-pagination-page-index-'+id).val()) > 1){
				var pageIndex = parseInt($('#x-table-pagination-page-index-'+id).val());
				_condition[id].page_index = ((pageIndex > 1)?pageIndex-1:1);
				_this.loadData(id,object,config);
			}
		});
		
		btnNext.click(function(){
			if (parseInt($('#x-table-pagination-total-page-'+id).text()) > parseInt($('#x-table-pagination-page-index-'+id).val())){
				var pageIndex = parseInt($('#x-table-pagination-page-index-'+id).val());
				var totalPage = parseInt($('#x-table-pagination-total-page-'+id).html());
				_condition[id].page_index = ((pageIndex < totalPage)?pageIndex+1:totalPage);
				_this.loadData(id,object,config);
			}
		});
		
		btnLast.click(function(){
			if (parseInt($('#x-table-pagination-total-page-'+id).text()) > parseInt($('#x-table-pagination-page-index-'+id).val())){
				_condition[id].page_index = $('#x-table-pagination-total-page-'+id).html();
				_this.loadData(id,object,config);
			}
		});
		
		object
		.append(
				$('<div></div>')
				.addClass('pagination')
				.attr({id:'#x-table-pagination-'+id})
				.append(
						$('<div></div>')
						.addClass('control')
						.append(
								$('<table  border="0" cellpadding="0" cellspacing="0" ></table>')
								.append(
										$('<tbody></tbody>')
										.append(
												$('<tr valign="middle"></tr>')
												.append('<td><div><div class="separate"><span/></div></div></td>')
												.append(
														$('<td></td>')
														.append(
																$('<div></div>')
																.append(btnFirst)
														)
												)
												.append(
														$('<td></td>')
														.append(
																$('<div></div>')
																.append(btnPrev)
														)
												)
												.append('<td><div><div class="separate"><span/></div></div></td>')
												.append('<td><div><span> Page: </span></div></td>')
												.append('<td><input type="text" value="1" id="x-table-pagination-page-index-'+id+'"/></td>')
												.append('<td><div> of <span id="x-table-pagination-total-page-'+id+'"></span> </div></td>')
												.append('<td><div><div class="separate"><span/></div></div></td>')
												.append(
														$('<td></td>')
														.append(
																$('<div></div>')
																.append(btnNext)
														)
												)
												.append(
														$('<td></td>')
														.append(
																$('<div></div>')
																.append(btnLast)
														)
												)
										)
								)
						)
				)
				.append('<span class="info" id="x-table-pagination-info-'+id+'"></span>')
		);
		
		$('#x-table-pagination-page-index-'+id).keypress(function(e){
			if (e.keyCode == 13){
				var total_page = parseInt($('#x-table-pagination-total-page-'+id).text());
				var page_index = parseInt($('#x-table-pagination-page-index-'+id).val());
				if (page_index > total_page){
					_condition[id].page_index = total_page;
				}else{
					_condition[id].page_index = page_index;
				}
				_this.loadData(id,object,config);
			}
		});
		return object;
	}
	
	xTable.prototype.filter = function(arr,id){
		var conditions = _condition[id].conditions;
		return jQuery.grep(arr, function(n, i){
			var _obj = arr[i];
			var flg = true;
			if (conditions != undefined){
				for(var i in conditions){
					if (_obj[i] != undefined && _obj[i] != '' && conditions[i] != undefined && conditions[i] != ''){
						switch (typeof _obj[i]){
							case 'string':
								conditions[i] = conditions[i]+"";
								if (_obj[i].toLowerCase().indexOf($.trim(conditions[i].toLowerCase())) == -1){
									flg = false;
									break;
								}
								break;
							case 'number':
								if (_obj[i] != conditions[i]){
									flg = false;
									break;
								}
								break;
						}
					}
				}
			}
			return flg;
		});
	};
	
	xTable.prototype.paginate = function(id,total_page,total_record,record_per_page,page_index) {
		var config = _config[id];
		if (config.pagination != undefined && config.pagination.message != undefined){
			var _begin_record = (page_index - 1)*record_per_page + 1;
			var _end_record = page_index*record_per_page;
			if (_end_record > total_record){
				_end_record = total_record;
			}
			$('#x-table-pagination-info-'+id).html(this.printf(config.pagination.message,_begin_record,_end_record,total_record));
		}
		
		$('#x-table-pagination-total-record-'+id).html(total_record);
		$('#x-table-pagination-total-page-'+id).html(total_page);
		
		$('#x-table-pagination-page-index-'+id).val(page_index);
		if (page_index == 1){
			$('#x-table-pagination-button-first-'+id).css({cursor:'default',opacity:'0.4',filter:'alpha(opacity=40)'});
			$('#x-table-pagination-button-prev-'+id).css({cursor:'default',opacity:'0.4',filter:'alpha(opacity=40)'});
		}else{
			$('#x-table-pagination-button-first-'+id).css({cursor:'pointer',opacity:'1',filter:'alpha(opacity=100)'});
			$('#x-table-pagination-button-prev-'+id).css({cursor:'pointer',opacity:'1',filter:'alpha(opacity=100)'});
		}
		
		if (page_index >= total_page){
			$('#x-table-pagination-button-next-'+id).css({cursor:'default',opacity:'0.4',filter:'alpha(opacity=40)'});
			$('#x-table-pagination-button-last-'+id).css({cursor:'default',opacity:'0.4',filter:'alpha(opacity=40)'});
		}else{
			$('#x-table-pagination-button-next-'+id).css({cursor:'pointer',opacity:'1',filter:'alpha(opacity=100)'});
			$('#x-table-pagination-button-last-'+id).css({cursor:'pointer',opacity:'1',filter:'alpha(opacity=100)'});
		}
	}
	
	xTable.prototype.loadData = function(id,object, config) {
		var config = _config[id];
		var loading_message = (config.loading_message != undefined)?config.loading_message:'Loading...';
		this.mask(id,loading_message);
		if (typeof _data[id] == 'object'){
			$('#x-table-body-table-'+id+' > tbody > tr').remove();
			this.buildData(id,object,config,_data[id]);
			this.unmask(id);
		}else{
			switch (config.type){
				case 'xml':
					this._loadXmlData(id,object,config);
					break;
				default:
					this._loadJsonData(id,object,config);
					break;
			}
			
		}
		
		return object;
	}
	
	xTable.prototype.buildData = function(id,object,config,o){
		var config = _config[id];
		 o.rows = this.filter(o.rows,id);
		 if (_condition[id].order_type != undefined){
			 if (_condition[id].order_type.toLowerCase() == 'asc'){
				 o.rows.sort(this.asc(_condition[id].order_column));
			 }else{
				 o.rows.sort(this.desc(_condition[id].order_column));
			 }
		}
		 if (config.pagination != undefined && config.pagination.record_per_page != undefined){
			 var data = o.rows.slice((_condition[id].page_index - 1)*config.pagination.record_per_page,_condition[id].page_index*config.pagination.record_per_page);
		 }else{
			 var data = o.rows;
		 }
		
		for(var k in data){
			var tr = $('<tr></tr>');
			var o1 = data[k];

			if (o1['id'] != undefined){
				tr.attr({id:o1['id']});
			}
			tr = this.createRow(id,object,config,o1,tr);
			$('#x-table-body-table-'+id+' > tbody')
			.append(tr);
		}
		if (config.pagination != undefined && config.pagination.record_per_page != undefined){
			var total_page = Math.ceil(o.rows.length/config.pagination.record_per_page) ;
			this.paginate(id,total_page,o.rows.length,config.pagination.record_per_page,_condition[id].page_index);
		}
		$('#x-table-header-'+id).scrollLeft($('#x-table-body-'+id).scrollLeft());
		this.resetWidth(id,object,config);
		this.setSliderPosition(id);
	}
	
	xTable.prototype.createRow = function(id,object,config,o1,tr){
		for(var k1 in config.columns){
			var td = $('<td></td>');
			var dtd = $('<div></div>');
			if (config.columns[k1].align != undefined){
				dtd.css({textAlign:config.columns[k1].align});
			}
			if (config.columns[k1].name != undefined){
				dtd.attr({id:config.columns[k1].name});
			}
			if (config.columns[k1].data != undefined){
				switch (typeof config.columns[k1].data){
					case 'function':
						var _f = config.columns[k1].data(o1,id,tr);
						dtd.append(_f);
						break;
					default:
						dtd.append(config.columns[k1].data);
						break;
				}
			}else{
				dtd.html(o1[config.columns[k1].name]);
			}
			tr.append(td.append(dtd));
		}
		return tr;
	}
	
	xTable.prototype._loadXmlData = function (id,object, config){
		if (config.url != undefined){
			var _this = this;
			$.post(config.url,{'x-table':_condition[id]},function(xml){
				var o = _this.xml2Json(xml);
				_data[id] = {rows:o};
				$('#x-table-body-table-'+id+' > tbody > tr').remove();
				_this.buildData(id,object,config,_data[id]);
				_this.unmask(id);
			},'xml');
		}else if (config.rows != undefined){
			if (typeof config.rows == 'object'){
				_data[id] = {rows:config.rows};
			}else{
				var o = this.xml2Json(config.rows);
				_data[id] = {rows:o};
			}
			$('#x-table-body-table-'+id+' > tbody > tr').remove();
			this.buildData(id,object,config,_data[id]);
			this.unmask(id);
		}
	}
	
	xTable.prototype._loadJsonData = function(id,object, config){
		var _this = this;
		if (config.url != undefined){
			$.post(config.url,{'x-table':_condition[id]},function(o){
				if (o.rows == undefined){
					_data[id] = {rows:o};
				}else{
					_data[id] = o;
				}
				$('#x-table-body-table-'+id+' > tbody > tr').remove();
				_this.buildData(id,object,config,_data[id]);
				_this.unmask(id);
			},'json');
		}else if (config.rows != undefined){
			if (typeof config.rows == 'object'){
				_data[id] = {rows:config.rows};
			}else{
				var o = this.xml2Json(config.rows);
				_data[id] = {rows:o};
			}
			$('#x-table-body-table-'+id+' > tbody > tr').remove();
			this.buildData(id,object,config,_data[id]);
			this.unmask(id);
		}
	}
	
	xTable.prototype.resetWidth = function(id,object,config){
		$('#x-table-header-table-'+id+' > thead > tr >th').each(function(index){
			var bWidth = 1;
			if ($.browser.msie && $.browser.version < 8){
				bWidth = (index == $('#x-table-header-table-'+id+' > thead > tr >th').length -1)?2:bWidth;
				$($('#x-table-body-table-'+id+' > tbody > tr >td')[index]).width($(this).outerWidth() - bWidth);
			}else if($.browser.webkit){
				if (/chrome/.test(navigator.userAgent.toLowerCase())){
					if (index ==0) bWidth--;
					$($('#x-table-body-table-'+id+' > tbody > tr >td')[index]).width($(this).outerWidth() - bWidth);
				}else{
                                        if (index ==0) bWidth--;
					$($('#x-table-body-table-'+id+' > tbody > tr >td')[index]).width($(this).outerWidth() - bWidth);
				}
			}else if($.browser.opera){
				if (index ==0) bWidth--;
				$($('#x-table-body-table-'+id+' > tbody > tr >td')[index]).width($(this).outerWidth() - bWidth);
			}else{
				$($('#x-table-body-table-'+id+' > tbody > tr >td')[index]).width($(this).outerWidth() - bWidth);
			}
		});
	}
	
	xTable.prototype.mask = function(id, label){
		var target = $('#x-table-body-'+id);
		if(target.hasClass("masked")) {
			this.unmask(id);
		}
		if(target.css("position") == "static") {
			target.addClass("masked-relative");
		}
		target.addClass("masked");
		var maskDiv = $('<div class="loadmask"></div>');
		
		maskDiv.height(target.height() + parseInt(target.css("padding-top")) + parseInt(target.css("padding-bottom")) + target.scrollTop());
		maskDiv.width(target.width() + parseInt(target.css("padding-left")) + parseInt(target.css("padding-right")) + target.scrollLeft());
		
		target.append(maskDiv);
		if(label !== undefined) {
			var maskMsgDiv = $('<div class="loadmask-msg" style="display:none;"></div>');
			maskMsgDiv.append('<div>' + label + '</div>');
			target.append(maskMsgDiv);
			var top = Math.round(target.height() / 2 + target.scrollTop() - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2);
			var left = Math.round(target.width() / 2 + target.scrollLeft() - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2);
			maskMsgDiv.css({top:top,left:left}).show();
		}
	}
	
	xTable.prototype.unmask = function(id){
		var target = $('#x-table-body-'+id);
		target.find(".loadmask-msg,.loadmask").remove();
		target.removeClass("masked");
		target.removeClass("masked-relative");
		target.find("select").removeClass("masked-hidden");
	}
	
	xTable.prototype.dragOffset = {};
	
	xTable.prototype.startDrag = function(o,index,id){
		var l = o.offset().left;
		this.dragOffset = {target:o,left:l,index:index,targetId:id};
		$(o).toggleClass( "dragged" );
  		$(o).prev('.draghandle').addClass( "dragged" );
  		$(this.dragOffset.target).prev('.draghandle').addClass( "dragged" );
  		var h = 0;
  		$(o).css({height: h+22});
  		$(o).prev('.draghandle').css({height: h+22});
	};
	
	xTable.prototype.dragMove = function(left){
		if (this.dragOffset.target != undefined){
			if (window.getSelection) window.getSelection().removeAllRanges();
			else if (document.selection) document.selection.empty();
			this.dragOffset.target.css({left:left-4});
		}
	};
	xTable.prototype.stopDrag = function(){
		if (this.dragOffset.target != undefined){
			var oldPos  = this.dragOffset.left;
			var newPos = this.dragOffset.target.offset().left;
		    $(this.dragOffset.target).removeClass( "dragged" );
		    $(this.dragOffset.target).prev('.draghandle').removeClass( "dragged" );
		    this.resetHeaderColumn(newPos-oldPos-2, this.dragOffset.index,this.dragOffset.targetId);
		    $(this.dragOffset.target).css({height: 22});
		    $(this.dragOffset.target).prev('.draghandle').css({height:22});
		    this.dragOffset = {};
		}
	};
	
	xTable.prototype.resetHeaderColumn = function(change,index,id){
		var colWidth = $('#x-table-header-table-'+id+' > thead > tr >th')[index].offsetWidth;
		var tableWidth = $('#x-table-header-table-'+id)[0].offsetWidth;
		if (colWidth+change > 30){
			var newColWidth = (colWidth+change);
		}else{
			newColWidth = 30;
			change = newColWidth - colWidth;
		}
		$('#x-table-header-box-'+id).width(tableWidth + change);
		$('#x-table-body-box-'+id).width(tableWidth + change);
		$($('#x-table-header-table-'+id+' > thead > tr >th')[index]).width(Math.ceil(newColWidth)-1);
		
		
		this.resetWidth(id);
		$('#x-table-header-'+id).scrollLeft($('#x-table-body-'+id).scrollLeft());
		this.setSliderPosition(id);
	};
	xTable.prototype.setSliderPosition = function(id){
		$('#x-table-header-table-'+id+' > thead > tr >th').each(function(index){
			var position = $(this).offset().left+$(this).outerWidth() ;
			$($('div[id="x-table-drag-'+id+'"]')[index]).css({left:position-3,height: 25});
		});
	};
	
	xTable.prototype.printf = function(string){
		if (arguments.length < 2) { return string; }
			for (var i=1; i<arguments.length; i++)
			{ string = string.replace(/%s/, arguments[i]); }
		return string;
	};
	
	xTable.prototype.asc = function (property) {
	    return function (a,b) {
	        return (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
	    };
	};
	xTable.prototype.desc = function (property) {
	    return function (b,a) {
	        return (a[property] < b[property]) ? -1 : (a[property] > b[property]) ? 1 : 0;
	    };
	};
	xTable.prototype.xml2Json = function(xml){
		var o = [];
		$(xml).find("row").each(function(){
			var tmp = {};
			$(this).children().each(function(){
				switch(this.nodeName.toLowerCase()){
					case 'id':
						tmp[this.nodeName] = parseInt($(this).text());
						break;
					default:
						tmp[this.nodeName] = $(this).text();
						break;
				}
			});
			o[o.length] = tmp;
		});
		return o;
	};

	xTable.decorators = {};
	xTable.prototype.decorate = function(decorator) {
		var F = function() {
		}, overrides = this.constructor.decorators[decorator], i, newobj;
		F.prototype = this;
		newobj = new F();
		newobj.uber = F.prototype;
		for (i in overrides) {
			if (overrides.hasOwnProperty(i)) {
				newobj[i] = overrides[i];
			}
		}
		return newobj;
	};
	
	xTable.decorators.remote = {
		buildData:function(id,object,config,o){
			var config = _config[id];
			var data = o.rows;
			
			for(var k in data){
				var tr = $('<tr></tr>');
				var o1 = data[k];

				if (o1['id'] != undefined){
					tr.attr({id:o1['id']});
				}
				tr = this.createRow(id,object,config,o1,tr);
				$('#x-table-body-table-'+id+' > tbody')
				.append(tr);
			}
			if (config.pagination != undefined && config.pagination.record_per_page != undefined){
				var total_page = o.total_page;
				this.paginate(id,total_page,o.total_row,config.pagination.record_per_page,_condition[id].page_index);
			}
			$('#x-table-header-'+id).scrollLeft($('#x-table-body-'+id).scrollLeft());
			this.resetWidth(id,object,config);
			this.setSliderPosition(id);
		},
		loadData:function(id,object, config) {
			var config = _config[id];
			var loading_message = (config.loading_message != undefined)?config.loading_message:'Loading...';
			this.mask(id,loading_message);
			switch (config.type){
				case 'xml':
					this._loadXmlData(id,object,config);
					break;
				default:
					this._loadJsonData(id,object,config);
					break;
			}
				
			return object;
		}
	};
	

	$.fn.xTable = function(config,conditions) {
		var id = $(this).attr('id');
		var type = config;
		if (_config[id] != undefined){
			config = _config[id];
		}
		var t = new xTable();
		if (typeof(config.pakages)=='object'&&(config.pakages instanceof Array)){
			for(var i in config.pakages){
				t = t.decorate(config.pakages[i]);
			}
		}
		
		if (conditions == undefined) conditions = undefined;
		switch (typeof type){
			case 'string':
				var object = $('#x-table-'+id);
				switch (type){
					case 'reload':
						_data[id] = undefined;
                                                _condition[id].page_index = 1;
                                                _condition[id].order_column = _config[id].order.column;
                                                _condition[id].order_type = _config[id].order.type;

                                                $('#x-table-header-table-'+id+' > thead > tr > th > div').removeClass('up');
                                                $('#x-table-header-table-'+id+' > thead > tr > th > div').removeClass('down');
                                                switch (_condition[id].order_type.toLowerCase()){
                                                    case 'asc':
                                                            $('th[name="'+_condition[id].order_column+'"]').children().addClass('up');
                                                            break;
                                                    case 'desc':
                                                            $('th[name="'+_condition[id].order_column+'"]').children().addClass('down');
                                                            break;
                                                }
						t.loadData(id,object,config);
						break;
					case 'conditions':
						switch (typeof conditions){
							case 'object':
								_condition[id].conditions = $.extend(true,{}, conditions);
							break;
							case 'undefined':
								return (_condition[id].conditions != undefined)?_condition[id].conditions:{};
							break;
						}
						break;
				}
				break;
			case 'object':
				return $(this).each(function(){
					var object = t.init(id,config);
					object = t.createTitle(id,object,config);
					object = t.createToolbar(id,object,config);
					object = t.createHeader(id,object,config);
					object = t.createSlider(id,object,config);
					object = t.createBody(id,object,config);
					object = t.createPagination(id,object,config);
					t.loadData(id,object,config);
				});
				break;
		}
	};
})(jQuery);