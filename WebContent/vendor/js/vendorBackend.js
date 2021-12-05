$(function(){
	
	$('.update').on('click', function(){
		var that = $(this);		
		
		if(that.hasClass('stop') || that.hasClass('okay')){
			return;
		}
		
		var vendorid = that.children('button').attr('data-vendorid');
		var status = that.children('button').text();

		console.log(vendorid);
		console.log(status);
		
		$.ajax({
			url: vendorStatus_url,
			type: "POST",
			data: {"vendorid": vendorid, "status": status},
			dataType: "json",
			success: function(res){				
				if(status == "停權"){
					that.toggleClass("stop");
					that.prev().toggleClass('okay');
				} else if (status == "正常"){
					that.toggleClass("okay");
					that.next().toggleClass('stop');
					
				}
			},
			
			error: function(res){
				console.log('error');
			}
				
		})
	})
	
	$('.allselect').on('click', function(){
		$(location).attr('href', vendorAll_url);
	})
	
	
})