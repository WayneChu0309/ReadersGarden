<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reader's Garden</title>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/home/css/map.css'>
</head>
<body>

<%@ include file="/parts/header.text" %>

<section id="map">
    <div class="wrap">
      <div class="kinds">
        <div class="libraryitems">
          <ul class="indoor check">
            <li>
              <a href="#">場館介紹</a>
              <ul>
                <li><a href="#" id="mainhouse">花園主館</a>
                  <ul>
                    <li><a href="#" class="mainhouse">讀者花園</a></li>
                  </ul>
                </li>
                <li><a href="#" id="artist">藝術空間</a>
                  <ul>
                    <li><a href="#" class="artist">藝術空間A區</a></li>
                    <li><a href="#" class="artist">藝術空間B區</a></li>
                    <li><a href="#" class="artist">藝術空間C區</a></li>
                    <li><a href="#" class="artist">藝術空間D區</a></li>
                    <li><a href="#" class="artist">神奇藝廊</a></li>
                  </ul>
                </li>
                <li><a href="#" id="view">視聽空間</a>
                  <ul>
                    <li><a href="#" class="view">視聽空間A區</a></li>
                    <li><a href="#" class="view">視聽空間B區</a></li>
                    <li><a href="#" class="view">日光通道</a></li>
                    <li><a href="#" class="view">白色世界</a></li>
                  </ul>
                </li>
                <li><a href="#" id="show">演藝空間</a>
                  <ul>
                    <li><a href="#" class="show">演藝A棟</a></li>
                    <li><a href="#" class="show">演藝B棟</a></li>
                    <li><a href="#" class="show">演藝C棟</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="#">戶外空間</a>
              <ul>
                <li><a href="#">商家</a>
                  <ul>
                    <li><a href="#" class="memento">紀念品店</a></li>
                    <li><a href="#" class="subway">Subway</a></li>
                    <li><a href="#" class="drink">麻古茶坊</a></li>
                    <li><a href="#" class="coffee">星巴克</a></li>
                    <li><a href="#" class="icecream">吧噗</a></li>
                  </ul>
                </li>
                <li><a href="#">公共設施</a>
                  <ul>
                    <li><a href="#" class="toliet">Toliet</a></li>
                    <li><a href="#" class="hospital">急救站</a></li>
                    <li><a href="#" class="resta">乘涼A座</a></li>
                    <li><a href="#" class="restb">乘涼B座</a></li>
                    <li><a href="#" class="restc">乘涼C座</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li class="googlemap"><a href="#">週邊景點</a></li>
          </ul>
        </div>
      </div>
    </div>

    <div class="twomap">
      <div id="maparea">
        <span class="mainhouse"> <img src="${pageContext.request.contextPath}/home/icon/library.svg" alt=""> </span>
        <span class="artist"><img src="${pageContext.request.contextPath}/home/icon/dome.svg" alt=""></span>
        <span class="view"><img src="${pageContext.request.contextPath}/home/icon/view.svg" alt=""></span>
        <span class="show"><img src="${pageContext.request.contextPath}/home/icon/show.svg" alt=""></span>
        <span class="memento"><img src="${pageContext.request.contextPath}/home/icon/shop.svg" alt=""></span>
        <span class="subway"><img src="${pageContext.request.contextPath}/home/icon/restaurant.svg" alt=""></span>
        <span class="coffee"><img src="${pageContext.request.contextPath}/home/icon/coffee-shop.svg" alt=""></span>
        <span class="icecream"><img src="${pageContext.request.contextPath}/home/icon/ice-cream-shop.svg" alt=""></span>
        <span class="drink"><img src="${pageContext.request.contextPath}/home/icon/drinks.svg" alt=""></span>
        <span class="toliet"><img src="${pageContext.request.contextPath}/home/icon/public-toilet.svg" alt=""></span>
        <span class="hospital"><img src="${pageContext.request.contextPath}/home/icon/hospital.svg" alt=""></span>
        <span class="resta"><img src="${pageContext.request.contextPath}/home/icon/tree_seat1.svg" alt=""></span>
        <span class="restb"><img src="${pageContext.request.contextPath}/home/icon/tree_seat2.svg" alt=""></span>
        <span class="restc"><img src="${pageContext.request.contextPath}/home/icon/tree_seat1.svg" alt=""></span>
        <span class="flower1"><img src="${pageContext.request.contextPath}/home/icon/flower1.svg" alt=""></span>
        <span class="flower2"><img src="${pageContext.request.contextPath}/home/icon/flower2.svg" alt=""></span>
        <span class="flower3"><img src="${pageContext.request.contextPath}/home/icon/flower3.svg" alt=""></span>
        <span class="tree1"><img src="${pageContext.request.contextPath}/home/icon/tree1.svg" alt=""></span>
        <span class="tree2"><img src="${pageContext.request.contextPath}/home/icon/tree2.svg" alt=""></span>
        <span class="tree3"><img src="${pageContext.request.contextPath}/home/icon/tree3.svg" alt=""></span>
        <span class="tree4"><img src="${pageContext.request.contextPath}/home/icon/tree4.svg" alt=""></span>
      </div>
      <div id="googlemap">
        <input id="findloc" type="text" placeholder="輸入出發地...">
        <select id="mode">
          <option value="0">選擇交通方式</option>
          <option value="DRIVING">開車</option>
          <option value="BICYCLING">腳踏車</option>
          <option value="TRANSIT">大眾運輸</option>
          <option value="WALKING">步行</option>
        </select>
        <button id="loc" type="button" disabled>檢查路線</button>
        <div id="showgooglemap">

        </div>
      </div>
    </div>
  </section>

<%@ include file="/parts/footer.text" %>
  <script src='${pageContext.request.contextPath}/home/js/map.js'></script>
 <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDQQwQe8l8XBZjGkNjzXUS_eKNFeZl8k88&libraries=places,drawing,geometry&v=3&callback=initMap">
 </script>
 <script>
 	var iconurl = "${pageContext.request.contextPath}/home/icon/1.png";
 </script>
 
 
</body>
</html>