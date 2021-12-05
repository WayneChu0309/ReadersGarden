<html>
<head><meta charset="utf-8"></head>
<body>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <!-- 若需相容 IE11，要加載 Promise Polyfill-->
    <script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
    <button onclick='alertTest()'>Alert</button>
    <button onclick='confirmTest()'>Confirm</button>
    <script>
        function alertTest() {
        	 
        	Swal.fire({
        	  title: '帳號或密碼錯誤',
        	  showClass: {
        	    popup: 'animate__animated animate__fadeInDown'
        	  },
        	  hideClass: {
        	    popup: 'animate__animated animate__fadeOutUp'
        	  }
        	})
        }
        
    </script>
</body>
</html>
