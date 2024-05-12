var myChart; // Biến lưu trữ biểu đồ

var chart1;
var element = document.getElementById('change-title-chart');
var element1 = document.getElementById('change-year');

var getdate;

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

function formatDate(date) {
    var timestamp;
    if (date) {
        // Chuyển đổi ngày tháng thành đối tượng ngày
        timestamp = new Date(date);
    } else {
        // Nếu không có giá trị ngày tháng, sử dụng ngày hiện tại
        timestamp = new Date();
    }
    
    var formattedDate = "";

    switch (valueRadioBtn()) {
        case "1":
            formattedDate = timestamp.toISOString().substring(0, 10);
            getdate = formattedDate;
            break;
        case "2":
            formattedDate = timestamp.toISOString().substring(0, 7);
            getdate = formattedDate;
            break;
        case "3":
            formattedDate = timestamp.toISOString().substring(0, 4);
            getdate = formattedDate;
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
    var valueSelect = document.getElementById('statistic_Select').value;
    console.log(valueSelect);
    var selectedDate = document.getElementById('date').value; // Lấy giá trị ngày tháng đã chọn

    var formattedDate = formatDate(selectedDate); // Định dạng lại ngày tháng

    if (formattedDate === "") {
        // Code truy xuất dữ liệu không có ngày tháng
        if (valueSelect === "0") {
            if(chart1){
                chart1.destroy();
            }
            SLTVKhoa();
            element.innerHTML = 'Số lượng thành viên theo khoa';
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '; 
        } else if (valueSelect === "1") {
            // Code vẽ biểu đồ cho ngành
            if(chart1){
                chart1.destroy();
            }
            SLTVNganh();
            element.innerHTML = 'Số lượng thành viên theo ngành';
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
        } else if (valueSelect === "2") {
            // Code vẽ biểu đồ cho thiết bị đã mượn
            if(chart1){
                chart1.destroy();
            }
            SLTBMuon();
            element.innerHTML = 'Số lần thiết bị đã được mượn';
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
        } else {
            // Code vẽ biểu đồ cho vi phạm đã xử lý
            if(chart1){
                chart1.destroy();
            }
            SLVPham();
            element.innerHTML = 'Số lượng vi phạm đã xử lý';
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
            
            


            
        }
    } else {
        // Code truy xuất dữ liệu có ngày tháng (truyền date = formatted Date)
        if (valueSelect === "0") {
            if(chart1){
                chart1.destroy();
            }
            $.ajax({
                type: "POST",
                url: "/admin",
                contentType: "application/json",
                data: JSON.stringify({ time: formattedDate, action: "countTVKhoaTheoThoiGian"}),
                success: function(response) {
                    console.log("Success: " + response);
                    // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                    TVKhoaByTime = response;

                    // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                    SLTVKhoaByTime();
                },
                error: function(xhr, status, error) {
                    console.error("Error: " + error);
                }
            });
            element.innerHTML = 'Số lượng thành viên theo khoa vào khu học tập trong '+getdate;
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
        } else if (valueSelect === "1") {
            // Code vẽ biểu đồ cho ngành
            if(chart1){
                chart1.destroy();
            }
            $.ajax({
                
                type: "POST",
                url: "/admin",
                contentType: "application/json",
                data: JSON.stringify({ time: formattedDate, action: "countTVNganhTheoThoiGian"}),
                success: function(response) {
                    console.log("Success: " + response);
                    // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                    nganhAndCountByTime = response;

                    // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                    SLTVNganhByTime();
                },
                error: function(xhr, status, error) {
                    console.error("Error: " + error);
                }
            });
            element.innerHTML = 'Số lượng thành viên theo ngành vào khu học tập trong '+getdate;
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
        } else if (valueSelect === "2") {
            // Code vẽ biểu đồ cho thiết bị đã mượn
            if(chart1){
                chart1.destroy();
            }
            $.ajax({
                type: "POST",
                url: "/admin",
                contentType: "application/json",
                data: JSON.stringify({ time: formattedDate ,action: "countSoLanThietBiDuocMuonTheoThoiGian"}),
                success: function(response) {
                    console.log("Success: " + response);
                    // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                    TBmuonCountByTime = response;

                    // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                    SLTBMuonByTime();
                },
                error: function(xhr, status, error) {
                    console.error("Error: " + error);
                }
            });
            element.innerHTML = 'Số lần thiết bị đã được mượn trong '+getdate;
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm '
        } else if (valueSelect === "3") {
            // Code vẽ biểu đồ cho vi phạm đã xử lý
            $.ajax({
                type: "POST",
                url: "/adminyear",
                contentType: "application/json",
                data: JSON.stringify({ time: formattedDate, action: "countVPDaXuLyTheoNam"}),
                success: function(response) {
                    console.log("Success: " + response);
                    // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                    dataChart = response;
                    

                    // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                    VPDaXuLyTheoNam();
                },
                error: function(xhr, status, error) {
                    console.error("Error: " + error);
                }
            });
            element1.innerHTML = 'Số lượng vi phạm đã xử lý theo năm ' + getdate.substr(0, 4);
            element.innerHTML = 'Số lượng vi phạm đã xử lý trong '+getdate;
            $.ajax({
                type: "POST",
                url: "/admin",
                contentType: "application/json",
                data: JSON.stringify({ time: formattedDate, action: "countVPDaXuLyTheoThoiGian"}),
                success: function(response) {
                    console.log("Success: " + response);
                    // Cập nhật dữ liệu của biểu đồ từ phản hồi Ajax
                    ViPhamCountByTime = response;

                    // Gọi lại hàm tạo biểu đồ để vẽ lại biểu đồ với dữ liệu mới
                    countVPDaXuLyTheoThoiGian();
                },
                error: function(xhr, status, error) {
                    console.error("Error: " + error);
                }
            });
            
        }
        
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
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }
   
    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}
function SLVPham(){
    var labels = [];
    var data = [];

    for (var i = 0; i < ViPhamCount.length; i++) {
        var item = ViPhamCount[i];
        labels.push(item[0]); // Tên khoa
        data.push(item[1]);   // Số lượng
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }
   
    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}
function SLTVKhoaByTime(){
    var labels = [];
    var data = [];
    if (TVKhoaByTime !== null){
        for (var i = 0; i < TVKhoaByTime.length; i++) {
            var item = TVKhoaByTime[i];
            labels.push(item[0]); // Tên thiết bị
            data.push(item[1]);   // Số lần mượn
        }
    }else{
        console.log("không có dữ liệu");
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}

function SLTVNganh(){
    var labels = [];
    var data = [];

    for (var i = 0; i < nganhAndCount.length; i++) {
        var item = nganhAndCount[i];
        labels.push(item[0]); // Tên khoa
        data.push(item[1]);   // Số lượng
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }
   
    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}

function SLTVNganhByTime(){
    var labels = [];
    var data = [];
    if (nganhAndCountByTime !== null){
        for (var i = 0; i < nganhAndCountByTime.length; i++) {
            var item = nganhAndCountByTime[i];
            labels.push(item[0]); // Tên thiết bị
            data.push(item[1]);   // Số lần mượn
        }
    }else{
        console.log("không có dữ liệu");
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}

function SLTBMuon(){
    var labels = [];
    var data = [];

    for (var i = 0; i < TBmuonCount.length; i++) {
        var item = TBmuonCount[i];
        labels.push(item[0]); // Tên thiết bị
        data.push(item[1]);   // Số lần mượn
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}
function SLTBMuonByTime(){
    var labels = [];
    var data = [];
    if (TBmuonCountByTime !== null){
        for (var i = 0; i < TBmuonCountByTime.length; i++) {
            var item = TBmuonCountByTime[i];
            labels.push(item[0]); // Tên thiết bị
            data.push(item[1]);   // Số lần mượn
        }
    }else{
        console.log("không có dữ liệu");
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}
function countVPDaXuLyTheoThoiGian(){
    var labels = [];
    var data = [];
    if (ViPhamCountByTime !== null){
        for (var i = 0; i < ViPhamCountByTime.length; i++) {
            var item = ViPhamCountByTime[i];
            labels.push(item[0]); // Tên thiết bị
            data.push(item[1]);   // Số lần mượn
        }
    }else{
        console.log("không có dữ liệu");
    }
    var ctx = document.getElementById('Chart2').getContext('2d');

    // Hủy biểu đồ cũ nếu tồn tại
    if (myChart) {
        myChart.destroy();
    }

    // Tạo biểu đồ mới
    myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: labels, // Tên khoa
        datasets: [{
            label: 'Số lượng', // Nhãn của dữ liệu
            data: data,        // Dữ liệu
            backgroundColor: "#365CF5", // Màu nền của cột
            borderWidth: 0, // Không có đường viền
            borderRadius: 30,
            barThickness: 6,
            maxBarThickness: 8
        }]
    },
    options: {
        plugins: {
            tooltip: {
                callbacks: {
                    titleColor: function(context) {
                        return "#8F92A1";
                    },
                    label: function(context) {
                        let label = context.dataset.label || "";

                        if (label) {
                            label += ": ";
                        }
                        label += context.parsed.y;
                        return label;
                    },
                },
                backgroundColor: "#F3F6F8",
                titleAlign: "center",
                bodyAlign: "center",
                titleFont: {
                    size: 12,
                    weight: "bold",
                    color: "#8F92A1",
                },
                bodyFont: {
                    size: 16,
                    weight: "bold",
                    color: "#171717",
                },
                displayColors: false,
                padding: {
                    x: 30,
                    y: 10,
                },
            },
        },
        layout: {
            padding: {
                top: 15,
                right: 15,
                bottom: 15,
                left: 15,
            },
        },
        responsive: true,
        maintainAspectRatio: false,
        scales: {
            y: {
                grid: {
                    display: false,
                    drawTicks: false,
                    drawBorder: false,
                },
                ticks: {
                    padding: 35,
                    max: 1200,
                    min: 0,
                },
            },
            x: {
                grid: {
                    display: false,
                    drawBorder: false,
                    color: "rgba(143, 146, 161, .1)",
                    drawTicks: false,
                    zeroLineColor: "rgba(143, 146, 161, .1)"
                },
                ticks: {
                    padding: 20
                }
            }
        },
        legend: {
            display: false
        },
        title: {
            display: false
        }
    }
});

}

function VPDaXuLyTheoNam(){
    /*<![CDATA[*/
    var mydata = dataChart ;
    console.log(mydata);
    
   
   
    var ctx1 = document.getElementById("Chart1").getContext("2d");
     if (chart1) {
        chart1.destroy();
    }
         chart1 = new Chart(ctx1, {
            type: "line",
            data: {
                labels: [
                    "Jan",
                    "Fab",
                    "Mar",
                    "Apr",
                    "May",
                    "Jun",
                    "Jul",
                    "Aug",
                    "Sep",
                    "Oct",
                    "Nov",
                    "Dec",
                ],
                datasets: [
                    {
                        label: "",
                        backgroundColor: "transparent",
                        borderColor: "#365CF5",
                        data : mydata,
                        pointBackgroundColor: "transparent",
                        pointHoverBackgroundColor: "#365CF5",
                        pointBorderColor: "transparent",
                        pointHoverBorderColor: "#fff",
                        pointHoverBorderWidth: 5,
                        borderWidth: 5,
                        pointRadius: 8,
                        pointHoverRadius: 8,
                        cubicInterpolationMode: "monotone", // Add this line for curved line
                    },
                ],
            },
            options: {
                plugins: {
                    tooltip: {
                        callbacks: {
                            labelColor: function (context) {
                                return {
                                    backgroundColor: "#ffffff",
                                    color: "#171717"
                                };
                            },
                        },
                        intersect: false,
                        backgroundColor: "#f9f9f9",
                        title: {
                            fontFamily: "Plus Jakarta Sans",
                            color: "#8F92A1",
                            fontSize: 12,
                        },
                        body: {
                            fontFamily: "Plus Jakarta Sans",
                            color: "#171717",
                            fontStyle: "bold",
                            fontSize: 16,
                        },
                        multiKeyBackground: "transparent",
                        displayColors: false,
                        padding: {
                            x: 30,
                            y: 10,
                        },
                        bodyAlign: "center",
                        titleAlign: "center",
                        titleColor: "#8F92A1",
                        bodyColor: "#171717",
                        bodyFont: {
                            family: "Plus Jakarta Sans",
                            size: "16",
                            weight: "bold",
                        },
                    },
                    legend: {
                        display: false,
                    },
                },
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    display: false,
                },
                scales: {
                    y: {
                        grid: {
                            display: false,
                            drawTicks: false,
                            drawBorder: false,
                        },
                        ticks: {
                            padding: 35,
                            max: 1200,
                            min: 500,
                        },
                    },
                    x: {
                        grid: {
                            drawBorder: false,
                            color: "rgba(143, 146, 161, .1)",
                            zeroLineColor: "rgba(143, 146, 161, .1)",
                        },
                        ticks: {
                            padding: 20,
                        },
                    },
                },
            },
        });
        /*]]>*/
}


