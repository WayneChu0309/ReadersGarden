
$("div.twomap div#googlemap input").on("change", function(){
	var mode = $("select#mode").val();
	if (($(this).val().trim().length != 0) && mode != 0){
		$(this).siblings("button").attr("disabled", false)
	} else {
		$(this).siblings("button").attr("disabled", true)
	}
})

$("div.twomap div#googlemap select").on("change", function(){
	var loc = $("input#findloc").val().trim().length
	if (($(this).val() != 0) && loc != 0) {
		$(this).siblings("button").attr("disabled", false)
	} else {
		$(this).siblings("button").attr("disabled", true)
	}
})

$("div.twomap div#googlemap input").on("focus", function(){
	$(this).attr("placeholder", "輸入出發地...");
})

$("button#loc").on("click", function(){
  var loc = $("input#findloc").val();
  var mode = $("select#mode").val();
  initMap();
  searchMap(loc, mode)
})

var map;

function searchMap(loc, mode) {

  let directionsService = new google.maps.DirectionsService;
  let request = {
      origin: loc,
      destination: {
      //緯度
      lat: 25.052312654290784,
      //經度
      lng: 121.54324216847596,
      },
      travelMode: mode
    };
    directionsService.route(request, function(response) {
      if (response["status"] == 'OK') {
        let directionsDisplay = new google.maps.DirectionsRenderer({
            map: map,
            directions: response,
            polylineOptions: {
              strokeColor: 'red'
            },
            suppressMarkers: false,
            draggable: true
        })     
      } else {
    	  $("input#findloc").val("");
    	  $("input#findloc").attr("placeholder", "***出發地無法辨識***");
    	  $("select#mode").val("0");
      }
    });    
  }


$("section#map ul.indoor li").on("click", function(){
    if ($(this).filter(".googlemap").length == 1) {
        $(this).closest("ul").removeClass("check");
        $("section#map div#maparea").css("height", "500px");
        $("section#map div#maparea span").each(function(){
            $(this).css("animation", "none");
        })
            
        $("section#map div#maparea").css("transform", "rotateY(-180deg)");
        $("section#map div#googlemap").css("transform", "rotateY(0deg) translateX(5%)");
    } else {
        $("section#map div#maparea").css("height", "auto");
        $(this).closest("ul").toggleClass("check", true);
        $("section#map div#maparea").css("transform", "rotateY(0deg)");
        $("section#map div#googlemap").css("transform", "rotateY(180deg)");
        $("section#map div#maparea span").each(function(){
            var classname = $(this).attr("class")
            if (classname.substr(0, 6) === "flower") {
                $(this).css("animation", "flower 2s infinite");
            }

            if (classname.substr(0, 4) === "tree") {
                $(this).css("animation", "tree 3s infinite");
            }

            if (classname === "mainhouse") {
                $(this).css("animation", "animat 5s infinite");
            }
        })
    }
})


$("section#map ul.indoor a").each(function(){
    $(this).on("click", function(){
        var tagclassname = $(this).attr("class")
        $("section#map div.twomap div#maparea span").css("background-color","transparent");
        $(`section#map div.twomap div#maparea span.${tagclassname}`).css("background-color","rgba(255,0,0,0.3)");
    })
})

var map;
// 用來找id="map"
// 初始化地圖
function initMap() {
map = new google.maps.Map(document.getElementById("showgooglemap"), {
    //地圖中心位置
    center: {
    //緯度
    lat: 25.052312654290784,
    //經度
    lng: 121.54324216847596
    },
    //縮放比
    zoom: 16
    });

    //建立InfoWindow物件

    iWindow = new google.maps.InfoWindow();

    //繪製標記與提示訊息(這個是自訂的函數)

    genInfoMark();

}

// 繪製
function genInfoMark() { 
    //自訂標記的圖
    var iconUrl = iconurl; 
    //標記的座標
    var placeLoc = {
        lat: 25.052312654290784,
        lng: 121.54324216847596
    };
    //繪製標記  
    var mkx = new google.maps.Marker({
        position: placeLoc, //座標
        map: map, //地圖DOM
        title: 'Aidec Test Mark', //標記的標題
        icon: iconUrl //標記圖
    }); 
    //使marker可以點擊 觸發infowindow 生成框 設定內容
    google.maps.event.addListener(mkx, 'click', function() {
      //設定的內容可以為HTML
      iWindow.setContent("<H1 style='font-size: 24px;'>讀者花園</H1>");
      iWindow.open(map, this);
    });
  }