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



function checkSelectionQuery(){
    var valueSelect=document.getElementById('statistic_Select').value;
    console.log(valueSelect);
    var formattedDate = formatDate();
    if (formattedDate==""){
        // code truy xuất dữ liệu không có date
       if (valueSelect==="0"){
        //code vẽ biểu đồ cho khoa
            SLTVKhoa();
        }
        else if( valueSelect==="1"){
            //code vẽ biểu đồ cho ngành 
        }
        else if( valueSelect==="2"){
            //code vẽ biểu đồ cho thiết bị  đã mượn
        }
        else{
            //code vẽ biểu đồ cho vi phạm  đã xử lý
        }
    }    
    else{
        //code truy xuất dữ liệu có date ( truyền date = formatted Date)
    }
    
}

function SLTVKhoa(){
        var labels = [];
        var data = [];

        for (var i = 0; i < khoaAndCount.length; i++) {
            var item = khoaAndCount[i];
            labels.push(item[0]); // Tên khoa
            data.push(item[1]);   // Số lượng
        }
        var ctx = document.getElementById('myChart').getContext('2d');

// Tạo biểu đồ
        var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: labels, // Tên khoa
            datasets: [{
                label: 'Số lượng', // Nhãn của dữ liệu
                data: data,        // Dữ liệu
                backgroundColor: 'rgba(54, 162, 235, 0.2)', // Màu nền của cột
                borderColor: 'rgba(54, 162, 235, 1)',       // Màu viền của cột
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true // Bắt đầu từ 0 trên trục y
                }
            }
        }
    });
}


