function setDefaultDate() {
    // Lấy ngày hiện tại
    var today = new Date();

    // Format ngày hiện tại thành yyyy-MM-dd (định dạng mà trường input type="date" yêu cầu)
    var formattedDate = today.toISOString().substr(0, 10);

    // Thiết lập giá trị mặc định cho trường ngày
    document.getElementById("date").value = formattedDate;
}

// Gọi hàm để thiết lập giá trị mặc định khi trang được tải
setDefaultDate();

function formatDate() {
    var dateChoose = document.getElementById("date");
    var timestamp = new Date(dateChoose.value);
    var formattedDate = "";

    switch (valueRadioBtn()) {
        case "1":
            formattedDate = timestamp.toISOString().substring(0, 10);
            break;
        case "2":
            formattedDate = timestamp.toISOString().substring(0, 7);
            break;
        case "3":
            formattedDate = timestamp.toISOString().substring(0, 4);
            break;
        default:
            formattedDate = "";
            break;
    }

    return formattedDate;
}

function valueRadioBtn() {
    var radBtns = document.getElementsByName('time');
    for (var i = 0; i < radBtns.length; i++) {
        if (radBtns[i].checked) {
            return radBtns[i].value;
        }
    }
    return "";
}

function checkRadioBtnQuery() {
    var formattedDate = formatDate();
    console.log("Formatted Date: ", formattedDate);
    if (formattedDate==""){
        // code truy xuất dữ liệu không có date
        
    }    
    else{
        //code truy xuất dữ liệu có date ( truyền date = formatted Date)
    }
    //gán kết quả cho các thẻ h

}


function checkSelectionQuery(){
    var valueSelect=document.getElementById('statistic_Select').value;
    // console.log(valueSelect);
    if (valueSelect==="0"){
        //code vẽ biểu đồ cho khoa
    }
    else if( valueSelect==="1"){
        //code vẽ biểu đồ cho ngành 
    }
    else if( valueSelect==="2"){
        //code vẽ biểu đồ cho thiết bị đang mượn, đã mượn
    }
    else{
        //code vẽ biểu đồ cho vi phạm đang xử lý, đã xử lý
    }
}