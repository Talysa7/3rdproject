<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script type="text/javascript">
	//<!--
    String.prototype.replaceAll = function(org, dest) {
	    return this.split(org).join(dest);
	}
    $(document).ready(function() {
		 $.ajax({
			url : '/3rdProject/csv/cityInfo.csv',
			dataType : 'text',
			success : function(data){
				var lines = data.split("\n");
                var csvContent = ''
                var cityInfos = []
				for (var i = 0; i<lines.length-1; i++){
					var column = lines[i].split(",");
                    var cityInfo = []
                    cityInfo[0] = column[0]
                    cityInfo[1] = column[1]
                    var lat  = column[2].replaceAll("\"" ,'')
                    var longti = column[3].replaceAll("\"" ,'')
                    cityInfo[3] =  "\"" + parseFloat(lat).toFixed(6) +  "\""
                    cityInfo[2] =  "\"" + parseFloat(longti).toFixed(6) +  "\""
                    cityInfos[i] = cityInfo
				}
				

                cityInfos.forEach(function(cityInfo){
                    var cont = cityInfo.join(",");
                    csvContent += cont + "\n"
                })
                var hiddenElement = document.createElement('a');
                hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csvContent);
                hiddenElement.target = '_blank';
                hiddenElement.download = 'editCity.csv';
                hiddenElement.click();
                }
		}) 
	})

    //-->
</script>